package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.BroadcastPushReq;
import com.lxtx.im.admin.service.request.SendBroadcastSwitchMessageReq;
import org.springframework.ui.ModelMap;

/**
 * @author CaiRH
 */
public interface BroadcastService {



    /**
     * 广播/推送消息
     *
     * @param req
     * @return
     */
    BaseResult push(BroadcastPushReq req) throws Exception;

    /**
     *
     * 功能描述: 向所有在线用户发送离线消息
     *
     * @param
     * @return
     * @author liboyan
     * @date 2018-11-13 15:43
     */
    BaseResult sendBroadcastOfflineMessage() throws Exception;


    BaseResult sendBroadcastSwitchMessage(SendBroadcastSwitchMessageReq req) throws Exception;

    void index(ModelMap map);
}
