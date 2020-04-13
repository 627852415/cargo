package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CoinChargeDeleteReq;
import com.lxtx.im.admin.service.request.CoinChargeInfoReq;
import com.lxtx.im.admin.service.request.CoinChargeListPageReq;
import com.lxtx.im.admin.service.request.CoinChargeSaveReq;

/**
 * @description: 币种手续费管理
 * @author: tangdy
 * @create: 2018-10-12 14:53
 */
public interface CoinChargeService {

    /**
     * 获取币种手续费管理列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(CoinChargeListPageReq req);

    /**
     * 删除币种手续费
     *
     * @param req
     * @return
     */
    BaseResult delete(CoinChargeDeleteReq req);

    /**
     * 保存或更新币种手续费
     *
     * @param req
     * @return
     */
    BaseResult saveOrUpdate(CoinChargeSaveReq req);

    /**
     * 根据id获取币种手续费信息
     *
     * @param req
     * @return
     */
    BaseResult info(CoinChargeInfoReq req);
}
