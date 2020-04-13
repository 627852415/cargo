package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.WalletUserCoinFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author liyunhua
 * @Date 2018-09-01 0001
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = WalletUserCoinFeignFallbackFactory.class)
public interface WalletUserCoinFeign {

    @PostMapping(value = "/admin/user/coin/list/page")
    BaseResult list(FeignUserReq feignUserReq);

    @PostMapping(value = "/admin/user/coin/update")
    BaseResult update(FeignUserCoinUpdateReq req);

    /**
     * 查询余额
     *
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/wallet/balance")
    BaseResult queryBalance(FeignQueryBalanceReq feignReq);

    /**
     * 根据userID查找钱包用户列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/wallet/user/getList")
    BaseResult queryWalletUserCoinListByUserId(FeignWalletUserInfoReq req);

    /**
     * 根据钱包地址查询用户钱包ID
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/wallet/user/getUserByAddr")
    BaseResult queryWalletUserByAddr(FeignQueryWalletUserByAddrReq req);
}
