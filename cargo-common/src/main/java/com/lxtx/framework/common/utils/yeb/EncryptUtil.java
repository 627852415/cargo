package com.lxtx.framework.common.utils.yeb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

@Slf4j
public class EncryptUtil {

    public static String HMAC_SHA256 = "HmacSHA256";
    public static String UTF8 = "UTF-8";

    /**
     * 资金托管api模块签名过程
     * @param message
     * @param key
     * @return
     */
    public static String hmacSha256(String message, String key){
        try{
            Mac mac = Mac.getInstance(HMAC_SHA256);
            byte[] keyBytes = key.getBytes(Charset.forName(UTF8));
            log.info("签名KEY："+byteArrayToHexString(keyBytes));
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] messageBytes = message.getBytes(Charset.forName(UTF8));
            log.info("待签名数据："+message);
            byte[] bytes = mac.doFinal(messageBytes);
            return byteArrayToHexString(bytes);
        }catch (Exception e){
            log.error(HMAC_SHA256+"加密失败：", e);
        }
        return "";
    }

    /**
     * 资金托管otc模块签名过程
     * @param message
     * @param key
     * @return
     */
    public static String hmacSha256ForOtc(String message, String key){
        try{
            Mac mac = Mac.getInstance(HMAC_SHA256);
            byte[] keyBytes = key.getBytes(Charset.forName(UTF8));
            log.info("签名KEY："+byteArrayToHexString(keyBytes));
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] messageBytes = message.getBytes(Charset.forName(UTF8));
            log.info("待签名数据："+message);
            byte[] bytes = mac.doFinal(messageBytes);
            return byteArrayToUpperHexString(bytes);
        }catch (Exception e){
            log.error(HMAC_SHA256+"加密失败：", e);
        }
        return "";
    }

    /**
     * 资金托管otc模块签名过程
     * @param message
     * @param key
     * @return
     */
    public static String hmacSha256ForWallet(String message, String key){
        try{
            Mac mac = Mac.getInstance(HMAC_SHA256);
            byte[] keyBytes = toByteArray(key);
            log.info("签名KEY："+byteArrayToHexString(keyBytes));
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] messageBytes = message.getBytes(Charset.forName(UTF8));
            log.info("待签名数据："+message);
            byte[] bytes = mac.doFinal(messageBytes);
            return byteArrayToUpperHexString(bytes);
        }catch (Exception e){
            log.error(HMAC_SHA256+"加密失败：", e);
        }
        return "";
    }

    public static void main(String[] args){
        String msg = "adb";
        String seed = "B912909B2838614E464180CD28CC5739A2E6210747B9431926F732516BF1CE74";
        String result = hmacSha256ForWallet(msg, seed);
        System.out.println("msg: "+msg);
        System.out.println("seed: "+seed);
        System.out.println("result: "+result);



    }




    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1){
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    public static String byteArrayToUpperHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1){
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    public static byte[] toByteArray(String hex) {
        if (StringUtils.isEmpty(hex) || hex.length()%2==1)
            return null;
        byte[] output = new byte[hex.length() / 2];
        for (int i = 0; i < output.length; i++) {
            String data = hex.substring(i*2, i*2+2);
            Integer value =Integer.valueOf(data, 16);
            output[i] = (byte)(value & 0xff);
        }

        return output;
    }
}
