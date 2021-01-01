package com.glongmen.coursedesign.realm;

import com.glongmen.coursedesign.entity.Customer;
import com.glongmen.coursedesign.service.CustomerService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/31 20:49
 */
public class AccountRealm extends AuthorizingRealm {
    //用户权限信息
    @Autowired
    CustomerService customerService;

    /**
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @description: TODO 授权信息
     * @author Ariel~~
     * @date 20:51 2020/12/31
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * @param null:
     * @return
     * @description: TODO 认证登录
     * @author Ariel~~
     * @date 20:51 2020/12/31
     */
    //用户角色信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token =(UsernamePasswordToken) authenticationToken;
        Customer account = customerService.findByAccount(token.getUsername());
        if (account!=null){
                return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }
        return null;
    }
}
