package com.buterfleoge.whale.biz.order;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.biz.OrderCancelBiz;
import com.buterfleoge.whale.dao.CouponRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderHistoryRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.CouponStatus;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.entity.Coupon;
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
@Service("orderCancelBiz")
public class OrderCancelBizImpl implements OrderCancelBiz {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private CouponRepository couponRepository;

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
            Coupon discountCode = couponRepository.findOne(orderDiscount.getCouponid());
            discountCode.setStatus(CouponStatus.CREATED.value);
            discountCode.setModTime(new Date());
            couponRepository.save(discountCode);
        }
        orderHistoryRepository.save(OrderHistory.newInstance(oldOrderStatus, orderInfo));
    }

}
