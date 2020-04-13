package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.NoticeBroadCastFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.NoticeBroadCastFeignReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author PengPai
 * Date: Created in 13:58 2020/2/24
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = NoticeBroadCastFeignFallbackFactory.class)
public interface NoticeBroadCastFeign {

    @PostMapping("/notice/broadcast/page")
    BaseResult page(NoticeBroadCastFeignReq req);

    @PostMapping("/notice/broadcast/list")
    BaseResult list(NoticeBroadCastFeignReq req);

    @PostMapping("/notice/broadcast/delete/ids")
    BaseResult deleteByIds(NoticeBroadCastFeignReq req);

    @PostMapping("/notice/broadcast/delete/condition")
    BaseResult deleteByCondition(NoticeBroadCastFeignReq req);

    @PostMapping("/notice/broadcast/save")
    BaseResult insertOrUpdate(NoticeBroadCastFeignReq req);
}
