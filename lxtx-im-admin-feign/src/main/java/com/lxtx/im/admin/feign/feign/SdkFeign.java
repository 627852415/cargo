package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.SdkFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
* @description:  sdk feign层调用
* @author:   CXM
* @create:   2018-11-30 11:35
*/
@FeignClient(value = "lxtx-im-core", fallbackFactory = SdkFeignFallbackFactory.class)
public interface SdkFeign {

    /**
     * 第三方游戏列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/sdk/user/games")
    BaseResult listPage(FeignSdkThirdGameListReq req);

    /**
     * 第三方游戏保存
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/sdk/user/game/save")
    BaseResult save(FeignSdkSaveThirdGameReq req);

    /**
     * 根据id获取第三方游戏信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/sdk/user/game/info")
    BaseResult gameInfo(FeignSdkThirdGameInfoReq req);

    /**
     * 删除游戏
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/sdk/user/game/delete")
    BaseResult delete(FeignSdkThirdGameDeleteReq req);

    /**
     * 修改游戏状态
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/sdk/user/game/update/status")
    BaseResult updateGameStatus(FeignSdkUpdateThirdGameStatusReq req);
}
