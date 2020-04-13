package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.OffsiteExchangeCloseGoodsByAdminV5Req;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsDownloadReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsEditReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsPageReq;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:30
 * @Description
 */
public interface OffsiteExchangeGoodsService {
    /**
     * 功能描述: 获取商品列表
     *
     * @param
     * @return
     * @author liboyan
     * @date 2019-04-24 09:31
     */
    BaseResult listPage(OffsiteExchangeGoodsPageReq req);

    /**
     * 商品列表下载
     *
     * @param response
     * @param req
     */
    void goodsDownload(HttpServletResponse response, OffsiteExchangeGoodsDownloadReq req);
    /**
     *
     * 功能描述: 商品商家
     *
     * @param
     * @return
     * @author liboyan
     * @date 2019-04-25 15:52
     */
    BaseResult up(OffsiteExchangeGoodsEditReq req);

    /**
     * 关闭商家商品
     * @param req
     * @return
     */
    BaseResult closeGoodsByAdmin(OffsiteExchangeCloseGoodsByAdminV5Req req);

    /**
     *  商品下架
     * @param req
     * @return
     */
    BaseResult down(OffsiteExchangeGoodsEditReq req);

    /**
     * 补旧数据的商品的付款方式t_wallet_offsite_exchange_goods_out_pay_type
     */
    BaseResult syncOldGoodsOutPay();
    
    /**
     * 修复挂单无保证金，买家取消订单，补扣总账号的资金
     */
    BaseResult deductTheFundsOfTheTotalAccount();

}
