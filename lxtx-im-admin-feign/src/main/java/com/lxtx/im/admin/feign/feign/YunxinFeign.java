package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.YunxinFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignYunXinAddGroupMemberReq;
import com.lxtx.im.admin.feign.request.FeignYunXinCreateGroupReq;
import com.lxtx.im.admin.feign.request.FeignYunXinCreateReq;
import com.lxtx.im.admin.feign.request.FeignYunXinDisbandGroupReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 网易云信调用
 * @author: zkj
 * @create: 2018-11-17 16:39
 */
@FeignClient(value = "lxtx-im-push", fallbackFactory = YunxinFeignFallbackFactory.class)
public interface YunxinFeign {

    @PostMapping(value = "/yunxin/group/create")
    BaseResult createGroup(FeignYunXinCreateGroupReq req);

    @PostMapping(value = "/yunxin/group/add/member")
    BaseResult addGroupMember(FeignYunXinAddGroupMemberReq req);

    @PostMapping(value = "/yunxin/user/get")
    BaseResult getUser(FeignYunXinCreateReq req);

    @PostMapping(value = "/yunxin/group/disbandGroup")
    BaseResult disbandGroup(FeignYunXinDisbandGroupReq req);


}