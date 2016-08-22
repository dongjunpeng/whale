package com.buterfleoge.whale.biz.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.biz.order.PayOrderBiz;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.service.AlipayService;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.PayOrderByAlipayResponse;
import com.buterfleoge.whale.type.protocol.order.PayOrderRequest;

/**
 * @author xiezhenzong
 *
 */
@Service("payOrderBiz")
public class PayOrderBizImpl implements PayOrderBiz {

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private AlipayService alipayService;

    @Override
    public void payOrder(Long accountid, PayOrderRequest request, PayOrderByAlipayResponse response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo order = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        TravelGroup group = travelGroupRepository.findOne(order.getGroupid());

        order.setStatus(OrderStatus.PAYING.value);
        orderInfoRepository.save(order);

        String alipayForm = alipayService.createDirectPay(orderid, order.getActualPrice(), group.getPrice(), group.getTitle());
        response.setAlipayFrom(alipayForm);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void alipay(Long accountid, PayOrderRequest request, Response response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findOne(orderid);
        Integer status = orderInfo.getStatus();

        // switch (status) {
        // case WAITING:
        // // TODO 支付宝相关跳转
        // response.setStatus(Status.OK);
        // break;
        // case CANCEL:
        // response.setErrors(Arrays.asList(new Error("订单已取消")));
        // response.setStatus(Status.PARAM_ERROR);
        // break;
        // case TIMEOUT:
        // response.setErrors(Arrays.asList(new Error("订单已超时")));
        // response.setStatus(Status.PARAM_ERROR);
        // break;
        // // case PAYING:
        // // response.setErrors(Arrays.asList(new Error("订单正在支付中")));
        // // response.setStatus(Status.PARAM_ERROR);
        // // break;
        // case PAID:
        // response.setErrors(Arrays.asList(new Error("订单已支付成功")));
        // response.setStatus(Status.PARAM_ERROR);
        // break;
        // case CLOSED:
        // break;
        // case FINISH:
        // break;
        // case PAYING:
        // break;
        // case REFOUND:
        // break;
        // default:
        // break;
        // }
    }

}
