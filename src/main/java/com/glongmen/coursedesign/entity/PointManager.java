package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 9:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("point_manager")
public class PointManager {
    private long id;
    private String account;
    private String password;
    private String email;
    private long pointId;
    private long roleId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private long deleted;
    @TableField(exist = false)
    private String token;
}
