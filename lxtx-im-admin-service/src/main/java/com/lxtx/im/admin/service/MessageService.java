package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.DeleteMessageReq;
import com.lxtx.im.admin.service.request.MessageListReq;
import com.lxtx.im.admin.service.request.SendMessageReq;

/**
* @author:   CXM
* @create:   2018-09-05 18:44
*/
public interface MessageService {
    /**
     * 给在线用户发送消息
     *
     * @param req
     * @return
     */
    BaseResult send(SendMessageReq req);

    BaseResult delete(DeleteMessageReq req) throws Exception;

    BaseResult listPage(MessageListReq req) throws Exception;

    BaseResult dataMigration() throws Exception;
}
