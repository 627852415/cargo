package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.AssetStatisticsListFeign;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsDailyListDownReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsDalyListDetailReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsExportReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author CaiRH
 * @since 2019-05-31
 */
@Component
@Slf4j
@Setter
public class AssetStatisticsDailyListFallback implements AssetStatisticsListFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignAssetStatisticsListReq listPageReq) {
        log.error("feign 获取列表数据", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult detail(FeignAssetStatisticsDalyListDetailReq feignReq) {
        log.error("feign 获取详情数据失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult createDetailExcel(FeignAssetStatisticsExportReq feignReq) {
        log.error("feign 创建excle异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult downData(FeignAssetStatisticsDailyListDownReq feignReq) {
        log.error("feign 下载失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }


}