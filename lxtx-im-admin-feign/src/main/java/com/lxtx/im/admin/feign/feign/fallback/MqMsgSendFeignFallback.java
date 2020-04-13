package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.MqMsgSendFeign;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendCancelReq;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendListPageReq;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendRetryReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2020-01-03
 **/
@Component
@Slf4j
@Setter
public class MqMsgSendFeignFallback implements MqMsgSendFeign {

    private Throwable cause;

    @Override
    public BaseResult listPage(FeignMqMsgSendListPageReq feignMqMsgSendListPageReq) {
        log.error("feign 调用查询MQ消息重试列表数据异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult cancel(FeignMqMsgSendCancelReq feignMqMsgSendCancelReq) {
        log.error("feign 取消MQ消息重试处理异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult retry(FeignMqMsgSendRetryReq feignMqMsgSendRetryReq) {
        log.error("feign MQ消息手动重试异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
