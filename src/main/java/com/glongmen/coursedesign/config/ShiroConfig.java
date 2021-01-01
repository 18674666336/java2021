package com.glongmen.coursedesign.config;

import com.glongmen.coursedesign.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/31 20:59
 */

//@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        factoryBean.setSecurityManager(securityManager);
        //权限设置
        /*认证过滤器
         anon:无需认证
         authc:必须认证
         authBasic:要通过HttpBasic认证
         user不一定要认证,只要被shiro记录即可
         授权过滤器
         perms:必须拥有某个权限才能访问
         role:必须拥有某个角色才能访问
         port:请求的端口必须是指定值
         rest:请求必须基于RESTful,POST,PUT,GET,DELETE
         ssl:必须是安全的URL请求,协议HTTPS
         */
        Map<String, String> map = new Hashtable<>();
        map.put("/main","authc");
        map.put("/manager","perms[manage]");
        map.put("/administaor","roles[administator]");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accountRealm);
        return manager;
    }


    @Bean
    public AccountRealm accountRealm() {
        return new AccountRealm();
    }
}
