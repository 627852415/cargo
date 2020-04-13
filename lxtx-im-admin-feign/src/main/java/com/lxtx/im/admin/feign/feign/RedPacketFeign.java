package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.RedPacketFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignAdminReceiveListReq;
import com.lxtx.im.admin.feign.request.FeignAdminSendListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 红包相关
 *
 * @author xumf
 * @date 2019/11/27 17:24
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = RedPacketFeignFallbackFactory.class)
public interface RedPacketFeign {

    /**
     * 红包发送交易记录
     *
     * @param feignAdminSendListReq {@link FeignAdminSendListReq}
     * @return {@link BaseResult}
     */
    @PostMapping(value = "/red/packet/admin/send/list")
    BaseResult adminSendList(FeignAdminSendListReq feignAdminSendListReq);

    /**
     * 红包领取交易记录
     *
     * @param feignAdminReceiveListReq {@link FeignAdminReceiveListReq}
     * @return {@link BaseResult}
     */
    @PostMapping(value = "/red/packet/admin/receive/list")
    BaseResult adminReceiveList(FeignAdminReceiveListReq feignAdminReceiveListReq);

    /**
     * 红包发送交易记录条数
     *
     * @param feignAdminSendListReq {@link FeignAdminSendListReq}
     * @return {@link BaseResult}
     */
    @PostMapping(value = "/red/packet/admin/send/list/count")
    BaseResult countAdminSendList(FeignAdminSendListReq feignAdminSendListReq);

    /**
     * 红包领取交易记录条数
     *
     * @param feignAdminReceiveListReq {@link FeignAdminReceiveListReq}
     * @return {@link BaseResult}
     */
    @PostMapping(value = "/red/packet/admin/receive/list/count")
    BaseResult countAdminReceiveList(FeignAdminReceiveListReq feignAdminReceiveListReq);

}
