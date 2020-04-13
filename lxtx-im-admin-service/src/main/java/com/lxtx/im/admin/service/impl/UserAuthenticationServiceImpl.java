package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.UserAuthenticationFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.UserAuthenticationService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumPayMerchantCertificateStatus;
import com.lxtx.im.admin.service.enums.EnumPayMerchantGender;
import com.lxtx.im.admin.service.enums.EnumPayMerchantStatus;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.UserAuthenticationDetailResp;
import com.lxtx.im.admin.service.response.UserAuthenticationPageResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户身份认证服务类实现
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final int COUNTRY_PHONE_CODE_LENGTH = 2;
    private static final String ZERO = "0";

    @Autowired
    private UserAuthenticationFeign userAuthenticationFeign;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private UserService userService;

    @Override
    public BaseResult page(UserAuthenticationPageReq req) {
        UserAuthenticationPageResp resp = new UserAuthenticationPageResp();
        Map<String, UserDetailResp> userMap = new HashMap<>();

        FeignUserAuthenticationPageReq feignReq = new FeignUserAuthenticationPageReq();
        BeanUtils.copyProperties(req, feignReq);
        feignReq.setKHShowAccount(userService.isShowAccount());

        if (StringUtils.isNotBlank(req.getAccount())
                || StringUtils.isNotBlank(req.getPhoneNum())
                || StringUtils.isNotBlank(req.getUserName())) {
            FeignMemberListReq memberListReq = new FeignMemberListReq();
            memberListReq.setAccount(req.getAccount());
            memberListReq.setTelephone(req.getPhoneNum());
            memberListReq.setName(req.getUserName());
//            List<String> userAccountByUserInfo = userService.getUserAccountByUserInfo(infoReq);
            BaseResult userResult = userFeign.list(memberListReq);
            if (userResult.isSuccessAndDataNotNull()) {
                Map<String, Object> dataMap = (Map<String, Object>) userResult.getData();
                UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(dataMap), UserListResp.class);
                if (CollectionUtils.isEmpty(userListResp.getList())) {
                    return BaseResult.success(resp);
                }
                List<String> accountList = new ArrayList<>();
                userListResp.getList().forEach(userDetailResp -> {
                    userMap.put(userDetailResp.getAccount(), userDetailResp);
                    accountList.add(userDetailResp.getAccount());
                });
                feignReq.setAccountList(accountList);
            }
        }
//        if (StringUtils.isNotBlank(req.getAccount())) {
//            FeignQueryUserLikeAccountReq feignQueryUserLikeAccountReq = new FeignQueryUserLikeAccountReq();
//            feignQueryUserLikeAccountReq.setAccount(req.getAccount());
//            BaseResult userResult = userFeign.queryListLikeAccount(feignQueryUserLikeAccountReq);
//            if (userResult.isSuccessAndDataNotNull()) {
//                Map<String, Object> dataMap = (Map<String, Object>) userResult.getData();
//                UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(dataMap), UserListResp.class);
//                if (CollectionUtils.isEmpty(userListResp.getList())) {
//                    return BaseResult.success(resp);
//                }
//                List<String> accountList = new ArrayList<>();
//                userListResp.getList().forEach(userDetailResp -> {
//                    userMap.put(userDetailResp.getAccount(), userDetailResp);
//                    accountList.add(userDetailResp.getAccount());
//                });
//                feignReq.setAccountList(accountList);
//            }
//        }

        BaseResult baseResult = userAuthenticationFeign.page(feignReq);
        if (baseResult.isSuccessAndDataNotNull()) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            resp = JSONObject.parseObject(JSONArray.toJSONString(dataMap), UserAuthenticationPageResp.class);
            if (userMap.isEmpty()) {
                FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
                queryUserListReq.setIds(resp.getPlatformUserIdList());
                BaseResult userListResult = userFeign.queryList(queryUserListReq);
                if (userListResult.isSuccessAndDataNotNull()) {
                    Map<String, Object> userListDataMap = (Map<String, Object>) userListResult.getData();
                    UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListDataMap), UserListResp.class);
                    if (!CollectionUtils.isEmpty(userListResp.getList())) {
                        userListResp.getList().forEach(userDetailResp -> userMap.put(userDetailResp.getAccount(), userDetailResp));
                    }
                }
            }
            resp.getRecords().forEach(pageDto -> {
                UserDetailResp userDetailResp = userMap.get(pageDto.getPlatformUserId());
                if (Objects.nonNull(userDetailResp)) {
                    pageDto.setName(userDetailResp.getName());
                    pageDto.setTelephone(userDetailResp.getTelephone());
                    pageDto.setCountryPhoneCode(setCountryPhoneCode(userDetailResp.getFullTelephone(), userDetailResp.getTelephone()));
                }
            });
        }
        return BaseResult.success(resp);
    }

    /**
     * 设置国际区号 CN/86
     *
     * @param fullTelephone
     * @param telephone
     * @return
     */
    public String setCountryPhoneCode(String fullTelephone, String telephone) {
        if (StringUtils.isBlank(fullTelephone)) {
            return null;
        }
        String countryNumCode = fullTelephone.replace(telephone, "");
        if (countryNumCode.length() == COUNTRY_PHONE_CODE_LENGTH) {
            countryNumCode = ZERO.concat(countryNumCode);

        }
        return countryNumCode;
    }

    @Override
    public BaseResult updateStatus(UserAuthenticationUpdateStatusReq req) {
        FeignUserAuthenticationUpdateStatusReq feignReq = new FeignUserAuthenticationUpdateStatusReq();
        BeanUtils.copyProperties(req, feignReq);
        return userAuthenticationFeign.updateStatus(feignReq);
    }

    @Override
    public BaseResult audit(UserAuthenticationAuditReq req) {
        FeignUserAuthenticationAuditReq feignReq = new FeignUserAuthenticationAuditReq();
        BeanUtils.copyProperties(req, feignReq);
        return userAuthenticationFeign.audit(feignReq);
    }

    @Override
    public BaseResult edit(UserAuthenticationEditReq req) {
        FeignUserAuthenticationEditReq feignReq = new FeignUserAuthenticationEditReq();
        BeanUtils.copyProperties(req, feignReq);
        return userAuthenticationFeign.edit(feignReq);
    }

    @Override
    public String detail(UserAuthenticationDetailReq req, Model model) {
        FeignUserAuthenticationDetailReq feignReq = new FeignUserAuthenticationDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult baseResult = userAuthenticationFeign.detail(feignReq);
        if (!baseResult.isSuccess()) {
            throw LxtxBizException.newException("获取不到用户认证详情!");
        }
        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        UserAuthenticationDetailResp detailResp = JSONObject.parseObject(JSONArray.toJSONString(dataMap), UserAuthenticationDetailResp.class);
        EnumPayMerchantGender enumPayMerchantGender = EnumPayMerchantGender.find(detailResp.getGender());
        if (Objects.nonNull(enumPayMerchantGender)) {
            detailResp.setGenderName(enumPayMerchantGender.getDescription());
        }
        model.addAttribute("detail", detailResp);
        if (req.getEdit()) {
            return "user/user-authentication-edit";
        }
        return "user/user-authentication-detail";
    }

    @Override
    public BaseResult del(UserAuthenticationDelReq req) {
        FeignUserAuthenticationDelReq feignReq = new FeignUserAuthenticationDelReq();
        BeanUtils.copyProperties(req, feignReq);
        return userAuthenticationFeign.del(feignReq);
    }

}
