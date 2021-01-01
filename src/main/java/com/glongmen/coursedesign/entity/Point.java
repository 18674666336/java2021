package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 9:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("point")
public class Point {
    @TableId(value = "id",type = IdType.AUTO)
    private long value;
    @TableField("address")
    private String address;
    @TableField(select = false)
    @TableLogic
    private long deleted;

}
