package com.glongmen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/29 9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("power")
public class Power {
    private long id;
    private String manage;
}
