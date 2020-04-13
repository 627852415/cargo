package com.lxtx.im.admin.service.notice;

import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.service.request.NoticeBroadCastPushReq;

/**
 * @author PengPai
 * Date: Created in 12:09 2020/2/25
 */
public interface INoticePush {
    default FeignSendMsgReq getCommandReq() {
        return null;
    }

    default FeignSendMsgReq getBroadCastReq(NoticeBroadCastPushReq req) {
        return null;
    }
}
