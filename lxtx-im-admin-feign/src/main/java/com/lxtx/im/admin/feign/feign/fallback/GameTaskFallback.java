package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.GameTaskFeign;
import com.lxtx.im.admin.feign.request.FeignGroupGameCancelReq;
import com.lxtx.im.admin.feign.request.FeignGroupGameListPageReq;
import com.lxtx.im.admin.feign.request.FeignGroupGameStopReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 群游戏列表
 *
 * @Author: liyunhua
 * @Date: 2019/02/26
 */
@Component
@Slf4j
@Setter
public class GameTaskFallback implements GameTaskFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignGroupGameListPageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult stopGame(FeignGroupGameStopReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult cancelGame(FeignGroupGameCancelReq feignReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
