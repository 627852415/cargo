package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.WalletOtcFeign;
import com.lxtx.im.admin.feign.request.FeignQueryOtcOrderListReq;
import com.lxtx.im.admin.feign.request.FeignUpdateOrderCheckStateReq;
import com.lxtx.im.admin.service.OtcService;
import com.lxtx.im.admin.service.request.OtcOrderReq;
import com.lxtx.im.admin.service.request.UpdateOtcOrderCheckStateReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author CaiRH
 * @since 2018-08-29
 */
@Service
public class OtcServiceImpl implements OtcService {

    @Autowired
    private WalletOtcFeign otcFeign;

    /**
     *  获取otc订单
     * @param req
     * @param session
     * @return com.lxtx.framework.common.base.BaseResult
     * @auther LiuLP
     * @date 2018/10/25 0025
     */
    @Override
    public BaseResult listPage(OtcOrderReq req, HttpSession session) {
        FeignQueryOtcOrderListReq feignReq = new FeignQueryOtcOrderListReq();
        BeanUtils.copyProperties(req,feignReq);
        return otcFeign.listPage(feignReq);
    }

    /**
     *  审核订单
     * @param req
     * @param session
     * @return com.lxtx.framework.common.base.BaseResult
     * @auther LiuLP
     * @date 2018/10/25 0025
     */
    @Override
    public BaseResult updateCheckState(UpdateOtcOrderCheckStateReq req, HttpSession session) {
        FeignUpdateOrderCheckStateReq feignReq = new FeignUpdateOrderCheckStateReq();
        BeanUtils.copyProperties(req,feignReq);
        return otcFeign.updateCheckState(feignReq);
    }
}
