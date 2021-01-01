package com.glongmen.coursedesign.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glongmen.coursedesign.Vo.GoodsVo;
import com.glongmen.coursedesign.Vo.OrdersVo;
import com.glongmen.coursedesign.entity.Goods;

import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/27 8:21
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    @Select("SELECT goods.id,name,content,picPath,stock,price,point.address,type,create_time,update_time FROM goods,point WHERE goods.deleted=0 AND goods.point_id=point.id AND goods.type=#{type}")
    Page<GoodsVo> goodsList(Page page, int type);

    @Select("SELECT goods.id,name,content,picPath,stock,price,point.address,type,create_time,update_time FROM goods,point WHERE goods.deleted=0 AND goods.point_id=point.id AND goods.type=#{type} And goods.point_id=#{point}")
    Page<GoodsVo> goodsList2(Page page, int type, int point);

    @Select("SELECT orders.id as id,\n" +
            "       goods.name as goodsName,\n" +
            "       customer.account as customer,\n" +
            "       point.address as point,\n" +
            "       money,\n" +
            "       state,\n" +
            "       numb,\n" +
            "       send_time,\n" +
            "       orders.create_time\n" +
            "from orders\n" +
            "         left join goods on orders.goods_id = goods.id\n" +
            "         left join customer on orders.customer_id = customer.id\n" +
            "         left join point on orders.point_id = point.id\n" +
            "where state=#{state} AND orders.deleted=0 AND point.id = #{pointId}")
    Page<OrdersVo> ordersList(Page page, int state, int pointId);


    @Select("SELECT orders.id as id,\n" +
            "       goods.name as goodsName,\n" +
            "       customer.account as customer,\n" +
            "       point.address as point,\n" +
            "       money,\n" +
            "       state,\n" +
            "       numb,\n" +
            "       send_time,\n" +
            "       orders.create_time\n" +
            "from orders\n" +
            "         left join goods on orders.goods_id = goods.id\n" +
            "         left join customer on orders.customer_id = customer.id\n" +
            "         left join point on orders.point_id = point.id\n" +
            "where state=#{state} AND orders.deleted=0 ")
    Page<OrdersVo> ordersList2(Page page, int state);
}
