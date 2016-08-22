package com.buterfleoge.whale.biz.order;

import com.buterfleoge.whale.type.protocol.order.GetDiscountRequest;
import com.buterfleoge.whale.type.protocol.order.GetDiscountResponse;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeRequest;
import com.buterfleoge.whale.type.protocol.order.ValidateCodeResponse;

/**
 * @author xiezhenzong
 *
 */
public interface OrderDiscountBiz {

    void getDiscount(Long accountid, GetDiscountRequest request, GetDiscountResponse response) throws Exception;

    void validateDiscountCode(Long accountid, ValidateCodeRequest request, ValidateCodeResponse response)
            throws Exception;

}
