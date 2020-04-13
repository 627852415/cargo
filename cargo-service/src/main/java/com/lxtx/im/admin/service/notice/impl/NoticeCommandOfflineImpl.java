package com.lxtx.im.admin.service.notice.impl;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.utils.message.FeignMessageOption;
import com.lxtx.framework.common.utils.message.FeignMessageReq;
import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.service.notice.INoticePush;
import org.springframework.stereotype.Component;

/**
 * @author PengPai
 * Date: Created in 13:39 2020/2/25
 */
@Component("offline")
public class NoticeCommandOfflineImpl implements INoticePush {
    @Override
    public FeignSendMsgReq getCommandReq() {

        FeignMessageReq feign = new FeignMessageReq();
        feign.setAction(Constants.MessageAction.ACTION_999);
        feign.setSender(Constants.SYSTEM);
        feign.setReceiver(Constants.SYSTEM);
        feign.setContent("{}");

        FeignSendMsgReq yunXinSendMsgReq = new FeignSendMsgReq();
        yunXinSendMsgReq.setBody(feign);
        yunXinSendMsgReq.setOpe(0);
        yunXinSendMsgReq.setFrom(Constants.SYSTEM);
        yunXinSendMsgReq.setTo(Constants.SYSTEM);
        yunXinSendMsgReq.setMessageOption(new FeignMessageOption());
        return yunXinSendMsgReq;

    }
}
