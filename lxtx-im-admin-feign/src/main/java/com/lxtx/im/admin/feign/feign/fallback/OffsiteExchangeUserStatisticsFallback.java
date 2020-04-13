package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeUserStatisticsFeign;
import com.lxtx.im.admin.feign.request.FeignExchangeUserStatisticsListPageReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 用户换汇统计
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-12-12
 **/
@Component
@Slf4j
@Setter
public class OffsiteExchangeUserStatisticsFallback implements OffsiteExchangeUserStatisticsFeign {

    private Throwable cause;

    @Override
    public BaseResult listPage(FeignExchangeUserStatisticsListPageReq feignReq) {
        log.error("feign 获取换汇用户统计失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

}
