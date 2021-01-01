package com.glongmen.coursedesign.service.impl;

import com.glongmen.coursedesign.entity.Administrator;
import com.glongmen.coursedesign.Vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    AdminServiceImpl adminService;


    @Test
    void test1(){

        Administrator administrator=new Administrator();
        administrator.setAccount("985211");
        administrator.setPassword("9852112");

        Result result = adminService.adminLogin(administrator);
        System.out.println(result);
    }

    @Test
    void test2(){
        Administrator accountByAccount = adminService.findAccountByAccount("985211rjgc");
        System.out.println(accountByAccount);
    }
}
