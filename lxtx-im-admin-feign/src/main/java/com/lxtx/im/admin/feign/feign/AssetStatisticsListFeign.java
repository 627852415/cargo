package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.AssetStatisticsDailyListFallBackFactory;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsDailyListDownReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsDalyListDetailReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsExportReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 快照管理
 *
 * @author CaiRH
 * @since 2019-05-31
*/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = AssetStatisticsDailyListFallBackFactory.class)
public interface AssetStatisticsListFeign {

    @PostMapping(value = "/admin/asset/coin/list/page")
    BaseResult listPage(FeignAssetStatisticsListReq listPageReq);

    @PostMapping(value = "/admin/asset/coin/detail")
    BaseResult detail(FeignAssetStatisticsDalyListDetailReq feignReq);

    @PostMapping(value = "/admin/asset/coin/excel")
    BaseResult createDetailExcel(FeignAssetStatisticsExportReq feignReq);

    @PostMapping(value = "/admin/asset/coin/list/down")
    BaseResult downData(FeignAssetStatisticsDailyListDownReq feignReq);

}
