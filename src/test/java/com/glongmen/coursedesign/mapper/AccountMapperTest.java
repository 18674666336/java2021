package com.glongmen.coursedesign.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AccountMapperTest {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Test
    void test1(){
        administratorMapper.selectList(null).forEach(System.out::println);
    }

}
