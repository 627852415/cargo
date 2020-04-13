package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.AppVersionFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignAppVersionDeleteReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionListPageReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionSaveReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionSelectReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tangdayoong
 * @Date 2018-11-12
 * @Description
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = AppVersionFeignFallbackFactory.class)
public interface AppVersionFeign {

    @PostMapping("/app/version/listPage")
    BaseResult listPage(FeignAppVersionListPageReq req);

    @PostMapping("/app/version/save")
    BaseResult save(FeignAppVersionSaveReq saveReq);
    @PostMapping("/app/version/info")
    BaseResult info(FeignAppVersionSelectReq selectReq);
    @PostMapping("/app/version/delete")
    BaseResult delete(FeignAppVersionDeleteReq delReq);
}
