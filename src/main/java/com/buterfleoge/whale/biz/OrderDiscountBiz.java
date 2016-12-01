package com.buterfleoge.whale.biz;

import java.util.List;
import java.util.Set;

import com.buterfleoge.whale.type.entity.OrderDiscount;
import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;

/**
 * @author xiezhenzong
 *
 */
public interface OrderDiscountBiz {

    void getDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) throws Exception;

    OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, int type);

    OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, Long orderid, int type);

    OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, Set<Integer> types);

    OrderDiscount filterDiscounts(List<OrderDiscount> orderDiscounts, Long orderid, Set<Integer> types);

}
