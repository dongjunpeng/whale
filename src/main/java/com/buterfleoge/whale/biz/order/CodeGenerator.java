package com.buterfleoge.whale.biz.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.type.entity.DiscountCode;

/**
 * @author Brent24
 *
 */

@Service("codegenerator")
public class CodeGenerator {

    @Autowired
    private DiscountCodeRepository discountCodeRepository;
    private DiscountCode discountCode;
    private Long code;

    public void generate(int count, Long value, Long agent, Long startTime, Long endTime) {
        for (int i = 0; i < count; i++) {
            code = (long) (Math.random() * 8999999999L + 1000000000L);
            if (discountCodeRepository.findByDiscountCode(code) == null) {
                discountCode = new DiscountCode();
                discountCode.setDiscountCode(code);
                discountCode.setAgent(agent);
                discountCode.setValue(value);
                discountCode.setStartTime(startTime);
                discountCode.setEndTime(endTime);
                discountCode.setAddTime(System.currentTimeMillis());
                discountCodeRepository.save(discountCode);
            } else {
                i--;
            }
        }
    }
}
