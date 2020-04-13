package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

public interface TopgateRechargePayWayService {

    void updateEnable(TopGateWithdrawPaywayOnOrOffReq req);

    void add(Model model, Locale locale);

    /**
     * topgate充值支付分页
     *
     * @param req
     * @return
     */
    BaseResult page(TopGateRechargePaywayPageReq req,Locale locale);

    /**
     * 添加topgate充值支付
     *
     * @param req
     * @return
     */
    BaseResult save(TopGateRechargePaywaySaveReq req);

    /**
     * 删除topgate充值支付
     *
     * @param req
     * @return
     */
    BaseResult remove(TopGateRechargePaywayRemoveReq req);

    /**
     * 查找topgate充值支付
     *
     * @param req
     * @return
     */
    void findOne(TopGateRechargePaywayFindOneReq req, Model model,Locale locale);

    /**
     * 上传支付方式logo
     *
     * @param file
     * @return
     */
    BaseResult upload(MultipartFile file) throws IOException;
}
