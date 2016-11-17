package com.buterfleoge.whale.service;

import java.util.List;

import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AssemblyInfo;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;

/**
 * @author Brent24
 *
 */
public interface ShortMessageService {

    /**
     * 付款成功
     * 
     * 管理员->付款成功！路线${routeName}，日期${startDate}，共${count}人，实付${actualPrice}，该团已报$
     * {groupCount}人。姓名列表：${travellerList}
     * 
     * 付款账户->恭喜！${travellerList}已成功报名“${routeName}”旅行，订单号${prefixOrderid}，将于${
     * startDate}日出发！
     * 
     * 
     * @return
     */
    boolean sendPaySuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTravellers> orderTravellers, AccountInfo accoutInfo);

    /**
     * 每个出行人->亲爱的${travellerName}，您报名的“${routeName}”马上就要出发啦！${startDate}日，${
     * hotel}不见不散！领队${leader}，微信/手机${mobile}。（物资准备/当地天气等请在“我的行程”中查看详细哦）。
     * 
     * @return
     */
    int sendAssemblyInfo(TravelRoute travelRoute, TravelGroup travelGroup, AssemblyInfo assembleInfo,
            List<OrderTravellers> orderTravellers);

    /**
     * 重大事项->管理员
     * 
     * @return
     */
    boolean sendManagersImportant(String message);

    /**
     * 申请退款->管理员+付款账户
     * 
     * @return
     */
    boolean sendRefundApplyMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTravellers> orderTravellers);

    /**
     * 退款成功->管理员+付款账户
     * 
     * @return
     */
    boolean sendRefundSuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTravellers> orderTravellers);

    /**
     * 验证码
     * 
     * @return
     */
    boolean sendVerificationCode(String verificationCode);

    /**
     * 货到通知
     * 
     * @return
     */
    boolean sendGroupAvailable();

    // 代理提成

}
