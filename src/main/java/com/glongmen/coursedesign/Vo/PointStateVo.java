package com.glongmen.coursedesign.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/31 10:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointStateVo {
    private int allOrder;
    private int finishOrder;
    private int unFinishOrder;
    private int UnFinishOrderPercent;
    private int FinishOrderPercent;
    private double countRMB;
}
