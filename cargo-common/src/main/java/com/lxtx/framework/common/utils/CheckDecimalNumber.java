package com.lxtx.framework.common.utils;

import java.math.BigDecimal;

/**
 * @author ww
 */
public class CheckDecimalNumber {

    /**
     * 判断是否是符合的位数（num）
     * @param bigDecimal
     * @param num
     * @return
     */
    public static Boolean getFlagCheckNumber(BigDecimal bigDecimal , Integer num){
        String s = bigDecimal.toPlainString();
        if(s.indexOf(".") == -1){
           return true;
        }else{
            String[] split = s.split("\\.");
            if(split[1].length() <= num){
                return true;
            }else{
                return false;
            }
        }
    }
}
