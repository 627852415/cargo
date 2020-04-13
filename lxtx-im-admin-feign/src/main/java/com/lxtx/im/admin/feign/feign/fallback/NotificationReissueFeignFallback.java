package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.NotificationReissueFeign;
import com.lxtx.im.admin.feign.request.FeignNotificationReissuePageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-06-12
 **/
@Component
@Slf4j
@Setter
public class NotificationReissueFeignFallback implements NotificationReissueFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignNotificationReissuePageReq feignNotificationReissuePageReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }



}
