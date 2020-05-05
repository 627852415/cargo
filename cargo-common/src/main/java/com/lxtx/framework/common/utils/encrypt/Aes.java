package com.lxtx.framework.common.utils.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

/**
 * @author zhiHua chen
 * Created  data on 2018/08/03
 */
@Slf4j
public class Aes {

    private static boolean initialized = false;
    public static final String AES_TYPE = "AES/CBC/PKCS7Padding";
    public static final String AES_KEY = "AES";


    private Aes() {

    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @param iv
     * @return
     */
    public static String encrypt(String key, String data, String iv) throws Exception{
        byte[] dataByte = data.getBytes();
        byte[] result = encryptOrDecrypt(key, dataByte, iv, Cipher.ENCRYPT_MODE);
        return new String(Base64.encodeBase64(result));

    }

    /**
     * AES解密
     *
     * @param data
     * @param key
     * @param iv
     * @return
     * @throws InvalidAlgorithmParameterException
     */
    public static String decrypt(String key, String data, String iv) throws Exception {
        byte[] dataByte = Base64.decodeBase64(data);
        return new String(encryptOrDecrypt(key, dataByte, iv, Cipher.DECRYPT_MODE), "UTF-8");

    }

    /**
     * 加密或者解密
     *
     * @param key
     * @param iv
     * @param data
     * @param cipherMode
     * @return
     * @throws Exception
     */
    public static byte[] encryptOrDecrypt(String key, byte[] data, String iv, int cipherMode) throws Exception{
        initialize();
        byte[] keyByte = Base64.decodeBase64(key);
        byte[] ivByte = Base64.decodeBase64(iv);
        Cipher cipher = Cipher.getInstance(AES_TYPE);

        Key sKeySpec = new SecretKeySpec(keyByte, AES_KEY);
        // 初始化
        cipher.init(cipherMode, sKeySpec, generateIV(ivByte));
        return cipher.doFinal(data);
    }

    /**
     * 生成iv
     *
     * @param iv
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidParameterSpecException
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws NoSuchAlgorithmException, InvalidParameterSpecException {
        AlgorithmParameters params = AlgorithmParameters.getInstance(AES_KEY);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
      //  Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    /**
     * 生成密钥
     *
     * @param length
     * @return
     * @throws Exception
     */
    public static String generateAesKey(int length) throws Exception {
        //实例化
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        //设置密钥长度
        kg.init(length);
        //生成密钥
        SecretKey key = kg.generateKey();
        //返回密钥的二进制编码
        return Base64.encodeBase64String(key.getEncoded());
    }


    public static void main(String[] args) throws Exception {

        String data = "{\"openId\":\"oR2Qb5OUv8O0gOXH8yQtgkZup3BE\",\"nickName\":\"志华\",\"gender\":1,\"language\":\"en\",\"city\":\"\",\"province\":\"Carlow\",\"country\":\"Ireland\",\"avatarUrl\":\"https://wx.qlogo.cn/mmopen/vi_32/JVqb7na5AAQD7Sx8Pdd0PPudzfB3CVM1jUW1qYY57YzQUIkyeu9A18TWXiaQicxjCziaa6jekd7b9RWyag0ibia4M1Q/0\",\"watermark\":{\"timestamp\":1517975623,\"appid\":\"wx4458b597c672ded1\"}}";
//        String key = "bHh0eGRvc3Bkb3NsZGFhYQ==";
//        String iv = "NSnaTP5TTT9/bMNI3N/DIQ==";
        String key = generateAesKey(128);
        String iv = generateAesKey(128);

        System.out.println("key:"+ key + ",iv:" + iv);
        String encrypt = encrypt(key, data, iv);
        System.out.println("密文:" + encrypt);
        String decrypt = decrypt(key, encrypt, iv);
        System.out.println("明文:" + decrypt);

        System.out.println(encrypt(key,data,iv));
    }

}
