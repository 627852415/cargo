package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.MerchantDepositDelReq;
import com.lxtx.im.admin.service.request.MerchantDepositListPageReq;
import com.lxtx.im.admin.service.request.MerchantDepositSaveReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * 线下换汇商家保证金信用管理
 *
 * @Author: liyunhua
 * @Date: 2019/4/19
 */
public interface MerchantDepositService {


    BaseResult listPage(MerchantDepositListPageReq req);

    void add(Model model);

    void detail(MerchantDepositSaveReq saveReq, Model model);

    BaseResult save(MerchantDepositSaveReq req, HttpSession session);

    BaseResult del(MerchantDepositDelReq req);

}
