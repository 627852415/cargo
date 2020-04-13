package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.SdkWalletFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignSdkThirdGameOrderAuditReq;
import com.lxtx.im.admin.feign.request.FeignSdkThirdGameOrderListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
* @description:  sdk feign层调用
* @author:   CXM
* @create:   2018-11-30 11:35
*/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = SdkWalletFeignFallbackFactory.class)
public interface SdkWalletFeign {

    /**
     * 第三方游戏订单列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/sdk/wallet/order/list")
    BaseResult orderListPage(FeignSdkThirdGameOrderListReq req);

    /**
     * 第三方游戏订单审核
     * @param auditReq
     * @return
     */
    @PostMapping(value = "/sdk/wallet/order/audit")
    BaseResult orderAudit(FeignSdkThirdGameOrderAuditReq auditReq);
}
