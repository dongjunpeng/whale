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
     * 恭喜！${travellerList}已成功报名“${routeName}”旅行，订单号${prefixOrderid}，将于${startDate}日出发！
     *
     * 
     * @return
     */
    boolean sendPaySuccessMessage(TravelRoute travelRoute, TravelGroup travelGroup, OrderInfo orderInfo,
            List<OrderTraveller> orderTravellers, AccountInfo accountInfo);

    /**
     * 
     * 每个出行人->${travellerName}，【${routeName}】${startDate}至${endDate}马上就要出发啦！领队${
     * leader}，微信/手机${mobile}，${hotel}见！详细微信/网站查看集合文件。
     *
     * 
     * @return
     */
    boolean sendAssemblyInfo(TravelRoute travelRoute, TravelGroup travelGroup, AssemblyInfo assembleInfo,
            List<OrderTraveller> orderTravellers);

}
