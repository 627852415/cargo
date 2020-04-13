package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.GameTaskFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignGroupGameCancelReq;
import com.lxtx.im.admin.feign.request.FeignGroupGameListPageReq;
import com.lxtx.im.admin.feign.request.FeignGroupGameStopReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 群游戏列表
 *
 * @Author: liyunhua
 * @Date: 2019/02/18
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = GameTaskFallbackFactory.class)
public interface GameTaskFeign {

    /**
     * 群游戏列表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/game/gameTask/listPage")
    BaseResult listPage(FeignGroupGameListPageReq req);

    /**
     * 停止游戏
     *
     * @param req
     * @return
     */
    @PostMapping("/game/stop")
    BaseResult stopGame(FeignGroupGameStopReq req);

    /**
     * 取消游戏排队
     *
     * @param feignReq
     * @return
     */
    @PostMapping("/game/cancel")
    BaseResult cancelGame(FeignGroupGameCancelReq feignReq);
}
