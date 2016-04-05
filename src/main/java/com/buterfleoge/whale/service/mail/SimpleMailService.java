package com.buterfleoge.whale.service.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.buterfleoge.whale.Constants.DefaultValue;
import com.buterfleoge.whale.service.MailService;

/**
 *
 * @author xiezhenzong
 *
 */
@Service("mailService")
public class SimpleMailService implements MailService {

    private static final int MAX_MAIL_INFO_POOL_SIZE = 2048;
    private static final int MAX_FAILED_MAIL_INFO_POOL_SIZE = 1024;
    private static final int MAX_TRY_TIME = 3;

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMailService.class);

    @Autowired
    private JavaMailSender mailSender;

    private List<MailInfo> mailInfoPool = new CopyOnWriteArrayList<MailInfo>();
    private List<MailInfo> failedMailInfoPool = new CopyOnWriteArrayList<MailInfo>();

    @Override
    public boolean sendMail(MailInfo mailInfo) {
        Assert.notNull(mailInfo, "mailInfo arg can't be null");
        if (mailInfoPool.size() > MAX_MAIL_INFO_POOL_SIZE) {
            if (failedMailInfoPool.size() > MAX_FAILED_MAIL_INFO_POOL_SIZE) {
                return false;
            } else {
                return failedMailInfoPool.add(mailInfo);
            }
        } else {
            return mailInfoPool.add(mailInfo);
        }
    }

    @Scheduled(fixedDelay = 5000)
    private void sendMailOneTime() {
        List<MailInfo> mailInfos = new ArrayList<MailInfo>(mailInfoPool);
        mailInfoPool.clear();
        for (MailInfo mailInfo : mailInfos) {
            if (checkMailInfoValid(mailInfo)) {
                try {
                    sendMailInternal(mailInfo);
                    logSendMailSuccess(mailInfo);
                } catch (Exception e) {
                    failedMailInfoPool.add(mailInfo);
                    LOG.error("send mail failed, add to failedMailInfoPool.", e);
                }
            }
        }
    }
    
    @Scheduled(fixedDelay = 20000)
    private void sendFaieldMailWithTry() {
        List<MailInfo> failedMailInfo = new ArrayList<MailInfo>(failedMailInfoPool);
        failedMailInfoPool.clear();
        for (MailInfo mailInfo : failedMailInfo) {
            if (checkMailInfoValid(mailInfo)) {
                int tryTime = 0;
                while (tryTime++ < MAX_TRY_TIME) {
                    try {
                        sendMailInternal(mailInfo);
                        break;
                    } catch (Exception e) {
                        LOG.error("send mail failed again, tryTime: " + tryTime, e);
                    }
                }
                if (tryTime >= MAX_TRY_TIME) {
                    LOG.error("send mail failed, ignore it");
                } else {
                    logSendMailSuccess(mailInfo);
                }
            }
        }
    }

    private void sendMailInternal(final MailInfo mailInfo) throws Exception {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailInfo.getEmail());
                message.setFrom(DefaultValue.EMAIL_FROM);
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("user", null);
                String text = "";
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

    private void logSendMailSuccess(MailInfo mailInfo) {
        LOG.info("send mail success, email: " + mailInfo.getEmail() + ", accountid: " + mailInfo.getAccountid());
    }

    private boolean checkMailInfoValid(MailInfo mailInfo) {
        long creatTime = mailInfo.getCreatTime();
        return System.currentTimeMillis() - creatTime <= DefaultValue.VALID_MAIL_INVALID_PERIOD;
    }

}
