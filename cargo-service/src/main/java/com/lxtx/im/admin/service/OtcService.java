package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.OtcOrderReq;
import com.lxtx.im.admin.service.request.UpdateOtcOrderCheckStateReq;

import javax.servlet.http.HttpSession;

/**
 * 交易流水模块业务接口
 *
 * @author CaiRH
 * @since 2018-09-27
 */
public interface OtcService {


    /**
     *  获取otc订单
     * @param req
     * @param session
     * @return com.lxtx.framework.common.base.BaseResult
     * @auther LiuLP
     * @date 2018/10/25 0025
     */
    BaseResult listPage(OtcOrderReq req, HttpSession session);

    /**
     *  审核订单
     * @param req
     * @param session
     * @return com.lxtx.framework.common.base.BaseResult
     * @auther LiuLP
     * @date 2018/10/25 0025
     */
    BaseResult updateCheckState(UpdateOtcOrderCheckStateReq req, HttpSession session);
}
