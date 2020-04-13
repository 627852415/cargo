package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.WithdrawApplyFailFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailRecordDealFailReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailRecordDealSuccessReq;
import com.lxtx.im.admin.feign.request.FeignWithdrawApplyFailReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = WithdrawApplyFailFeignFallbackFactory.class)
public interface WithdrawApplyFailFeign {

    /**
     * 获取交易流水数据
     *
     * @param feignWithdrawApplyFailReq
     * @return
     */
    @PostMapping(value = "/admin/withdraw/apply/fail/record/list/page")
    BaseResult listPage(FeignWithdrawApplyFailReq feignWithdrawApplyFailReq);

    @PostMapping(value = "/admin/withdraw/apply/fail/record/deal/success")
    BaseResult doSuccess(FeignWithdrawApplyFailRecordDealSuccessReq feignWithdrawApplyFailRecordDealSuccessReq);

    @PostMapping(value = "/admin/withdraw/apply/fail/record/deal/fail")
    BaseResult doFail(FeignWithdrawApplyFailRecordDealFailReq feignWithdrawApplyFailRecordDealFailReq);
}
