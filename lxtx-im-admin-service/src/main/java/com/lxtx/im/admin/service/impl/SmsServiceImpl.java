package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.SmsFeign;
import com.lxtx.im.admin.feign.request.FeignSmsListPageReq;
import com.lxtx.im.admin.service.SmsService;
import com.lxtx.im.admin.service.request.SmsListPageReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsFeign smsFeign;

    @Override
    public BaseResult list(SmsListPageReq req) {
        FeignSmsListPageReq feignSmsListPageReq = new FeignSmsListPageReq();
        BeanUtils.copyProperties(req,feignSmsListPageReq);
        BaseResult list = smsFeign.list(feignSmsListPageReq);
        return list;
    }
}
