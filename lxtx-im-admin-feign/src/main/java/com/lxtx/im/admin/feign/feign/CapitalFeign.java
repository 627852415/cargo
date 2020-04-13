package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.CapitalFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignCapitalDetailReq;
import com.lxtx.im.admin.feign.request.FeignCapitalReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author CaiRH
 * @since 2018-09-27
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = CapitalFeignFallbackFactory.class)
public interface CapitalFeign {

    /**
     * 获取交易流水数据
     *
     * @param feignCapitalReq
     * @return
     */
    @PostMapping(value = "/capital/listPage")
    BaseResult listPage(FeignCapitalReq feignCapitalReq);

    /**
     * 获取交易流水数据 - 不带分页
     *
     * @param feignCapitalReq
     * @return
     */
    @PostMapping(value = "/capital/list")
    BaseResult list(FeignCapitalReq feignCapitalReq);

    /**
     * 获取交易流水详情
     *
     * @param feignCapitalDetailReq
     * @return
     */
    @PostMapping(value = "/capital/detail")
    BaseResult detail(FeignCapitalDetailReq feignCapitalDetailReq);

}
