package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.YunxinFeign;
import com.lxtx.im.admin.feign.request.FeignYunXinAddGroupMemberReq;
import com.lxtx.im.admin.feign.request.FeignYunXinCreateGroupReq;
import com.lxtx.im.admin.feign.request.FeignYunXinCreateReq;
import com.lxtx.im.admin.feign.request.FeignYunXinDisbandGroupReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: CXM
 * @create: 2018-09-06 14:28
 */
@Component
@Setter
@Slf4j
public class YunxinFeignFallback implements YunxinFeign {
    private Throwable cause;

    @Override
    public BaseResult createGroup(FeignYunXinCreateGroupReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult addGroupMember(FeignYunXinAddGroupMemberReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult getUser(FeignYunXinCreateReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult disbandGroup(FeignYunXinDisbandGroupReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
