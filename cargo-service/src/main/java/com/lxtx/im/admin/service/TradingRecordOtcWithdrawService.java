package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public interface TradingRecordOtcWithdrawService {

    /**
     * 获取交易流水列表数据
     *
     * @param tradingRecordOtcWithdrawReq
     * @param session
     * @return
     */
    BaseResult listPage(TradingRecordOtcWithdrawReq tradingRecordOtcWithdrawReq, HttpSession session);

    /**
     * 导出文件锁
     *
     * @param response
     * @param tradingRecordOtcWithdrawReq
     */
    BaseResult downloadLock(TradingRecordOtcWithdrawReq req, HttpSession session);
    
    /**
     * 导出文件
     *
     * @param response
     * @param tradingRecordOtcWithdrawReq
     */
    void downloadList(HttpServletResponse response, HttpSession session, TradingRecordOtcWithdrawReq tradingRecordOtcWithdrawReq);

    /**
     * 查看交易流水详情
     *
     * @param tradingRecordOtcWithdrawDetailReq
     * @param model
     * @return
     */
    String detail(TradingRecordOtcWithdrawDetailReq tradingRecordOtcWithdrawDetailReq, Model model);
}
