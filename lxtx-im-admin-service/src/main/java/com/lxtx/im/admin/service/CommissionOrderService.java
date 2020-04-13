package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CommissionOrderDetailReq;
import com.lxtx.im.admin.service.request.CommissionOrderListReq;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface CommissionOrderService {

    /**
     * 分佣订单列表
     * @return
     */
    BaseResult orderList(CommissionOrderListReq req);

    BaseResult detail(CommissionOrderDetailReq req);

    /**
     * 导出文件锁
     *
     * @param req
     * @param session
     */
    BaseResult downloadLock(CommissionOrderListReq req, HttpSession session);

    /**
     * 导出文件
     */
    void downloadList(HttpServletResponse response, HttpSession session, CommissionOrderListReq req);

}
