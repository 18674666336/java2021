package com.glongmen.coursedesign.service.impl;

import com.glongmen.coursedesign.service.MailService;
import com.glongmen.coursedesign.utils.GetCode;
import com.glongmen.coursedesign.utils.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MailServiceImplTest {
    @Autowired
    MailService mailService;

    @Test
    void sendMail() {
    }

    @Test
    void sendHtmlMail() {
        String receive="1044204713@qq.com";
        String subject="社区蔬菜水果商店";
        String code= GetCode.getSecurityCode();
        String context="<p style=\" font-size: 18px;\">你好欢迎你注册!你的验证码是(10分钟有效):</p>\n" +
                "   <b style=\"color: #F56C6C; font-size: 30px;\">"+code+"</b>";
        mailService.sendHtmlMail(receive,subject,context);
    }
}
