package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.UserAuthenticationFeign;
import com.lxtx.im.admin.feign.request.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-21
 **/
@Setter
@Slf4j
@Component
public class UserAuthenticationFeignFallback implements UserAuthenticationFeign {

    private Throwable cause;

    @Override
    public BaseResult page(FeignUserAuthenticationPageReq feignReq) {
        log.error("请求用户身份认证列表失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult updateStatus(FeignUserAuthenticationUpdateStatusReq feignReq) {
        log.error("请求更新用户身份认证状态失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult audit(FeignUserAuthenticationAuditReq feignReq) {
        log.error("请求更新用户身份认证状态失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult edit(FeignUserAuthenticationEditReq feignReq) {
        log.error("请求更新用户身份认证信息失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignUserAuthenticationDetailReq feignReq) {
        log.error("请求用户身份认证详情失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult del(FeignUserAuthenticationDelReq feignReq) {
        log.error("删除用户身份认证失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult platformUserIdList(UserAuthenticationListReq req) {
        log.error("根据条件从用户认证表中查询平台用户ID失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}
