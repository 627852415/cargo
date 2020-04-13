package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.InviteRelationChangeLogFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignInviteChangeLogListPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: liyunhua
 * @Date: 2019/1/14
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = InviteRelationChangeLogFeignFallbackFactory.class)
public interface InviteRelationChangeLogFeign {

    /**
     * 查询列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/invite/relation/change/log/list/page")
    BaseResult listPage(FeignInviteChangeLogListPageReq req);


}
