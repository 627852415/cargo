package com.lxtx.framework.common.utils;

import java.util.Random;

/**
 * @author zhiHua chen
 * Created  data on 2018/08/08
 */
public class RandomUtil {


    /**
     * 生成全数字
     */
    public static String randomNumber(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(String.valueOf(random.nextInt(10)));
        }
        return builder.toString();
    }

    /**
     * 获取指定长度的随机字符串
     */
    public static String randomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //获取验证过的随机密码
    public static String getRandomPassword(int len) {
        String result = null;
        while(len>=6){
            result = makeRandomPassword(len);
            if (result.matches(".*[a-z]{1,}.*") && result.matches(".*[A-Z]{1,}.*") && result.matches(".*\\d{1,}.*")) {
                return result;
            }
            result = makeRandomPassword(len);
        }
        return "长度不得少于6位!";
    }
    //随机密码生成
    public static String makeRandomPassword(int len){
        char charr[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int x = 0; x < len; ++x) {
            sb.append(charr[r.nextInt(charr.length)]);
        }
        return sb.toString();
    }



}
