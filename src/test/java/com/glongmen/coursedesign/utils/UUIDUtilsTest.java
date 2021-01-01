package com.glongmen.coursedesign.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UUIDUtilsTest {
    @Test
    void test1() {
        Long id = UUIDUtils.getId(UUIDUtils.getUUID());
        System.out.println(id);
    }

    @Test
    void test2(){
        System.out.println(UUIDUtils.getUUID());
    }
}
