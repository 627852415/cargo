package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.Coin;
import com.lxtx.im.admin.service.request.UserCoinUpdateReq;
import com.lxtx.im.admin.service.request.UserReq;
import com.lxtx.im.admin.service.response.CoinResp;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * @Author liyunhua
 * @Date 2018-09-01 0001
 */
public interface WalletUserCoinService {

    BaseResult list(UserReq userReq, HttpSession session);

    /**
     * 更新用户币种资产
     *
     * @param userCoinUpdateReq
     * @param session
     * @return
     */
    BaseResult update(UserCoinUpdateReq userCoinUpdateReq, HttpSession session);

    /**
     * 功能描述: 钱包列表下载<br>
     *
     * @Param: [userReq]
     * @Return: com.lxtx.framework.common.base.BaseResult
     * @Author: peng
     * @Date: 2020/1/9 17:50
     */
    void download(HttpServletResponse response, HttpSession session, UserReq userReq, Locale locale);

    /**
     * 功能描述: 钱包导出锁<br>
     *
     * @Param: [userReq, session, locale]
     * @Return: com.lxtx.framework.common.base.BaseResult
     * @Author: peng
     * @Date: 2020/1/9 18:02
     */
    BaseResult downloadLock(UserReq userReq, HttpSession session, Locale locale);

    /**
     * 功能描述: <br>
     * @Param: 取得所有币种列表
     * @Return: java.util.List<com.lxtx.im.admin.service.response.CoinResp>
     * @Author: peng
     * @Date: 2020/4/7 12:15
     */
    List<CoinResp> findAllCoin();
}
