package com.glongmen.coursedesign.service;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/29 15:18
 */
public interface MailService {
    /**
     * 发送邮件
     * @param receiver 邮件收件人
     * @param subject 邮件主题
     * @param context 邮件验证码
     */
    public int sendMail(String receiver, String subject, String context);
    public int sendHtmlMail(String receiver, String subject, String context);

}
