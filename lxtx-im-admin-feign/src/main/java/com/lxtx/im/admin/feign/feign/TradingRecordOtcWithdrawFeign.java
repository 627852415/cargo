package com.lxtx.im.admin.feign.feign;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TradingRecordOtcFeignFallbackFactory;
import com.lxtx.im.admin.feign.feign.factory.TradingRecordOtcWithdrawFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TradingRecordOtcWithdrawFeignFallbackFactory.class)
public interface TradingRecordOtcWithdrawFeign {

    /**
     * 获取交易流水数据
     *
     * @param feignTradingRecordOtcWithdrawReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/otcWithdraw/listPage")
    BaseResult listPage(FeignTradingRecordOtcWithdrawReq feignTradingRecordOtcWithdrawReq);

    /**
     * 获取交易流水数据 - 不带分页
     *
     * @param feignTradingRecordOtcWithdrawReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/otcWithdraw/list")
    BaseResult list(FeignTradingRecordOtcWithdrawReq feignTradingRecordOtcWithdrawReq);

    /**
     * 获取交易流水详情
     *
     * @param feignTradingRecordOtcWithdrawDetailReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/otcWithdraw/detail")
    BaseResult detail(FeignTradingRecordOtcWithdrawDetailReq feignTradingRecordOtcWithdrawDetailReq);
}
