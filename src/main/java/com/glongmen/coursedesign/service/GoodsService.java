package com.glongmen.coursedesign.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glongmen.coursedesign.Vo.GoodsVo;
import com.glongmen.coursedesign.Vo.OrdersVo;
import com.glongmen.coursedesign.Vo.PointStateVo;
import com.glongmen.coursedesign.entity.Goods;
import com.glongmen.coursedesign.entity.Order;


import java.util.List;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/27 8:22
 */
public interface GoodsService {

    public List<Goods> findAllGoods();
    public Goods findGoodsByID(int id);

    public int deleteGoodsById(int id);

    public int insertGoods(Goods goods);

    public Page<GoodsVo> pagingQuery(Page pageable, int type,int point);

    public int updateGoods(Goods goods);


    public int insertOrder(Order order,int numb);

    public void updateStock(int goodsId,int numb);

    public Page<OrdersVo> findOrderByState(Page page,int state,int pointID);

    public int deleteOrderById(int id);

    public PointStateVo countOrderById(int pointID);

    public PointStateVo countAllOrder();

    public int updatePic(int goodsId,String url);

    int finishOrderById(int id);
}
