package com.lxtx.framework.common.utils.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhiHua chen
 * Created  data on 2018/08/04
 */
@Slf4j
public class Rsa {

    private Rsa() {
    }

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";


    /**
     * 创建密钥对
     *
     * @param keySize
     * @return
     */
    public static Map<String, String> createKeys(int keySize) {
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>(3);
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     *
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 私钥加密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String privateEncrypt(String data, String privateKey) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {

        byte[] bytes = privateEncryptOrDecrypt(data.getBytes(CHARSET), getPrivateKey(privateKey), Cipher.ENCRYPT_MODE);
        return Base64.encodeBase64URLSafeString(bytes);
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String privateDecrypt(String data, String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, UnsupportedEncodingException {

        byte[] bytes = privateEncryptOrDecrypt(Base64.decodeBase64(data), getPrivateKey(privateKey), Cipher.DECRYPT_MODE);
        return new String(bytes, CHARSET);
    }

    /**
     * 私钥加密或者解密
     *
     * @param data
     * @param privateKey
     * @param opMode
     * @return
     */
    public static byte[] privateEncryptOrDecrypt(byte[] data, RSAPrivateKey privateKey, int opMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(opMode, privateKey);
        return rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, data, privateKey.getModulus().bitLength());
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, String publicKey) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        byte[] bytes = publicEncryptOrDecrypt(data.getBytes(CHARSET), getPublicKey(publicKey), Cipher.ENCRYPT_MODE);
        return Base64.encodeBase64URLSafeString(bytes);
    }

    /**
     * 公钥解密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicDecrypt(String data, String publicKey) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        byte[] bytes = publicEncryptOrDecrypt(Base64.decodeBase64(data), getPublicKey(publicKey), Cipher.DECRYPT_MODE);
        return new String(bytes, CHARSET);
    }

    /**
     * 公钥加密或者解密
     *
     * @param data
     * @param publicKey
     * @param opMode
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] publicEncryptOrDecrypt(byte[] data, RSAPublicKey publicKey, int opMode) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(opMode, publicKey);
        return rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, data, publicKey.getModulus().bitLength());
    }

    /**
     * rsaSplitCodec
     *
     * @param cipher
     * @param opMode
     * @param data
     * @param keySize
     * @return
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opMode, byte[] data, int keySize) {
        int maxBlock = 0;
        if (opMode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (data.length > offSet) {
                if (data.length - offSet > maxBlock) {
                    buff = cipher.doFinal(data, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(data, offSet, data.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultData = out.toByteArray();
        try {
            out.close();
        } catch (IOException e) {
            log.error("IOException",e);
        }

        return resultData;
    }

//    public static void main(String[] args) throws Exception {
//        // Map<String, String> keyMap = Rsa.createKeys(2048);
//        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArCzEH1cCY0kBpcsWlslS5RaGjH1tmout5kYpuByWruPu61c2g+ZD1aSp0bGdU/XC929AeA+BtS+B8lHpgc3HvLw8l2AKANq66rChgWgfkjE9UZr2krQmM61sOn1Eritgwihmmb2JK5M3iH1E9wgrAsXmNvqAu7P8dvPoRSPrzqIMHTbh6XHC/H/3tJIrCafCmNRmPRo1fz1RdKLzPFaVQtPxTQNR1eC0QtJl0lXUeg0Aol3Z3kVVBwdSfVBp5gyyq+KawHCJ2eZTQ1gsGHSbB5qcsAKi1N3Ysk/3onp74WnYcdE1stisza6I5VotX9MU2sn7vTQDCSFO9yMJbz1iJQIDAQAB";
//        String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCsLMQfVwJjSQGlyxaWyVLlFoaMfW2ai63mRim4HJau4+7rVzaD5kPVpKnRsZ1T9cL3b0B4D4G1L4HyUemBzce8vDyXYAoA2rrqsKGBaB+SMT1RmvaStCYzrWw6fUSuK2DCKGaZvYkrkzeIfUT3CCsCxeY2+oC7s/x28+hFI+vOogwdNuHpccL8f/e0kisJp8KY1GY9GjV/PVF0ovM8VpVC0/FNA1HV4LRC0mXSVdR6DQCiXdneRVUHB1J9UGnmDLKr4prAcInZ5lNDWCwYdJsHmpywAqLU3diyT/eienvhadhx0TWy2KzNrojlWi1f0xTayfu9NAMJIU73IwlvPWIlAgMBAAECggEAHHhmkdwca/iFijnT6WkvCOy5oP+YoSaZwes2ONiZXiAYwNeyDwuDig9peWBKaLjJ3YqK+Y9u3lwVu9EpsKorbh0eU9WwscV5hRdiDXj/R3hW8GiUf1vEuWTi8aUHN1m67UAq/x8lUIxOcYZtYf/6xMGeuNahdOhlRfI9JTm0Y32I2+wBJkXPFaids7Pubh/pT71FkKrJ6r55rAk1VpPkKQDcnJICRQ+3qTxVEKckCshMIYCfmIwAttl8tmjghbypHHWi8xJuXAC10RzVy4QaIF1k7MipIaR0AO2Qi0Oh4G3gk1psznGax01oZCa/MByOhqKbt1+WAhDCMma9UX4+vQKBgQDVjylWCXIFMOA4Z84K8hlEDqLmknSTCfrbo8X+5HB2EQ7UnVuWihkxdawYYMJsunXcumMMPjEY0snX8m1N0P+9QiRWXsujGpOJqUZKonj1bc38HLOh9R9kMxhlifuPIJ63S6zUPP8HY37IwqO61k9vwcPMGSetVA3bDftlydFpewKBgQDOZCxHJlk9WOs8tKTklb3M56Ga7cA+YR+te5BoaDeCLSOu4JZBKiQMQ23TPk88taMeekgGFGg1PSQkTFf/LVScL9+W1PAbJ/JvOaxsTqdMY+E+Ose9zUHUZXp7nGCpJK0m7l5k+7XyZ1LihGO8brtnOaTgJo6O4fb7K8XG1q+A3wKBgQCr4AlrpjRu+xQqMVlBs/EjtgfjPBG4WXrdFKUAbMgaLcZa1b5lnQWDqen0wMMZkMPU/ulCbDdFfDhYpVLVIxUAM+Bc5vRLc9M0Itlgrfxdwp7afhsQiOBX20uSRQgB/EXgzXXuZpz2TFw4pXXypBLQpgi4FbK6LADuwQUIufHhSwKBgQCOzuuAU72d4D/s4kXSUPZiqMe+btH9l7CWlpAmXi5Cab9uCsKM1n7Y6XM9nrivFjOHLC9X3Zj5dZPjddvRmmkYJVv9ftugvrRWV77Btm62QcoiCAQbhcIOGCV3EI4lm1YgQQGZuD1+PBYy3E4ynSAY3d8Cpksv/JSr2O7hlb0cWwKBgQCn4JQ9s23+oxYl0rtGVvDJW2Nt6pMPS2LpaGe8BonktSxwdymKzI5bRLFFDXxNqLrviDbRPcPwWQug4xOvgkimDWM4sCuNU6WZ6BldlLG6NLeqjMT7eldlaqN8k70MawL3jFNYFJC78ne1TP9sZQUAX8+qczt97HvLnkcRAl/a4Q==";
//        System.out.println("公钥: \n\r" + publicKey);
//        System.out.println("私钥： \n\r" + privateKey);
//        System.out.println("公钥加密——私钥解密");
//        String str = "2018年4月13日-RSA加密算法是一种非对称加密算法。在公开密钥加密和电子商业中RSA被广泛使用。";
//        System.out.println("\r明文：\r\n" + str);
//        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
//        String encodedData = Rsa.publicEncrypt(str, publicKey);
//        System.out.println("密文：\r\n" + encodedData);
//        String decodedData = Rsa.privateDecrypt(encodedData, privateKey);
//        System.out.println("解密后文字: \r\n" + decodedData);
//        //支付宝测试
//        String aliContent = "PVG9g4JmiCBkUq+s/NZo3MkgVzl6YPUE2zE4ZbLkiNhUEcgfQlEihzHJr6XpQZeafrzqK3uIRo9/Ij+sVMVxfLJhDGAka/dq9LAgT09FNxxf5uM1FTEfeCfmMrk3ansU25FCbljQQWq+Jh9j/bm8+l6v12kuAKrFYb8/jPzTrXNSMuqUeVfrVp0jrq2TWhbT4QpPZqL/0GU7iXtX2dmXpH4ujoYEpQmHKfQeto1ALcoksqEqmfN+WPcPYSZwDIuUwGtO9wXe8L0Eixo6A72ZCuhIIrvNMI6/XY5+ohIfJltATJjiAjb2eU98baG9kQQ7WhAVhhDg0VqiA8a960vXWA==";
//        System.out.println("支付宝测试解密后文字: \r\n" + Rsa.privateDecrypt(aliContent, privateKey));
//    }
}
