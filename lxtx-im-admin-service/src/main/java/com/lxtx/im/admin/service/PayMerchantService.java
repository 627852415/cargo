package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.PayMerchantDetailReq;
import com.lxtx.im.admin.service.request.PayMerchantListPageReq;
import com.lxtx.im.admin.service.request.PayMerchantVerifyReq;
import org.springframework.ui.Model;
import com.lxtx.im.admin.service.request.PayMerchantUpdateStatusReq;

/**
* @description: 商家管理
* @author:   CXM
* @create:   2019-03-11 15:25
*/
public interface PayMerchantService {
    /**
     * 获取空投列表数据
     *
     * @param req
     * @return
     */
    BaseResult listPage(PayMerchantListPageReq req);

    /**
     * 更新商家状态
     *
     * @param req
     * @return
     */
    BaseResult updateStatus(PayMerchantUpdateStatusReq req);

    String detail(PayMerchantDetailReq req, Model model);

    String edit(PayMerchantDetailReq req, Model model);

    BaseResult verify(PayMerchantVerifyReq req);

}
