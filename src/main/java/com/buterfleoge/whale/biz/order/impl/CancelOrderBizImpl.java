package com.buterfleoge.whale.biz.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.biz.order.CancelOrderBiz;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderHistoryRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.DiscountCodeStatus;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderHistory;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.OrderRequest;

/**
 * @author xiezhenzong
 *
 */
@Service("cancelOrderBiz")
public class CancelOrderBizImpl implements CancelOrderBiz {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelOrder(Long accountid, OrderRequest request, Response response) throws Exception {
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findOne(orderid);
        Integer oldOrderStatus = orderInfo.getStatus();
        orderInfo.setStatus(OrderStatus.CANCEL.value);
        orderInfoRepository.save(orderInfo);

        Long groupid = orderInfo.getGroupid();
        int orderCount = orderInfo.getCount();
        TravelGroup group = travelGroupRepository.findOne(groupid);
        group.setStatus(GroupStatus.OPEN.value);
        group.setActualCount(group.getActualCount() - orderCount);
        travelGroupRepository.save(group);

        OrderDiscount orderDiscount = orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.COUPON.value);
        if (orderDiscount != null) {
            String code = orderDiscount.getDiscountCode();
            DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
            discountCode.setStatus(DiscountCodeStatus.VERIFIED.value);
            discountCodeRepository.save(discountCode);
        }
        orderHistoryRepository.save(OrderHistory.newInstance(oldOrderStatus, orderInfo));
    }

}
