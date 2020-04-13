package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.AdviceFeedbackFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignAdviceFeedbackDetailReq;
import com.lxtx.im.admin.feign.request.FeignAdviceFeedbackPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/4 17:24
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = AdviceFeedbackFeignFallbackFactory.class)
public interface AdviceFeedbackFeign {

    /**
     * 反馈列表
     *
     * @param feignAdviceFeedbackPageReq
     * @return
     */
    @PostMapping(value = "/advice/feedback/list")
    BaseResult listPage(FeignAdviceFeedbackPageReq feignAdviceFeedbackPageReq);

    /**
     * 根据ID获取反馈详情
     *
     * @param feignAdviceFeedbackDetailReq
     * @return
     */
    @PostMapping(value = "/advice/feedback/detail")
    BaseResult detail(FeignAdviceFeedbackDetailReq feignAdviceFeedbackDetailReq);

    /**
     * 导出excel
     *
     * @param feignAdviceFeedbackPageReq
     * @return
     */
    @PostMapping(value = "/advice/feedback/export")
    BaseResult createExcel(FeignAdviceFeedbackPageReq feignAdviceFeedbackPageReq);
}
