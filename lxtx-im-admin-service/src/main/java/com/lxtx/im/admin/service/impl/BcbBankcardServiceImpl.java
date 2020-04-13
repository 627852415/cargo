package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.BcbBankcardFeign;
import com.lxtx.im.admin.feign.feign.SendPhoneFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.BcbBankcardService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.BcbBankApplyAuditDetailResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BCB银行卡
 *
 * @author : CaiRH
 * @since : 2019-04-23
 */
@Service
@Slf4j
public class BcbBankcardServiceImpl implements BcbBankcardService {

    @Autowired
    private BcbBankcardFeign bcbBankcardFeign;
    @Autowired
    private SendPhoneFeign sendPhoneFeign;


    public BaseResult cardList(BcbBankCardNumberPageReq req) {
        FeignBcbBankCardNumberPageReq feignBcbBankCardNumReq = new FeignBcbBankCardNumberPageReq();
        BeanUtils.copyProperties(req, feignBcbBankCardNumReq);
        return bcbBankcardFeign.cardList(feignBcbBankCardNumReq);
    }

    public BaseResult cardAdd(BcbBankCardNumReq req) {
        FeignBcbBankCardNumReq feignBcbBankCardNumReq = new FeignBcbBankCardNumReq();
        BeanUtils.copyProperties(req, feignBcbBankCardNumReq);
        return bcbBankcardFeign.cardAdd(feignBcbBankCardNumReq);
    }

    @Override
    public BaseResult getById(String id) {
        FeignBcbBankCardNumDetailReq req = new FeignBcbBankCardNumDetailReq();
        req.setId(id);
        return bcbBankcardFeign.getCardNumById(req);
    }


    @Override
    public BaseResult listPage(BcbBankcardListPageReq req) {
        FeignBcbBankcardListPageReq feign = new FeignBcbBankcardListPageReq();
        BeanUtils.copyProperties(req, feign);
        return bcbBankcardFeign.listPage(feign);
    }

    @Override
    public BaseResult audit(BcbBankAuditReq req) {
        FeignBcbBankAuditReq feignBcbBankAuditReq = new FeignBcbBankAuditReq();
        BeanUtils.copyProperties(req, feignBcbBankAuditReq);
        //设置后台管理员userId
        feignBcbBankAuditReq.setAdminUserId(ShiroUtils.getUserId());

        BaseResult result = bcbBankcardFeign.audit(feignBcbBankAuditReq);
        if (!result.isSuccess()) {
            log.error("审核bcb银行卡的申请订单失败！无法发送bcb官网登录密码。申请订单ID：{}", req.getId());
            throw LxtxBizException.newException("审核bcb银行卡的申请订单失败！无法发送bcb官网登录密码。");
        }

        //只有审核通过才发bcb官网登录密码短信
        if (!req.getIsPass()) {
            return result;
        }

        BcbBankApplyDetailReq bcbBankApplyDetailReq = new BcbBankApplyDetailReq();
        bcbBankApplyDetailReq.setId(req.getId());
        BaseResult baseResult = applyAuditDetail(bcbBankApplyDetailReq);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }

        String jsonResult = JSONArray.toJSONString(baseResult.getData());
        BcbBankApplyAuditDetailResp detailResp = JSONObject.parseObject(jsonResult, BcbBankApplyAuditDetailResp.class);
        FeignSmsSendBcbLoginPWReq feignSmsSendBcbLoginPWReq = new FeignSmsSendBcbLoginPWReq();

        feignSmsSendBcbLoginPWReq.setCountryCode(detailResp.getPhoneCountryCode());
        feignSmsSendBcbLoginPWReq.setTelephone(detailResp.getCellPhone());
        feignSmsSendBcbLoginPWReq.setCardNo(detailResp.getCardNo());
        feignSmsSendBcbLoginPWReq.setLoginPassword(detailResp.getLoginPassword());
        sendPhoneFeign.sendBcbCardLoginPassword(feignSmsSendBcbLoginPWReq);
        return result;
    }

    @Override
    public BaseResult applyAuditDetail(BcbBankApplyDetailReq req) {
        FeignBcbBankApplyDetailReq feignBcbBankApplyDetailReq = new FeignBcbBankApplyDetailReq();
        BeanUtils.copyProperties(req, feignBcbBankApplyDetailReq);
        return bcbBankcardFeign.applyAuditDetail(feignBcbBankApplyDetailReq);
    }

    @Override
    public BaseResult save(BcbBankCardSaveReq req) {
        FeignBcbBankCardSaveReq feignBcbBankCardSaveReq = new FeignBcbBankCardSaveReq();
        BeanUtils.copyProperties(req, feignBcbBankCardSaveReq);
        return bcbBankcardFeign.cardSave(feignBcbBankCardSaveReq);
    }
}
