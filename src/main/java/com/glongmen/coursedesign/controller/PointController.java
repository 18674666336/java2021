package com.glongmen.coursedesign.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.glongmen.coursedesign.Vo.Result;
import com.glongmen.coursedesign.entity.Point;
import com.glongmen.coursedesign.entity.PointManager;
import com.glongmen.coursedesign.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 9:20
 */
@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    PointService pointService;

    /**
     * @param :
     * @return com.glongmen.coursedesign.Vo.Result<java.util.List>
     * @description: TODO 获取所有的代收点
     * @author Ariel~~
     * @date 15:33 2020/12/28
     */
    @GetMapping("/allPoint")
    public Result<List> selectOptions() {
        List<Point> points = pointService.selectALlPont();
        Result<List> listResult = new Result<List>(200, "查询成功!", points);
        return listResult;
    }

    /**
     * @param address: 地址
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO 添加代收点
     * @author Ariel~~
     * @date 15:33 2020/12/28
     */
    @PostMapping("/addPoint/{address}")
    public Result addPoint(@PathVariable String address) {
        Point point = new Point();
        point.setAddress(address);
        int i = pointService.insertPoint(point);
        if (i == 1) {
            return new Result(200, "添加代收点成功!");
        } else
            return new Result(500, "添加代收点失败!");
    }

    /**
     * @description: TODO 代收点登录
     * @author Ariel~~
     * @param account: 账号
     * @param password: 密码
     * @return com.glongmen.coursedesign.Vo.Result
     * @date 10:53 2020/12/29
     */
    @PostMapping("/login/{account}/{password}")
    public Result login(@PathVariable String account, @PathVariable String password) {
        PointManager pointM = pointService.managerLogin(account, password);
        if (pointM != null) {
            return new Result(200, "代收点登录成功!", pointM);
        } else {
            return new Result(200, "代收点登录成功!");
        }

    }
}
