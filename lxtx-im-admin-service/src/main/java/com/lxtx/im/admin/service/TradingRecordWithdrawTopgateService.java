package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.service.request.TradingRecordWithdrawTopgateDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordWithdrawTopgateReq;
import com.lxtx.im.admin.service.response.WithdrawTopgateOrderStatisticsResp;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Topgate提现订单模块业务接口
 *
 * @author hechizhi
 * @since 2020-1-3
 */
public interface TradingRecordWithdrawTopgateService {

    /**
     * 获取交易流水列表数据
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult listPage(TradingRecordWithdrawTopgateReq req, HttpSession session, Locale locale);

    /**
     * 导出文件锁
     *
     * @param req
     * @param session
     */
    BaseResult downloadLock(TradingRecordWithdrawTopgateReq req, HttpSession session, Locale locale);

    /**
     * 导出文件
     *
     * @param response
     * @param tradingRecordWithdrawReq
     */
    void downloadList(HttpServletResponse response, HttpSession session, TradingRecordWithdrawTopgateReq tradingRecordWithdrawReq, Locale locale);

    /**
     * 查看交易流水详情
     *
     * @param req
     * @param model
     * @return
     */
    String detail(TradingRecordWithdrawTopgateDetailReq req, Model model, Locale locale);

    WithdrawTopgateOrderStatisticsResp getAllStatistics(boolean refresh);
    WithdrawTopgateOrderStatisticsResp getStatistics(FeignStatisticsOrderReq req);
}

