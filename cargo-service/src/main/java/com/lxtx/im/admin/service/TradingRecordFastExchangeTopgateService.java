package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeTopgateDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeTopgateReq;
import com.lxtx.im.admin.service.response.FastExchangeTopgateOrderStatisticsResp;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Topgate闪兑交易流水模块业务接口
 *
 * @author hechizhi
 * @since 2020-1-3
 */
public interface TradingRecordFastExchangeTopgateService {

    /**
     * 获取交易流水列表数据
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult listPage(TradingRecordFastExchangeTopgateReq req, HttpSession session, Locale locale);
    
    /**
     * 导出文件锁
     *
     * @param req
     * @param session
     */
    BaseResult downloadLock(TradingRecordFastExchangeTopgateReq req, HttpSession session, Locale locale);

    /**
     * 导出文件
     *
     * @param response
     * @param tradingRecordFastExchangeReq
     */
    void downloadList(HttpServletResponse response, HttpSession session, TradingRecordFastExchangeTopgateReq tradingRecordFastExchangeReq, Locale locale);

    /**
     * 查看交易流水详情
     *
     * @param req
     * @param model
     * @return
     */
    String detail(TradingRecordFastExchangeTopgateDetailReq req, Model model, Locale locale);

    FastExchangeTopgateOrderStatisticsResp getAllStatistics(boolean refresh);

}

