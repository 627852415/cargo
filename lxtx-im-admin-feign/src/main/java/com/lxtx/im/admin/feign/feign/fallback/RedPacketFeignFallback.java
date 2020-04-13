package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.RedPacketFeign;
import com.lxtx.im.admin.feign.request.FeignAdminReceiveListReq;
import com.lxtx.im.admin.feign.request.FeignAdminSendListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 红包相关
 *
 * @author xumf
 * @date 2019/11/27 17:24
 */
@Component
@Slf4j
@Setter
public class RedPacketFeignFallback implements RedPacketFeign {

    private Throwable cause;

    @Override
    public BaseResult adminSendList(FeignAdminSendListReq feignAdminSendListReq) {
        log.error("feign 红包发送交易记录查询错误", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult adminReceiveList(FeignAdminReceiveListReq feignAdminReceiveListReq) {
        log.error("feign 红包领取交易记录查询错误", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult countAdminSendList(FeignAdminSendListReq feignAdminSendListReq) {
        log.error("feign 红包发送交易记录条数查询错误", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult countAdminReceiveList(FeignAdminReceiveListReq feignAdminReceiveListReq) {
        log.error("feign 红包领取交易记录条数查询错误", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants
                .SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}