package com.buterfleoge.whale.biz.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;
import com.buterfleoge.whale.type.enums.GroupStatus;
import com.buterfleoge.whale.type.enums.OrderStatus;

/**
 * @author Brent24
 *
 */

@Service("task")
public class Task {

    private static final Set<DiscountCodeStatus> CODECHECK = new HashSet<DiscountCodeStatus>();
    private static final Set<GroupStatus> GROUPCHECK = new HashSet<GroupStatus>();

    static {
        // 优惠码 创建和验证但未使用的
        CODECHECK.add(DiscountCodeStatus.CREATED);
        CODECHECK.add(DiscountCodeStatus.VERIFIED);
        // 团状态 招募中和已满的
        GROUPCHECK.add(GroupStatus.OPEN);
        GROUPCHECK.add(GroupStatus.FULL);
        GROUPCHECK.add(GroupStatus.TRAVELLING);
    }

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderDiscountRepository orderDiscountRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    // 优惠码过期,每天00:01执行
    public void changeDiscountCodeStatus() {
        List<DiscountCode> codeList = discountCodeRepository.findByStatusIn(CODECHECK);
        for (DiscountCode temp : codeList) {
            if (temp.getEndTime() < System.currentTimeMillis()) {
                temp.setStatus(DiscountCodeStatus.TIMEOUT);
            }
        }
        discountCodeRepository.save(codeList);
    }

    // group状态改变,每天00:01执行
    public void changeTravelGroupStatus() {
        List<TravelGroup> groupList = travelGroupRepository.findByStatusIn(GROUPCHECK);
        for (TravelGroup temp : groupList) {
            if (temp.getStartDate() < System.currentTimeMillis()) {
                temp.setStatus(GroupStatus.TRAVELLING);
            }
            if (temp.getEndDate() < System.currentTimeMillis()) {
                temp.setStatus(GroupStatus.FINISHED);
            }
        }
        travelGroupRepository.save(groupList);
    }

    // 订单状态改变,下单后120分钟执行
    public void changeOrderStatus(Long orderid) {
        OrderInfo orderInfo = orderInfoRepository.findByOrderid(orderid);
        orderInfo.setStatus(OrderStatus.TIMEOUT);
        orderInfoRepository.save(orderInfo);

        Long groupid = orderInfo.getGroupid();
        int orderCount = orderInfo.getCount();
        TravelGroup group = travelGroupRepository.findByGroupid(groupid);
        group.setStatus(GroupStatus.OPEN);
        group.setActualCount(group.getActualCount() - orderCount);
        travelGroupRepository.save(group);

        OrderDiscount orderDiscount = orderDiscountRepository.findByOrderidAndDiscountCodeNotNull(orderid);
        if (orderDiscount != null) {
            Long code = orderDiscount.getDiscountCode();
            DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
            discountCode.setStatus(DiscountCodeStatus.VERIFIED);
            discountCodeRepository.save(discountCode);
        }
    }
}
