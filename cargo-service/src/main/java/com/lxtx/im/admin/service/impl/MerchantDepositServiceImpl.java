package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CoinFeign;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeMerchantDepositFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.MerchantDepositService;
import com.lxtx.im.admin.service.request.MerchantDepositDelReq;
import com.lxtx.im.admin.service.request.MerchantDepositListPageReq;
import com.lxtx.im.admin.service.request.MerchantDepositSaveReq;
import com.lxtx.im.admin.service.response.CoinListResp;
import com.lxtx.im.admin.service.response.MerchantDepositResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: liyunhua
 * @Date: 2019/4/19
 */
@Service
public class MerchantDepositServiceImpl implements MerchantDepositService {


    @Autowired
    private OffsiteExchangeMerchantDepositFeign offsiteExchangeMerchantDepositFeign;
    @Autowired
    private CoinFeign coinFeign;


    @Override
    public BaseResult listPage(MerchantDepositListPageReq req) {
        FeignMerchantDepositListPageReq feignReq = new FeignMerchantDepositListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeMerchantDepositFeign.listPage(feignReq);
    }

    @Override
    public void add(Model model) {
        BaseResult coinResult = offsiteExchangeMerchantDepositFeign.getDepositCoinList();
        Map<String, Object> coinMap = (Map<String, Object>) coinResult.getData();
        String jsonResult = JSONArray.toJSONString(coinMap);
        CoinListResp coinListResp = JSONObject.parseObject(jsonResult, CoinListResp.class);
        model.addAttribute("coinList", coinListResp.getList());
    }

    @Override
    public void detail(MerchantDepositSaveReq saveReq, Model model) {
        FeignMerchantDepositSaveReq feignReq = new FeignMerchantDepositSaveReq();
        BeanUtils.copyProperties(saveReq, feignReq);
        BaseResult result = offsiteExchangeMerchantDepositFeign.detail(feignReq);
        MerchantDepositResp depositResp = JSONObject.parseObject(JSON.toJSONString(result.getData()), MerchantDepositResp.class);
        model.addAttribute("merchantDeposit", depositResp);

        BaseResult coinResult = offsiteExchangeMerchantDepositFeign.getDepositCoinList();
        Map<String, Object> coinMap = (Map<String, Object>) coinResult.getData();
        String jsonResult = JSONArray.toJSONString(coinMap);
        CoinListResp coinListResp = JSONObject.parseObject(jsonResult, CoinListResp.class);
        model.addAttribute("coinList", coinListResp.getList());
    }

    @Override
    public BaseResult save(MerchantDepositSaveReq req, HttpSession session) {
        FeignMerchantDepositSaveReq feignReq = new FeignMerchantDepositSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeMerchantDepositFeign.save(feignReq);
    }

    @Override
    public BaseResult del(MerchantDepositDelReq req) {
        FeignMerchantDepositDelReq feignReq = new FeignMerchantDepositDelReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeMerchantDepositFeign.del(feignReq);
    }
}
