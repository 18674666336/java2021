package com.glongmen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glongmen.coursedesign.entity.Customer;
import com.glongmen.coursedesign.mapper.CustomerMapper;
import com.glongmen.coursedesign.service.CustomerService;
import com.glongmen.coursedesign.service.MailService;
import com.glongmen.coursedesign.utils.GetCode;
import com.glongmen.coursedesign.utils.RedisUtils;
import com.glongmen.coursedesign.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/29 8:26
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    MailService mailService;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public Customer login(String account, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        queryWrapper.eq("password", password);
        Customer customer = customerMapper.selectOne(queryWrapper);
        if (customer!=null){
            String uuid = UUIDUtils.getUUID();
            customer.setToken(uuid);
            redisUtils.setLogin(uuid,customer.getId(),customer.getRoleId());
        }
        return customer;
    }


    /**
     * @description: TODO 发送验证码到邮件
     * @author Ariel~~
     * @param email: 收件人
     * @return int
     * @date 8:24 2020/12/30
     */
    @Override
    public int sendEmailCode(String email) {
        AtomicInteger flag = new AtomicInteger();
        String code = GetCode.getSecurityCode();
        String context="<p style=\" font-size: 18px;\">天气这么冷,衣服穿够了吗?你的验证码是(10分钟有效):</p>\n" +
                "   <b style=\"color: #F56C6C; font-size: 30px;\">"+code+"</b>";
        String subject = "社区水果蔬菜超市保安";
        redisUtils.setCode(email,code);
        new Thread(() -> {
            int i = mailService.sendHtmlMail(email, subject, context);
        }).start();
        return 1;
    }

    @Override
    public int signIn(Customer customer) {
        QueryWrapper queryWrapper=new QueryWrapper();
        int update = 0;
        try {
            update = customerMapper.insert(customer);
            return update;
        } catch (Exception e) {
           return 0;
        }
    }

    @Override
    public Customer findByAccount(String account) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("account",account);
        Customer customer = customerMapper.selectOne(queryWrapper);
        return customer;
    }
}
