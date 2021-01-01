package com.glongmen.coursedesign.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.glongmen.coursedesign.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 22:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo  {
    private long id;
    private String name;
    private String content;
    private String picPath;
    private long stock;
    private double price;
    private String address;
    private int type;
}
