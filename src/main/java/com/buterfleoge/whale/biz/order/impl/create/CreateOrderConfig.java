package com.buterfleoge.whale.biz.order.impl.create;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author xiezhenzong
 *
 */
@Configurable
public class CreateOrderConfig {

    @Bean(name = "validateProcessor")
    public CreateOrderProcessor getValidateProcessor() {
        return new ValidateProcessor();
    }

    @Bean(name = "bizProcessor")
    public CreateOrderProcessor getBizProcessor() {
        return new BizProcessor();
    }

    @Bean(name = "createOrderHandler")
    public CreateOrderHandler getCreateOrderHandler() {
        CreateOrderHandler handler = new CreateOrderHandler();
        handler.addProcessor(getValidateProcessor());
        handler.addProcessor(getBizProcessor());
        return handler;
    }

}
