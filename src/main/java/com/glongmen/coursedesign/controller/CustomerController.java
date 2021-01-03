package com.glongmen.coursedesign.controller;

import com.glongmen.coursedesign.Vo.Result;
import com.glongmen.coursedesign.entity.Customer;
import com.glongmen.coursedesign.entity.PointManager;
import com.glongmen.coursedesign.entity.Token;
import com.glongmen.coursedesign.service.CustomerService;
import com.glongmen.coursedesign.service.PointService;
import com.glongmen.coursedesign.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/28 8:09
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    PointService pointService;

    /**
     * @param account:  账号
     * @param password: 密码
     * @return com.glongmen.coursedesign.Vo.Result<com.glongmen.coursedesign.entity.Customer>
     * @description: TODO 用户账号登录
     * @author Ariel~~
     * @date 14:54 2020/12/29
     */
    @PostMapping("/login/{account}/{password}")
    public Result<Customer> login(@PathVariable String account, @PathVariable String password) {
        Customer customer = customerService.login(account, password);
        if (customer != null) {
            return new Result(200, "登录成功", customer);
        } else {
            return new Result(500, "登录失败");
        }
    }
    /**
     * @description: TODO 发送验证码
     * @author Ariel~~
     * @param email: 邮箱
     * @return com.glongmen.coursedesign.Vo.Result
     * @date 8:22 2021/1/2
     */
    @GetMapping("/emailCode/{email}")
    public Result SignIn(@PathVariable String email) {
        int i = customerService.sendEmailCode(email);
        if (i == 1) {
            return new Result(200, "验证码已经发送");
        } else {
            return new Result(500, "验证码发送失败");
        }

    }

    /**
     * 注册账号并删除验证码
     * @param account:   账号
     * @param checkCode: 验证码
     * @param email:
     * @param password:
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO
     * @author Ariel~~
     * @date 10:39 2020/12/30
     */
    @PostMapping("/signIn/{account}/{password}/{role}/{email}/{checkCode}")
    public Result signIn(@PathVariable String account, @PathVariable String checkCode, @PathVariable String email, @PathVariable String password, @PathVariable int role) {
        Object o = (String) redisUtils.getCode(email);
        int i = -1;
        if (o != null && o.equals(checkCode)) {
            if (role==1){
                Customer customer = new Customer();
                customer.setAccount(account);
                customer.setPassword(password);
                customer.setRoleId(1);
                customer.setEmail(email);
                i = customerService.signIn(customer);
            }
            else{
                PointManager pointManager=new PointManager();
                pointManager.setAccount(account);
                pointManager.setPassword(password);
                pointManager.setEmail(email);
                pointManager.setRoleId(2);
                i=pointService.signIn(pointManager);
            }
            if (i == 1) {
                redisUtils.delete(email);
                return new Result(200, "注册成功");
            } else {
                return new Result(500, "注册失败");
            }
        } else {
            return new Result(500, "验证码失效!");
        }
    }

    /**
     * @param token:
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO 查询token是否有效
     * @author Ariel~~
     * @date 17:32 2020/12/30
     */
    @GetMapping("/findToken/{token}")
    public Result findToken(@PathVariable String token) {
        Token token1 = redisUtils.findToken(token);
        if (token1 != null) {
            System.out.println(token1);
            return new Result(200, "token有效", token1);
        } else {
            return new Result(500, "token无效");
        }
    }
}
