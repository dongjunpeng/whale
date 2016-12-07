package com.buterfleoge.whale.service.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.DefaultValue;
import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.dao.WxIdMappingRepository;
import com.buterfleoge.whale.exception.WeixinException;
import com.buterfleoge.whale.log.InvokeLogger;
import com.buterfleoge.whale.service.WeixinCgibinService;
import com.buterfleoge.whale.service.WeixinPayService;
import com.buterfleoge.whale.service.weixin.protocol.WxCgibinAccessTokenResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxGetTicketResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxGoodsDetail;
import com.buterfleoge.whale.service.weixin.protocol.WxOrderQueryRequest;
import com.buterfleoge.whale.service.weixin.protocol.WxOrderQueryResponse;
import com.buterfleoge.whale.service.weixin.protocol.WxUnifiedOrderRequest;
import com.buterfleoge.whale.service.weixin.protocol.WxUnifiedOrderResponse;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.WxIdMapping;
import com.buterfleoge.whale.type.entity.converter.PriceConverter;

/**
 * @author xiezhenzong
 *
 */
@Service("weixinPayService")
public class WeixinPayServiceImpl implements WeixinPayService {

    private static final Logger LOG = LoggerFactory.getLogger(WeixinPayServiceImpl.class);

    @Value("${wx.cgi-bin.appid}")
    private String appid;

    @Value("${wx.pay.mchid}")
    private String mchid;

    @Value("${wx.pay.key}")
    private String key;

    @Value("${wx.pay.unifiedorder}")
    private String unifiedOrderUrl;

    @Value("${wx.pay.notify_url}")
    private String notifyUrl;

    @Value("${wx.pay.jsapi_url}")
    private String jsapiUrl;

    @Value("${wx.pay.orderquery}")
    private String orderQueryUrl;

    @Autowired
    private WxIdMappingRepository wxIdMappingRepository;

    @Autowired
    private WeixinCgibinService weixinCgibinService;

    @Override
    public WxUnifiedOrderResponse unifiedOrder(Long orderid, OrderInfo orderInfo, String subject, String ip) throws Exception {
        Date createTime = orderInfo.getCreateTime();
        Date expiretime = DateUtils.addHours(createTime, 2);
        WxUnifiedOrderRequest request = new WxUnifiedOrderRequest();
        request.setAppid(appid);
        request.setMch_id(mchid);
        request.setNotify_url(notifyUrl);
        request.setNonce_str(Utils.createNonceStr());
        request.setBody(createBody(subject));
        request.setDetail(createDetail(orderInfo, subject));
        request.setOut_trade_no(String.valueOf(orderid));
        request.setTotal_fee(PriceConverter.yuanToFen(orderInfo.getActualPrice()).intValue());
        request.setSpbill_create_ip(ip);
        request.setTime_start(DateFormatUtils.format(createTime, Pattern.DATE_TIME_WX));
        request.setTime_expire(DateFormatUtils.format(expiretime, Pattern.DATE_TIME_WX));
        request.setOpenid(getOpenid(orderInfo.getAccountid()));
        request.fillSign(key);

        WxUnifiedOrderResponse response = queryWeixin("unifiedOrder", unifiedOrderUrl, request, WxUnifiedOrderResponse.class);
        if (response.isCodeSuccess() && validateResponseSign(response)) {
            return response;
        }
        throw new WeixinException("预下单失败，wx request: " + request + "; response: " + response);
    }

    @Override
    public Map<String, Object> geneJsapiParam(Long orderid, BigDecimal actualPrice, String prepayId) throws Exception {
        WxCgibinAccessTokenResponse accessToken = weixinCgibinService.getCgibinAccessToken();
        if (accessToken == null) {
            throw new WeixinException("获取accessToken失败");
        }
        WxGetTicketResponse ticket = weixinCgibinService.getTicket(accessToken.getAccess_token(), "jsapi");
        if (ticket == null) {
            throw new WeixinException("获取jsapiTicket失败");
        }

        String nonceStr = Utils.createNonceStr();
        String jsapiTicket = ticket.getTicket();
        long timeStamp = System.currentTimeMillis();
        String url = new StringBuilder(jsapiUrl).append("?orderid=").append(orderid).append("&payType=1").toString();
        String signature = createSignaturForJsapi(nonceStr, jsapiTicket, timeStamp, url);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("config_appid", appid);
        param.put("config_timestamp", String.valueOf(timeStamp));
        param.put("config_nonceStr", nonceStr);
        param.put("config_signature", signature);

        Thread.sleep(3);
        timeStamp = System.currentTimeMillis();
        nonceStr = Utils.createNonceStr();
        String packageStr = "prepay_id=" + prepayId;
        String paySign = createPaySignForJsapi(timeStamp, nonceStr, packageStr, "MD5");

        param.put("pay_actualPrice", Utils.formatPrice(actualPrice));
        param.put("pay_timestamp", String.valueOf(timeStamp));
        param.put("pay_nonceStr", nonceStr);
        param.put("pay_package", packageStr);
        param.put("pay_signType", "MD5");
        param.put("pay_paySign", paySign);

        param.put("orderid", String.valueOf(orderid));
        param.put("hxy_hotline", DefaultValue.HOTLINE);
        return param;
    }

    @Override
    public WxOrderQueryResponse orderQuery(Long orderid) throws Exception {
        WxOrderQueryRequest request = new WxOrderQueryRequest();
        request.setAppid(appid);
        request.setMch_id(mchid);
        request.setOut_trade_no(String.valueOf(orderid));
        request.setNonce_str(Utils.createNonceStr());
        request.fillSign(key);

        WxOrderQueryResponse response = queryWeixin("orderQuery", orderQueryUrl, request, WxOrderQueryResponse.class);
        if (response.isReturnCodeSuccess() && !validateOrderQueryResponseSign(response)) {
            return response;
        }
        throw new WeixinException("查询支付信息失败，wx response: " + response);
    }

    private String createBody(String subject) {
        return "海逍遥旅行工作室-" + subject;
    }

    private String createDetail(OrderInfo orderInfo, String productName) throws Exception {
        return WxGoodsDetail
                .newInstance(orderInfo.getGroupid(), productName, orderInfo.getCount(), orderInfo.getActualPrice(), orderInfo.getRouteid())
                .toJsonString();
    }

    private String getOpenid(Long accountid) {
        WxIdMapping wxIdMapping = wxIdMappingRepository.findByAccountid(accountid);
        return wxIdMapping.getOpenid();
    }

    protected <T> T queryWeixin(String tag, String uri, Object request, Class<T> responseType) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpResponse = null;
        T response = null;
        long start = System.currentTimeMillis();
        int status = Status.OK;
        try {
            httpclient = HttpClients.createDefault();
            StringEntity data = new StringEntity(toXml(request), "UTF-8");
            HttpPost httppost = new HttpPost(uri);
            httppost.setEntity(data);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.addHeader("Accept", "*/*");
            httpResponse = httpclient.execute(httppost);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                response = fromXml(entity.getContent(), responseType);
                return response;
            } else {
                throw new IllegalStateException("wx response is empty");
            }
        } catch (Exception e) {
            status = Status.INVOKE_ERROR;
            LOG.error("call wxpay failed, uri: " + uri, e);
            return null;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
            }
            InvokeLogger.log("wxpay", tag, request, response != null ? response : httpResponse, start, System.currentTimeMillis() - start,
                    status);
        }
    }

    private String toXml(Object object) throws JAXBException {
        StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(object, sw);
        // CDATAAadpter 转化的<> 被转为&lt;和&gt;
        return sw.toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">");
    }

    @SuppressWarnings("unchecked")
    private <T> T fromXml(InputStream content, Class<T> responseType) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(responseType);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object obj = unmarshaller.unmarshal(content);
        if (responseType.isAssignableFrom(obj.getClass())) {
            return (T) obj;
        } else {
            throw new RuntimeException("转换得到的对象并非指定类型范围内的对象！");
        }
    }

    private boolean validateResponseSign(WxUnifiedOrderResponse response) {
        if (!appid.equals(response.getAppid()) || !mchid.equals(response.getMch_id())) {
            return false;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("return_code", response.getReturn_code());
        param.put("return_msg", response.getReturn_msg());
        param.put("appid", response.getAppid());
        param.put("mch_id", response.getMch_id());
        param.put("device_info", response.getDevice_info());
        param.put("nonce_str", response.getNonce_str());
        param.put("result_code", response.getResult_code());
        param.put("err_code", response.getErr_code());
        param.put("err_code_des", response.getErr_code_des());
        param.put("trade_type", response.getTrade_type());
        param.put("prepay_id", response.getPrepay_id());
        param.put("code_url", response.getCode_url());
        return Utils.createWxSign(param, key).equals(response.getSign());
    }

    private String createSignaturForJsapi(String nonceStr, String jsapiTicket, long timeStamp, String url) {
        Map<String, Object> paramToEncry = new HashMap<String, Object>();
        paramToEncry.put("noncestr", nonceStr);
        paramToEncry.put("jsapi_ticket", jsapiTicket);
        paramToEncry.put("timestamp", timeStamp);
        paramToEncry.put("url", url);
        String link = Utils.createLinkString(paramToEncry);
        return DigestUtils.sha1Hex(link);
    }

    private String createPaySignForJsapi(long timeStamp, String nonceStr, String packageStr, String signType) {
        Map<String, Object> paramToEncry = new HashMap<String, Object>();
        paramToEncry.put("appId", appid);
        paramToEncry.put("timeStamp", timeStamp);
        paramToEncry.put("nonceStr", nonceStr);
        paramToEncry.put("package", packageStr);
        paramToEncry.put("signType", signType);
        return Utils.createWxSign(paramToEncry, key);
    }

    private boolean validateOrderQueryResponseSign(WxOrderQueryResponse response) throws Exception {
        Map<String, Object> param = objectToMap(response);
        param.remove("sign");
        String sign = Utils.createWxSign(param, key);
        return sign.equals(response.getSign());
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return new HashMap<String, Object>(0);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
