package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.PayOrderFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignBillListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Czh
 * Date: 2019-03-12 14:57
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = PayOrderFeignFallbackFactory.class)
public interface PayOrderFeign {

    @PostMapping("/pay/order/bill/list")
    BaseResult payOrderListPage(FeignBillListReq feignReq);
}
