package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/29 9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Token {
    private long id;
    private long powerId;
}
