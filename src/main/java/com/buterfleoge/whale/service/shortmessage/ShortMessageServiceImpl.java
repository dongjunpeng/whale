package com.buterfleoge.whale.service.shortmessage;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.Printer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.buterfleoge.whale.Constants.Pattern;
import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.service.ShortMessageService;
import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AssemblyInfo;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTraveller;
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

    private String path = "/singleSendSms";

    @Value("${sms.appCode}")
    private String appCode;

    @SuppressWarnings("serial")
    private Map<String, String> headers = new HashMap<String, String>() {
        {
            put("Authorization", "APPCODE " + appCode);
        }
    };

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean sendPaySuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTraveller> orderTravellers, AccountInfo accountInfo) {
        // 基本信息
        String routeName = travelRoute.getName();
        String startDate = DateFormatUtils.format(travelGroup.getStartDate(), Pattern.DATE);
        String travellerList = Utils.join(orderTravellers, ",", new Printer<OrderTraveller>() {
            @Override
            public String print(OrderTraveller object, Locale locale) {
                return object.getName();
            }
        });

        String mobile = accountInfo.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            LOG.error("user mobile is null");
            return false;
        }
        StringBuilder userParam = new StringBuilder();
        userParam.append("{");
        userParam.append("\"travellerList\":").append(travellerList).append("\",");
        userParam.append("\"routeName\":").append(routeName).append("\",");
        userParam.append("\"prefixOrderid\":").append(orderInfo.getOrderNo()).append("\",");
        userParam.append("\"startDate\":").append(startDate).append("\"");
        userParam.append("}");

        Map<String, String> userQuerys = new HashMap<String, String>();
        userQuerys.put("ParamString", userParam.toString());
        userQuerys.put("RecNum", mobile);
        userQuerys.put("SignName", "走之旅行");
        userQuerys.put("TemplateCode", "SMS_25150343");
        return sendMessage(userQuerys);
    }

    @Override
    public boolean sendAssemblyInfo(TravelRoute travelRoute, TravelGroup travelGroup, AssemblyInfo assembleInfo,
            List<OrderTraveller> orderTravellers) {
        // 基本信息
        String startDate = DateFormatUtils.format(travelGroup.getStartDate(), Pattern.DATE);
        String endDate = DateFormatUtils.format(travelGroup.getEndDate(), Pattern.DATE);
        String leader = assembleInfo.getLeader();
        String mobile = assembleInfo.getMobile();
        String hotel = assembleInfo.getHotel();

        if (StringUtils.hasText(mobile) || StringUtils.hasText(hotel) || StringUtils.hasText(leader)) {
            LOG.error("assembleInfo is null");
            return false;
        }

        String mobiles = Utils.join(orderTravellers, ",", new Printer<OrderTraveller>() {
            @Override
            public String print(OrderTraveller object, Locale locale) {
                return object.getMobile();
            }
        });
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"routeName\":").append(Utils.getProductName(travelRoute, travelGroup)).append("\",");
        builder.append("\"startDate\":").append(startDate).append("\"");
        builder.append("\"endDate\":").append(endDate).append("\",");
        builder.append("\"leader\":").append(leader).append("\",");
        builder.append("\"mobile\":").append(mobile).append("\",");
        builder.append("\"hotel\":").append(hotel).append("\"");
        builder.append("}");

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ParamString", builder.toString());
        querys.put("RecNum", mobiles);
        querys.put("SignName", "走之旅行");
        querys.put("TemplateCode", "SMS_34780009");
        return sendMessage(querys);
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
