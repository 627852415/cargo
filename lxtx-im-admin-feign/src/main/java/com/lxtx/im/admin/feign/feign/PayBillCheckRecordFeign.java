package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.PayBillCheckRecordFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignBillListReq;
import com.lxtx.im.admin.feign.request.FeignPayBillCheckRecordEditReq;
import com.lxtx.im.admin.feign.request.FeignPayBillCheckRecordIndexReq;
import com.lxtx.im.admin.feign.request.FeignPayCheckDetailReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Czh
 * Date: 2019-03-12 14:39
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = PayBillCheckRecordFeignFallbackFactory.class)
public interface PayBillCheckRecordFeign {

    @PostMapping("/pay/bill/bill/list")
    BaseResult billListPage(FeignBillListReq feignReq);

    @PostMapping("/pay/bill/list")
    BaseResult listPage(FeignPayBillCheckRecordIndexReq feignPayBillCheckRecordIndexReq);

    @PostMapping("/pay/bill/check/detail")
    BaseResult checkDetail(FeignPayCheckDetailReq feignPayCheckDetailReq);

    @PostMapping("/pay/bill/pre/edit")
    BaseResult preEdit(FeignPayBillCheckRecordEditReq feignPayBillCheckRecordEditReq);

    @PostMapping("/pay/bill/edit")
    BaseResult editCyleAndRate(FeignPayBillCheckRecordEditReq feignPayBillCheckRecordEditReq);
}
