package com.glongmen.coursedesign.service.impl;

import com.glongmen.coursedesign.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/29 15:19
 */
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * @param receiver: 收件人
     * @param subject:  邮件主题
     * @param context:  邮件内容
     * @return void
     * @description: TODO
     * @author Ariel~~
     * @date 15:29 2020/12/29
     */
    @Override
    public int sendMail(String receiver, String subject, String context) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setText(context);
        message.setSubject(subject);
        try {
            mailSender.send(message);
            System.out.println("邮件发送成功!");
            return 1;
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败!");
            return 0;
        }
    }

    @Override
    public int sendHtmlMail(String receiver, String subject, String context) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(context, true);
            mailSender.send(message);
            System.out.println("邮件发送成功!");
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败!");
            return  0;
        }
    }
}
