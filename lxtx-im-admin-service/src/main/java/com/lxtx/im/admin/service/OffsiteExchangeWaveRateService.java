package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.BasePageReq;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.OffsiteExchangeWaveAreaRateListResp;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 换汇汇率
 *
 * @author CaiRH
 * @since 2019-05-24 14:05
 **/
public interface OffsiteExchangeWaveRateService {

    void add(Model model);

    void detail(OffsiteExchangeWaveRateIdReq saveReq, Model model);

    BaseResult save(OffsiteExchangeWaveRateSaveReq req);

    BaseResult listPage(BasePageReq req);

    BaseResult del(OffsiteExchangeWaveRateIdReq req);

    /**
     * 地区浮动汇率
     * @param req
     * @return
     */
    List<OffsiteExchangeWaveAreaRateListResp> areaRateList(OffsiteExchangeWaveAreaRateListReq req);

    /**
     * 更新地区浮动汇率
     * @param req
     */
    BaseResult areaRateUpdate(OffsiteExchangeWaveRateAreaUpdateReq req);

    BaseResult getCurrentRate(CurrencyExchangeRateReq req);
}
