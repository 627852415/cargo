package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.ChargeReportFallbackFactory;
import com.lxtx.im.admin.feign.feign.fallback.ChargeReportFallback;
import com.lxtx.im.admin.feign.request.FeignChargeReportListPageReq;
import com.lxtx.im.admin.feign.request.FeignChargeReportListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 手续费报表
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = ChargeReportFallbackFactory.class)
public interface ChargeReportFeign {

    /**
     * 手续费报表列表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/charge/report")
    BaseResult listPage(FeignChargeReportListPageReq req);

    /**
     * 手动生成手续费报表
     *
     * @param
     * @return
     */
    @PostMapping("/charge/generateReport")
    BaseResult generateReport();



    /**
     * 手续费报表列表（所有）
     *
     * @param req
     * @return
     */
    @PostMapping("/charge/listAll")
    BaseResult listAll(FeignChargeReportListReq req);
}
