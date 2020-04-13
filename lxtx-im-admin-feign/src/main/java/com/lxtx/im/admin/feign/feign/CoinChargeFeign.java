package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.CoinChargeFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignCoinChargeDeleteReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeInfoReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeListPageReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeSaveReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 字典管理
 * @author: CXM
 * @create: 2018-10-12 14:52
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = CoinChargeFeignFallbackFactory.class)
public interface CoinChargeFeign {

    /**
     * 字典管理列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/coinCharge/list")
    BaseResult listPage(FeignCoinChargeListPageReq req);

    /**
     * 删除字典
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/coinCharge/delete")
    BaseResult delete(FeignCoinChargeDeleteReq req);

    /**
     * 保存或更新字典
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/coinCharge/save")
    BaseResult saveOrUpdate(FeignCoinChargeSaveReq req);

    /**
     * 根据id获取字典信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/coinCharge/info")
    BaseResult info(FeignCoinChargeInfoReq req);
}
