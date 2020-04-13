package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.TransferFriendsFeign;
import com.lxtx.im.admin.feign.feign.fallback.TransferFriendsFeignFallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description
 * @author qing 
 * @date: 2019年11月21日 下午6:49:36
 */
@Component
public class TransferFriendsFeignFallbackFactory implements FallbackFactory<TransferFriendsFeign> {
    @Override
    public TransferFriendsFeign create(Throwable throwable) {
    	TransferFriendsFeignFallback feignFallback = new TransferFriendsFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}
