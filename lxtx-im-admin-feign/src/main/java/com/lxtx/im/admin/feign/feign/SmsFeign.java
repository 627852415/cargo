package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.SmsFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignSmsListPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-im-core", fallbackFactory = SmsFeignFallbackFactory.class)
public interface SmsFeign {

    @PostMapping(value = "/sms/listPage")
    BaseResult list(FeignSmsListPageReq feignSmsListPageReq);

}