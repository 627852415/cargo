package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.MqMsgSendFeign;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendCancelReq;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendListPageReq;
import com.lxtx.im.admin.feign.request.FeignMqMsgSendRetryReq;
import com.lxtx.im.admin.service.MqMsgSendService;
import com.lxtx.im.admin.service.request.MqMsgSendCancelReq;
import com.lxtx.im.admin.service.request.MqMsgSendListPageReq;
import com.lxtx.im.admin.service.request.MqMsgSendRetryReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MqMsgSendServiceImpl implements MqMsgSendService {

    @Autowired
    private MqMsgSendFeign mqMsgSendFeign;

    @Override
    public BaseResult listPage(MqMsgSendListPageReq req, HttpSession session) {
        FeignMqMsgSendListPageReq feignReq = new FeignMqMsgSendListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        return mqMsgSendFeign.listPage(feignReq);
    }

    @Override
    public BaseResult cancel(MqMsgSendCancelReq req, HttpSession session) {
        FeignMqMsgSendCancelReq feignReq = new FeignMqMsgSendCancelReq();
        BeanUtils.copyProperties(req, feignReq);
        return mqMsgSendFeign.cancel(feignReq);
    }

    @Override
    public BaseResult retry(MqMsgSendRetryReq req, HttpSession session) {
        FeignMqMsgSendRetryReq feignReq = new FeignMqMsgSendRetryReq();
        BeanUtils.copyProperties(req, feignReq);
        return mqMsgSendFeign.retry(feignReq);
    }
}
