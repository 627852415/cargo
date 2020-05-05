package com.lxtx.framework.common.utils.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author tangdy
 * Created  data on 2018/08/03
 */
@Slf4j
public class EncryptUtils {

    /**
     * AES加密偏移量,可自行修改
     */
    private static final String AES_IV_PARAMETER = "0000000000000000";
    /**
     * AES加密transformation
     */
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    /**
     * AES加密key
     */
    private static final String AES_KEY = "AES";

    /**
     * key
     */
    private static final String ENCRYPT_KEY = "4db5705a409d6ed6235ed36ba66f4d4d";

    /**
     * AES加密
     */
    public static String aesEncrypt(String content) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(ENCRYPT_KEY.getBytes(), AES_KEY);
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV_PARAMETER.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * AES 解密
     */
    public static String aesDecrypt(String content) throws Exception {
        byte[] raw = ENCRYPT_KEY.getBytes(StandardCharsets.US_ASCII);
        SecretKeySpec keySpec = new SecretKeySpec(raw, AES_KEY);
        IvParameterSpec iv = new IvParameterSpec(AES_IV_PARAMETER.getBytes());
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(content));
        return new String(bytes);

    }

    public static void main(String[] args) throws Exception {
        String s = aesEncrypt("00");
        System.out.println(s);
        String s1 = aesDecrypt(s);
        System.out.println(s1);

    }
}
