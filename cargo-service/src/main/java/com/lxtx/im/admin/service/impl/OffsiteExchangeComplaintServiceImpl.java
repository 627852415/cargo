package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.MessageUtil;
import com.lxtx.framework.common.utils.encrypt.EncryptUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.framework.common.utils.message.FeignQuerySingleMsgReq;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeComplaintFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.OffsiteExchangeComplaintService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @description: 线下汇换投诉实现类
 * @author: CXM
 * @create: 2019-04-22 15:05
 */
@Service
@Slf4j
@DependsOn("propertiesUtil")
public class OffsiteExchangeComplaintServiceImpl implements OffsiteExchangeComplaintService {
    @Resource
    private OffsiteExchangeComplaintFeign offsiteExchangeComplaintFeign;
    @Autowired
    private QiNiuUtil qiNiuUtil;
    @Resource
    private UserFeign userFeign;

    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(OffsiteExchangeArbitrationListPageReq req) {
        FeignOffsiteExchangeArbitrationListPageReq feign = new FeignOffsiteExchangeArbitrationListPageReq();
        BeanUtils.copyProperties(req, feign);
        feign.setMerchantAccount(getAccountListByTelephone(req.getMerchantAccount()));
        feign.setField("create_time");
        feign.setOrder("desc");
        feign.setShowAccount(userService.isShowAccount());

        BaseResult result = offsiteExchangeComplaintFeign.listPage(feign);
        if (!result.isSuccessAndDataNotNull()) {
            return result;
        }

        String jsonObject = JSON.toJSONString(result.getData());
        OffsiteExchangeArbitrationListPageResp resp = JSONObject.parseObject(jsonObject, OffsiteExchangeArbitrationListPageResp.class);

        if(resp == null || CollectionUtils.isEmpty(resp.getRecords())){
            return result;
        }
        //设置电话
        Map<String, String> telephoneMap = getTelephoneMap(resp.getAccountList());
        for (OffsiteExchangeArbitrationResp arbitrationResp : resp.getRecords()) {
            if (!CollectionUtils.isEmpty(telephoneMap) && telephoneMap.containsKey(arbitrationResp.getBuyer())) {
                arbitrationResp.setBuyer(telephoneMap.get(arbitrationResp.getBuyer()));
            } else {
                arbitrationResp.setBuyer(null);
            }
            if (!CollectionUtils.isEmpty(telephoneMap) && telephoneMap.containsKey(arbitrationResp.getMerchant())) {
                arbitrationResp.setMerchant(telephoneMap.get(arbitrationResp.getMerchant()));
            } else {
                arbitrationResp.setMerchant(null);
            }
        }
        return BaseResult.success(resp);
    }

    private Map<String, String> getTelephoneMap(List<String> accountList) {
        FeignQueryUserListByIdReq accountReq = new FeignQueryUserListByIdReq();
        accountReq.setIds(accountList);
        BaseResult accountResult = userFeign.selectBatchIds(accountReq);
        if (!accountResult.isSuccessAndDataNotNull()) {
            return null;
        }
        UserList userListResp = JSONObject.parseObject(JSONArray.toJSONString(accountResult.getData()), UserList.class);
        List<UserResp> list = userListResp.getList();
        Map<String, String> telephoneMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (UserResp userResp : list) {
                if (!telephoneMap.containsKey(userResp.getAccount())) {
                    telephoneMap.put(userResp.getAccount(), userResp.getFullTelephone());
                }
            }
        }
        return telephoneMap;
    }

    private List<String> getAccountListByTelephone(String telephone) {
        if (StringUtils.isNotBlank(telephone)) {
            FeignUserInfoReq feignUserInfoReq = new FeignUserInfoReq();
            feignUserInfoReq.setTelephone(telephone);
            BaseResult userResult = userFeign.getUserAccountByUserInfo(feignUserInfoReq);
            if (!userResult.isSuccess()) {
                return null;
            }
            if (userResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(userResult.getData()));
                List<String> accountList = JSONObject.parseArray(jsonObject.getString(BaseResult.LIST), String.class);
                return accountList;
            }
        }
        return null;
    }

    @Override
    public  OffsiteArbitrationDetailResp detail(OffsiteExchangeComplaintDetailReq req) {
        FeignOffsiteExchangeComplaintDetailReq feign = new FeignOffsiteExchangeComplaintDetailReq();
        BeanUtils.copyProperties(req, feign);
        BaseResult baseResult = offsiteExchangeComplaintFeign.detail(feign);
        if (!baseResult.isSuccessAndDataNotNull()) {
            return null;
        }
        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        OffsiteArbitrationDetailResp arbitrationDetailResp = JSONObject.parseObject(JSON.toJSONString(dataMap), OffsiteArbitrationDetailResp.class);
        List<String> accountList = new ArrayList<>();
        accountList.add(arbitrationDetailResp.getBuyer());
        accountList.add(arbitrationDetailResp.getMerchant());
        //设置电话
        Map<String, String> telephoneMap = getTelephoneMap(accountList);
        arbitrationDetailResp.setBuyerTelephone(telephoneMap.get(arbitrationDetailResp.getBuyer()));
        arbitrationDetailResp.setMerchantTelephone(telephoneMap.get(arbitrationDetailResp.getMerchant()));

        for (OffsiteExchangeComplaintDetailResp detailResp : arbitrationDetailResp.getComplaintDetailRespList()) {

            if (telephoneMap.containsKey(detailResp.getUserId())) {
                detailResp.setUserId(telephoneMap.get(detailResp.getUserId()));
            } else {
                detailResp.setUserId(null);
            }
            if (telephoneMap.containsKey(detailResp.getDefendant())) {
                detailResp.setDefendant(telephoneMap.get(detailResp.getDefendant()));
            } else {
                detailResp.setDefendant(null);
            }
        }
        if(!CollectionUtils.isEmpty(arbitrationDetailResp.getRecords())){
            arbitrationDetailResp.getRecords().forEach(resp -> {
                Integer sourceType = resp.getSourceType();
                if(Objects.nonNull(sourceType)){
                    String sourceTypeName = (sourceType == 0 ? "系统" : "用户");
                    resp.setSourceTypeName(sourceTypeName);
                }
            });
        }
        arbitrationDetailResp.setComplaintId(arbitrationDetailResp.getComplaintDetailRespList().get(0).getId());

        return arbitrationDetailResp;
    }

    @Override
    public BaseResult saveRecord(OffsiteExchangeComplaintRecordSaveReq req) {
        String userName = ShiroUtils.getUserName();
        FeignOffsiteExchangeComplaintRecordSaveReq feign = new FeignOffsiteExchangeComplaintRecordSaveReq();
        BeanUtils.copyProperties(req, feign);
        feign.setCreateBy(userName);
        return offsiteExchangeComplaintFeign.saveRecord(feign);
    }

    @Override
    public BaseResult uploadCertificate(MultipartFile multipartFile) throws IOException {
//        String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_COMPLAINT.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.IDCERTIFICATION);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(PropertiesUtil.getFileViewUrl(filePath));
    }

    @Override
    public BaseResult completed(OffsiteExchangeComplaintCompletedReq req) {
        FeignOffsiteExchangeComplaintCompletedReq feign = new FeignOffsiteExchangeComplaintCompletedReq();
        BeanUtils.copyProperties(req, feign);
        feign.setCreateBy(ShiroUtils.getUserName());
        return offsiteExchangeComplaintFeign.completed(feign);
    }

    @Override
    public BaseResult messageList(SingleHistroyListReq req) throws Exception {
        // 获取客服账号
        String serviceAccount = dictService.getDictValue(DictConstants.DICT_DOMAIN_OFFSITE_EXCHANGE, DictConstants.DICT_KEY_DOMAIN_OFFSITE_HELPDESK_ACCOUNT);

        FeignQuerySingleMsgReq feign = new FeignQuerySingleMsgReq();
        String[] dateArr = req.getSearchDate().split("~");
        Date startDate = DateUtils.formatDate(dateArr[0].trim().concat(" 00:00:00"), DateUtils.DATE_FORMAT_DEFAULT);
        Date endDate = DateUtils.formatDate(dateArr[1].trim().concat(" 23:59:59"), DateUtils.DATE_FORMAT_DEFAULT);
        feign.setStartTimeMillis(startDate.getTime());
        feign.setEndTimeMillis(endDate.getTime());
        feign.setFriendAccount(req.getUserAccount());
        feign.setAccount(serviceAccount);
        feign.setAction(Constants.MessageAction.ACTION_14);
        BaseResult baseResult = MessageUtil.querySingleMsg(feign);

        if (!baseResult.isSuccessAndDataNotNull()) {
            return BaseResult.success(new ArrayList<>());
        }
        JSONObject parseObject = JSONObject.parseObject(JSONObject.toJSONString(baseResult.getData()));
        JSONArray objectJSONArray = parseObject.getJSONArray(BaseResult.LIST);
        ArrayList<Object> messageList = new ArrayList<>();
        try {
            for (Object obj : objectJSONArray) {
                MessageResp messageResp = JSON.parseObject(JSON.toJSONString(obj), MessageResp.class);
                if (Constants.MessageAction.ACTION_14.equals(messageResp.getAction())) {
                    JSONObject jsonContent = JSON.parseObject(messageResp.getContent());

                    String keyJson = EncryptUtils.aesDecrypt(jsonContent.getString("key"));
                    JSONObject keyObject = JSON.parseObject(keyJson);

                    // 订单号过滤
                    if(!req.getOrderId().equals(keyObject.getString("orderId"))){
                        continue;
                    }
                    if (Constants.MessageFormat.FORMAT_TEXT.equals(messageResp.getFormat())) {
                        messageResp.setContent(keyObject.getString("key"));

                    } else if (Constants.MessageFormat.FORMAT_IMAGE.equals(messageResp.getFormat())) {
                        messageResp.setContent("<img width='300px' src='" + keyObject.getString("image") + "'/>");
                    }
                    messageList.add(messageResp);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return BaseResult.success(messageList);
    }

    @Override
    public BaseResult revocation(OffsiteExchangeComplaintRevocationReq req) {
        FeignOffsiteExchangeComplaintRevocationReq feign = new FeignOffsiteExchangeComplaintRevocationReq();
        BeanUtils.copyProperties(req, feign);
        feign.setCreateBy(ShiroUtils.getUserName());
        return offsiteExchangeComplaintFeign.revocation(feign);
    }
}
