package com.glongmen.coursedesign.service;


import com.glongmen.coursedesign.entity.Point;
import com.glongmen.coursedesign.entity.PointManager;

import java.util.List;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 12:45
 */
public interface PointService {
    public List<Point> selectALlPont();

    public int insertPoint(Point point);

    public PointManager managerLogin(String account, String password);

    public int signIn(PointManager pointManager);

    public double totalRMB(int id);

}
