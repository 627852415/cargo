package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.WalletUserFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tangdy
 * @Date 2018-08-029
 * @Description
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = WalletUserFeignFallbackFactory.class)
public interface WalletUserFeign {

    /**
     * 钱包用户注册
     *
     * @param userReq
     * @return
     */
    @PostMapping(value = "/user/register")
    BaseResult registerWalletUser(FeignWallletRegisterUserReq userReq);

    @PostMapping(value = "/user/list")
    BaseResult list(FeignUserReq feignUserReq);

    /**
     * 更改用户状态（禁用/开启）
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/user/status/turn")
    BaseResult turnUserStatus(FeignTurnUserStatusReq req);

    /**
     * 查询已经注册存在的对应钱包用户ID
     *
     * @param userReq
     * @return
     */
    @PostMapping(value = "/user/register/query")
    BaseResult queryRegisterWalletUser(FeignQueryWalletRegisterUserReq userReq);

    /**
     * 用户钱包模式 - 导入旧数据
     *
     * @param req
     * @return
     * @since 2019-05-09
     */
    @PostMapping("/user/wallet/pattern/loadOldData")
    BaseResult loadOldData(FeignUserWalletPatternLoadOldDataReq req);

    /**
     * 根据钱包用户ID获取钱包用户信息
     *
     * @param req
     * @return
     */
    @PostMapping("/user/platformUserId")
    BaseResult queryWalletUserById(FeignWalletUserInfoReq req);

    /**
     * 同步core用户数据至钱包用户
     *
     * @param
     * @return
     */
    @PostMapping("/user/synchronize/userData")
    BaseResult synchronizeUserData(FeignWalletUserSynchronizeReq req);

    /**
     * 同步core用户数据至钱包用户
     *
     * @param
     * @return
     */
    @PostMapping("/user/im/info")
    BaseResult queryUserIdByImUser(FeignQueryUserIdByImUserReq queryUserIdByImUserReq);

    /**
     * 功能描述:根据用户名或国家编码或手机号查询钱包用户 <br>
     *
     * @Param: [feignUserReq]
     * @Return: com.lxtx.framework.common.base.BaseResult
     * @Date: 2020/1/20 17:48
     */
    @PostMapping("/user/selectUserByCountryAndPhone")
    BaseResult selectUserByCountryAndPhone(FeignUserReq feignUserReq);
}
