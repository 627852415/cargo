package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.WalletOtcFeign;
import com.lxtx.im.admin.feign.request.FeignWithdrawConfigOneReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawConfigPageReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawConfigSaveOrUpdateReq;
import com.lxtx.im.admin.service.OtcWithdrawConfigService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.WithdrawConfigDeleteReq;
import com.lxtx.im.admin.service.request.WithdrawConfigPageReq;
import com.lxtx.im.admin.service.request.WithdrawConfigSaveOrUpdateReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Czh
 * Date: 2019-06-25 14:40
 */
@Service
public class OtcWithdrawConfigServiceImpl implements OtcWithdrawConfigService {

    @Autowired
    private WalletOtcFeign walletOtcFeign;

    @Override
    public Object selectById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        FeignWithdrawConfigOneReq feignReq = new FeignWithdrawConfigOneReq();
        feignReq.setId(id);
        BaseResult baseResult = walletOtcFeign.withdrawConfigOne(feignReq);
        if (!baseResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException(baseResult.getCode(), baseResult.getMsg());
        }
        return baseResult.getData();
    }

    @Override
    public BaseResult withdrawConfigSaveOrUpdate(WithdrawConfigSaveOrUpdateReq req) {
        FeignWithdrawConfigSaveOrUpdateReq feignReq = new FeignWithdrawConfigSaveOrUpdateReq();
        BeanUtils.copyProperties(req, feignReq);
        return walletOtcFeign.withdrawConfigSaveOrUpdate(feignReq);
    }

    @Override
    public BaseResult withdrawConfigListPage(WithdrawConfigPageReq req) {
        FeignWithdrawConfigPageReq feignReq = new FeignWithdrawConfigPageReq();
        BeanUtils.copyProperties(req, feignReq);
        return walletOtcFeign.withdrawConfigListPage(feignReq);
    }

    @Override
    public BaseResult withdrawConfigDelete(WithdrawConfigDeleteReq req) {
        FeignWithdrawConfigOneReq feignReq = new FeignWithdrawConfigOneReq();
        feignReq.setId(req.getId());
        return walletOtcFeign.withdrawConfigDelete(feignReq);
    }

    @Override
    public BaseResult withdrawConfigEnable(WithdrawConfigDeleteReq req) {
        FeignWithdrawConfigOneReq feignReq = new FeignWithdrawConfigOneReq();
        feignReq.setId(req.getId());
        return walletOtcFeign.withdrawConfigEnable(feignReq);
    }
}