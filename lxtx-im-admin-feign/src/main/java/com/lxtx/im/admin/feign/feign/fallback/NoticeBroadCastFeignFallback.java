package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.NoticeBroadCastFeign;
import com.lxtx.im.admin.feign.request.NoticeBroadCastFeignReq;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author PengPai
 * Date: Created in 14:18 2020/2/24
 */
@Slf4j
@Data
@Component
public class NoticeBroadCastFeignFallback implements NoticeBroadCastFeign {

    private Throwable cause;

    @Override
    public BaseResult page(NoticeBroadCastFeignReq req) {
        log.error("NoticeBroadCastFeign广播查询分页列表异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult list(NoticeBroadCastFeignReq req) {
        log.error("NoticeBroadCastFeign广播查询列表异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult deleteByIds(NoticeBroadCastFeignReq req) {
        log.error("NoticeBroadCastFeign根据ID集合删除异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult deleteByCondition(NoticeBroadCastFeignReq req) {
        log.error("NoticeBroadCastFeign根据条件删除异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult insertOrUpdate(NoticeBroadCastFeignReq req) {
        log.error("NoticeBroadCastFeign新增或修改异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
