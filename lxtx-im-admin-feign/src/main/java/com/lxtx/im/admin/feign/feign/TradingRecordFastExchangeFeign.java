package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TradingRecordFastExchangeFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignCapitalDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordFastExchangeDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordFastExchangeReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xufeifei
 * @since 2019-11-23
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TradingRecordFastExchangeFeignFallbackFactory.class)
public interface TradingRecordFastExchangeFeign {

    /**
     * 获取交易流水数据
     *
     * @param feignTradingRecordFastExchangeReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/fastExchange/listPage")
    BaseResult listPage(FeignTradingRecordFastExchangeReq feignTradingRecordFastExchangeReq);

    /**
     * 获取交易流水数据 - 不带分页
     *
     * @param feignTradingRecordFastExchangeReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/fastExchange/list")
    BaseResult list(FeignTradingRecordFastExchangeReq feignTradingRecordFastExchangeReq);

    /**
     * 获取交易流水详情
     *
     * @param feignTradingRecordFastExchangeDetailReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/fastExchange/detail")
    BaseResult detail(FeignTradingRecordFastExchangeDetailReq feignTradingRecordFastExchangeDetailReq);
}
