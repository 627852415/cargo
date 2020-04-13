package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.PlatformWithdrawConfigFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignGameSelectReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawConfigDeleteReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawConfigListReq;
import com.lxtx.im.admin.feign.request.FeignPlatformWithdrawConfigSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 系统提现配置
 *
 * @author CaiRH
 * @since 2018-12-20
 **/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = PlatformWithdrawConfigFeignFallbackFactory.class)
public interface PlatformWithdrawConfigFeign {

    /**
     * 系统提现申请列表
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/platform/withdraw/config/list")
    BaseResult listPage(FeignPlatformWithdrawConfigListReq listPageReq);

    /**
     * 平台提款配置查询详情
     *
     * @param selectReq
     * @return
     */
    @PostMapping(value = "/platform/withdraw/config/info")
    BaseResult info(FeignGameSelectReq selectReq);

    /**
     * 保存
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/platform/withdraw/config/save")
    BaseResult save(FeignPlatformWithdrawConfigSaveReq req);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/platform/withdraw/config/delete")
    BaseResult delete(FeignPlatformWithdrawConfigDeleteReq req);
}
