package com.glongmen.coursedesign.Vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/30 15:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersVo {
    private long id;
    private String goodsName;
    private String customer;
    private String point;
    private long numb;
    private double money;
    private long state;
    private Date sendTime;
    private Date createTime;
}
