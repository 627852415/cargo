package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.CallBackFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignFastExchangeNoticeReq;
import com.lxtx.im.admin.feign.request.FeignOtcNoticeReq;
import com.lxtx.im.admin.feign.request.FeignSixMerNoticeReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tangdy
 * @Date 2018-08-029
 * @Description
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = CallBackFeignFallbackFactory.class)
public interface CallBackFeign {

    /**
     * 处理6x资金回调
     *
     * @param userReq
     * @return
     */
    @PostMapping(value = "/callback/six")
    BaseResult sixCallBack(FeignSixMerNoticeReq userReq);

    /**
     * otc 订单变动回调
     *
     * @param feignOtcNoticeReq
     * @return
     */
    @PostMapping(value = "/callback/six/otc")
    BaseResult processOrderCallBack(FeignOtcNoticeReq feignOtcNoticeReq);

    /**
     * 闪兑订单变动回调
     *
     * @param fastExchangeNoticeReq
     * @return
     */
    @PostMapping(value = "/callback/six/fex")
    BaseResult fexOrderNotice(FeignFastExchangeNoticeReq fastExchangeNoticeReq);

}
