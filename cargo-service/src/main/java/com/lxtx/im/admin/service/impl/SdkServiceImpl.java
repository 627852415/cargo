package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.SdkFeign;
import com.lxtx.im.admin.feign.feign.SdkWalletFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.SdkService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.SdkGameOrderResp;
import com.lxtx.im.admin.service.response.SdkOrderPageResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* @description:  sdk实现类
* @author:   CXM
* @create:   2018-11-30 11:30
*/
@Service
@Slf4j
public class SdkServiceImpl implements SdkService {
    @Resource
    private SdkFeign sdkFeign;
    @Resource
    private SdkWalletFeign sdkWalletFeign;
    @Resource
    private UserFeign userFeign;
    @Autowired
    private QiNiuUtil qiNiuUtil;

    @Override
    public BaseResult listPage(SdkThirdGameListReq req) {
        FeignSdkThirdGameListReq feignReq = new FeignSdkThirdGameListReq();
        BeanUtils.copyProperties(req, feignReq);
        return sdkFeign.listPage(feignReq);
    }

    /**
     * 获取订单列表
     * @param req
     * @return
     */
    @Override
    public BaseResult orderListPage(SdkThirdGameOrderListReq req) {
        FeignSdkThirdGameOrderListReq listReq = new FeignSdkThirdGameOrderListReq();
        BeanUtils.copyProperties(req, listReq);
        BaseResult baseResult = sdkWalletFeign.orderListPage(listReq);
        if (!baseResult.isSuccess() || baseResult.getData() == null) {
            return baseResult;
        }
        return setOrderUserName(baseResult);
    }

    private BaseResult setOrderUserName(BaseResult baseResult) {
        //将转出账号ID和转入账号ID设置成对应的游戏用户ID
        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        SdkOrderPageResp resp = JSONObject.parseObject(jsonResult, SdkOrderPageResp.class);
        List<SdkGameOrderResp> records = resp.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return baseResult;
        }
        List<String> userIdList = new ArrayList<>();
        for (SdkGameOrderResp sdkGameOrderResp : records) {
            userIdList.add(sdkGameOrderResp.getUserId());
            userIdList.add(sdkGameOrderResp.getReceiverId());
        }

        // 根据ID查用户信息
        FeignQueryUserListReq feignQueryUserListReq = new FeignQueryUserListReq();
        feignQueryUserListReq.setIds(userIdList);
        BaseResult userResult = userFeign.queryList(feignQueryUserListReq);
        if (!baseResult.isSuccess() || baseResult.getData() == null) {
            return baseResult;
        }
        Map<String, Object> userMap = (Map<String, Object>) userResult.getData();
        List<Map<String, Object>> userList = (List<Map<String, Object>>) userMap.get("list");
        if (CollectionUtils.isEmpty(userList)) {
            return baseResult;
        }
        //设置用户名称
        for (SdkGameOrderResp sdkGameOrderResp : records) {
            String userId = sdkGameOrderResp.getUserId();
            String receiverId = sdkGameOrderResp.getReceiverId();
            for (Map<String, Object> umap : userList) {
                String id = (String) umap.get("account");
                String userName = (String) umap.get("name");
                if (userId.equals(id)) {
                    sdkGameOrderResp.setUserName(userName);
                }
                if (receiverId.equals(id)) {
                    sdkGameOrderResp.setReceiverUserName(userName);
                }
            }
        }
        baseResult.setData(resp);
        return baseResult;
    }

    @Override
    public BaseResult upload(MultipartFile multipartFile) {
       // String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_GAME.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_PAYTYPE_LOGO);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(filePath);
    }

    @Override
    public BaseResult save(SdkSaveThirdGameReq req) {
        FeignSdkSaveThirdGameReq feignReq = new FeignSdkSaveThirdGameReq();
        BeanUtils.copyProperties(req, feignReq);
        if (StringUtils.isBlank(req.getId())) {
            String signKey = UUID.randomUUID().toString().replaceAll("-", "");
            //加密次数
            int hashIterations = 10;
            String signSecret = new Md5Hash(signKey, signKey, hashIterations).toString();
            feignReq.setAppKey(signKey);
            feignReq.setAppSecret(signSecret);
        }
        return sdkFeign.save(feignReq);
    }

    @Override
    public BaseResult orderAudit(SdkThirdGameOrderAuditReq req) {
        FeignSdkThirdGameOrderAuditReq auditReq = new FeignSdkThirdGameOrderAuditReq();
        BeanUtils.copyProperties(req, auditReq);
        return sdkWalletFeign.orderAudit(auditReq);
    }

    @Override
    public BaseResult gameInfo(SdkThirdGameInfoReq req) {
        FeignSdkThirdGameInfoReq feignReq = new FeignSdkThirdGameInfoReq();
        BeanUtils.copyProperties(req, feignReq);
        return sdkFeign.gameInfo(feignReq);
    }

    @Override
    public BaseResult delete(SdkThirdGameDeleteReq req) {
        FeignSdkThirdGameDeleteReq feignReq = new FeignSdkThirdGameDeleteReq();
        BeanUtils.copyProperties(req, feignReq);
        return sdkFeign.delete(feignReq);
    }

    @Override
    public BaseResult updateGameStatus(SdkUpdateThirdGameStatusReq req) {
        FeignSdkUpdateThirdGameStatusReq feignReq = new FeignSdkUpdateThirdGameStatusReq();
        BeanUtils.copyProperties(req, feignReq);
        return sdkFeign.updateGameStatus(feignReq);
    }
}
