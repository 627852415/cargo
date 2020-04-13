package com.lxtx.im.admin.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.ScanPayFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignScanPayListPageReq;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = ScanPayFeignFallbackFactory.class)
public interface ScanPayFeign {

}
