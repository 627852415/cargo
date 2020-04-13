package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.StatisticsFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignStatisticsCoinChargeListPageReq;
import com.lxtx.im.admin.feign.request.FeignStatisticsCoinChargeListReq;
import com.lxtx.im.admin.feign.request.FeignStatisticsCoinContainMerchantAssetsReq;
import com.lxtx.im.admin.feign.request.FeignStatisticsCoinReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 币种资产统计
 *
 * @author tangdy
 * @since 2018-09-10
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = StatisticsFallbackFactory.class)
public interface StatisticsFeign {

    /**
     * 币种资产统计
     *
     * @param coinReq
     * @return
     */
    @PostMapping(value = "/statistics/coin/list")
    BaseResult listCoin(FeignStatisticsCoinReq coinReq);

    /**
     * 币种资产统计（不含商户资产）
     * @param coinReq
     * @return
     */
    @PostMapping(value = "/statistics/coin/admin/list")
    BaseResult listCoinAdmin(FeignStatisticsCoinReq coinReq);

    /**
     * 币种资产统计（含商户资产）
     * @param feignReq
     * @return
     */
    @PostMapping(value = "/statistics/coin/admin/list/contain/merchant/assets")
    BaseResult listCoinContainMerchantAssets(FeignStatisticsCoinContainMerchantAssetsReq feignReq);

    /**
     * 币种资产统计
     *
     * @param response
     * @return
     */
    @PostMapping(value = "/statistics/download")
    BaseResult download(HttpServletResponse response);
    /**
     * 手续费列表（统计模块）
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/statistics/coinCharge/listPage")
    BaseResult coinChargeListPage(FeignStatisticsCoinChargeListPageReq listPageReq);

    /**
     * 币种手续费统计
     * @param coinReq
     * @return
     */
    @PostMapping(value = "/statistics/coinCharge/list")
    BaseResult coinChargeList(FeignStatisticsCoinChargeListReq coinReq);

    /**
     * 手续费统计(新)
     * @param coinReq
     * @return
     */
    @PostMapping(value = "/statistics/feeStatement/list")
    BaseResult feeStatementList(FeignStatisticsCoinChargeListReq coinReq);

    /**
     * 手续费列表（统计模块新）
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/statistics/feeStatement/listPage")
    BaseResult feeStatementListPage(FeignStatisticsCoinChargeListPageReq listPageReq);

    /**
     * 同步余额宝转出手续费旧数据
     */
    @PostMapping(value = "/statistics/feeStatement/syncOldYebOutWithdrawFee")
    BaseResult syncOldYebOutWithdrawFee();


    @PostMapping(value = "/daily/assets/statistics/daily/by")
    BaseResult redoStatistics(FeignStatisticsCoinReq feignReq);


}
