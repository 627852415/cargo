package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CoinChargeFeign;
import com.lxtx.im.admin.feign.request.FeignCoinChargeDeleteReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeInfoReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeListPageReq;
import com.lxtx.im.admin.feign.request.FeignCoinChargeSaveReq;
import com.lxtx.im.admin.service.CoinChargeService;
import com.lxtx.im.admin.service.request.CoinChargeDeleteReq;
import com.lxtx.im.admin.service.request.CoinChargeInfoReq;
import com.lxtx.im.admin.service.request.CoinChargeListPageReq;
import com.lxtx.im.admin.service.request.CoinChargeSaveReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description: 币种手续费管理
 * @author: tangdy
 * @create: 2018-10-12 14:37
 */
@Service
public class CoinChargeServiceImpl implements CoinChargeService {

    @Autowired
    private CoinChargeFeign coinChargeFeign;

    @Override
    public BaseResult listPage(CoinChargeListPageReq req) {
        FeignCoinChargeListPageReq feignCoinChargeListPageReq = new FeignCoinChargeListPageReq();
        BeanUtils.copyProperties(req, feignCoinChargeListPageReq);
        return coinChargeFeign.listPage(feignCoinChargeListPageReq);
    }

    @Override
    public BaseResult delete(CoinChargeDeleteReq req) {
        FeignCoinChargeDeleteReq feignCoinChargeDeleteReq = new FeignCoinChargeDeleteReq();
        BeanUtils.copyProperties(req, feignCoinChargeDeleteReq);
        return coinChargeFeign.delete(feignCoinChargeDeleteReq);
    }

    @Override
    public BaseResult saveOrUpdate(CoinChargeSaveReq req) {
        FeignCoinChargeSaveReq feignCoinChargeSaveReq = new FeignCoinChargeSaveReq();
        BeanUtils.copyProperties(req, feignCoinChargeSaveReq);
        return coinChargeFeign.saveOrUpdate(feignCoinChargeSaveReq);
    }

    @Override
    public BaseResult info(CoinChargeInfoReq req) {
        FeignCoinChargeInfoReq feignCoinChargeInfoReq = new FeignCoinChargeInfoReq();
        BeanUtils.copyProperties(req, feignCoinChargeInfoReq);
        return coinChargeFeign.info(feignCoinChargeInfoReq);
    }

}
