package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.CoinFeign;
import com.lxtx.im.admin.feign.feign.LegalCoinFeign;
import com.lxtx.im.admin.feign.request.FeignCoinReq;
import com.lxtx.im.admin.feign.request.FeignLegalCoinDelReq;
import com.lxtx.im.admin.feign.request.FeignLegalCoinListPageReq;
import com.lxtx.im.admin.feign.request.FeignLegalCoinSaveReq;
import com.lxtx.im.admin.service.LegalCoinService;
import com.lxtx.im.admin.service.request.LegalCoinDelReq;
import com.lxtx.im.admin.service.request.LegalCoinListPageReq;
import com.lxtx.im.admin.service.request.LegalCoinSaveReq;
import com.lxtx.im.admin.service.response.CoinListResp;
import com.lxtx.im.admin.service.response.LegalCoinResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 法币管理
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Service
public class LegalCoinServiceImpl implements LegalCoinService {

    @Autowired
    private LegalCoinFeign legalCoinFeign;
    @Autowired
    private CoinFeign coinFeign;


    @Override
    public BaseResult listPage(LegalCoinListPageReq req) {
        FeignLegalCoinListPageReq feignReq = new FeignLegalCoinListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        return legalCoinFeign.listPage(feignReq);
    }

    @Override
    public BaseResult save(LegalCoinSaveReq req, HttpSession session) {
        FeignLegalCoinSaveReq feignReq = new FeignLegalCoinSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return legalCoinFeign.save(feignReq);
    }

    @Override
    public BaseResult del(LegalCoinDelReq req) {
        FeignLegalCoinDelReq feignReq = new FeignLegalCoinDelReq();
        BeanUtils.copyProperties(req, feignReq);
        return legalCoinFeign.del(feignReq);
    }

    @Override
    public BaseResult detail(LegalCoinSaveReq req, Model model) {
        FeignLegalCoinSaveReq feignReq = new FeignLegalCoinSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult result = legalCoinFeign.detail(feignReq);
        LegalCoinResp coinResp = JSONObject.parseObject(JSON.toJSONString(result.getData()), LegalCoinResp.class);
        coinResp.setIcoUrlView(PropertiesUtil.getString(PropertiesUtil.FILE_VIEW_URL).concat(coinResp.getIcoUrl()));
        model.addAttribute("legalCoin", coinResp);

        FeignCoinReq coinReq = new FeignCoinReq();
        BaseResult coinResult = coinFeign.listAll(coinReq);
        Map<String, Object> coinMap = (Map<String, Object>) coinResult.getData();
        String jsonResult = JSONArray.toJSONString(coinMap);
        CoinListResp coinListResp = JSONObject.parseObject(jsonResult, CoinListResp.class);

        model.addAttribute("coinList", coinListResp.getList());
        model.addAttribute("viewUrl", PropertiesUtil.getString(PropertiesUtil.FILE_VIEW_URL));
        return result;
    }

    @Override
    public void add(Model model) {
        FeignCoinReq coinReq = new FeignCoinReq();
        BaseResult coinResult = coinFeign.listAll(coinReq);
        Map<String, Object> coinMap = (Map<String, Object>) coinResult.getData();
        String jsonResult = JSONArray.toJSONString(coinMap);
        CoinListResp coinListResp = JSONObject.parseObject(jsonResult, CoinListResp.class);
        model.addAttribute("coinList", coinListResp.getList());
    }

}
