package com.glongmen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glongmen.coursedesign.Vo.GoodsVo;
import com.glongmen.coursedesign.Vo.OrdersVo;
import com.glongmen.coursedesign.Vo.PointStateVo;
import com.glongmen.coursedesign.entity.Goods;
import com.glongmen.coursedesign.entity.Order;
import com.glongmen.coursedesign.mapper.GoodsMapper;
import com.glongmen.coursedesign.mapper.OrderMapper;
import com.glongmen.coursedesign.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/27 8:22
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * @param :
     * @return java.util.List<com.glongmen.coursedesign.entity.Goods>
     * @description: TODO 查询所有商品列表
     * @author Ariel~~
     * @date 12:52 2020/12/27
     */
    @Override
    public List<Goods> findAllGoods() {
        List<Goods> goods = goodsMapper.selectList(null);
        return goods;
    }

    /**
     * @description: TODO 通过id查询商品
     * @author Ariel~~
     * @param id: 商品id
     * @return com.glongmen.coursedesign.entity.Goods
     * @date 8:21 2021/1/2
     */
    @Override
    public Goods findGoodsByID(int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        return goods;
    }

    /**
     * @param id:
     * @return int
     * @description: TODO 通过id删除商品
     * @author Ariel~~
     * @date 12:53 2020/12/27
     */
    @Override
    public int deleteGoodsById(int id) {
        int i = goodsMapper.deleteById(id);
        return i;
    }

    /**
     * @param goods:
     * @return int
     * @description: TODO 添加商品
     * @author Ariel~~
     * @date 12:53 2020/12/27
     */
    @Override
    public int insertGoods(Goods goods) {
        int insert = goodsMapper.insert(goods);
        int id = 0;
        if (insert != 0) {
            id = (int) goods.getId();
        }
        return id;
    }


    /**
     * @param page: 分页查询对象
     * @return java.util.List<com.glongmen.coursedesign.entity.Goods>
     * @description: TODO 分页查询
     * @author Ariel~~
     * @date 20:54 2020/12/27
     */
    @Override
    public Page<GoodsVo> pagingQuery(Page page, int type, int point) {
        if (point == 0) {
            return goodsMapper.goodsList(page, type);
        } else
            return goodsMapper.goodsList2(page, type, point);

    }

    /**
     * @param page:
     * @param state: 状态
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.glongmen.coursedesign.Vo.OrdersVo>
     * @description: TODO 订单的分页查询
     * @author Ariel~~
     * @date 15:53 2020/12/30
     */
    @Override
    public Page<OrdersVo> findOrderByState(Page page, int state, int pointID) {
        if (pointID == 0) {
            return goodsMapper.ordersList2(page, state);
        } else {
            return goodsMapper.ordersList(page, state, pointID);
        }
    }

    /**
     * @param goods: 要更新的商品
     * @return int
     * @description: TODO
     * @author Ariel~~
     * @date 20:58 2020/12/28
     */
    @Override
    public int updateGoods(Goods goods) {
        int update = goodsMapper.updateById(goods);
        return update;
    }

    @Override
    public int insertOrder(Order order, int numb) {
        int insert = orderMapper.insert(order);
        updateStock((int) order.getGoodsId(), numb);
        return insert;
    }

    /**
     * @param goodsId: 商品Id
     * @param numb:    商品数量
     * @return void
     * @description: TODO 修改库存
     * @author Ariel~~
     * @date 14:38 2020/12/30
     */
    @Override
    public void updateStock(int goodsId, int numb) {
        Goods goodsByID = findGoodsByID(goodsId);
        goodsByID.setStock(goodsByID.getStock() - numb);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", goodsId);
        goodsMapper.update(goodsByID, queryWrapper);
    }

    /**
     * @description: TODO 通过id删除订单
     * @author Ariel~~
     * @param id: 订单id
     * @return int
     * @date 8:23 2021/1/2
     */
    @Override
    public int deleteOrderById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        return orderMapper.delete(queryWrapper);
    }


    /**
     * @description: TODO 统计所有订单的情况,封装到视图类
     * @author Ariel~~
     * @param :
     * @return com.glongmen.coursedesign.Vo.PointStateVo
     * @date 8:23 2021/1/2
     */
    @Override
    public PointStateVo countAllOrder() {
        PointStateVo pointStateVo = new PointStateVo();
        double result, result2;
        //所有订单
        QueryWrapper queryWrapper = new QueryWrapper();
        Integer integer1 = orderMapper.selectCount(queryWrapper);

        //已完成订单
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("state", 1);
        Integer integer2 = orderMapper.selectCount(queryWrapper2);
        //未完成订单
        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("state", 0);
        Integer integer3 = orderMapper.selectCount(queryWrapper3);

        result = (double) integer2 / (double) integer1;
        System.out.println("result" + result);
        result = result * 100;

        result2 = (double) integer3 / (double) integer1;
        System.out.println("result" + result2);
        result2 = result2 * 100;


        pointStateVo.setAllOrder(integer1);
        pointStateVo.setFinishOrder(integer2);
        pointStateVo.setFinishOrderPercent((int) result);
        pointStateVo.setUnFinishOrder(integer3);
        pointStateVo.setUnFinishOrderPercent((int) result2);
        pointStateVo.setCountRMB(orderMapper.countAllRMB());
        return pointStateVo;
    }

    /**
     * @description: TODO 更新照片
     * @author Ariel~~
     * @param goodsId: 商品id
     * @param url: 图片地址
     * @return int
     * @date 8:23 2021/1/2
     */
    @Override
    public int updatePic(int goodsId, String url) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Goods goods = goodsMapper.selectById(goodsId);
        goods.setPicPath(url);
        queryWrapper.eq("id", goodsId);
        int update = goodsMapper.update(goods, queryWrapper);
        return update;
    }

    /**
     * @description: TODO 通过id完成订单
     * @author Ariel~~
     * @param id: 订单id
     * @return int
     * @date 8:24 2021/1/2
     */
    @Override
    public int finishOrderById(int id) {
        QueryWrapper queryWrapper =new QueryWrapper();
        Order order=orderMapper.selectById(id);
        order.setState(1);
        queryWrapper.eq("id",id);
        int update = orderMapper.update(order, queryWrapper);
        return update;
    }

    /**
     * @description: TODO 通过id统计订单
     * @author Ariel~~
     * @param pointID: 代收点id
     * @return com.glongmen.coursedesign.Vo.PointStateVo
     * @date 8:24 2021/1/2
     */
    @Override
    public PointStateVo countOrderById(int pointID) {
        PointStateVo pointStateVo = new PointStateVo();
        double result, result2;
        //所有订单
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("point_id", pointID);
        Integer integer1 = orderMapper.selectCount(queryWrapper);

        //已完成订单
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("point_id", pointID);
        queryWrapper2.eq("state", 1);
        Integer integer2 = orderMapper.selectCount(queryWrapper2);
        //未完成订单
        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("point_id", pointID);
        queryWrapper3.eq("state", 0);
        Integer integer3 = orderMapper.selectCount(queryWrapper3);

        result = (double) integer2 / (double) integer1;
        System.out.println("result" + result);
        result = result * 100;
        result2 = (double) integer3 / (double) integer1;
        result2 = result2 * 100;

        pointStateVo.setAllOrder(integer1);
        pointStateVo.setFinishOrder(integer2);
        pointStateVo.setFinishOrderPercent((int) result);
        pointStateVo.setUnFinishOrder(integer3);
        pointStateVo.setUnFinishOrderPercent((int) result2);
        pointStateVo.setCountRMB(orderMapper.countRMB(pointID));
        return pointStateVo;
    }


}
