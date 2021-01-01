package com.glongmen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glongmen.coursedesign.entity.Administrator;
import com.glongmen.coursedesign.mapper.AdministratorMapper;
import com.glongmen.coursedesign.service.AdminService;
import com.glongmen.coursedesign.Vo.Result;
import com.glongmen.coursedesign.utils.RedisUtils;
import com.glongmen.coursedesign.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/26 15:13
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @param administrator: 传入的管理员对象
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO 管理员登录方法
     * @author Ariel~~
     * @date 12:51 2020/12/27
     */
    @Override
    public Result<Administrator> adminLogin(com.glongmen.coursedesign.entity.Administrator administrator) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Map<String, Object> map = new HashMap<>();
        map.put("account", administrator.getAccount());
        map.put("password", administrator.getPassword());
        queryWrapper.allEq(map);
        Administrator admin = this.administratorMapper.selectOne(queryWrapper);
        System.out.println("C:" + admin);
        if (admin != null) {
            String uuid = UUIDUtils.getUUID();
            admin.setToken(uuid);
            redisUtils.setLogin(uuid, admin.getId(), admin.getRoleId());
        }
        if (admin != null) {
            return new Result(200, "成功", admin);
        } else
            return new Result(500, "失败");
    }

    /**
     * @param account: 账号
     * @return com.glongmen.coursedesign.entity.Administrator
     * @description: TODO 通过账号查询管理员
     * @author Ariel~~
     * @date 12:52 2020/12/27
     */
    @Override
    public Administrator findAccountByAccount(String account) {
        /**
         * @author: Ariel~~
         * @description: 通过account查询管理员账号
         * @Param: [account]
         * @return com.glongmen.coursedesign.Vo.Result
         * @date: 2020/12/27 8:08
         */
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        Administrator administrator = administratorMapper.selectOne(queryWrapper);
        return administrator;
    }
}
