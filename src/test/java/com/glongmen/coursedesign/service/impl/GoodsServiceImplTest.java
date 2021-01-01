package com.glongmen.coursedesign.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glongmen.coursedesign.Vo.GoodsVo;
import com.glongmen.coursedesign.Vo.OrdersVo;
import com.glongmen.coursedesign.entity.Goods;
import com.glongmen.coursedesign.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    void test1() {
        goodsService.findAllGoods().forEach(System.out::println);
    }

    @Test
    public void deleteGoodsById( ) {
        int i = goodsService.deleteGoodsById(1);
        System.out.println(i);
    }

    @Test
    public void insertTest(){
        Goods goods=new Goods();
        goods.setName("苹果");
        goods.setContent("湖南苹果");
        goods.setPicPath("45689465.jpg");
        goods.setStock(45);
        goods.setPrice(65.22);
        int i = goodsService.insertGoods(goods);
        System.out.println(new Date());
        System.out.println(i);
    }

    @Test
    public void pageQuery(){
        Page<GoodsVo> goodsPage = goodsService.pagingQuery(new Page<Goods>(1, 5),1,2);
        System.out.println(goodsPage.getTotal());
        goodsPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void update(){
        Goods newGoods = new Goods();
        newGoods.setId(3);
        newGoods.setName("芒果2");
        newGoods.setPrice(99);
        newGoods.setType(1);
        newGoods.setPointId(2);
        int i = goodsService.updateGoods(newGoods);
    }

    @Test
    public void pageQuery2(){
        Page<OrdersVo> goodsPage = goodsService.findOrderByState(new Page<Goods>(1, 5),1,1);
        System.out.println(goodsPage.getTotal());
        goodsPage.getRecords().forEach(System.out::println);
    }
  @Test
    public void count(){
      System.out.println(goodsService.countAllOrder());
     //System.out.println(goodsService.countOrderById(1));
      //goodsService.countUnFinishOrder(1);
  }
  @Test
    void upPic(){
      System.out.println(goodsService.updatePic(4 , "45646"));
  }
}
