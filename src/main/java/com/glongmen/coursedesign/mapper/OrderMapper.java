package com.glongmen.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glongmen.coursedesign.entity.Order;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/30 14:10
 */
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select sum(money) from orders where point_id=#{id}")
     double countRMB(int id);
    @Select("select sum(money) from orders")
    double countAllRMB();
}
