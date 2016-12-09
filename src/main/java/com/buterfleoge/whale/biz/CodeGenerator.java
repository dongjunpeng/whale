package com.buterfleoge.whale.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.dao.CouponRepository;
import com.buterfleoge.whale.type.CouponType;
import com.buterfleoge.whale.type.entity.Coupon;

/**
 * @author Brent24
 *
 */
@Service("codeGenerator")
public class CodeGenerator {

    @Autowired
    private CouponRepository discountCodeRepository;
    private Coupon discountCode;
    private String code;

    private Set<String> codeSet = new HashSet<String>();

    public void generate(int count, Long value) {
        for (Coupon coupon : discountCodeRepository.findAll()) {
            codeSet.add(coupon.getDiscountCode());
        }
        BigDecimal bValue = BigDecimal.valueOf(value);
        List<Coupon> toSave = new ArrayList<Coupon>();
        while (count > 0) {
            code = Utils.stringMD5(Utils.createNonceStr()).substring(0, 10).toUpperCase();
            if (!codeSet.contains(code)) {
                discountCode = Coupon.createDiscountCode("亲友优惠", "小伙伴快来支持我们呀", CouponType.FRIEND, bValue, code);
                discountCode.setEndTime(DateUtils.addMonths(discountCode.getAddTime(), 6));
                toSave.add(discountCode);
                codeSet.add(code);
                count--;
            }
        }
        discountCodeRepository.save(toSave);
    }

}
