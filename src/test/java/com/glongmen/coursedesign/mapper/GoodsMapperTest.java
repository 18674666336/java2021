package com.glongmen.coursedesign.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glongmen.coursedesign.Vo.GoodsVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodsMapperTest {
    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void test(){
        Page<GoodsVo> page = new Page<>(1,3);
    }
}
