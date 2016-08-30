/**
 *
 */
package com.buterfleoge.whale.biz.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.Constants.Status;
import com.buterfleoge.whale.biz.order.CreateOrderBiz;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.DiscountRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.OrderTravellersRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.DiscountCodeStatus;
import com.buterfleoge.whale.type.DiscountType;
import com.buterfleoge.whale.type.GroupStatus;
import com.buterfleoge.whale.type.OrderStatus;
import com.buterfleoge.whale.type.OrderStatusCategory;
import com.buterfleoge.whale.type.entity.Discount;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.OrderTravellers;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.protocol.Response;
import com.buterfleoge.whale.type.protocol.order.CreateOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderRequest;
import com.buterfleoge.whale.type.protocol.order.NewOrderResponse;

/**
 * @author xiezhenzong
 *
 */
@Service("createOrderBiz")
public class CreateOrderBizImpl implements CreateOrderBiz {

    private static final Logger LOG = LoggerFactory.getLogger(CreateOrderBizImpl.class);

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderTravellersRepository orderTravellersRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Override
    public void newOrder(Long accountid, NewOrderRequest request, NewOrderResponse response) throws Exception {
        try {
            OrderInfo orderInfo = orderInfoRepository.findByAccountidAndRouteidAndGroupidAndStatusIn(accountid,
                    request.getRouteid(), request.getGroupid(), OrderStatusCategory.NO_ALLOW_NEW.getOrderStatuses());
            if (orderInfo != null) {
                response.setOrderid(orderInfo.getOrderid());
                return;
            }
        } catch (Exception e) {
            LOG.error("find order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            return;
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAccountid(accountid);
        orderInfo.setRouteid(request.getRouteid());
        orderInfo.setGroupid(request.getGroupid());
        orderInfo.setStatus(OrderStatus.NEW.value);
        orderInfo.setIsAgreed(Boolean.FALSE);
        orderInfo.setAddTime(new Date());
        orderInfo.setModTime(orderInfo.getAddTime());
        try {
            orderInfo = orderInfoRepository.save(orderInfo);
            response.setOrderid(orderInfo.getOrderid());
        } catch (Exception e) {
            LOG.error("new order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(Long accountid, CreateOrderRequest request, Response response) throws Exception {
        List<OrderTravellers> travellers = request.getTravellers();
        for (OrderTravellers traveller : travellers) {
            traveller.setOrderid(request.getOrderid());
        }
        Date now = new Date();
        int count = travellers.size();
        int studentCount = request.getStudentCount();
        Long orderid = request.getOrderid();
        OrderInfo orderInfo = orderInfoRepository.findByOrderidAndAccountid(orderid, accountid);
        TravelGroup group = travelGroupRepository.findOne(orderInfo.getGroupid());

        orderInfo.setStatus(OrderStatus.WAITING.value);
        orderInfo.setCount(count);
        orderInfo.setStudentCount(studentCount);
        orderInfo.setPrice(group.getPrice().multiply(BigDecimal.valueOf(count)));
        orderInfo.setActualPrice(request.getActualPrice());
        orderInfo.setIsAgreed(Boolean.TRUE);
        orderInfo.setCreateTime(now);
        orderInfo.setModTime(now);

        group.setActualCount(group.getActualCount() + count);
        if (group.getActualCount().equals(group.getMaxCount())) {
            group.setStatus(GroupStatus.FULL.value);
        }

        DiscountCode discountCode = discountCodeRepository.findByDiscountCode(request.getDiscountCode());
        OrderDiscount policyOrderDiscount = createPolicyOrderDiscount(orderid, request.getPolicyDiscountid(), now);
        OrderDiscount studentOrderDiscount = createStudentOrderDiscount(orderid, request.getStudentDiscountid(), studentCount, now);
        OrderDiscount codeOrderDiscount = createCodeOrderDiscount(orderid, request.getDiscountCode(), now);
        List<OrderDiscount> discountList = getOrderDiscountList(policyOrderDiscount, studentOrderDiscount, codeOrderDiscount);

        try {
            orderInfoRepository.save(orderInfo);
            travelGroupRepository.save(group);
            orderTravellersRepository.save(travellers);
            if (!discountList.isEmpty()) {
                orderDiscountRepository.save(discountList);
            }
            if (discountCode != null) {
                discountCode.setStatus(DiscountCodeStatus.OCCUPIED.value);
                discountCodeRepository.save(discountCode);
            }
        } catch (Exception e) {
            LOG.error("save order info failed, reqid: " + request.getReqid(), e);
            response.setStatus(Status.DB_ERROR);
            throw e;
        }
    }

    private OrderDiscount createPolicyOrderDiscount(Long orderid, Long discountPolicyid, Date addTime) {
        OrderDiscount policyOrderDiscount = null;
        Discount policyDiscount = discountRepository.findOne(discountPolicyid);
        if (policyDiscount != null) {
            policyOrderDiscount = new OrderDiscount();
            policyOrderDiscount.setOrderid(orderid);
            policyOrderDiscount.setDiscountid(policyDiscount.getDiscountid());
            policyOrderDiscount.setType(policyDiscount.getType());
            policyOrderDiscount.setValue(policyDiscount.getValue());
            policyOrderDiscount.setDesc(policyDiscount.getDesc());
            policyOrderDiscount.setAddTime(addTime);
        }
        return policyOrderDiscount;
    }

    private OrderDiscount createStudentOrderDiscount(Long orderid, Long sutdentDiscountid, int studentCount, Date addTime) {
        OrderDiscount studentOrderDiscount = null;
        Discount studentDiscount = discountRepository.findOne(sutdentDiscountid);
        if (studentDiscount != null && studentCount > 0) {
            studentOrderDiscount = new OrderDiscount();
            studentOrderDiscount.setOrderid(orderid);
            studentOrderDiscount.setDiscountid(studentDiscount.getDiscountid());
            studentOrderDiscount.setType(DiscountType.STUDENT.value);
            studentOrderDiscount.setValue(studentDiscount.getValue().multiply(BigDecimal.valueOf(studentCount)));
            studentOrderDiscount.setDesc(studentDiscount.getDesc());
            studentOrderDiscount.setAddTime(addTime);
        }
        return studentOrderDiscount;
    }

    private OrderDiscount createCodeOrderDiscount(Long orderid, String code, Date addTime) {
        OrderDiscount codeOrderDiscount = null;
        DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
        if (discountCode != null) {
            codeOrderDiscount = new OrderDiscount();
            codeOrderDiscount.setOrderid(orderid);
            codeOrderDiscount.setDiscountCode(discountCode.getDiscountCode());
            codeOrderDiscount.setType(DiscountType.COUPON.value);
            codeOrderDiscount.setValue(discountCode.getValue());
            codeOrderDiscount.setAddTime(addTime);
        }
        return codeOrderDiscount;
    }

    private List<OrderDiscount> getOrderDiscountList(OrderDiscount policyOrderDiscount, OrderDiscount studentOrderDiscount,
            OrderDiscount codeOrderDiscount) {
        List<OrderDiscount> discounts = new ArrayList<OrderDiscount>(3);
        if (policyOrderDiscount != null) {
            discounts.add(policyOrderDiscount);
        }
        if (studentOrderDiscount != null) {
            discounts.add(studentOrderDiscount);
        }
        if (codeOrderDiscount != null) {
            discounts.add(codeOrderDiscount);
        }
        return discounts;
    }


}
