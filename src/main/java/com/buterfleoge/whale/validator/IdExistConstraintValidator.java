/**
 *
 */
package com.buterfleoge.whale.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.buterfleoge.whale.dao.OrderInfoRepository;
import com.buterfleoge.whale.dao.TravelGroupRepository;
import com.buterfleoge.whale.dao.TravelRouteRepository;
import com.buterfleoge.whale.validator.IdExist.IdType;

/**
 * @author xiezhenzong
 *
 */
public class IdExistConstraintValidator implements ConstraintValidator<IdExist, Long>, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(IdExistConstraintValidator.class);

    @Autowired
    private TravelRouteRepository travelRouteRepository;

    @Autowired
    private TravelGroupRepository travelGroupRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    private Map<IdType, CrudRepository<?, Long>> repositories = new HashMap<IdType, CrudRepository<?, Long>>(3);

    private IdType type;
    private boolean isNullable;

    @Override
    public void afterPropertiesSet() throws Exception {
        repositories.put(IdType.ROUTE_ID, travelRouteRepository);
        repositories.put(IdType.GROUP_ID, travelGroupRepository);
        repositories.put(IdType.ORDER_ID, orderInfoRepository);
    }

    @Override
    public void initialize(IdExist constraintAnnotation) {
        type = constraintAnnotation.type();
        isNullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (isNullable && value == null) {
            return true;
        }
        if (value == null || value < 0) {
            return false;
        }
        try {
            return findRepository(type).exists(value);
        } catch (Exception e) {
            LOG.error("get id from db error, idtype: " + type, e);
            return false;
        }
    }

    private CrudRepository<?, Long> findRepository(IdType type) {
        return repositories.get(type);
    }

}
