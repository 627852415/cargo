package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.WithdrawApplyDealFailReq;
import com.lxtx.im.admin.service.request.WithdrawApplyDealSuccessReq;
import com.lxtx.im.admin.service.request.WithdrawApplyFailReq;

public interface WithdrawApplyFailService {
    /**
     * 获取交易流水列表数据
     *
     * @param req
     * @return
     */
    BaseResult listPage(WithdrawApplyFailReq req);

    BaseResult doSuccess(WithdrawApplyDealSuccessReq req);

    BaseResult doFail(WithdrawApplyDealFailReq req);
}
