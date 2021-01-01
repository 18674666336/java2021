package com.glongmen.coursedesign.service;

import com.glongmen.coursedesign.entity.Customer;
import org.springframework.scheduling.support.SimpleTriggerContext;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/29 8:25
 */
public interface CustomerService {
    public Customer login(String account, String password);
    public int sendEmailCode(String email);
    public int signIn(Customer customer);
    public Customer findByAccount( String account);
}
