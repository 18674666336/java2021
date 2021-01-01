package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 9:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order {
    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    private long goodsId;
    private long customerId;
    private long pointId;
    private long numb;
    private double money;
    private long state;
    private Date sendTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableLogic
    private long deleted;
}
