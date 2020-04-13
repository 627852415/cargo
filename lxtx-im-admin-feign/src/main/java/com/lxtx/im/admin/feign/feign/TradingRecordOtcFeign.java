package com.lxtx.im.admin.feign.feign;


import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TradingRecordOtcFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignCapitalDetailReq;
import com.lxtx.im.admin.feign.request.FeignCapitalReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordOtcDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordOtcReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TradingRecordOtcFeignFallbackFactory.class)
public interface TradingRecordOtcFeign {

    /**
     * 获取交易流水数据
     *
     * @param feignTradingRecordOtcReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/otcRecharge/listPage")
    BaseResult listPage(FeignTradingRecordOtcReq feignTradingRecordOtcReq);

    /**
     * 获取交易流水数据 - 不带分页
     *
     * @param feignTradingRecordOtcReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/otcRecharge/list")
    BaseResult list(FeignTradingRecordOtcReq feignTradingRecordOtcReq);

    /**
     * 获取交易流水详情
     *
     * @param feignTradingRecordOtcDetailReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/otcRecharge/detail")
    BaseResult detail(FeignTradingRecordOtcDetailReq feignTradingRecordOtcDetailReq);
}
