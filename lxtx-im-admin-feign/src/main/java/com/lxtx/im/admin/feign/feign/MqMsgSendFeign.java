package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.MqMsgSendFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendCancelReq;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendListPageReq;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendRetryReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author CaiRH
 * @since 2020-01-03
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = MqMsgSendFeignFallbackFactory.class)
public interface MqMsgSendFeign {

    @PostMapping(value = "/admin/mq/msg/send/list")
    BaseResult listPage(FeignMqMsgSendListPageReq feignMqMsgSendListPageReq);

    @PostMapping(value = "/admin/mq/msg/send/cancel")
    BaseResult cancel(FeignMqMsgSendCancelReq feignMqMsgSendCancelReq);

    @PostMapping(value = "/admin/mq/msg/send/retry")
    BaseResult retry(FeignMqMsgSendRetryReq feignMqMsgSendRetryReq);
}
