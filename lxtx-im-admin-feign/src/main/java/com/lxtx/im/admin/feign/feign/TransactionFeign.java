package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TransactionFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignTransactionDetailReq;
import com.lxtx.im.admin.feign.request.FeignTransactionRechargePageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author CaiRH
 * @since 2019-11-22
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TransactionFeignFallbackFactory.class)
public interface TransactionFeign {

    /**
     * 获取资金入账交易流水数据
     *
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/transaction/recharge/listPage")
    BaseResult rechargeListPage(FeignTransactionRechargePageReq feignReq);

    /**
     * 获取资金入账交易流水详情
     *
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/transaction/recharge/detail")
    BaseResult rechargeDetail(FeignTransactionDetailReq feignReq);

}
