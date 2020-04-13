package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.PayMerchantFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.FeignPayMerchantDetailReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantListPageReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantVerifyReq;
import com.lxtx.im.admin.feign.request.FeignPayMerchantUpdateStatusReq;
import com.lxtx.im.admin.feign.request.FeignQueryUserLikeAccountReq;
import com.lxtx.im.admin.feign.request.FeignQueryUserListReq;
import com.lxtx.im.admin.service.PayMerchantService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumPayMerchantCertificateStatus;
import com.lxtx.im.admin.service.enums.EnumPayMerchantGender;
import com.lxtx.im.admin.service.enums.EnumPayMerchantStatus;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.PayMerchantDetailReq;
import com.lxtx.im.admin.service.request.PayMerchantListPageReq;
import com.lxtx.im.admin.service.request.PayMerchantVerifyReq;
import com.lxtx.im.admin.service.response.MerchantDetailResp;
import com.lxtx.im.admin.service.request.PayMerchantUpdateStatusReq;
import com.lxtx.im.admin.service.response.PayMerchantListPageResp;
import com.lxtx.im.admin.service.response.PayMerchantListResp;
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

/**
 * @description: 商家管理实现类
 * @author: CXM
 * @create: 2019-03-11 15:26
 */
@Service
public class PayMerchantServiceImpl implements PayMerchantService {
    private static final String ZERO = "0";
    private static final int COUNTRY_PHONE_CODE_LENGTH = 2;

    @Autowired
    private PayMerchantFeign payMerchantFeign;

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private UserService userService;


    @Override
    public BaseResult verify(PayMerchantVerifyReq req) {
        FeignPayMerchantVerifyReq feignReq = new FeignPayMerchantVerifyReq();
        BeanUtils.copyProperties(req,feignReq);
        return payMerchantFeign.verify(feignReq);
    }

    @Override
    public String detail(PayMerchantDetailReq req, Model model) {
        FeignPayMerchantDetailReq feignReq = new FeignPayMerchantDetailReq();
        feignReq.setId(req.getId());
        BaseResult detailResult = payMerchantFeign.detail(feignReq);
        if (!detailResult.isSuccess() || detailResult.getData() == null) {
            throw LxtxBizException.newException("获取不到商家详情!");
        }
        Map<String, Object> dataMap = (Map<String, Object>) detailResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        MerchantDetailResp detailResp = JSONObject.parseObject(jsonResult, MerchantDetailResp.class);
        detailResp.setCertificateFront(PropertiesUtil.getFileViewUrl(detailResp.getCertificateFront()));
        detailResp.setCertificateBack(PropertiesUtil.getFileViewUrl(detailResp.getCertificateBack()));
        detailResp.setCertificateSignature(PropertiesUtil.getFileViewUrl(detailResp.getCertificateSignature()));
        detailResp.setId(req.getId());
        Integer gender = detailResp.getGender();
        EnumPayMerchantGender enumPayMerchantGender = EnumPayMerchantGender.find(gender);
        if(enumPayMerchantGender != null){
            detailResp.setGenderName(enumPayMerchantGender.getDescription());
        }
        model.addAttribute("detail", detailResp);

        //根据商家审核状态和帐号冻结状态跳转页面
        Integer status = detailResp.getStatus();
        if(EnumPayMerchantStatus.FORBIDDEN.getCode().equals(status)){
            return "merchant/merchant-detail-check-only";
        }

        //待审核状态的跳转到审核详情页面，其他状态的跳转到只读页面
        Integer certificateStatus = detailResp.getCertificateStatus();
        if(EnumPayMerchantCertificateStatus.TO_BE_AUDITED.getCode().equals(certificateStatus)){
            return "merchant/merchant-detail";
        }
        //已审核状态跳转到已审核商家祥情页面
        if(EnumPayMerchantCertificateStatus.CERTIFIED.getCode().equals(certificateStatus)){
             return "merchant/merchant-detail-certified";
        }
        return "merchant/merchant-detail-check-only";
    }

    @Override
    public String edit(PayMerchantDetailReq req, Model model) {
        FeignPayMerchantDetailReq feignReq = new FeignPayMerchantDetailReq();
        feignReq.setId(req.getId());
        BaseResult detailResult = payMerchantFeign.detail(feignReq);
        if (!detailResult.isSuccess() || detailResult.getData() == null) {
            throw LxtxBizException.newException("获取不到商家详情!");
        }
        Map<String, Object> dataMap = (Map<String, Object>) detailResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        MerchantDetailResp detailResp = JSONObject.parseObject(jsonResult, MerchantDetailResp.class);
        detailResp.setCertificateFront(PropertiesUtil.getFileViewUrl(detailResp.getCertificateFront()));
        detailResp.setCertificateBack(PropertiesUtil.getFileViewUrl(detailResp.getCertificateBack()));
        detailResp.setCertificateSignature(PropertiesUtil.getFileViewUrl(detailResp.getCertificateSignature()));
        detailResp.setId(req.getId());
        Integer gender = detailResp.getGender();
        EnumPayMerchantGender enumPayMerchantGender = EnumPayMerchantGender.find(gender);
        if(enumPayMerchantGender != null){
            detailResp.setGenderName(enumPayMerchantGender.getDescription());
        }
        model.addAttribute("detail", detailResp);

        //根据商家审核状态和帐号冻结状态跳转页面
        Integer status = detailResp.getStatus();
        if(EnumPayMerchantStatus.FORBIDDEN.getCode().equals(status)){
            return "merchant/merchant-detail-check-only";
        }

        //待审核状态的跳转到审核详情页面，其他状态的跳转到只读页面
        Integer certificateStatus = detailResp.getCertificateStatus();
        if(EnumPayMerchantCertificateStatus.TO_BE_AUDITED.getCode().equals(certificateStatus)){
            return "merchant/merchant-detail";
        }
        //已审核状态跳转到已审核商家祥情页面
        if(EnumPayMerchantCertificateStatus.CERTIFIED.getCode().equals(certificateStatus)){
            return "merchant/merchant-detail-audited";
        }
        return "merchant/merchant-detail-check-only";
    }


    @Override
    public BaseResult listPage(PayMerchantListPageReq req) {
        FeignPayMerchantListPageReq feign = new FeignPayMerchantListPageReq();
        BeanUtils.copyProperties(req, feign);
        feign.setShowAccount(userService.isShowAccount());

        if (EnumPayMerchantCertificateStatus.FROZEN.getCode().equals(req.getAccount())) {
            feign.setStatus(EnumPayMerchantStatus.FORBIDDEN.getCode());
            feign.setCertificateStatus(null);
        }
        PayMerchantListPageResp payMerchantListPageResp = new PayMerchantListPageResp();
        //如果是按照IM账号模糊查询，先调用core获取模糊一次性查询出来所有匹配的账号，然后再拿这些账号去钱包查询认证的商家
        //因为并不是每个im用户都认证为商家
        if (StringUtils.isNotBlank(req.getAccount())) {
            FeignQueryUserLikeAccountReq feignQueryUserLikeAccountReq = new FeignQueryUserLikeAccountReq();
            feignQueryUserLikeAccountReq.setAccount(req.getAccount());
            BaseResult userResult = userFeign.queryListLikeAccount(feignQueryUserLikeAccountReq);
            if (userResult.isSuccess() && userResult.getData() != null) {
                //获取模糊查询的account集合
                Map<String, Object> coreDataMap = (Map<String, Object>) userResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                //core查询不到用户，直接返回
                if(CollectionUtils.isEmpty(userListResp.getList())){
                    return BaseResult.success(payMerchantListPageResp);
                }

                List<String> accountList = new ArrayList<>();
                //用map存返回的信息，避免wallet调用回来之后再去调用core获取用户信息
                Map<String, UserDetailResp> userMap = new HashMap<>();
                for (UserDetailResp userDetailResp: userListResp.getList()) {
                    accountList.add(userDetailResp.getAccount());
                    userMap.put(userDetailResp.getAccount(), userDetailResp);
                }
                feign.setAccountList(accountList);

                BaseResult merchantResult = payMerchantFeign.listPage(feign);
                if(merchantResult.isSuccess() && merchantResult.getData() != null){
                    Map<String, Object> merchantDataMap = (Map<String, Object>) merchantResult.getData();
                    if(CollectionUtils.isEmpty(merchantDataMap)){
                        return BaseResult.success(payMerchantListPageResp);
                    }
                }

                //调用wallet获取商家信息, 并设置返回
                payMerchantListPageResp = getPayMerchant(payMerchantFeign.listPage(feign), userMap);
            }

            //如果不是按照IM账号条件查询
        } else {
            //1、先条件查询出商家
            BaseResult payMerchantResult = payMerchantFeign.listPage(feign);
            //2、获取商家对应的IM用户信息
            if (payMerchantResult.isSuccess() && payMerchantResult.getData() != null) {
                payMerchantListPageResp = getPayMerchantUserInfo(payMerchantResult);
            }
        }
        return BaseResult.success(payMerchantListPageResp);
    }

    @Override
    public BaseResult updateStatus(PayMerchantUpdateStatusReq req) {
        FeignPayMerchantUpdateStatusReq feign = new FeignPayMerchantUpdateStatusReq();
        BeanUtils.copyProperties(req, feign);
        return payMerchantFeign.updateStatus(feign);
    }

    /**
     * 通过map设置商家im信息返回
     *
     * @param payMerchantResult
     * @param userMap
     * @return
     */
    private PayMerchantListPageResp getPayMerchant(BaseResult payMerchantResult, Map<String, UserDetailResp> userMap) {
        PayMerchantListPageResp payMerchantListPageResp = new PayMerchantListPageResp();
        if (payMerchantResult.isSuccess() && payMerchantResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) payMerchantResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap);
            payMerchantListPageResp = JSONObject.parseObject(jsonResult, PayMerchantListPageResp.class);

            for (PayMerchantListResp payMerchantListResp: payMerchantListPageResp.getRecords()) {
                if (userMap.containsKey(payMerchantListResp.getPlatformUserId())) {
                    UserDetailResp userDetailResp = userMap.get(payMerchantListResp.getPlatformUserId());
                    payMerchantListResp.setName(userDetailResp.getName());
                    payMerchantListResp.setTelephone(userDetailResp.getTelephone());
                    payMerchantListResp.setCountryPhoneCode(setCountryPhoneCode(userDetailResp.getFullTelephone(), userDetailResp.getTelephone()));
                }
            }
        }
        return payMerchantListPageResp;
    }


    /**
     * 获取商家对应的IM用户信息
     *
     * @param payMerchantResult
     */
    private PayMerchantListPageResp getPayMerchantUserInfo(BaseResult payMerchantResult) {
        Map<String, Object> dataMap = (Map<String, Object>) payMerchantResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        PayMerchantListPageResp payMerchantListPageResp = JSONObject.parseObject(jsonResult, PayMerchantListPageResp.class);
        if (!CollectionUtils.isEmpty(payMerchantListPageResp.getAccountList())) {
            //调用core获取im用户信息
            FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
            queryUserListReq.setIds(payMerchantListPageResp.getAccountList());
            BaseResult coreResult = userFeign.queryList(queryUserListReq);
            if (coreResult.isSuccess() && coreResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                //设置返回
                setPayMerchantListResp(payMerchantListPageResp, userListResp);

            }
        }
        return payMerchantListPageResp;
    }

    /**
     * 设置商家返回
     *
     * @param payMerchantListPageResp
     * @param userListResp
     */
    private void setPayMerchantListResp(PayMerchantListPageResp payMerchantListPageResp, UserListResp userListResp) {
        //设置返回
        for(UserDetailResp userDetailResp: userListResp.getList()) {
            for (PayMerchantListResp payMerchantListResp: payMerchantListPageResp.getRecords()) {
                if (userDetailResp.getAccount().equals(payMerchantListResp.getPlatformUserId())) {
                    payMerchantListResp.setName(userDetailResp.getName());
                    payMerchantListResp.setTelephone(userDetailResp.getTelephone());
                    payMerchantListResp.setCountryPhoneCode(setCountryPhoneCode(userDetailResp.getFullTelephone(), userDetailResp.getTelephone()));
                }
            }
        }
    }


    /**
     * 设置国际区号 CN/86
     * @param fullTelephone
     * @param telephone
     * @return
     */
    public String setCountryPhoneCode(String fullTelephone, String telephone){
        if(StringUtils.isBlank(fullTelephone)){
            return null;
        }
        String countryNumCode = fullTelephone.replace(telephone, "");
        if (countryNumCode.length() == COUNTRY_PHONE_CODE_LENGTH) {
            countryNumCode = ZERO.concat(countryNumCode);

        }
        return countryNumCode;
    }

}
