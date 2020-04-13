package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.CheckAccountFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignPlatformAllCoinCheckTimeReq;
import com.lxtx.im.admin.feign.request.PlatNameReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "lxtx-im-wallet", fallbackFactory = CheckAccountFeignFallbackFactory.class)
public interface CheckAccountFeign {

    /**
     * 查询对账时间内用户币种的交易流水
     *
     * @param platformAllCoinAccount
     * @return
     */
    @RequestMapping("/check/getFlowDetail")
    @ResponseBody
    BaseResult getFlowDetail(@RequestBody FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得平台某天各币种下的资产总额
     *
     * @param platformAllCoinAccount
     * @return
     */
    @RequestMapping("/check/getPlatformlCoinBatchDetail")
    @ResponseBody
    public BaseResult getPlatformlCoinBatchDetail(@RequestBody FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得平台某天某币种差错记录
     *
     * @param platformAllCoinAccount
     * @return
     */
    @RequestMapping("/check/getCoinMistakeRecords")
    @ResponseBody
    public BaseResult getCoinMistakeRecords(@RequestBody FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得平台某币种某天的差错总金额和总笔数
     *
     * @param platformAllCoinAccount
     * @return
     */
    @RequestMapping("/check/getMistakeAmountAndMistakeCount")
    @ResponseBody
    public BaseResult getMistakeAmountAndMistakeCount(
            @RequestBody FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得趋势图数据
     */
    @RequestMapping("/check/getTrendData")
    @ResponseBody
    public BaseResult getTrendData(@RequestBody FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得所有币种信息
     *
     * @return
     */
    @RequestMapping("/check/getAllCoin")
    @ResponseBody
    BaseResult getAllCoin();

    /**
     * 根据币种ID和对账时间取得平台当天总资产趋势图数据
     *
     * @param platformAllCoinAccount
     * @return
     */
    @RequestMapping("/check/data/total")
    @ResponseBody
    BaseResult getChartTotalData(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 根据币种ID和对账时间取得币种当天交易额趋势图数据，分交易类型显示多天线
     *
     * @param platformAllCoinAccount
     * @return
     */
    @RequestMapping("/check/data/currency")
    @ResponseBody
    BaseResult getChartCurrencyData(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 根据币种和对账时间取得币种当天差异额趋势图数据
     *
     * @param platformAllCoinAccount
     * @return
     */
    @RequestMapping("/check/data/diff")
    @ResponseBody
    BaseResult getChartDiffData(FeignPlatformAllCoinCheckTimeReq platformAllCoinAccount);

    /**
     * 取得IM对应的用户ID
     *
     * @param id
     * @return
     */
    @RequestMapping("/check/getPlatIdById")
    @ResponseBody
    BaseResult getPlatIdById(PlatNameReq id);
}
