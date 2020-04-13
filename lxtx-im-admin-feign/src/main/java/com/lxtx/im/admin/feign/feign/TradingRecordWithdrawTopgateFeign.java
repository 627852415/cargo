package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TradingRecordWithdrawTopgateFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordWithdrawTopgateDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordWithdrawTopgateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author hechizhi
 * @since 2020-1-3
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TradingRecordWithdrawTopgateFeignFallbackFactory.class)
public interface TradingRecordWithdrawTopgateFeign {

    /**
     * 获取交易流水数据
     *
     * @param feignTradingRecordWithdrawTopgateReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/withdrawTopgate/listPage")
    BaseResult listPage(FeignTradingRecordWithdrawTopgateReq feignTradingRecordWithdrawTopgateReq);


    /**
     * 获取交易流水详情
     *
     * @param feignTradingRecordWithdrawTopgateDetailReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/withdrawTopgate/detail")
    BaseResult detail(FeignTradingRecordWithdrawTopgateDetailReq feignTradingRecordWithdrawTopgateDetailReq);

    @PostMapping(value = "/tradingRecord/withdrawTopgate/manage/statistics")
    BaseResult getStatisticsOrder(FeignStatisticsOrderReq req);
}
