package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.MessageUtil;
import com.lxtx.framework.common.utils.message.FeignMessageDeleteReq;
import com.lxtx.im.admin.feign.feign.MessageFeign;
import com.lxtx.framework.common.utils.message.FeignMessagePageReq;
import com.lxtx.framework.common.utils.message.FeignMessageReq;
import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.service.Constants.Constants;
import com.lxtx.im.admin.service.MessageService;
import com.lxtx.im.admin.service.request.DeleteMessageReq;
import com.lxtx.im.admin.service.request.MessageListReq;
import com.lxtx.im.admin.service.request.SendMessageReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @description:  发送消息实现类
* @author:   CXM
* @create:   2018-09-05 16:35
*/
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageFeign messageFeign;

    @Override
    public BaseResult send(SendMessageReq req) {
        FeignMessageReq messageReq = new FeignMessageReq();
        BeanUtils.copyProperties(req, messageReq);
        messageReq.setSender(Constants.SENDER_DEFAULT);
        messageReq.setAccount(Constants.SENDER_DEFAULT);
        messageReq.setExtra("{}");
        MessageUtil.sendAsyncSingleCustomMsg(FeignSendMsgReq.getSingleSendMsgReq(messageReq));
        return BaseResult.success();
    }

    @Override
    public BaseResult delete (DeleteMessageReq deleteMessageReq) throws Exception {
        FeignMessageDeleteReq messageReq = new FeignMessageDeleteReq();
        BeanUtils.copyProperties(deleteMessageReq, messageReq);
        return MessageUtil.delete(messageReq);
    }

    @Override
    public BaseResult listPage(MessageListReq req) throws Exception {
        FeignMessagePageReq messageReq = new FeignMessagePageReq();
        BeanUtils.copyProperties(req, messageReq);
        return MessageUtil.listPage(messageReq);
    }

    @Override
    public BaseResult dataMigration() throws Exception {
        return MessageUtil.dataMigration();
    }
}
