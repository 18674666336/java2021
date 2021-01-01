package com.glongmen.coursedesign.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisUtilsTest {
    @Autowired
    RedisUtils redisUtils;


    @Test
    void setCode() {
        redisUtils.setCode("456465465","4564");
    }

    @Test
    void setLogin() {
        redisUtils.setLogin("456",1,1);
    }

    @Test
    void finToken(){
        System.out.println(redisUtils.findToken("683c36a96c6a4112ace515c592c99c3e"));
    }
}
