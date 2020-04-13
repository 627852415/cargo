package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.InviteFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: liyunhua
 * @Date: 2019/1/14
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = InviteFeignFallbackFactory.class)
public interface InviteFeign {

    /**
     * 查询邀请关系管理列表
     *
     * @param userListReq
     * @return
     */
    @PostMapping(value = "/core/invite/listPage")
    BaseResult listPage(FeignUserListReq userListReq);

    @PostMapping(value = "/core/invite/addRelation")
    BaseResult addRelation(FeignInviteRelationReq feignInviteRelationReq);

    @PostMapping(value = "/core/invite/checkRelation")
    BaseResult checkInviteRelation(FeignInviteRelationReq feignInviteRelationReq);

    @PostMapping(value = "/core/invite/higherList")
    BaseResult higherList(FeignViewRelationReq feignInviteRelationReq);

    @PostMapping(value = "/core/invite/lowerList")
    BaseResult lowerList(FeignViewRelationPageReq feignInviteRelationReq);

    @PostMapping(value = "/core/invite/directList")
    BaseResult directList(FeignInviteDirectRelationReq feignInviteRelationReq);

    @PostMapping(value = "/core/invite/eachLevelNumList")
    BaseResult eachLevelNumList(FeignViewRelationReq feignViewRelationReq);

    @PostMapping(value = "/core/invite/get/parent")
    BaseResult directHigher(FeignViewRelationReq feignViewRelationReq);

    @PostMapping(value = "/core/invite/relationChange")
    BaseResult relationChange(FeignRelationChangeReq feignReq);

}
