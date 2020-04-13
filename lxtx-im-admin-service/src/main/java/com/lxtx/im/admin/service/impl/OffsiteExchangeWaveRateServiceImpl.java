package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.im.admin.feign.feign.GlobalCodeFeign;
import com.lxtx.im.admin.feign.feign.LegalCoinFeign;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeWaveRateFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.OffsiteExchangeWaveRateService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.utils.OffsiteExchangeUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 换汇汇率
 *
 * @author CaiRH
 * @since 2019-05-24 14:07
 **/
@Service
public class OffsiteExchangeWaveRateServiceImpl implements OffsiteExchangeWaveRateService {

    // 中国','泰国','柬埔寨','缅甸','菲律宾','新加坡','马来西亚
    private static List<String> DEFAULT_COUNTRY_CODES = Lists.newArrayList("CN", "TH", "KH", "MY", "PH", "SG", "MM");
    private static BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @Autowired
    private LegalCoinFeign legalCoinFeign;
    @Autowired
    private OffsiteExchangeWaveRateFeign offsiteExchangeWaveRateFeign;
    @Autowired
    private GlobalCodeFeign globalCodeFeign;

    @Override
    public void add(Model model) {
        setLegalCoinData(model);
        setRebateData(model, null);
        setMerchantOutPayment(model, null);
    }

    @Override
    public void detail(OffsiteExchangeWaveRateIdReq idReq, Model model) {
        //详情
        FeignOffsiteExchangeWaveRateIdReq feignReq = new FeignOffsiteExchangeWaveRateIdReq();
        BeanUtils.copyProperties(idReq, feignReq);
        BaseResult result = offsiteExchangeWaveRateFeign.detail(feignReq);
        OffsiteExchangeWaveRateModelResp waveRateModelResp = JSONObject.parseObject(JSON.toJSONString(result.getData()), OffsiteExchangeWaveRateModelResp.class);
        model.addAttribute("waveRateModel", waveRateModelResp);

        //法币信息
        setLegalCoinData(model);
        setRebateData(model, waveRateModelResp.getRebateIdList());
        setMerchantOutPayment(model, waveRateModelResp.getMerchantOutPaymentIdList());
    }

    private void setMerchantOutPayment(Model model, List<String> merchantOutPaymentIdList) {
        BaseResult rebateListResult = offsiteExchangeWaveRateFeign.rebateList(new FeignOffsiteExchangeWaveRateRebateReq());
        if(rebateListResult.isSuccessAndDataNotNull()){
            if(CollectionUtils.isEmpty(merchantOutPaymentIdList)){
                Map<String, Object> map = (Map<String, Object>) rebateListResult.getData();
                model.addAttribute("merchantOutPaymentList", map.get("list"));
            }else{
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(rebateListResult.getData()));
                List<OffsiteExchangeRateSimpleResp> list = JSONObject.parseArray(jsonObject.getString("list"), OffsiteExchangeRateSimpleResp.class);
                list.forEach(resp -> {
                    boolean chooseFlag = merchantOutPaymentIdList.contains(resp.getId());
                    resp.setChooseFlag(chooseFlag);
                });
                model.addAttribute("merchantOutPaymentList", list);
            }
        }
    }

    private void setLegalCoinData(Model model) {
        BaseResult legalCoinListResult = legalCoinFeign.list();
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(legalCoinListResult.getData()));
        if (jsonObject != null) {
            List<LegalCoinResp> coinResps = JSONObject.parseArray(jsonObject.getString("list"), LegalCoinResp.class);
            model.addAttribute("coinList", coinResps);
        }
    }

    private void setRebateData(Model model, List<String> rateIdList){
        BaseResult rebateListResult = offsiteExchangeWaveRateFeign.rebateList(new FeignOffsiteExchangeWaveRateRebateReq());
        if(rebateListResult.isSuccessAndDataNotNull()){
            if(CollectionUtils.isEmpty(rateIdList)){
                Map<String, Object> map = (Map<String, Object>) rebateListResult.getData();
                model.addAttribute("rebateList", map.get("list"));
            }else{
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(rebateListResult.getData()));
                List<OffsiteExchangeRateSimpleResp> list = JSONObject.parseArray(jsonObject.getString("list"), OffsiteExchangeRateSimpleResp.class);
                list.forEach(resp -> {
                    boolean chooseFlag = rateIdList.contains(resp.getId());
                    resp.setChooseFlag(chooseFlag);
                });
                model.addAttribute("rebateList", list);
            }
        }
    }

    @Override
    public BaseResult save(OffsiteExchangeWaveRateSaveReq req) {
        FeignOffsiteExchangeWaveRateSaveReq feignReq = new FeignOffsiteExchangeWaveRateSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeWaveRateFeign.save(feignReq);
    }

    @Override
    public BaseResult listPage(BasePageReq req) {
        FeignBasePageReq feignReq = new FeignBasePageReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeWaveRateFeign.listPage(feignReq);
    }

    @Override
    public BaseResult del(OffsiteExchangeWaveRateIdReq req) {
        FeignOffsiteExchangeWaveRateIdReq feignReq = new FeignOffsiteExchangeWaveRateIdReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeWaveRateFeign.del(feignReq);
    }

    @Override
    public List<OffsiteExchangeWaveAreaRateListResp> areaRateList(OffsiteExchangeWaveAreaRateListReq req) {
        // 地区汇率
        FeignOffsiteExchangeWaveAreaRateListReq feign = new FeignOffsiteExchangeWaveAreaRateListReq();
        BeanUtils.copyProperties(req, feign);
        BaseResult areaWaveRateResult = offsiteExchangeWaveRateFeign.areaRateList(feign);
        FeignOffsiteExchangeWaveRateRebateReq feignReq = new FeignOffsiteExchangeWaveRateRebateReq();
        feignReq.setWaveRateId(req.getWaveRateId());
        BaseResult rebateListResult = offsiteExchangeWaveRateFeign.rebateList(feignReq);
        JSONObject rebateListResultJsonObject = JSONObject.parseObject(JSON.toJSONString(rebateListResult.getData()));
        List<OffsiteExchangeRateSimpleResp> list = JSONObject.parseArray(rebateListResultJsonObject.getString("list"), OffsiteExchangeRateSimpleResp.class);

        Map<String, OffsiteExchangeWaveRateAreaResp> waveRateAreaRespMap = new HashMap<>();
        if (areaWaveRateResult.isSuccessAndDataNotNull()) {
            Map map = (Map)areaWaveRateResult.getData();
            List<OffsiteExchangeWaveRateAreaResp> rateAreaRespList = JSON.parseObject(JSON.toJSONString(map.get("list")), new TypeReference<List<OffsiteExchangeWaveRateAreaResp>>() {});
            waveRateAreaRespMap = rateAreaRespList.stream().collect(Collectors.toMap(OffsiteExchangeWaveRateAreaResp::getCountryCode, Function.identity()));
        }

        // 国际简码
        BaseResult globalCodeResult = globalCodeFeign.list(new FeignGlobalCodeByCountryReq(DEFAULT_COUNTRY_CODES));
        List<GlobalCodeResp> globalCodeRespList = null;
        if (globalCodeResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(globalCodeResult.getData()));
            globalCodeRespList = JSONObject.parseArray(jsonObject.getString("list"), GlobalCodeResp.class);
        }

        // 实时汇率
        BigDecimal realTimeRate = getRealTimeRate(req.getTargetCoinId(), req.getSourceCoinId());

        List<OffsiteExchangeWaveAreaRateListResp> resultList = new ArrayList<>();

        for (String countryCode : DEFAULT_COUNTRY_CODES) {
            OffsiteExchangeWaveRateAreaResp areaResp = waveRateAreaRespMap.get(countryCode);
            BigDecimal waveRate = getAreaWaveRateByCountryCode(areaResp, BigDecimal.ZERO);

            BigDecimal exchangeRate;
            if (areaResp != null && areaResp.getRateType() == 1) {
                exchangeRate = NumberUtils.keepScale4RoundDown(waveRate, 4);;
            } else {
                exchangeRate = caclExchangeRate(realTimeRate, waveRate);
            }

            OffsiteExchangeWaveAreaRateListResp resp = new OffsiteExchangeWaveAreaRateListResp();
            resp.setWaveRateId(req.getWaveRateId());
            resp.setCountryCode(countryCode);
            resp.setCountryName(getCountryName(globalCodeRespList, countryCode));
            if(req.getExchangeRateModel().equals(0)){
                resp.setExchangeCoinName(req.getPayCoinName());
                resp.setPayCoinName(req.getExchangeCoinName());
            }else{
                resp.setExchangeCoinName(req.getExchangeCoinName());
                resp.setPayCoinName(req.getPayCoinName());
            }
            resp.setRealTimeRate(realTimeRate);
            resp.setWaveRate(waveRate);
            resp.setExchangeRate(exchangeRate);
            resp.setRateType(areaResp == null ? null : areaResp.getRateType());

            List<OffsiteExchangeWaveAreaRateRebateSimpleResp> simpleRespList = new ArrayList<>(list.size());
            list.forEach(tmp -> {
                OffsiteExchangeWaveAreaRateRebateSimpleResp simpleResp = new OffsiteExchangeWaveAreaRateRebateSimpleResp();
                BeanUtils.copyProperties(tmp, simpleResp);
                if(Objects.nonNull(areaResp)){
                    boolean chooseFlag = areaResp.getRebateIdList().contains(tmp.getId());
                    simpleResp.setChooseFlag(chooseFlag);
                }
                simpleRespList.add(simpleResp);
            });
            resp.setRebateList(simpleRespList);
            resultList.add(resp);
        }

        return resultList;
    }

    @Override
    public BaseResult areaRateUpdate(OffsiteExchangeWaveRateAreaUpdateReq req) {
        for(OffsiteExchangeWaveRateAreaUpdateReq.WaveRateAreaUpdate waveRateArea : req.getList()) {
            // 100 > 浮动汇率 > 0
            if (waveRateArea.getWaveRate().compareTo(BigDecimal.ZERO) != 1) {
                throw LxtxBizException.newException("浮动汇率不正确,wave=" + waveRateArea.getWaveRate().toPlainString());
            }

            BigDecimal wave = null;
            if (waveRateArea.getRateType() == 0) {
                wave = NumberUtils.divide(waveRateArea.getWaveRate(), new BigDecimal("100"), 8);
            } else if (waveRateArea.getRateType() == 1) {
                wave = waveRateArea.getWaveRate();
            }
            waveRateArea.setWaveRate(wave);
        }

        FeignOffsiteExchangeWaveRateAreaUpdateReq feign = new FeignOffsiteExchangeWaveRateAreaUpdateReq();
        BeanUtils.copyProperties(req, feign);
        return offsiteExchangeWaveRateFeign.areaRateUpdate(feign);
    }

    @Override
    public BaseResult getCurrentRate(CurrencyExchangeRateReq req) {
        return offsiteExchangeWaveRateFeign.realTimeRate(new FeignOffsiteExchangeRealTimeRateReq(req.getSourceCoin(), req.getTargetCoin()));
    }

    private String getCountryName(List<GlobalCodeResp> globalCodeRespList, String countryCode) {
        if (CollectionUtils.isNotEmpty(globalCodeRespList)) {
            for (GlobalCodeResp resp : globalCodeRespList) {
                if (Objects.equals(resp.getCountryCode(), countryCode)) {
                    return resp.getCnName();
                }
            }
        }

        return countryCode;
    }

    /**
     * 获取地区浮动汇率，如果没设置则使用默认浮动汇率
     *
     * @return
     */
    private BigDecimal getAreaWaveRateByCountryCode(OffsiteExchangeWaveRateAreaResp rateAreaResp, BigDecimal defaultWaveRate) {
        BigDecimal areaWaveRate = defaultWaveRate;
        if (defaultWaveRate == null || BigDecimal.ZERO.compareTo(defaultWaveRate) == 0) {
            areaWaveRate = null;
        }
        if (rateAreaResp != null) {
            areaWaveRate = rateAreaResp.getWaveRate();
        }
        return areaWaveRate;
    }

    /**
     * 获取实时汇率
     * @param currenctIn
     * @param currencyOut
     * @return
     */
    private BigDecimal getRealTimeRate(String currenctIn, String currencyOut) {
        BaseResult baseResult = offsiteExchangeWaveRateFeign.realTimeRate(new FeignOffsiteExchangeRealTimeRateReq(currenctIn, currencyOut));
        if (baseResult.isSuccessAndDataNotNull()) {
            return new BigDecimal(baseResult.getData().toString());
        } else {
            throw LxtxBizException.newException(baseResult.getMsg());
        }

    }

    /**
     * 计算实时汇率
     * @return
     */
    private BigDecimal caclExchangeRate(BigDecimal realTimeRate, BigDecimal waveRate) {
        if (waveRate == null) {
            return NumberUtils.keepScale4RoundDown(realTimeRate, 4);
        }
        BigDecimal persent = NumberUtils.add(BigDecimal.ONE, waveRate);
        BigDecimal result = NumberUtils.multiply(realTimeRate, persent);
        result = NumberUtils.keepScale4RoundDown(result, 4);
        return result;
    }
}
