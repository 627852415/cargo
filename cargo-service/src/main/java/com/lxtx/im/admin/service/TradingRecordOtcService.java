package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.TradingRecordOtcDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordOtcReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public interface TradingRecordOtcService {

    /**
     * 获取交易流水列表数据
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult listPage(TradingRecordOtcReq req, HttpSession session);
    
    /**
     * 
     * @Description 导出文件锁
     * @param req
     * @param session
     * @return
     */
    BaseResult downloadLock(TradingRecordOtcReq req, HttpSession session);

    /**
     * 导出文件
     *
     * @param response
     * @param tradingRecordOtcReq
     */
    void downloadList(HttpServletResponse response, HttpSession session, TradingRecordOtcReq req);

    /**
     * 查看交易流水详情
     *
     * @param req
     * @param model
     * @return
     */
    String detail(TradingRecordOtcDetailReq req, Model model);
}
