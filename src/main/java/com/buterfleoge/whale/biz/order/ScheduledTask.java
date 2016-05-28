package com.buterfleoge.whale.biz.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.dao.OrderDiscountRepository;
import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.entity.OrderInfo;
import com.buterfleoge.whale.type.entity.TravelGroup;
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;
import com.buterfleoge.whale.type.enums.DiscountType;
import com.buterfleoge.whale.type.enums.GroupStatus;
import com.buterfleoge.whale.type.enums.OrderStatus;

/**
 * @author Brent24
 *
 */
@Component
public class ScheduledTask {

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
    @Transactional(rollbackFor = Exception.class)
    // @Scheduled(cron = "1 0 0 * * ? ")
    public void changeDiscountCodeStatus() {
        List<DiscountCode> codeList = discountCodeRepository.findByStatusIn(CODECHECK);
        for (DiscountCode temp : codeList) {
            // if (temp.getEndTime() < System.currentTimeMillis()) {
                temp.setStatus(DiscountCodeStatus.TIMEOUT);
            // }
        }
        discountCodeRepository.save(codeList);
    }

    // group状态改变,每天00:01执行
    @Transactional(rollbackFor = Exception.class)
    // @Scheduled(cron = "1 0 0 * * ? ")
    public void changeTravelGroupStatus() {
        List<TravelGroup> groupList = travelGroupRepository.findByStatusIn(GROUPCHECK);
        for (TravelGroup temp : groupList) {
            if (temp.getStartDate().getTime() < System.currentTimeMillis()) {
                temp.setStatus(GroupStatus.TRAVELLING);
            }
            if (temp.getEndDate().getTime() < System.currentTimeMillis()) {
                temp.setStatus(GroupStatus.FINISHED);
            }
        }
        travelGroupRepository.save(groupList);
    }

    // 订单状态改变每分钟检查数据库
    @Transactional(rollbackFor = Exception.class)
    // @Scheduled(fixedRate = 1000 * 60)
    public void changeOrderStatus() {
        List<OrderInfo> orderList = null;
        // orderInfoRepository.findByStatusAndAddTimeLessThan(OrderStatus.WAITING,
        // System.currentTimeMillis() - 1000 * 60 * 120);

        for (OrderInfo temp : orderList) {
            temp.setStatus(OrderStatus.TIMEOUT);

            Long orderid = temp.getOrderid();
            Long groupid = temp.getGroupid();
            int orderCount = temp.getCount();
            TravelGroup group = travelGroupRepository.findOne(groupid);
            group.setStatus(GroupStatus.OPEN);
            group.setActualCount(group.getActualCount() - orderCount);
            travelGroupRepository.save(group);

            OrderDiscount orderDiscount = orderDiscountRepository.findByOrderidAndType(orderid, DiscountType.COUPON);
            if (orderDiscount != null) {
                String code = orderDiscount.getDiscountCode();
                DiscountCode discountCode = discountCodeRepository.findByDiscountCode(code);
                discountCode.setStatus(DiscountCodeStatus.VERIFIED);
                discountCodeRepository.save(discountCode);
            }
        }
        orderInfoRepository.save(orderList);
    }
}
