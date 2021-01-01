package com.glongmen.coursedesign.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointMapperTest {
    @Autowired
    PointMapper pointMapper;

    @Test
    void test1(){
        pointMapper.selectList(null).forEach(System.out::println);
    }
}
