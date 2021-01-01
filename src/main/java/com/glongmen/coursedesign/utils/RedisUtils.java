package com.glongmen.coursedesign.utils;

import com.glongmen.coursedesign.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/30 9:51
 */
@Component
public class RedisUtils {
    @Autowired
    RedisTemplate redisTemplate;


    public void setCode(String email, String code) {
        redisTemplate.opsForValue().set(email, code, 10, TimeUnit.MINUTES);
    }
    public String getCode(String email){
       return (String)redisTemplate.opsForValue().get(email);

    }

    public void setLogin(String uuid, long id, long role) {
        redisTemplate.boundHashOps(uuid).put("id", id);
        redisTemplate.boundHashOps(uuid).put("role", role);
        redisTemplate.expire(uuid, 30, TimeUnit.MINUTES);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }


    /**
     * @description: TODO 查询token,并为token加时间
     * @author Ariel~~
     * @param token: 令牌
     * @return com.glongmen.coursedesign.entity.Token
     * @date 10:50 2020/12/31
     */
    public Token findToken(String token) {
        List values = redisTemplate.boundHashOps(token).values();
        System.out.println("redis:"+values);
        Token role = null;
        if (values.size()>0){
            role=new Token();
            role.setId((Long) values.get(0));
            role.setPowerId((Long) values.get(1));
            redisTemplate.expire(token,30,TimeUnit.MINUTES);
        }
        return role;
    }
}
