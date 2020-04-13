package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.GroupMemberFeign;
import com.lxtx.im.admin.feign.feign.fallback.GroupMemberFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-13 14:14
 * @Description
 */
@Component
public class GroupMemberFeignFallbackFactory implements FallbackFactory<GroupMemberFeign> {
    @Override
    public GroupMemberFeign create(Throwable throwable) {
        GroupMemberFeignFallback feignFallback = new GroupMemberFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}
