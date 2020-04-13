package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.GlobalCodeFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeByCountryReq;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeListReq;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeModifyReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author liyunhua
 * @date 2018-11-16 0016
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = GlobalCodeFallbackFactory.class)
public interface GlobalCodeFeign {

    /**
     * 查询国际简码列表
     *
     * @param feignGlobalCodeListReq
     * @return
     */
    @PostMapping(value = "/globalCode/listPage")
    BaseResult listPage(FeignGlobalCodeListReq feignGlobalCodeListReq);

    /**
     * 通过编码查询国际简码列表
     *
     * @param feignGlobalCodeByCountryReq
     * @return
     */
    @PostMapping(value = "/globalCode/list")
    BaseResult list(FeignGlobalCodeByCountryReq feignGlobalCodeByCountryReq);

    @PostMapping(value = "/globalCode/add")
    BaseResult add(FeignGlobalCodeModifyReq feignGlobalCodeModifyReq);

    @PostMapping(value = "/globalCode/modify")
    BaseResult modify(FeignGlobalCodeModifyReq feignGlobalCodeModifyReq);

    @PostMapping(value = "/globalCode/delete")
    BaseResult delete(FeignGlobalCodeModifyReq feignGlobalCodeModifyReq);

}
