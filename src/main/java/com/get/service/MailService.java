package com.get.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;


    public void sendForgetPasswordMail(String email, String resetPassHref) throws Exception {
        final String mailSubject = "密码重置邮件";
        final String mailContent = "请点击以下地址:<br/><a href='{0}'>重置密码</a>";
        //
        String emailContent = getMessage(mailContent, resetPassHref);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailFrom);
        helper.setTo(email);
        helper.setSubject(mailSubject);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    private static String getMessage(String template, String... keys) {
        int count = 0;
        for (String key : keys) {
            template = template.replace("{" + count++ + "}", key);
        }
        return template;
    }
}
