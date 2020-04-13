package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.GameTaskFeign;
import com.lxtx.im.admin.feign.feign.fallback.GameTaskFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 群游戏列表
 *
 * @Author: liyunhua
 * @Date: 2019/02/26
 */
@Component

public class GameTaskFallbackFactory implements FallbackFactory<GameTaskFeign> {
    @Override
    public GameTaskFeign create(Throwable throwable) {
        GameTaskFallback feignFallback = new GameTaskFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}
