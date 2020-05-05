package com.lxtx.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author CaiRH
 */
public class HideDataUtil {
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
     * 方法描述
     * 小于等于4位，不管
     * 如果小于12位，那就只显示后面4位，前面的全部打码，超出12位后，前4+后4，中间打码
     * @param phoneNo
     * @return
     */
    public static String hideSplitCardNoNew(String phoneNo) {
        if (StringUtils.isBlank(phoneNo) || phoneNo.length() <= 4) {
            return phoneNo;
        }
        String removePhoneNo = null;
        int length = phoneNo.length();
        if( length <= 12){
            removePhoneNo = StringUtils.leftPad(StringUtils.right(phoneNo, 4), StringUtils.length(phoneNo), "*");
        }else {
            int beforeLength = 4;
            int afterLength = 4;
            // 替换字符串，当前使用“*”
            String replaceSymbol = "*";
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                if (i < beforeLength || i >= (length - afterLength)) {
                    sb.append(phoneNo.charAt(i));
                } else {
                    sb.append(replaceSymbol);
                }
            }
            removePhoneNo = sb.toString();
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(removePhoneNo.charAt(i));
            // 添加空格
            if ((i+1) % 4 == 0) {
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }


    /**
     * 方法描述 隐藏手机号中间位置字符，显示前三后三个字符
     *
     * @param phoneNo
     * @return
     */
    public static String hidePhoneNo(String phoneNo) {
        if (StringUtils.isBlank(phoneNo)) {
            return phoneNo;
        }

        int length = phoneNo.length();
        int beforeLength = 3;
        int afterLength = 3;
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i < beforeLength || i >= (length - afterLength)) {
                sb.append(phoneNo.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
    }

    /**
     * 方法描述
     * 每四位显示空格
     *
     * @param cardNo
     * @return
     */
    public static String splitCardNo(String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            return cardNo;
        }

        int length = cardNo.length();
        int beforeLength = 4;
        int afterLength = 4;
        String replaceSymbol = "*";
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



    public static void main(String[] args) {
//        System.out.println("|" + splitCardNo("1232111122223111") + "|");
        String cardNo = "1234567890123";
        System.out.println(HideDataUtil.hideSplitCardNoNew(cardNo));
    }
}