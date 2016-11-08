package com.buterfleoge.whale.service.shortmessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.service.ShortMessageService;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AssemblyInfo;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Brent24
 *
 */

@Service("ShortMessageService")
public class ShortMessageServiceImpl implements ShortMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(ShortMessageServiceImpl.class);

    @Value("${sms.host}")
    private String host;

    @Value("${sms.path}")
    private String path;

    @Value("${sms.appCode}")
    private String appCode;

    @Value("${sms.managerList}")
    private String managerList;

    @SuppressWarnings("serial")
    private Map<String, String> headers = new HashMap<String, String>() {
        {
            put("Authorization", "APPCODE " + appCode);
        }
    };

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 付款成功
     * 
     * 管理员->付款成功：${routeName}，${startDate}，${count}人，实付${actualPrice}，已报$
     * {groupCount}人，姓名：${travellerList}
     * 
     * 付款账户->${travellerList}已成功报名“${routeName}”，订单号${createTime}${
     * orderid}，${startDate}出发！
     * 
     * @return
     */
    @Override
    public boolean sendPaySuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTravellers> orderTravellers, AccountInfo accountInfo) {

        // 基本信息
        String routeName = travelRoute.getName();
        String startDate = DateFormatUtils.format(travelGroup.getStartDate(), Pattern.DATE);
        String count = orderInfo.getCount().toString();
        String groupCount = travelGroup.getActualCount().toString();
        String actualPrice = Utils.formatPrice(orderInfo.getActualPrice());
        String createTime = DateFormatUtils.format(orderInfo.getCreateTime(), Pattern.ORDER_PREFIX);
        String orderid = orderInfo.getOrderid().toString();

        // 拼出行人字符串
        StringBuilder travellerList = new StringBuilder(orderTravellers.get(0).getName());
        for (int i = 1; i < orderTravellers.size(); i++) {
            travellerList.append(",").append(orderTravellers.get(i).getName());
        }

        // 管理员
        StringBuilder managerParam = new StringBuilder();
        managerParam.append("{");
        managerParam.append("\"routeName\":").append(routeName).append("\",");
        managerParam.append("\"startDate\":").append(startDate).append("\",");
        managerParam.append("\"count\":").append(count).append("\",");
        managerParam.append("\"groupCount\":").append(groupCount).append("\",");
        managerParam.append("\"actualPrice\":").append(actualPrice).append("\",");
        managerParam.append("\"travellerList\":").append(travellerList).append("\"");
        managerParam.append("}");

        Map<String, String> managerQuerys = new HashMap<String, String>();
        managerQuerys.put("ParamString", managerParam.toString());
        managerQuerys.put("RecNum", managerList);
        managerQuerys.put("SignName", "海逍遥旅行");
        managerQuerys.put("TemplateCode", "managerPaySuccess");

        boolean managerSuccess = sendMessage(managerQuerys);

        // 用户
        String userNum = accountInfo.getMobile();
        if (userNum == null || "".equals(userNum)) {
            LOG.error("user mobile is null");
            return false;
        } else {

            StringBuilder userParam = new StringBuilder();
            userParam.append("{");
            userParam.append("\"travellerList\":").append(travellerList).append("\",");
            userParam.append("\"routeName\":").append(routeName).append("\",");
            userParam.append("\"createTime\":").append(createTime).append("\",");
            userParam.append("\"orderid\":").append(orderid).append("\",");
            userParam.append("\"startDate\":").append(startDate).append("\"");
            userParam.append("}");

            Map<String, String> userQuerys = new HashMap<String, String>();
            userQuerys.put("ParamString", userParam.toString());
            userQuerys.put("RecNum", userNum);
            userQuerys.put("SignName", "海逍遥旅行");
            userQuerys.put("TemplateCode", "userPaySuccess");
            return sendMessage(userQuerys) && managerSuccess;
        }

    }

    /**
     * 每个出行人->${travellerName}，【${routeName}】${startDate}至${endDate}马上就要出发啦！领队${
     * leader}，微信/手机${mobile}，${hotel}见！详细见公众号/网站集合文件。
     * 
     * @return
     */
    @Override
    public int sendAssemblyInfo(TravelRoute travelRoute, TravelGroup travelGroup, AssemblyInfo assembleInfo,
            List<OrderTravellers> orderTravellers) {

        // 成功发送条数
        int sent = 0;

        // 基本信息
        String routeName = travelRoute.getName();
        String startDate = DateFormatUtils.format(travelGroup.getStartDate(), Pattern.DATE);
        String endDate = DateFormatUtils.format(travelGroup.getEndDate(), Pattern.DATE);
        String leader = assembleInfo.getLeader();
        String mobile = assembleInfo.getMobile();
        String hotel = assembleInfo.getHotel();

        if (!assembleInfo.getReady()) {
            LOG.error("assembleInfo is not ready");
            return sent;
        }

        if (assembleInfo.getSent()) {
            LOG.error("assembleInfo has been sent");
            return sent;
        }

        if (leader == null || mobile == null || hotel == null || "".equals(leader) || "".equals(mobile)
                || "".equals(hotel)) {
            LOG.error("assembleInfo is null");
            return sent;
        }

        for (OrderTravellers traveller : orderTravellers) {

            StringBuilder builder = new StringBuilder();
            builder.append("{");
            builder.append("\"travellerName\":").append(traveller.getName()).append("\",");
            builder.append("\"routeName\":").append(routeName).append("\",");
            builder.append("\"startDate\":").append(startDate).append("\"");
            builder.append("\"endDate\":").append(endDate).append("\",");
            builder.append("\"leader\":").append(leader).append("\",");
            builder.append("\"mobile\":").append(mobile).append("\",");
            builder.append("\"hotel\":").append(hotel).append("\"");
            builder.append("}");

            Map<String, String> querys = new HashMap<String, String>();
            querys.put("ParamString", builder.toString());
            querys.put("RecNum", traveller.getMobile());
            querys.put("SignName", "海逍遥旅行");
            querys.put("TemplateCode", "assemblyInfo");
            if (sendMessage(querys)) {
                sent++;
            }
        }
        return sent;
    }

    @Override
    public boolean sendRefundApplyMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTravellers> orderTravellers) {
        return false;
    }

    @Override
    public boolean sendRefundSuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTravellers> orderTravellers) {
        return false;
    }

    @Override
    public boolean sendVerificationCode(String verificationCode) {
        return false;
    }

    @Override
    public boolean sendGroupAvailable() {
        return false;
    }

    @Override
    public boolean sendManagersImportant(String message) {
        return false;
    }

    private boolean sendMessage(Map<String, String> querys) {

        for (int i = 0; i < 3; i++) {
            try {
                HttpResponse response = HttpUtils.doGet(host, path, "GET", headers, querys);
                JsonNode responseNode = mapper.readTree(response.getEntity().getContent());
                if ("true".equals(responseNode.get("success").asText())) {
                    return true;
                } else {
                    LOG.error("sms send failed, return message:" + responseNode.get("message").asText());
                }
            } catch (Exception e) {
                LOG.error("sms exception:", e);
            }
        }
        return false;
    }

}
