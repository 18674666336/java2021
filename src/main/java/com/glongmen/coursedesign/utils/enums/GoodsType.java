package com.glongmen.coursedesign.utils.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 14:55
 */
public enum GoodsType {
    FRUIT(1,"水果"),
    VEGETABLE(2,"蔬菜");


    private int typeId;
    private String desc;

    GoodsType(int typeId, String desc) {
        this.typeId = typeId;
        this.desc = desc;
    }
}
