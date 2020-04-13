package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeRebateFeign;
import com.lxtx.im.admin.feign.request.FeignBasePageReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeRebateIdReq;
import com.lxtx.im.admin.feign.request.FeignOffsiteExchangeRebateSaveReq;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-28
 */
@Component
@Slf4j
@Setter
public class OffsiteExchangeRebateFallback implements OffsiteExchangeRebateFeign {
    private Throwable cause;


    @Override
    public BaseResult listPage(FeignBasePageReq listPageReq) {
        log.error("feign 获取列表数据", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignOffsiteExchangeRebateIdReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult save(FeignOffsiteExchangeRebateSaveReq feignReq) {
        log.error("feign 保存支付方式/返利", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult del(FeignOffsiteExchangeRebateIdReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getRebateAmount(FeignStatisticsOrderReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}