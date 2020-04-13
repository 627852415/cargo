package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 闪兑交易流水模块业务接口
 *
 * @author xufeifei
 * @since 2019-11-23
 */
public interface TradingRecordFastExchangeService {

    /**
     * 获取交易流水列表数据
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult listPage(TradingRecordFastExchangeReq req, HttpSession session);
    
    /**
     * 导出文件锁
     *
     * @param response
     * @param tradingRecordFastExchangeReq
     */
    BaseResult downloadLock(TradingRecordFastExchangeReq req, HttpSession session);

    /**
     * 导出文件
     *
     * @param response
     * @param tradingRecordFastExchangeReq
     */
    void downloadList(HttpServletResponse response, HttpSession session, TradingRecordFastExchangeReq tradingRecordFastExchangeReq);

    /**
     * 查看交易流水详情
     *
     * @param req
     * @param model
     * @return
     */
    String detail(TradingRecordFastExchangeDetailReq req, Model model);
}

