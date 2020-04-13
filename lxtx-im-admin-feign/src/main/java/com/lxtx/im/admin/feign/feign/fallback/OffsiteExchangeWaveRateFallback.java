package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeWaveRateFeign;
import com.lxtx.im.admin.feign.request.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-24 14:07
*/
@Component
@Slf4j
@Setter
public class OffsiteExchangeWaveRateFallback implements OffsiteExchangeWaveRateFeign {
    private Throwable cause;


    @Override
    public BaseResult listPage(FeignBasePageReq listPageReq) {
        log.error("feign 获取列表数据", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignOffsiteExchangeWaveRateIdReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult save(FeignOffsiteExchangeWaveRateSaveReq feignReq) {
        log.error("feign 保存浮动汇率", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult del(FeignOffsiteExchangeWaveRateIdReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult areaRateList(FeignOffsiteExchangeWaveAreaRateListReq feignReq) {
        log.error("feign 获取地区浮动汇率异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult areaRateUpdate(FeignOffsiteExchangeWaveRateAreaUpdateReq feignReq) {
        log.error("feign 更新地区浮动汇率异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult realTimeRate(FeignOffsiteExchangeRealTimeRateReq req) {
        log.error("feign 获取实时汇率异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult rebateList(FeignOffsiteExchangeWaveRateRebateReq feignReq) {
        log.error("feign 获取支付方式异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult list() {
        log.error("feign 获取币对列表异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}