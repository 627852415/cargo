package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.NoticeCommandFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.NoticeCommandFeignReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author PengPai
 * Date: Created in 13:57 2020/2/24
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = NoticeCommandFeignFallbackFactory.class)
public interface NoticeCommandFeign {

    @PostMapping("/notice/command/page")
    BaseResult page(NoticeCommandFeignReq req);

    @PostMapping("/notice/command/list")
    BaseResult list(NoticeCommandFeignReq req);

    @PostMapping("/notice/command/delete/ids")
    BaseResult deleteByIds(NoticeCommandFeignReq req);

    @PostMapping("/notice/command/delete/condition")
    BaseResult deleteByCondition(NoticeCommandFeignReq req);

    @PostMapping("/notice/command/save")
    BaseResult insertOrUpdate(NoticeCommandFeignReq req);
}
