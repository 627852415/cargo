package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.AdFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignAdDetailByIdReq;
import com.lxtx.im.admin.feign.request.FeignAdListReq;
import com.lxtx.im.admin.feign.request.FeignAdPositionSaveReq;
import com.lxtx.im.admin.feign.request.FeignAdSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 广告Feign
 *
 * @author map
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = AdFeignFallbackFactory.class)
public interface AdFeign {

    /**
     * 列表
     *
     * @param req
     * @return
     */
    @PostMapping("/admin/ad/list/page")
    BaseResult listPage(FeignAdListReq req);

    /**
     * 保存/修改
     *
     * @param addReq
     * @return
     */
    @PostMapping("/admin/ad/add")
    BaseResult save(FeignAdSaveReq addReq);

    /**
     * 广告位详情
     *
     * @param req
     * @return
     */
    @PostMapping("/admin/ad/details")
    BaseResult detail(FeignAdDetailByIdReq req);

}
