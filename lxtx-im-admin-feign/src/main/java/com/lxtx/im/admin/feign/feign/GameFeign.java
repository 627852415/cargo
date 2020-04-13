package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.GameFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignGameDeleteReq;
import com.lxtx.im.admin.feign.request.FeignGameListPageReq;
import com.lxtx.im.admin.feign.request.FeignGameSaveReq;
import com.lxtx.im.admin.feign.request.FeignGameSelectReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 游戏Feign
 *
 * @author CaiRH
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = GameFeignFallbackFactory.class)
public interface GameFeign {
    /**
     * 列表
     *
     * @param req
     * @return
     */
    @PostMapping("/game/listPage")
    BaseResult listPage(FeignGameListPageReq req);

    /**
     * 保存/修改
     *
     * @param saveReq
     * @return
     */
    @PostMapping("/game/save")
    BaseResult save(FeignGameSaveReq saveReq);

    /**
     * 游戏详情
     *
     * @param selectReq
     * @return
     */
    @PostMapping("/game/info")
    BaseResult info(FeignGameSelectReq selectReq);

    /**
     * 游戏删除
     *
     * @param delReq
     * @return
     */
    @PostMapping("/game/delete")
    BaseResult delete(FeignGameDeleteReq delReq);
}
