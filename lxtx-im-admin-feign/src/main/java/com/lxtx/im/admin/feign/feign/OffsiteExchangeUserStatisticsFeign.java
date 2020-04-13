package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.OffsiteExchangeUserStatisticsFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignExchangeUserStatisticsListPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 用户换汇统计
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = OffsiteExchangeUserStatisticsFeignFallbackFactory.class)
public interface OffsiteExchangeUserStatisticsFeign {

    @PostMapping("/admin/merchant/user/statistics/list/page")
    BaseResult listPage(FeignExchangeUserStatisticsListPageReq feignReq);

}
