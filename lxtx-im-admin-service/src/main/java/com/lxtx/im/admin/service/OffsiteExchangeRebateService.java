package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.BasePageReq;
import com.lxtx.im.admin.feign.request.FeignStatisticsOrderReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeRebateIdReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeRebateSaveReq;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.servlet.http.HttpSession;

/**
 * 支付方式/返利
 *
 * @author CaiRH
 * @since 2019-05-28
 **/
public interface OffsiteExchangeRebateService {

    void add(Model model);

    void detail(OffsiteExchangeRebateIdReq saveReq, Model model);

    BaseResult save(OffsiteExchangeRebateSaveReq req, HttpSession session);

    BaseResult listPage(BasePageReq req);

    BaseResult del(OffsiteExchangeRebateIdReq req);

    BaseResult getRebateAmount(FeignStatisticsOrderReq req);
    
    /**
     * 上传支付方式logo
     *
     * @param file
     * @return
     */
    BaseResult upload(MultipartFile file) throws IOException;
}
