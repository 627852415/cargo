package com.lxtx.im.admin.service;

import com.lxtx.im.admin.service.request.StatisticsOrderReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeFundAcountResp;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/10 16:22
 */
public interface OffsiteExchangeFundAccountService {

    /**
     * 获取资金池账号信息
     *
     * @return
     */
    OffsiteExchangeFundAcountResp getFundAccountInfo();

    /**
     * 根据日期统计资金池账号数据
     *
     * @param statisticsOrderReq
     * @return
     */
    OffsiteExchangeFundAcountResp getFundAccountInfoByDate(StatisticsOrderReq statisticsOrderReq);
}
