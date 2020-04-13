package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.UserAuthenticationFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = UserAuthenticationFeignFallbackFactory.class)
public interface UserAuthenticationFeign {

    @PostMapping("/user/authentication/page")
    BaseResult page(FeignUserAuthenticationPageReq feignReq);

    @PostMapping("/user/authentication/update/status")
    BaseResult updateStatus(FeignUserAuthenticationUpdateStatusReq feignReq);

    @PostMapping("/user/authentication/audit")
    BaseResult audit(FeignUserAuthenticationAuditReq feignReq);

    @PostMapping("/user/authentication/edit")
    BaseResult edit(FeignUserAuthenticationEditReq feignReq);

    @PostMapping("/user/authentication/detail")
    BaseResult detail(FeignUserAuthenticationDetailReq feignReq);

    @PostMapping("/user/authentication/del")
    BaseResult del(FeignUserAuthenticationDelReq feignReq);

    @PostMapping("/user/authentication/platformUserIdList")
    BaseResult platformUserIdList(UserAuthenticationListReq req);
}
