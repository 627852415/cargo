package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.ExceptionRecordFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignExceptionRecordDetailReq;
import com.lxtx.im.admin.feign.request.FeignExceptionRecordReq;
import com.lxtx.im.admin.feign.request.FeignExceptionRecordStatusReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tangdy
 * @Date 2018-08-28
 * @Description
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = ExceptionRecordFeignFallbackFactory.class)
public interface ExceptionRecordFeign {

    /**
     * 异常处理记录
     *
     * @param feignExceptionRecordReq
     * @return
     */
    @PostMapping(value = "/exception/record")
    BaseResult obtainRecordPage(FeignExceptionRecordReq feignExceptionRecordReq);


    /**
     * 更改异常记录状态
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/exception/record/status")
    BaseResult turnStatus(FeignExceptionRecordStatusReq req);

    /**
     * 更改异常记录状态
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/exception/record/detail")
    BaseResult detail(FeignExceptionRecordDetailReq req);
}
