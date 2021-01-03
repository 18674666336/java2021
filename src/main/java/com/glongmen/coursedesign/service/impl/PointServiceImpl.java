package com.glongmen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glongmen.coursedesign.entity.Order;
import com.glongmen.coursedesign.entity.Point;
import com.glongmen.coursedesign.entity.PointManager;
import com.glongmen.coursedesign.mapper.OrderMapper;
import com.glongmen.coursedesign.mapper.PointManagerMapper;
import com.glongmen.coursedesign.mapper.PointMapper;
import com.glongmen.coursedesign.service.PointService;
import com.glongmen.coursedesign.utils.RedisUtils;
import com.glongmen.coursedesign.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 12:48
 */
@Service
public class PointServiceImpl implements PointService {
    @Autowired
    PointMapper pointMapper;


    @Autowired
    RedisUtils redisUtils;
    @Autowired
    PointManagerMapper pointManagerMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Point> selectALlPont() {
        return pointMapper.selectList(null);
    }

    /**
     * @description: TODO 注册代收点
     * @author Ariel~~
     * @param point: 代收点编号
     * @return int
     * @date 8:27 2021/1/2
     */
    @Override
    public int insertPoint(Point point) {
        int insert = 0;
        try {
            insert = pointMapper.insert(point);
        } catch (Exception e) {
            return 0;
        }
        return insert;
    }


    /**
     * @param account:  账号
     * @param password: 密码
     * @return int
     * @description: TODO 代收点的登录方法
     * @author Ariel~~
     * @date 10:34 2020/12/29
     */
    @Override
    public PointManager managerLogin(String account, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        queryWrapper.eq("password", password);
        PointManager pointManager = pointManagerMapper.selectOne(queryWrapper);
        if (pointManager != null) {
            String uuid = UUIDUtils.getUUID();
            pointManager.setToken(uuid);
            redisUtils.setLogin(uuid, pointManager.getId(), pointManager.getRoleId());
        }
        return pointManager;

    }


    /**
     * @description: TODO 代收点登录
     * @author Ariel~~
     * @param pointManager:
     * @return int
     * @date 9:52 2021/1/1
     */
    @Override
    public int signIn(PointManager pointManager) {
        int insert = pointManagerMapper.insert(pointManager);
        return insert;
    }

    /**
     * @param id: 代收点
     * @return double
     * @description: TODO 销售总额
     * @author Ariel~~
     * @date 9:52 2021/1/1
     */
    @Override
    public double totalRMB(int id) {
        double v = orderMapper.countRMB(id);
        return v;
    }


}
