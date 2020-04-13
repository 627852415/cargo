package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.MessageFeign;
import com.lxtx.im.admin.feign.request.FeignMessagePageReq;
import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.feign.request.FeignQuerySingleMsgReq;
import com.lxtx.im.admin.feign.request.FeignUserDevicePageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: CXM
 * @create: 2018-09-06 14:28
 */
@Deprecated
@Component
@Slf4j
@Setter
public class MessageFeignFallback implements MessageFeign {
    private Throwable cause;

    @Override
    public BaseResult sendAsyncSingleCustomMsg(FeignSendMsgReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult delete(FeignMessagePageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult listPage(FeignMessagePageReq req) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult dataMigration() {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult sendBroadcastMsg(FeignSendMsgReq req) {
        log.error("feign 发送广播消息失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult querySingleMsg(FeignQuerySingleMsgReq  req) {
        log.error("feign 查询历史消息失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult userDevicePage(FeignUserDevicePageReq req) {
        log.error("feign 查询在线用户列表失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
