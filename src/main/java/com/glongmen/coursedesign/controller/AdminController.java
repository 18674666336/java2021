package com.glongmen.coursedesign.controller;

import com.glongmen.coursedesign.entity.Administrator;
import com.glongmen.coursedesign.service.impl.AdminServiceImpl;
import com.glongmen.coursedesign.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Ariel~~
 * @description：管理员登录类
 * @date ：2020/12/26 16:06
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;


    /**
     * 管理员登录
     * @author Ariel~~
     * @param account: 账号
     * @param password: 密码
     * @return com.glongmen.coursedesign.Vo.Result
     * @date 12:47 2020/12/27
     */
    @PostMapping("/login/{account}/{password}")
    public Result login(@PathVariable("account") String account,@PathVariable("password") String password){
        Administrator administrator=new Administrator();
        administrator.setAccount(account);
        administrator.setPassword(password);
        Result result = adminService.adminLogin(administrator);

        return result;
    }
}
