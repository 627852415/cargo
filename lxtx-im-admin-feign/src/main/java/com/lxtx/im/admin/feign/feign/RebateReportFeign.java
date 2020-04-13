package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.RebateReportFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignRebateDetailPageReq;
import com.lxtx.im.admin.feign.request.FeignRebatePlayerGameDetailPageReq;
import com.lxtx.im.admin.feign.request.FeignRebateReportListPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 游戏返佣报表
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = RebateReportFallbackFactory.class)
public interface RebateReportFeign {

    /**
     * 游戏返佣报表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/rebate/report")
    BaseResult listPage(FeignRebateReportListPageReq req);

    /**
     * 用户返佣详情（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/rebate/report/user")
    BaseResult userRebateDetailList(FeignRebateDetailPageReq req);

    /**
     * 玩家对局详情详情（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/rebate/report/playerGameDetail")
    BaseResult playerGameDetail(FeignRebatePlayerGameDetailPageReq req);

    @PostMapping("/rebate/list")
    BaseResult rebateList(FeignRebateReportListPageReq listPageReq);

    @PostMapping("/rebate/detail")
    BaseResult rebateDetail(FeignRebateDetailPageReq feign);
}
