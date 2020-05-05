package com.lxtx.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 *  银行卡工具类
 * @Author LiuLP on 2018/10/24.
 */
public class BankCardUtils {
    /*
    校验过程：
    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    */
    /**
     * 校验银行卡卡号
     */
    public static boolean checkBankCard(String bankCard) {
        if(bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeBankCard
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard){
        if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }
    
    /**
     * 方法描述 隐藏银行卡号中间的字符串（使用*号），显示前四后四
     * 每四位显示空格
     *
     * @param cardNo
     * @return
     */
    public static String hideCardNo(String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            return cardNo;
        }

        int length = cardNo.length();
        int beforeLength = 4;
        int afterLength = 4;
        // 替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i < beforeLength || i >= (length - afterLength)) {
                sb.append(cardNo.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }

            // 添加空格
            if (i % 5 == 0) {
                sb.insert(i, " ");
            }
        }

        return sb.toString().trim();
    }
    
    /**
     * 方法描述 每四位显示空格
     *
     * @param cardNo
     * @return
     */
    public static String cardNoSpace(String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            return cardNo;
        }

        int length = cardNo.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
             sb.append(cardNo.charAt(i));

            // 添加空格
            if (i % 5 == 0) {
                sb.insert(i, " ");
            }
        }

        return sb.toString().trim();
    }
}
