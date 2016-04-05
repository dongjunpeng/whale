package com.buterfleoge.whale.service.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author xiezhenzong
 *
 */
@Configuration
@EnableAsync
@EnableScheduling
public class MailConfig {

    @Bean(name = "mailSender")
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        return mailSender;
    }

}
