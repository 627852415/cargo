package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TradingRecordFastExchangeTopgateFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordFastExchangeTopgateDetailReq;
import com.lxtx.im.admin.feign.request.FeignTradingRecordFastExchangeTopgateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author hechizhi
 * @since 2020-1-3
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TradingRecordFastExchangeTopgateFeignFallbackFactory.class)
public interface TradingRecordFastExchangeTopgateFeign {

    /**
     * 获取交易流水数据
     *
     * @param feignTradingRecordFastExchangeTopgateReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/fastExchangeTopgate/listPage")
    BaseResult listPage(FeignTradingRecordFastExchangeTopgateReq feignTradingRecordFastExchangeTopgateReq);


    /**
     * 获取交易流水详情
     *
     * @param feignTradingRecordFastExchangeTopgateDetailReq
     * @return
     */
    @PostMapping(value = "/tradingRecord/fastExchangeTopgate/detail")
    BaseResult detail(FeignTradingRecordFastExchangeTopgateDetailReq feignTradingRecordFastExchangeTopgateDetailReq);

    @PostMapping(value = "/tradingRecord/fastExchangeTopgate/manage/statistics")
    BaseResult getStatisticsOrder(FeignStatisticsOrderReq req);
}
