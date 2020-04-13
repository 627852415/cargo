package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.MessageFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignMessagePageReq;
import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.feign.request.FeignQuerySingleMsgReq;
import com.lxtx.im.admin.feign.request.FeignUserDevicePageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @description:  发送消息
* @author:   CXM
* @create:   2018-09-05 16:39
*/
@Deprecated
@FeignClient(value = "lxtx-im-push", fallbackFactory = MessageFeignFallbackFactory.class)
public interface MessageFeign {

    /**
     * 给在线用户发送消息
     * @param req
     * @return
     */
    @PostMapping("/message/sendAsyncSingleCustomMsg")
    BaseResult sendAsyncSingleCustomMsg(@RequestBody FeignSendMsgReq req);

    @PostMapping(value = "/message/delete")
    BaseResult delete(FeignMessagePageReq req);

    @PostMapping(value = "/message/list")
    BaseResult listPage(FeignMessagePageReq req);

    @PostMapping(value = "/message/dataMigration")
    BaseResult dataMigration();

    @PostMapping(value = "/message/sendBroadcastMsg")
    BaseResult sendBroadcastMsg(FeignSendMsgReq req);

    @PostMapping(value = "/message/querySingleMsg")
    BaseResult querySingleMsg(@RequestBody FeignQuerySingleMsgReq  req);

    @PostMapping(value = "/user/device/page")
    BaseResult userDevicePage(@RequestBody FeignUserDevicePageReq req);
}
