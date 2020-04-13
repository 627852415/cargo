package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.SmsListPageReq;

public interface SmsService {

    BaseResult list(SmsListPageReq req);
}
