package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TopgateWithdrawPayWayFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TopgateWithdrawPayWayFeignFallbackFactory.class)
public interface TopgateWithdrawPayWayFeign {

    @RequestMapping("/admin/withdraw/payway/updateEnable")
    BaseResult updateEnable(TopGateWithdrawPaywayOnOrOffReq req);

    /**
     * topgate 提币支付分页
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/withdraw/payway/page")
    BaseResult page(@RequestBody TopGateWithdrawPaywayPageReq req);

    /**
     * 添加topgate提币支付
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/withdraw/payway/save")
    BaseResult save(@RequestBody TopGateWithdrawPaywaySaveReq req);

    /**
     * 删除topgate提币支付
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/withdraw/payway/remove")
    BaseResult remove(@RequestBody TopGateWithdrawPaywayRemoveReq req);

    /**
     * 查找topgate提币支付
     *
     * @param req
     * @return
     */
    @RequestMapping("/admin/withdraw/payway/findOne")
    BaseResult findOne(@RequestBody TopGateWithdrawPaywayFindOneReq req);

    /**
     * 获取全部支付方式
     *
     * @return
     */
    @RequestMapping("/admin/withdraw/payway/listAll")
    BaseResult listAll();
}
