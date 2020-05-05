package com.lxtx.framework.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.lxtx.framework.common.utils.http.method.Post;
import com.lxtx.framework.common.utils.http.soa.HttpInvoker;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by shangyu on 21/3/16.
 */
@Slf4j
public class PhoneUtils {
    /*******************    253 云通讯平台     *********************/
    private static final String yunBaseUrl = "http://intapi.253.com/validate";
    private static final String yunAccount = "I7630452";
    private static final String yunPassword = "rvuZ0sb3nMab70";

    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    private static final String International_Code = "00";
    private static final String International_Plus = "+";

    private static final String Singapore_International_Code = "0065";
    private static final String[] Singapore_Prefix_Without_65 = new String[]{"8", "9"};
    private static final String[] Singapore_Prefix_With_65 = new String[]{"658", "659"};

    private static final String China_International_Code = "0086";
    private static final String[] China_Prefix_Without_86 = new String[]{"13", "14", "15", "17", "18"};
    private static final String[] China_Prefix_With_86 = new String[]{"8613", "8614", "8615", "8617", "8618"};

    private static final String Malaysia_International_Code = "0060";
    private static final String[] Malaysia_11_Digit_Prefix_Without_60 = new String[]{"011", "015"};
    private static final String[] Malaysia_10_Digit_Prefix_Without_60 = new String[]{"010", "012", "013", "014", "016", "017", "018", "019"};
    private static final String[] Malaysia_12_Digit_Prefix_With_60 = new String[]{"6011", "6015"};
    private static final String[] Malaysia_11_Digit_Prefix_With_60 = new String[]{"6010", "6012", "6013", "6014", "6016", "6017", "6018", "6019"};

    private static final String Thailand_International_Code = "0066";
    private static final String[] Thailand_Prefix_Without_66 = new String[]{"06", "08", "09"};
    private static final String[] Thailand_Prefix_With_66 = new String[]{"666", "668", "669"};

    private static final String Vietnam_International_Code = "0084";
    private static final String[] Vietnam_11_Digit_Prefix_Without_84 = new String[]{"012", "0162", "0163", "0164", "0165", "0166", "0167", "0168", "0169", "0186", "0188", "0199"};
    private static final String[] Vietnam_10_Digit_Prefix_Without_84 = new String[]{"0868", "088", "089", "09"};
    private static final String[] Vietnam_12_Digit_Prefix_With_84 = new String[]{"8412", "84162", "84163", "84164", "84165", "84166", "84167", "84168", "84169", "84186", "84188", "84199"};
    private static final String[] Vietnam_11_Digit_Prefix_With_84 = new String[]{"84868", "8488", "8489", "849"};

    private static final String Indonesia_International_Code = "0062";
    private static final String[] Indonesia_Prefix_Without_62 = new String[]{"08"};
    private static final String[] Indonesia_Prefix_With_62 = new String[]{"628"};

    public static String[] isValidMobile(String countryCode, String phone) {
        try {
            if ("LAO".equalsIgnoreCase(countryCode)){
                countryCode = "LA";
            }
            Phonenumber.PhoneNumber mobileProto = phoneUtil.parse(phone, countryCode);
//            // 更新校验手机号码是否通过方法
//            if (validateMobileProto(mobileProto)) {
//                return new String[]{"" + mobileProto.getCountryCode(), "" + mobileProto.getNationalNumber()};
//            }
            //Google lib校验号码是否正确,中国号码校验，国际号码忽略
            if (mobileProto.getCountryCode() != 86 || phoneUtil.isValidNumber(mobileProto)) {
                return new String[]{"" + mobileProto.getCountryCode(), "" + mobileProto.getNationalNumber()};
            }
        } catch (NumberParseException e) {
            log.error(e.getMessage(),e);
        }

        return null;
    }

    public static String toSMSGateway(String mobileCode, String phone) {
        return International_Code + mobileCode + phone;
    }

    public static String toInternationalPhone(String mobileCode, String phone){
        return International_Plus + mobileCode + phone;
    }

    public static String toSMSGateway(String fullPhone) {
        return International_Code + fullPhone;
    }

    public static String randomToken(int noOfDigits) {
        int bound = (int) Math.pow(10, noOfDigits) - 1;

        return String.format("%0" + noOfDigits + "d", new Random().nextInt(bound));
    }

    public static String formatMobileNumber(String mobileNumber) {
        String result = mobileNumber.replaceAll("[^\\d]", "");

        if (result.startsWith(International_Code)) {
            return result;
        }

        int length = result.length();

        // Singapore mobile number
        if (length == 8 && startsWith(result, Singapore_Prefix_Without_65)) {
            return Singapore_International_Code + result;
        }
        if (length == 10 && startsWith(result, Singapore_Prefix_With_65)) {
            return International_Code + result;
        }

        // China mobile number
        if (length == 11 && startsWith(result, China_Prefix_Without_86)) {
            return China_International_Code + result;
        }
        if (length == 13 && startsWith(result, China_Prefix_With_86)) {
            return International_Code + result;
        }

        // Malaysia mobile number
        if (length == 11 && startsWith(result, Malaysia_11_Digit_Prefix_Without_60)) {
            return Malaysia_International_Code + result.substring(1, length);
        }
        if (length == 10 && startsWith(result, Malaysia_10_Digit_Prefix_Without_60)) {
            return Malaysia_International_Code + result.substring(1, length);
        }
        if (length == 12 && startsWith(result, Malaysia_12_Digit_Prefix_With_60)) {
            return International_Code + result;
        }
        if (length == 11 && startsWith(result, Malaysia_11_Digit_Prefix_With_60)) {
            return International_Code + result;
        }

        // Thailand mobile number
        if (length == 10 && startsWith(result, Thailand_Prefix_Without_66)) {
            return Thailand_International_Code + result.substring(1, length);
        }
        if (length == 11 && startsWith(result, Thailand_Prefix_With_66)) {
            return International_Code + result;
        }

        // Vietnam mobile number
        if (length == 11 && startsWith(result, Vietnam_11_Digit_Prefix_Without_84)) {
            return Vietnam_International_Code + result.substring(1, length);
        }
        if (length == 10 && startsWith(result, Vietnam_10_Digit_Prefix_Without_84)) {
            return Vietnam_International_Code + result.substring(1, length);
        }
        if (length == 12 && startsWith(result, Vietnam_12_Digit_Prefix_With_84)) {
            return International_Code + result;
        }
        if (length == 11 && startsWith(result, Vietnam_11_Digit_Prefix_With_84)) {
            return International_Code + result;
        }

        // Indonesia mobile number
        if (within(length, new int[]{10, 11, 12}) && startsWith(result, Indonesia_Prefix_Without_62)) {
            return Vietnam_International_Code + result.substring(1, length);
        }
        if (within(length, new int[]{11, 12, 13}) && startsWith(result, Indonesia_Prefix_With_62)) {
            return International_Code + result;
        }

        return null;
    }

    private static boolean startsWith(String number, String[] starts) {
        for (String start : starts) {
            if (number.startsWith(start)) {
                return true;
            }
        }

        return false;
    }

    private static boolean within(int length, int[] range) {
        for (int r : range) {
            if (length == r) {
                return true;
            }
        }

        return false;
    }

    /**
     * 253 云通讯平台手机号码校验接口
     * @param mobileProto
     * @return
     */
    private static boolean validateMobileProto(Phonenumber.PhoneNumber mobileProto){
        String countryCode = String.valueOf(mobileProto.getCountryCode());
        Post post = new Post(yunBaseUrl);
        post.setUrlParam("account", yunAccount);
        post.setUrlParam("password", yunPassword);
        post.setUrlParam("mobile", countryCode + String.valueOf(mobileProto.getNationalNumber()));
        String body = HttpInvoker.execute(post).getBody();
        JSONObject object = JSONObject.parseObject(body);
        Integer status = Integer.parseInt(String.valueOf(object.get("status")));
        if (status == 0) {
            return true;
        }
        return false;
    }

    public static String middlePhoneNum(String phone){
        if(phone.length() < 3){
            return phone;
        }
        int phoneLength = phone.length();
        //显示前面的位数
        int startText = phoneLength / 3;
        String startNum = phone.substring(0,startText);

        //显示后面位数
        int endText = startText + 1;
        String endNum = phone.substring(phoneLength - endText);
        //显示*的位数
        int middleText = phoneLength - startText - endText;
        StringBuilder builder = new StringBuilder();
        for(int i =0;i < middleText; i++){
            builder.append("*");
        }
        return startNum + builder.toString() + endNum;
    }

    /**
     *  86 --> CN
     * @param mobileCode
     * @return
     */
    public static String toCountryCode(String mobileCode){

       return phoneUtil.getRegionCodeForCountryCode(Integer.valueOf(mobileCode));
    }

    /**
     *  CN --> 86
     * @param mobileCode
     * @return
     */
    public static String toCountryCodeForRegion(String mobileCode){

       return phoneUtil.getRegionCodeForCountryCode(Integer.valueOf(mobileCode));
    }

    public static void main(String[] args) {
//        isValidMobile("PH", "639455242750");
        isValidMobile("CN", "13800138000");
        System.out.println(middlePhoneNum("159"));
    }
}
