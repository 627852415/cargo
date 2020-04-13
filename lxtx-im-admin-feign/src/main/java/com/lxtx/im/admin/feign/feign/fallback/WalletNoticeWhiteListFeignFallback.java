package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.WalletNoticeWhiteListFeign;
import com.lxtx.im.admin.feign.request.FeignNoticeWhiteListDeleteReq;
import com.lxtx.im.admin.feign.request.FeignNoticeWhiteListListReq;
import com.lxtx.im.admin.feign.request.FeignNoticeWhiteListSaveReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tangdy
 * @Date 2018-08-29
 * @Description
 */

@Component
@Setter
@Slf4j
public class WalletNoticeWhiteListFeignFallback implements WalletNoticeWhiteListFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignNoticeWhiteListListReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult save(FeignNoticeWhiteListSaveReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult delete(FeignNoticeWhiteListDeleteReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}
