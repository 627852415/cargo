package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Maps;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.framework.common.utils.yeb.EncryptUtil;
import com.lxtx.im.admin.dao.model.SysUser;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.WebUsbTokenService;
import com.lxtx.im.admin.service.request.UsbTokenReq;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WebUsbTokenServiceImpl implements WebUsbTokenService {


    @Autowired
    private DictService dictService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUserService sysUserService;

    private static final String UKEY_REDIS_PRE="ADMIN_UKEY_REDIS:";

    @Override
    public BaseResult isOpenUsbToken() {
        Map<String,Object> rs = Maps.newHashMap();
        Boolean isOpenUsbMode = Boolean.valueOf(dictService.getDictValue(DictConstants.DICT_DOMAIN_GLOBAL, DictConstants.DICT_KEY_USB_TOKEN_SWITCH));
        rs.put("openUsbMode",isOpenUsbMode);
        String token =  UUID.randomUUID().toString();
        rs.put("token", token);
        setCacheObject(UKEY_REDIS_PRE+token, token, 30);
        return BaseResult.success(rs);
    }

    @Override
    public BaseResult verifyToken(UsbTokenReq dto)  {
        //redis验证token
        String redisTokenKey = UKEY_REDIS_PRE+dto.getToken();
        if( StringUtils.isNotEmpty(dto.getToken())&&
                getCacheObject(redisTokenKey)!=null&&
                getCacheObject(redisTokenKey).toString().equals(redisTokenKey)){
            log.info("usbtoken验证，随机token校验失败，UsbTokenReq:{}", JSON.toJSONString(dto));
            return BaseResult.success(false);
        }

        String ca = dto.getCa();
        StringBuffer sb = new StringBuffer();
        // 验证当前证书是否有效
        X509Certificate root = null;
        try{
            root = loadCurrCert(ca);
            sb.append("根证书公钥："+root.getPublicKey().toString()+"\n");
        }catch (Exception e){
            log.error("加载根证书失败：", e);
            sb.append("加载根证书失败："+e.getLocalizedMessage()+"\n");
            log.info(sb.toString());
            return BaseResult.success(false);
        }

        X509Certificate curr = null;
        try{
            curr = loadCurrCert(dto.getCert());
            sb.append("当前证书信息："+curr.getSubjectDN().getName()+"\n");
        }catch (Exception e){
            log.error("加载当前证书失败：", e);
            sb.append("加载当前证书失败："+e.getLocalizedMessage()+"\n");
            log.info(sb.toString());
            return BaseResult.success(false);
        }

        try{
            curr.verify(root.getPublicKey());
            sb.append("使用根证书验签成功"+"\n");
        }catch (Exception e){
            log.error("使用根证书验签接收到的证书数据失败：", e);
            sb.append("使用根证书验签失败："+e.getLocalizedMessage()+"\n");
            log.info(sb.toString());
            return BaseResult.success(false);
        }
        try {
            // 对数据验签
            Signature  signature = Signature.getInstance("SHA256WithRSA");
            Principal principal = curr.getSubjectDN();
            String principalName =  principal.getName();//CN=COMM.TOKEN.222

            //校验用户编号
            String principleShortName = principalName.split(",")[0];
            log.info("校验用户编号，X509Certificate.getSubjectDN.principal:{}", principleShortName);
            if (StringUtils.isBlank(principleShortName)) {
                return BaseResult.success(false);
            }
            SysUser sysUser = null;
            if(StringUtils.isNotEmpty(dto.getUserName())){
                SysUser sysUser1 = new SysUser();
                sysUser1.setUsername(dto.getUserName());
                sysUser = sysUserService.selectOne(sysUser1);
            }else{
                sysUser = sysUserService.selectById(ShiroUtils.getUserEntity().getUserId());
            }

            if(sysUser==null){
                log.info("usbtoken验证，用户不存在", JSONObject.toJSONString(dto));
                return BaseResult.success(false);
            }

            if (StringUtils.isBlank(sysUser.getUsbTokenNo())) {
                log.info("usbtoken验证，usbToken为空，用户信息:{}", JSONObject.toJSONString(sysUser));
                return BaseResult.success(false);
            }

            if (principleShortName.lastIndexOf(sysUser.getUsbTokenNo()) == -1) {
                log.info("usbtoken验证，usbToken编号校验出错，用户token:{},当前token:{}", sysUser.getUsbTokenNo(),principleShortName);
                return BaseResult.success(false);
            }
            log.info("校验IP={}",dto.getIp());
            //校验IP
            if(StringUtils.isEmpty(dto.getIp())||!dto.getIp().equals(sysUser.getIp())){
                log.info("usbtoken验证，IP校验不通过，用户信息:{},用户PC机IP:{}", JSONObject.toJSONString(sysUser),dto.getIp());
                return BaseResult.success(false);
            }

            signature.initVerify(curr.getPublicKey());
            signature.update(dto.getTime().getBytes(Charset.forName("utf-8")));
            boolean verify = signature.verify(EncryptUtil.toByteArray(dto.getSign()));
            if(verify){
                redisTemplate.delete(redisTokenKey);
                sb.append("当前证书验签数据成功"+"\n");
                return BaseResult.success(true);
            }else {
                sb.append("当前证书验签数据失败"+"\n");
                log.info("当前证书验签数据失败:"+sb.toString());
            }
            log.info(sb.toString());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            log.info("数据校签失败",e);
        }
        return BaseResult.success(false);
    }

    private static X509Certificate loadCurrCert(String cer) throws CertificateException {
        InputStream stream = new ByteArrayInputStream(EncryptUtil.toByteArray(cer));
        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        return (X509Certificate)factory.generateCertificate(stream);
    }

    public <T> ValueOperations<String, T> setCacheObject(String key, T value, int expire) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, expire, TimeUnit.SECONDS);
        return operation;
    }

    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

}
