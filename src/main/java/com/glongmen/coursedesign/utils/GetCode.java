package com.glongmen.coursedesign.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author ：Ariel~~
 * @description：TODO生成6位验证码
 * @date ：2020/12/29 15:50
 */
public class GetCode {
    public static String getSecurityCode() {
        int a = 1234567890;
        String b = "abcdefghijklmnopqrstuvwxyz";
        //字符串小写字母变大写
        String B = b.toUpperCase();
        String c = a + b + B;
        char[] d = c.toCharArray();
        Random random = new Random();
        String code = "";
        for (int k = 0; k < 6; k++) {
            int index = random.nextInt(d.length);
            code += d[index];
        }
        return code;
    }
}

