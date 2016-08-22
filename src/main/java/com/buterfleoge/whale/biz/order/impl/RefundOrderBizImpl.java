package com.buterfleoge.whale.biz.order.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.RefundOrderBiz;
import com.buterfleoge.whale.dao.OrderRefoundRepository;
import com.buterfleoge.whale.type.RefundStatus;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefund;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;
import com.buterfleoge.whale.type.protocol.order.RefundResponse;

/**
 * @author xiezhenzong
 *
 */
@Service("refundOrderBiz")
public class RefundOrderBizImpl implements RefundOrderBiz {

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

    @Override
    public void getRefundInfo(Long accountid, OrderRequest request, RefundResponse response) throws Exception {
        Long orderid = request.getOrderid();

        OrderInfo orderInfo = new OrderInfo();
        TravelGroup travelGroup = new TravelGroup();
        TravelRoute travelRoute = new TravelRoute();
        List<OrderTravellers> orderTravellers = new ArrayList<OrderTravellers>();
        OrderDiscount policy = new OrderDiscount();
        OrderDiscount code = new OrderDiscount();
        OrderDiscount student = new OrderDiscount();
        OrderRefund orderRefound = new OrderRefund();

        // setOrderObjects(orderid, orderInfo, travelRoute, travelGroup,
        // orderTravellers, policy, code, student,
        // orderRefound);

        if (orderRefound.getOrderid() != null) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }

        // Long actualPrice = orderInfo.getActualPrice();
        Integer travelType = travelRoute.getType();
        Long startDate = travelGroup.getStartDate().getTime();
        Long now = System.currentTimeMillis();
        Long leftMinutes = (startDate - now) / 1000 / 60;

        if (leftMinutes <= 0) {
            response.setStatus(Status.PARAM_ERROR);
            return;
        }

        orderRefound.setOrderid(orderid);
        // orderRefound.setAddTime(now);
        orderRefound.setStatus(RefundStatus.CREATED.value);

        // switch (travelType) {
        // case LONG_TRIP:
        // if (leftMinutes >= 60 * 24 * 21) {
        // orderRefound.setType(RefoundType.LONG_PCT_95);
        // // orderRefound.setRefound((long) (actualPrice * 0.95));
        // } else {
        // if (leftMinutes >= 60 * 24 * 14) {
        // orderRefound.setType(RefoundType.LONG_PCT_80);
        // // orderRefound.setRefound((long) (actualPrice * 0.80));
        // } else {
        // if (leftMinutes >= 60 * 24 * 7) {
        // orderRefound.setType(RefoundType.LONG_PCT_50);
        // // orderRefound.setRefound((long) (actualPrice * 0.50));
        // } else {
        // orderRefound.setType(RefoundType.LONG_PCT_20);
        // // orderRefound.setRefound((long) (actualPrice * 0.20));
        // }
        // }
        // }
        // break;
        // case SHORT_TRIP:
        // if (leftMinutes >= 60 * 24 * 7) {
        // orderRefound.setType(RefoundType.SHORT_PCT_100);
        // // orderRefound.setRefound((long) (actualPrice));
        // } else {
        // if (leftMinutes >= 60 * 24 * 4) {
        // orderRefound.setType(RefoundType.SHORT_PCT_80);
        // // orderRefound.setRefound((long) (actualPrice * 0.80));
        // } else {
        // if (leftMinutes >= 60 * 24 * 1) {
        // orderRefound.setType(RefoundType.SHORT_PCT_50);
        // // orderRefound.setRefound((long) (actualPrice * 0.50));
        // } else {
        // orderRefound.setType(RefoundType.SHORT_PCT_20);
        // // orderRefound.setRefound((long) (actualPrice * 0.20));
        // }
        // }
        // break;
        // }
        // case WEEKEND:
        // break;
        // case PARTY:
        // break;
        // case CITY_WALK:
        // break;
        // case INTERNATIONAL:
        // break;
        // }
        orderRefound = orderRefoundRepository.save(orderRefound);
        String leftTime = "剩余时间： " + leftMinutes / 60 / 24 + " 天 " + leftMinutes / 60 % 24 + " 小时 " + leftMinutes % 60 + " 分";

        response.setAll(orderInfo, travelGroup, travelRoute, orderTravellers, policy, code, student, leftTime, orderRefound);
        response.setStatus(Status.OK);
    }

}
