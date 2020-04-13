package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.StatisticsCoinChargeListPageReq;
import com.lxtx.im.admin.service.request.StatisticsCoinChargeListReq;
import com.lxtx.im.admin.service.request.StatisticsCoinContainMerchantAssetsReq;
import com.lxtx.im.admin.service.request.StatisticsCoinReq;
import com.lxtx.im.admin.service.request.StatisticsDownloadReq;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author tangdy
 * @Date 2018-09-10
 */
public interface StatisticsService {

    /**
     * 币种资产统计（不含商户资产）
     *
     * @param req
     * @return
     */
    BaseResult listCoin(StatisticsCoinReq req);

    /**
     * 资产统计列表（含商户资产）
     * @param req
     * @return
     */
    BaseResult listCoinContainMerchantAssets(StatisticsCoinContainMerchantAssetsReq req);

    BaseResult redoStatistics(StatisticsCoinReq req);

    /**
     * 导出文件
     *
     * @param response
     * @param req
     */
    void download(HttpServletResponse response, StatisticsDownloadReq req);

    /**
     * 手续费列表
     * @param req
     * @return
     */
    BaseResult coinChargeListPage(StatisticsCoinChargeListPageReq req);

    /**
     * 币种手续费统计
     * @param req
     * @return
     */
    BaseResult coinChargeList(StatisticsCoinChargeListReq req);

    /**
     * 币种手续费统计导出
     * @param response
     * @param req
     * @return void
     * @auther LiuLP
     * @date 2018/12/10 0010
     */
    void coinChargeDownload(HttpServletResponse response, StatisticsCoinChargeListReq req);


    /**
     * 币种手续费列表导出
     * @param response
     * @param req
     * @return void
     * @auther LiuLP
     * @date 2018/12/10 0010
     */
    void coinChargeListPageDownload(HttpServletResponse response, StatisticsCoinChargeListPageReq req);

    /**
     * 同步余额宝转出手续费旧数据
     */
    BaseResult syncOldYebOutWithdrawFee();
}
