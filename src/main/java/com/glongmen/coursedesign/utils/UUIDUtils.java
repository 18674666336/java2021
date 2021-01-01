package com.glongmen.coursedesign.utils;


import java.util.UUID;
/**
 * @author: Ariel~~
 * @description: UUID工具类
 * @date: 2020/12/26 8:59
 */
public class UUIDUtils {


    public static String getUUID(){
        /**
         * @author: Ariel~~
         * @description: UUID生成方法
         * @Param: []
         * @return java.lang.String
         * @date: 2020/12/26 9:02
         */
        return UUID.randomUUID().toString().replace("-","");
    }

    public static Long getId(String UUID){
        /**
         * @author: Ariel~~
         * @description: uuid转换成数字
         * @Param: [UUID]
         * @return java.lang.Long
         * @date: 2020/12/26 9:04
         */
        Integer userId=UUID.toString().hashCode();
        userId= userId< 0 ? -userId: userId;   //String.hashCode()可能会是负数，如果为负数需要转换为正数
        return Long.valueOf(String.valueOf(userId));
    }

}
