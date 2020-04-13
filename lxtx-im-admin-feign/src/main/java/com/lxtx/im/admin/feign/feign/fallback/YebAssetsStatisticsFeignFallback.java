package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.YebAssetsStatisticsFeign;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsCreateReq;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsDownReq;
import com.lxtx.im.admin.feign.request.FeignYebAssetsStatisticsPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 余额宝资产统计列表 feign调用出错解决
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@Component
@Setter
@Slf4j
public class YebAssetsStatisticsFeignFallback implements YebAssetsStatisticsFeign {

    private Throwable cause;

    @Override
    public BaseResult page(FeignYebAssetsStatisticsPageReq feignReq) {
        log.error("feign 余额宝资产统计列表 分页查询失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult create(FeignYebAssetsStatisticsCreateReq feignReq) {
        log.error("feign 余额宝资产统计列表 新建失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult down(FeignYebAssetsStatisticsDownReq feignReq) {
        log.error("feign 余额宝资产统计列表 下载失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}
