package com.glongmen.coursedesign.service;

import com.glongmen.coursedesign.entity.Administrator;
import com.glongmen.coursedesign.Vo.Result;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/26 15:12
 */
public interface AdminService {
    public Result adminLogin(Administrator administrator);
    public Administrator findAccountByAccount(String account);
}
