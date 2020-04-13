package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CallBackFeign;
import com.lxtx.im.admin.feign.feign.NotificationReissueFeign;
import com.lxtx.im.admin.feign.request.FeignFastExchangeNoticeReq;
import com.lxtx.im.admin.feign.request.FeignNotificationReissuePageReq;
import com.lxtx.im.admin.feign.request.FeignOtcNoticeReq;
import com.lxtx.im.admin.feign.request.FeignSixMerNoticeReq;
import com.lxtx.im.admin.service.NotificationReissueService;
import com.lxtx.im.admin.service.enums.EnumNotificationReissueType;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.NotificationReissueHandleReq;
import com.lxtx.im.admin.service.request.NotificationReissuePageReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author CaiRH
 * @since 2019-06-12
 */
@Service
public class NotificationReissueServiceImpl implements NotificationReissueService {

    @Autowired
    private NotificationReissueFeign notificationReissueFeign;
    @Autowired
    private CallBackFeign callBackFeign;

    @Override
    public BaseResult listPage(NotificationReissuePageReq req, HttpSession session) {
        FeignNotificationReissuePageReq feignNotificationReissuePageReq = new FeignNotificationReissuePageReq();
        BeanUtils.copyProperties(req, feignNotificationReissuePageReq);
        return notificationReissueFeign.listPage(feignNotificationReissuePageReq);
    }

    @Override
    public BaseResult handle(NotificationReissueHandleReq req, HttpSession session) {
        EnumNotificationReissueType enumNotificationReissueType = EnumNotificationReissueType.find(req.getType());
        if (enumNotificationReissueType == null) {
            throw LxtxBizException.newException("无效的类型！");
        }

        BaseResult baseResult;

        // 更多通知类型需定义在枚举类中
        switch (enumNotificationReissueType) {
            case SIX_CALLBACK:
                FeignSixMerNoticeReq feignSixMerNoticeReq = JSONObject.parseObject(req.getParams(), FeignSixMerNoticeReq.class);
                feignSixMerNoticeReq.setNotificationId(req.getNotificationId());
                baseResult = callBackFeign.sixCallBack(feignSixMerNoticeReq);
                break;

            case OTC_CALLBACK:
                FeignOtcNoticeReq feignOtcNoticeReq = JSONObject.parseObject(req.getParams(), FeignOtcNoticeReq.class);
                feignOtcNoticeReq.setNotificationId(req.getNotificationId());
                baseResult = callBackFeign.processOrderCallBack(feignOtcNoticeReq);
                break;

            case FEX_CALLBACK:
                FeignFastExchangeNoticeReq feignFastExchangeNoticeReq = JSONObject.parseObject(req.getParams(), FeignFastExchangeNoticeReq.class);
                feignFastExchangeNoticeReq.setNotificationId(req.getNotificationId());
                baseResult = callBackFeign.fexOrderNotice(feignFastExchangeNoticeReq);
                break;

            default:
                baseResult = BaseResult.error("404", "未定义的通知处理业务");
                break;
        }

        return baseResult;
    }

}
