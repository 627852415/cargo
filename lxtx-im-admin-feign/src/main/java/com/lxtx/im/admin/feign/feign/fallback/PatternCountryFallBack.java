package com.lxtx.im.admin.feign.feign.fallback;

import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.PatternCountryFeign;
import com.lxtx.im.admin.feign.request.FeignPatternCountryListReq;
import com.lxtx.im.admin.feign.request.FeignPatternCountryModifyReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
public class PatternCountryFallBack implements PatternCountryFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignPatternCountryListReq feignPatternCountryListReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult modifyPatternCountry(FeignPatternCountryModifyReq feignPatternCountryModifyReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult userPatternsPage(Page page) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}
