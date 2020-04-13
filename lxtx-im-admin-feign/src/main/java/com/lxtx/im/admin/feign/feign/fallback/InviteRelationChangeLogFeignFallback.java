package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.InviteRelationChangeLogFeign;
import com.lxtx.im.admin.feign.request.FeignInviteChangeLogListPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: liyunhua
 * @Date: 2019/1/14
 */
@Component
@Slf4j
@Setter
public class InviteRelationChangeLogFeignFallback implements InviteRelationChangeLogFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignInviteChangeLogListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
