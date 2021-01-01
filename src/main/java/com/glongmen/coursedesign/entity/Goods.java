package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/26 12:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods")
public class Goods {
    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    private String name;
    private String content;
    @TableField("picPath")
    private String picPath;
    private long stock;
    private double price;
    private long pointId;
    private int type;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private long deleted;
}
