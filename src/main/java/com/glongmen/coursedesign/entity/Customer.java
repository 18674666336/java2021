package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/27 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer")
public class Customer {
    @TableId(value = "id",type = IdType.AUTO)
    private long id;
    private String account;
    private String email;
    private String password;
    private long roleId;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableLogic
    private long deleted;
    private String uuid;
    @TableField(exist = false)
    private String token;
}
