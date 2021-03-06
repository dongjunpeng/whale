package com.buterfleoge.whale.biz.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.OrderRefundBiz;
import com.buterfleoge.whale.biz.order.refund.RefundStrategySelector;
import com.buterfleoge.whale.dao.OrderAlipayRepository;
import com.buterfleoge.whale.dao.OrderHistoryRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderRefoundRepository;
import com.buterfleoge.whale.dao.OrderWxpayRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.RefundStatus;
import com.buterfleoge.whale.type.RefundType;
import com.buterfleoge.whale.type.entity.OrderAlipay;
import com.buterfleoge.whale.type.entity.OrderHistory;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderRefund;
import com.buterfleoge.whale.type.entity.OrderWxpay;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.entity.TravelRoute;
import com.buterfleoge.whale.type.protocol.Error;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.GetRefundTypeResponse;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;
import com.buterfleoge.whale.type.protocol.order.RefundOrderRequest;

/**
 * @author xiezhenzong
 *
 */
@Service("orderRefundBiz")
public class OrderRefundBizImpl implements OrderRefundBiz {

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderRefoundRepository orderRefoundRepository;

    @Autowired
    private OrderAlipayRepository orderAlipayRepository;

    @Autowired
    private OrderWxpayRepository orderWxpayRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private RefundStrategySelector refundStrategySelector;

    @Override
    public void getRefundType(Long accountid, OrderRequest request, GetRefundTypeResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (orderInfo == null) {
            response.setStatus(Status.PARAM_ERROR);
            response.addError(new Error("订单不存在"));
            return;
        }

        TravelGroup travelGroup = travelGroupRepository.findOne(orderInfo.getGroupid());
        TravelRoute travelRoute = travelRouteRepository.findByRouteidAndVisibleTrue(orderInfo.getRouteid());

        RefundType refundType = refundStrategySelector.getRefundType(orderInfo, travelRoute, travelGroup);
        if (refundType == null) { // FIXME: 设置一个默认退款策略
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error("没有合适的退款策略"));
            return;
        }

        response.setActualPrice(orderInfo.getActualPrice());
        response.setDayLeft(travelGroup.getDayLeft());
        response.setType(refundType.value);
        response.setDeductPrice(refundType.getDeductPrice(orderInfo.getActualPrice()));
        response.setRefundPrice(refundType.getRefundPrice(orderInfo.getActualPrice()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refundOrder(Long accountid, RefundOrderRequest request, Response response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        if (orderInfo == null) {
            response.setStatus(Status.PARAM_ERROR);
            response.addError(new Error("订单不存在"));
            return;
        }
        int orderStatus = orderInfo.getStatus();
        if (orderStatus != OrderStatus.PAID.value) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error("订单并未支付"));
            return;
        }

        List<OrderAlipay> orderAlipays = orderAlipayRepository.findByOrderid(orderid);
        List<OrderWxpay> orderWxpays = orderWxpayRepository.findByOrderid(orderid);
        if (CollectionUtils.isEmpty(orderAlipays) && CollectionUtils.isEmpty(orderWxpays)) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error("订单没有支付的记录"));
            return;
        }

        OrderRefund orderRefund = orderRefoundRepository.findByOrderid(orderid);
        if (orderRefund != null) {
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error("订单已经在退款流程中，当前状态： " + RefundStatus.getDesc(orderRefund.getStatus())));
            return;
        }

        TravelGroup travelGroup = travelGroupRepository.findOne(orderInfo.getGroupid());
        TravelRoute travelRoute = travelRouteRepository.findByRouteidAndVisibleTrue(orderInfo.getRouteid());

        RefundType refundType = refundStrategySelector.getRefundType(orderInfo, travelRoute, travelGroup);
        if (refundType == null) { // FIXME: 设置一个默认退款策略
            response.setStatus(Status.BIZ_ERROR);
            response.addError(new Error("没有合适的退款策略"));
            return;
        }

        Date now = new Date();
        orderRefund = new OrderRefund();
        orderRefund.setOrderid(orderid);
        orderRefund.setStatus(RefundStatus.CREATED.value);
        orderRefund.setType(refundType.value);
        orderRefund.setRefund(refundType.getRefundPrice(orderInfo.getActualPrice()));
        orderRefund.setDesc(request.getDesc());
        orderRefund.setAddTime(now);
        orderRefund = orderRefoundRepository.save(orderRefund);

        orderInfo.setStatus(OrderStatus.REFUNDING.value);
        orderInfoRepository.save(orderInfo);
        orderHistoryRepository.save(OrderHistory.newInstance(orderid, OrderStatus.REFUNDING));

        travelGroup.setStatus(GroupStatus.OPEN.value);
        travelGroup.setActualCount(travelGroup.getActualCount() - orderInfo.getCount());
        travelGroupRepository.save(travelGroup);
    }

}
