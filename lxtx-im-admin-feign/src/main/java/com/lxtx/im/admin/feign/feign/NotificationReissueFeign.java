package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.NotificationReissueFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignNotificationReissuePageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author CaiRH
 * @since 2019-06-12
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = NotificationReissueFeignFallbackFactory.class)
public interface NotificationReissueFeign {

    @PostMapping(value = "/admin/notification/reissue/list/page")
    BaseResult listPage(FeignNotificationReissuePageReq feignNotificationReissuePageReq);

}
