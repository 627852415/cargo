package com.lxtx.framework.common.utils.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zhiHua chen
 * Created  data on 2018/08/03
 */
@Slf4j
public class Sha256Hmac {

    public static final String HMAC_SHA_256_KEY = "HmacSHA256";

    private Sha256Hmac() {

    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {

        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256HMAC(String message, String secret) {

        String hash = StringUtils.EMPTY;
        try {
            Mac sha = Mac.getInstance(HMAC_SHA_256_KEY);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), HMAC_SHA_256_KEY);
            sha.init(secretKeySpec);
            byte[] bytes = sha.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            log.error("Error HmacSHA256 ==========={}", e.getMessage());
        }
        return hash;
    }

}
