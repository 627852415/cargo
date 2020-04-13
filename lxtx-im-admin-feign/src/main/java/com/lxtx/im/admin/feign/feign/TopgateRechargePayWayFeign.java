package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TopgateRechargePayWayFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TopgateRechargePayWayFeignFallbackFactory.class)
public interface TopgateRechargePayWayFeign {

    @RequestMapping("/admin/recharge/payway/updateEnable")
    BaseResult updateEnable(TopGateWithdrawPaywayOnOrOffReq req);

    /**
     * topgate充值支付分页
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/recharge/payway/page")
    BaseResult page(@RequestBody TopGateRechargePaywayPageReq req);

    /**
     * 添加topgate充值支付
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/recharge/payway/save")
    BaseResult save(@RequestBody TopGateRechargePaywaySaveReq req);

    /**
     * 删除topgate充值支付
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/recharge/payway/remove")
    BaseResult remove(@RequestBody TopGateRechargePaywayRemoveReq req);

    /**
     * 查找topgate充值支付
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/recharge/payway/findOne")
    BaseResult findOne(@RequestBody TopGateRechargePaywayFindOneReq req);
}
