package com.glongmen.coursedesign.utils.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/26 15:16
 */

public enum orderEnum {
    FINISH(1, "完成支付"),
    FAIL(2, "未配送");

    @EnumValue
    private Integer code;

    private String description;

    orderEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public String getValue(){
        return this.description;
    }
}
