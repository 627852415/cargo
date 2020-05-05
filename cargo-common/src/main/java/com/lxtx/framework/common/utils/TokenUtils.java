package com.lxtx.framework.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * <p>
 * 生成token令牌的工具类
 * </p>
 *
 * @author liboyan
 * @Date 2018-11-11 22:15
 * @Description
 */
public class TokenUtils {
    /**
     * 签名秘钥
     */
    private static final String SECRET = "lxtx-im";
    /**
     * JWT的签发者
     * */
    private static final String ISSUER = "api.secret.one";
    /**
     * JWT所面向的用户
     * */
    private static final String SUBJECT = "secret@163.com";
    /***
     * 默认 有效时间
     * */
    private static final long TTLMILLIS = 3600000;

    /**
     * 生成Token
     *
     * @param uid        编号
     * @param ttlMillis 签发时间 （有效时间，过期会报错）
     * @return token String
     */
    public static String createJwtToken(String uid,  long ttlMillis) {
        if(StringUtils.isBlank(uid)){
            return null;
        }
        if (ttlMillis == 0){
            ttlMillis = TTLMILLIS;
        }

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(uid)
                .setIssuedAt(now)
                .setSubject(SUBJECT)
                .setIssuer(ISSUER)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    /**
     * 功能描述:
     *
     * @param uid
     * @return String
     * @author liboyan
     * @date 2018-11-11 22:26
     */
    public static String createJwtToken(String uid) {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(uid)
                .setIssuedAt(new Date())
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();

    }

    /**
     * 功能描述: 解析token
     *
     * @param
     * @return
     * @author liboyan
     * @date 2018-11-11 22:19
     */
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args) {
        long ttlMillis = 3600000;

        String jwtToken = TokenUtils.createJwtToken("111111", ttlMillis);
/*        System.out.println(jwtToken);
        Claims claims = TokenUtils.parseJWT(jwtToken);
        System.out.println(claims);*/


        jwtToken = TokenUtils.createJwtToken("1041875495441993730");
        System.out.println(jwtToken);
        Claims claims2 = TokenUtils.parseJWT(jwtToken);
        System.out.println(claims2);
    }
}
