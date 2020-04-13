package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.PlatformWithdrawApplyFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyAuditReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyDetailReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyListDownloadReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawApplyListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 系统提现
 * @author: CXM
 * @create: 2018-08-31 09:59
 **/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = PlatformWithdrawApplyFeignFallbackFactory.class)
public interface PlatformWithdrawApplyFeign {

    /**
     * 系统提现详情
     * @param detailReq
     * @return
     */
    @PostMapping(value = "/platform/withdraw/apply/detail")
    BaseResult detail(FeignPlatformWithdrawApplyDetailReq detailReq);

    /**
     * 平台提款申请审核
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/platform/withdraw/apply/audit")
    BaseResult platformWithdrawApplyAudit(FeignPlatformWithdrawApplyAuditReq req);

    /**
     * 系统提现申请列表(带分页)
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/platform/withdraw/apply/list/page")
    BaseResult listPage(FeignPlatformWithdrawApplyListReq listPageReq);


    /**
     * 系统提现申请列表
     *
     * @param downloadReq
     * @return
     */
    @PostMapping(value = "/platform/withdraw/apply/list")
    BaseResult list(FeignPlatformWithdrawApplyListDownloadReq downloadReq);
}
