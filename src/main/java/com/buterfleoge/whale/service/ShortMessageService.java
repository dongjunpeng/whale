package com.buterfleoge.whale.service;

import java.util.List;

import com.buterfleoge.whale.type.entity.AccountInfo;
import com.buterfleoge.whale.type.entity.AssemblyInfo;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTraveller;
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
     * 管理员->付款成功：${routeName}，${startDate}，${count}人，实付${actualPrice}，已报$
     * {groupCount}人，姓名：${travellerList}
     * 
     * 付款账户->${travellerList}已成功报名“${routeName}”，订单号${createTime}${
     * orderid}，${startDate}出发！
     * 
     * @return
     */
    boolean sendPaySuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTraveller> orderTravellers, AccountInfo accoutInfo);

    /**
     * 每个出行人->${travellerName}，【${routeName}】${startDate}至${endDate}马上就要出发啦！领队${
     * leader}，微信/手机${mobile}，${hotel}见！详细微信/网站查看集合文件。
     * 
     * @return
     */
    int sendAssemblyInfo(TravelRoute travelRoute, TravelGroup travelGroup, AssemblyInfo assembleInfo,
            List<OrderTraveller> orderTravellers);

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
            List<OrderTraveller> orderTravellers);

    /**
     * 退款成功->管理员+付款账户
     * 
     * @return
     */
    boolean sendRefundSuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTraveller> orderTravellers);

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
