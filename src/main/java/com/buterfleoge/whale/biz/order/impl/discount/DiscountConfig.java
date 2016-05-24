package com.buterfleoge.whale.biz.order.impl.discount;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author xiezhenzong
 *
 */
@Configurable
public class DiscountConfig {

    @Bean(name = "countDiscountStrategy")
    public DiscountStrategy getCountDiscountStrategy() {
        return new CountDiscountStrategy();
    }

    @Bean(name = "timeDiscountStrategy")
    public DiscountStrategy getTimeDiscountStrategy() {
        return new TimeDiscountStrategy();
    }

    @Bean(name = "routeDiscountStrategy")
    public DiscountStrategy getRouteDiscountStrategy() {
        return new RouteDiscountStrategy();
    }

    @Bean(name = "studentDiscountStrategy")
    public DiscountStrategy getStudentDiscountStrategy() {
        return new StudentDiscountStrategy();
    }

    @Bean(name="discountHandler")
    public DiscountHandler getDiscountHandler() {
        DiscountHandler handler = new DiscountHandler();
        handler.addDiscountStategy(getCountDiscountStrategy());
        handler.addDiscountStategy(getTimeDiscountStrategy());
        handler.addDiscountStategy(getRouteDiscountStrategy());
        handler.addDiscountStategy(getStudentDiscountStrategy());
        return handler;
    }
}
