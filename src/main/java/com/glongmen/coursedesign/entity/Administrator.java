package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/26 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("administrator")
public class Administrator {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private long roleId;
    @TableField("account")
    private String account;
    @TableField("password")
    private String password;
    @TableField(exist = false)
   private String token;
}
