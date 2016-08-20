package com.buterfleoge.whale.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author xiezhenzong
 *
 */
@Service("validators")
public class Validators extends ApplicationObjectSupport implements Validator, InitializingBean {

    private List<Validator> validators = Collections.emptyList();

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<Validator> validators = getApplicationContext().getBeansOfType(Validator.class).values();
        if (CollectionUtils.isEmpty(validators)) {
            return;
        }
        Collection<javax.validation.Validator> jsrValidators = getApplicationContext()
                .getBeansOfType(javax.validation.Validator.class).values();
        validators.removeAll(jsrValidators); // spring直接支持jsr规范
        validators.remove(this);
        this.validators = new ArrayList<Validator>(validators);
        Collections.sort(this.validators, new Comparator<Validator>() {

            @Override
            public int compare(Validator o1, Validator o2) {
                return getOrder(o1) - getOrder(o2);
            }

            private int getOrder(Validator o1) {
                return ClassUtils.isAssignableValue(javax.validation.Validator.class, o1) ? -1 : 0;
            }
        });
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        for (Validator validator : validators) {
            if (validator.supports(ClassUtils.getUserClass(target))) {
                validator.validate(target, errors);
            }
        }
    }

}
