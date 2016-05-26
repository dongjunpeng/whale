package com.buterfleoge.whale.biz.order.impl.create;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;
import com.buterfleoge.whale.type.enums.DiscountType;
import com.buterfleoge.whale.type.enums.GroupStatus;
import com.buterfleoge.whale.type.enums.OrderStatus;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.CreateOrderResponse;

/**
 *
 * @author xiezhenzong
 *
 */
public class BizProcessor extends CreateOrderProcessor {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Override
    public void doCreate(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            CreateOrderContext context) throws Exception {
        doBiz(accountid, request, response, context);
    }

    private void doBiz(Long accountid, CreateOrderRequest request, CreateOrderResponse response,
            CreateOrderContext context) {
        List<OrderTravellers> travellers = request.getTravellers();
        int count = travellers.size();
        int studentCount = request.getStudentCount();
        OrderInfo orderInfo = context.getOrderInfo();
        Long orderid = orderInfo.getOrderid();

        orderInfo.setStatus(OrderStatus.WAITING);
        orderInfo.setCount(count);
        orderInfo.setStudentCount(request.getStudentCount());
        orderInfo.setActualPrice(request.getActualPrice());
        orderInfo.setIsAgreed(Boolean.TRUE);

        for (OrderTravellers traveller : travellers) {
            traveller.setOrderid(orderid);
        }

        Date addTime = new Date();
        OrderDiscount policyOrderDiscount = null;
        Discount policyDiscount = context.getPolicyDiscount();
        if (policyDiscount != null) {
            policyOrderDiscount = new OrderDiscount();
            policyOrderDiscount.setOrderid(orderid);
            policyOrderDiscount.setDiscountid(policyDiscount.getDiscountid());
            policyOrderDiscount.setType(policyDiscount.getType());
            policyOrderDiscount.setValue(policyDiscount.getValue());
            policyOrderDiscount.setDesc(policyDiscount.getDesc());
            policyOrderDiscount.setAddTime(addTime);
        }
        OrderDiscount studentOrderDiscount = null;
        Discount studentDiscount = context.getStudentDiscount();
        if (studentDiscount != null) {
            studentOrderDiscount = new OrderDiscount();
            studentOrderDiscount.setOrderid(orderid);
            studentOrderDiscount.setDiscountid(studentDiscount.getDiscountid());
            studentOrderDiscount.setType(DiscountType.STUDENT);
            studentOrderDiscount.setValue(studentDiscount.getValue().multiply(BigDecimal.valueOf(studentCount)));
            studentOrderDiscount.setDesc(studentDiscount.getDesc());
            studentOrderDiscount.setAddTime(addTime);
        }
        OrderDiscount codeOrderDiscount = null;
        DiscountCode discountCode = context.getDiscountCode();
        if (discountCode != null) {
            codeOrderDiscount = new OrderDiscount();
            codeOrderDiscount.setOrderid(orderid);
            codeOrderDiscount.setDiscountCode(discountCode.getDiscountCode());
            codeOrderDiscount.setType(DiscountType.COUPON);
            codeOrderDiscount.setValue(discountCode.getValue());
            codeOrderDiscount.setAddTime(addTime);
        }

        TravelGroup group = context.getGroup();
        group.setActualCount(group.getActualCount() + count);
        if (group.getActualCount().equals(group.getMaxCount())) {
            group.setStatus(GroupStatus.FULL);
        }

        try {
            orderInfoRepository.save(orderInfo);
        } catch (Exception e) {
            LOG.error("save order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        try {
            orderTravellersRepository.save(travellers);
        } catch (Exception e) {
            LOG.error("save traveller failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
        try {
            orderDiscountRepository.save(Arrays.asList(codeOrderDiscount, studentOrderDiscount, codeOrderDiscount));
        } catch (Exception e) {
            LOG.error("save order Discount failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        try {
            // 改变优惠码状态
            discountCode.setStatus(DiscountCodeStatus.OCCUPIED);
            discountCodeRepository.save(discountCode);
        } catch (Exception e) {
            LOG.error("save discount code failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        try {
            travelGroupRepository.save(group);
        } catch (Exception e) {
            LOG.error("save travel group failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

}
