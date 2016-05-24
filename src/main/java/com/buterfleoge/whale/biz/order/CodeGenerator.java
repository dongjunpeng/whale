package com.buterfleoge.whale.biz.order;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buterfleoge.whale.Utils;
import com.buterfleoge.whale.dao.DiscountCodeRepository;
import com.buterfleoge.whale.type.entity.DiscountCode;
import com.buterfleoge.whale.type.enums.DiscountCodeStatus;

/**
 * @author Brent24
 *
 */
@Service("codeGenerator")
public class CodeGenerator {

    @Autowired
    private DiscountCodeRepository discountCodeRepository;
    private DiscountCode discountCode;
    private String code;

    private Set<String> codeSet = new HashSet<String>();

    @Transactional(rollbackFor = Exception.class)
    public void generate(int count, Long value, Long agent, Long startTime, Long endTime) {
        Iterable<DiscountCode> discountCodeIterable = discountCodeRepository.findAll();
        Iterator<DiscountCode> discountCodeIterator = discountCodeIterable.iterator();
        while (discountCodeIterator.hasNext()) {
            codeSet.add(discountCodeIterator.next().getDiscountCode());
        }
        for (int i = 0; i < count; i++) {
            code = Utils.stringMD5("hxy" + (Math.random() * 10000000)).substring(0, 10).toUpperCase();

            if (!codeSet.contains(code)) {
                discountCode = new DiscountCode();
                discountCode.setStatus(DiscountCodeStatus.CREATED);
                discountCode.setDiscountCode(code);
                discountCode.setAgent(agent);
                // discountCode.setValue(value);
                // discountCode.setStartTime(startTime);
                // discountCode.setEndTime(endTime);
                // discountCode.setAddTime(System.currentTimeMillis());
                discountCodeRepository.save(discountCode);
            } else {
                i--;
            }
        }
    }

    public void generate(int count, Long value, Long startTime, Long endTime) {
        generate(count, value, null, startTime, endTime);
    }

    public void generate(Long value, Long startTime, Long endTime) {
        generate(1, value, null, startTime, endTime);
    }
}
