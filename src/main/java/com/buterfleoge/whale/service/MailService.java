package com.buterfleoge.whale.service;

import com.buterfleoge.whale.service.mail.MailInfo;

/**
 * 发送邮件
 *
 * @author xiezhenzong
 *
 */
public interface MailService {

    boolean sendMail(MailInfo mailInfo);

}
