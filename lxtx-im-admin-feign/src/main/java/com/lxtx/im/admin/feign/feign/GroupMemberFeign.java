package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.GroupMemberFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignGroupListPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = GroupMemberFeignFallbackFactory.class)
public interface GroupMemberFeign {

    /**
     * 分页查询群成员列表
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/group/member/listPage")
    BaseResult listPage(FeignGroupListPageReq listPageReq);

    @PostMapping(value = "/group/member/listByGroupId")
    BaseResult listByGroupId(FeignGroupListPageReq listPageReq);

    @PostMapping(value = "/group/member/getHostByGroupId")
    BaseResult getHostByGroupId(FeignGroupListPageReq listPageReq);

    @PostMapping(value = "/group/member/yxGroupMemberBotherSyn")
    BaseResult yxGroupMemberBotherSyn();

    @PostMapping("/group/member/init/gmid")
    BaseResult initGmId();

}
