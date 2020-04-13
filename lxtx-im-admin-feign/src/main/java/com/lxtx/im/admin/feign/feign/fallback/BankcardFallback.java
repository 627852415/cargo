package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.BankcardFeign;
import com.lxtx.im.admin.feign.request.FeignBankcardListPageReq;
import com.lxtx.im.admin.feign.request.FeignOtcBindBankcardNewReq;
import com.lxtx.im.admin.feign.request.FeignOtcBindBankcardUpdateReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 银行卡
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@Component
@Slf4j
@Setter
public class BankcardFallback implements BankcardFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignBankcardListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult bindBankcardUpdate(FeignOtcBindBankcardUpdateReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult bind(FeignOtcBindBankcardNewReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}
