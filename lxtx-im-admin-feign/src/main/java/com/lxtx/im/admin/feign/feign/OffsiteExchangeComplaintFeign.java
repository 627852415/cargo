package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.OffsiteExchangeComplaintFeignFallBackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
* @description:  线下汇换投诉
* @author:   CXM
* @create:   2019-04-22 15:10
*/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = OffsiteExchangeComplaintFeignFallBackFactory.class)
public interface OffsiteExchangeComplaintFeign {

    /**
     * 投诉列表（分页）
     *
     * @param req
     * @return
     */
    @PostMapping("/offsite/complaint/listPage")
    BaseResult listPage(FeignOffsiteExchangeArbitrationListPageReq req);

    /**
     * 投诉详情
     *
     * @param req
     * @return
     */
    @PostMapping("/offsite/complaint/manage/detail")
    BaseResult detail(FeignOffsiteExchangeComplaintDetailReq req);

    /**
     * 投诉记录保存
     *
     * @param feign
     * @return
     */
    @PostMapping("/offsite/complaint/record/save")
    BaseResult saveRecord(FeignOffsiteExchangeComplaintRecordSaveReq feign);

    /**
     * 投诉处理完成
     *
     * @param feign
     * @return
     */
    @PostMapping("/offsite/complaint/completed")
    BaseResult completed(FeignOffsiteExchangeComplaintCompletedReq feign);

    /**
     * 投诉撤销
     * @param feign
     * @return
     */
    @PostMapping("/offsite/complaint/revocation")
    BaseResult revocation(FeignOffsiteExchangeComplaintRevocationReq feign);
}
