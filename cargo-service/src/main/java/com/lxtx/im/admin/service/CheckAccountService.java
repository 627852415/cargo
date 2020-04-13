package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.Coin;
import com.lxtx.im.admin.service.request.PlatformAllCoinCheckTimeReq;

import java.util.List;

/**
 * 对账数据展示
 *
 * @author pengpai
 */
public interface CheckAccountService {

    /**
     * 取得平台某天各币种下的资产总额
     *
     * @param platformAllCoinAccount
     * @return
     */
    BaseResult getPlatformlCoinBatchDetail(PlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得平台某天某币种差错记录
     *
     * @param platformAllCoinAccount
     * @return
     */
    BaseResult getCoinMistakeRecords(PlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得平台某币种某天的差错总金额和总笔数
     *
     * @param platformAllCoinAccount
     * @return
     */
    BaseResult getMistakeAmountAndMistakeCount(PlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得趋势图数据
     */
    BaseResult getTrendData(PlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得所有币种
     *
     * @return
     */
    List<Coin> getAllCoin();

    /**
     * 查询对账时间内用户币种的交易流水
     *
     * @param platformAllCoinAccount
     * @return
     */
    BaseResult getFlowDetail(PlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 根据币种ID和对账时间取得平台当天总资产趋势图数据
     *
     * @param platformAllCoinAccount
     * @return
     */
    BaseResult getChartTotalData(PlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 根据币种ID和对账时间取得币种当天交易额趋势图数据，分交易类型显示多天线
     *
     * @param platformAllCoinAccount
     * @return
     */
    BaseResult getChartCurrencyData(PlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 根据币种和对账时间取得币种当天差异额趋势图数据
     *
     * @param platformAllCoinAccount
     * @return
     */
    BaseResult getChartDiffData(PlatformAllCoinCheckTimeReq platformAllCoinAccount);
}
