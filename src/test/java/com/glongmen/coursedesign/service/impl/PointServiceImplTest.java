package com.glongmen.coursedesign.service.impl;

import com.glongmen.coursedesign.mapper.OrderMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PointServiceImplTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    void totalRMB() {
        System.out.println(orderMapper.countRMB(1));
    }
}
