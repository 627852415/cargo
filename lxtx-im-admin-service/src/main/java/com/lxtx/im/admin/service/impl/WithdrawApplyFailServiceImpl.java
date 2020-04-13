package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.WithdrawApplyFailFeign;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailRecordDealFailReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailRecordDealSuccessReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailReq;
import com.lxtx.im.admin.service.WithdrawApplyFailService;
import com.lxtx.im.admin.service.request.WithdrawApplyDealFailReq;
import com.lxtx.im.admin.service.request.WithdrawApplyDealSuccessReq;
import com.lxtx.im.admin.service.request.WithdrawApplyFailReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawApplyFailServiceImpl implements WithdrawApplyFailService {

    @Autowired
    private WithdrawApplyFailFeign withdrawApplyFailFeign;

    @Override
    public BaseResult listPage(WithdrawApplyFailReq failReq) {
        FeignWithdrawApplyFailReq req = new FeignWithdrawApplyFailReq();
        BeanUtils.copyProperties(failReq, req);
        return withdrawApplyFailFeign.listPage(req);
    }

    @Override
    public BaseResult doSuccess(WithdrawApplyDealSuccessReq failReq) {
        FeignWithdrawApplyFailRecordDealSuccessReq req = new FeignWithdrawApplyFailRecordDealSuccessReq();
        BeanUtils.copyProperties(failReq, req);
        return withdrawApplyFailFeign.doSuccess(req);
    }

    @Override
    public BaseResult doFail(WithdrawApplyDealFailReq failReq) {
        FeignWithdrawApplyFailRecordDealFailReq req = new FeignWithdrawApplyFailRecordDealFailReq();
        BeanUtils.copyProperties(failReq, req);
        return withdrawApplyFailFeign.doFail(req);
    }
}
