package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.UsbTokenReq;

/**
 　　* @author Lin hj
 　　* @date 2019/5/16 10:05
 */
public interface WebUsbTokenService {

    BaseResult isOpenUsbToken();

    BaseResult verifyToken(UsbTokenReq usbTokenReq);

}
