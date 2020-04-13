package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.AdPositionFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignAdPositionDetailByIdReq;
import com.lxtx.im.admin.feign.request.FeignAdPositionListPageReq;
import com.lxtx.im.admin.feign.request.FeignAdPositionSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 广告位Feign
 *
 * @author xufeifei
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = AdPositionFeignFallbackFactory.class)
public interface AdPositionFeign {

    /**
     * 列表
     *
     * @param req
     * @return
     */
    @PostMapping("/ad/position/listPage")
    BaseResult listPage(FeignAdPositionListPageReq req);

    /**
     * 保存/修改
     *
     * @param addReq
     * @return
     */
    @PostMapping("/ad/position/save")
    BaseResult save(FeignAdPositionSaveReq addReq);

    /**
     * 广告位详情
     *
     * @param req
     * @return
     */
    @PostMapping("/ad/position/detail")
    BaseResult detail(FeignAdPositionDetailByIdReq req);

    /**
     * 根据id删除广告位
     */
    @PostMapping(value = "/ad/position/delete")
    BaseResult deleteById(FeignAdPositionDetailByIdReq req);

    /**
     * 获取广告位列表
     */
    @PostMapping(value = "/ad/position/list")
    BaseResult selectList();
}
