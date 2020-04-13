package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.YebAssetsStatisticsFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsCreateReq;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsDownReq;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsPageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 余额宝资产统计列表
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = YebAssetsStatisticsFeignFallbackFactory.class)
public interface YebAssetsStatisticsFeign {

    /**
     * 分页查询余额宝资产统计列表
     * @param feignReq
     * @return
     */
    @PostMapping("/admin/yeb/assets/statistic/page")
    BaseResult page(FeignYebAssetsStatisticsPageReq feignReq);

    /**
     * 新建余额宝资产统计表
     * @param feignReq
     * @return
     */
    @PostMapping("/admin/yeb/assets/statistic/create")
    BaseResult create(FeignYebAssetsStatisticsCreateReq feignReq);

    /**
     * 下载余额宝资产统计表
     * @param feignReq
     * @return
     */
    @PostMapping("/admin/yeb/assets/statistic/down")
    BaseResult down(FeignYebAssetsStatisticsDownReq feignReq);
}
