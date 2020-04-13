package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.im.admin.feign.feign.CoinFeign;
import com.lxtx.im.admin.feign.feign.DictFeign;
import com.lxtx.im.admin.feign.feign.PlatformWithdrawConfigFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.PlatformWithdrawConfigService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.service.response.PlatformWithdrawConfigInfoResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

/**
 * 系统提现配置
 *
 * @author CaiRH
 * @since 2018-12-20
 **/
@Service
public class PlatformWithdrawConfigServiceImpl implements PlatformWithdrawConfigService {

    @Autowired
    private PlatformWithdrawConfigFeign platformWithdrawConfigFeign;
    @Autowired
    private CoinFeign coinFeign;
    @Autowired
    private DictFeign dictFeign;

    @Override
    public BaseResult listPage(PlatformWithdrawConfigListReq listReq) {
        FeignPlatformWithdrawConfigListReq req = new FeignPlatformWithdrawConfigListReq();
        BeanUtils.copyProperties(listReq, req);
        return platformWithdrawConfigFeign.listPage(req);
    }

    @Override
    public void info(PlatformWithdrawConfigSelectReq req, Model model) {
        if (StringUtils.isNotBlank(req.getId())) {
            FeignGameSelectReq selectReq = new FeignGameSelectReq();
            BeanUtils.copyProperties(req, selectReq);
            BaseResult result = platformWithdrawConfigFeign.info(selectReq);
            if (result.isSuccess() && result.getData() != null) {
                PlatformWithdrawConfigInfoResp configInfoResp = JSONObject.parseObject(JSON.toJSONString(result.getData()), PlatformWithdrawConfigInfoResp.class);
                model.addAttribute("config", configInfoResp);

                CoinResp coinResp = new CoinResp();
                coinResp.setCoinName(configInfoResp.getCoinName());
                coinResp.setId(configInfoResp.getCoinId());
                ArrayList<CoinResp> coinRespArrayList = new ArrayList<>();
                coinRespArrayList.add(coinResp);
                model.addAttribute("coins", coinRespArrayList);
            }
        } else {
            BaseResult coinBaseResult = coinFeign.listAll(new FeignCoinReq());
            if (coinBaseResult.isSuccess() && coinBaseResult.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(coinBaseResult.getData()));
                model.addAttribute("coins", JSONObject.parseArray(jsonObject.getString("list"), CoinResp.class));
            }
        }
    }

    @Override
    public BaseResult save(PlatformWithdrawConfigSaveReq saveReq) {
        FeignPlatformWithdrawConfigSaveReq req = new FeignPlatformWithdrawConfigSaveReq();
        BeanUtils.copyProperties(saveReq, req);
        return platformWithdrawConfigFeign.save(req);
    }

    @Override
    public BaseResult delete(PlatformWithdrawConfigDeleteReq deleteReq) {
        FeignPlatformWithdrawConfigDeleteReq req = new FeignPlatformWithdrawConfigDeleteReq();
        BeanUtils.copyProperties(deleteReq, req);
        return platformWithdrawConfigFeign.delete(req);
    }

    @Override
    public BaseResult limitListPage(OtcDailyLimitListPageReq req) {
        FeignDictListPageReq feignDictListPageReq = new FeignDictListPageReq();
        BeanUtils.copyProperties(req, feignDictListPageReq);
        feignDictListPageReq.setDomainName(DictConstants.DICT_DOMAIN_OTC_LIMIT_AMOUNT);
        return dictFeign.listPage(feignDictListPageReq);
    }
}
