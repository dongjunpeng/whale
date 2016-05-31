package com.buterfleoge.whale.biz.order.impl.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author xiezhenzong
 *
 */
@Configuration
public class CreateOrderConfig {

    @Bean(name = "fillOrderidProcessor")
    public CreateOrderProcessor getFillOrderidProcessor() {
        return new FillOrderidProcessor();
    }

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
        handler.addProcessor(getFillOrderidProcessor());
        handler.addProcessor(getValidateProcessor());
        handler.addProcessor(getBizProcessor());
        return handler;
    }

}
