package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.NoticeCommandFeign;
import com.lxtx.im.admin.feign.request.NoticeCommandFeignReq;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author PengPai
 * Date: Created in 14:03 2020/2/24
 */
@Slf4j
@Data
@Component
public class NoticeCommandFeignFallback implements NoticeCommandFeign {

    private Throwable cause;

    @Override
    public BaseResult page(NoticeCommandFeignReq req) {
        log.error("NoticeCommandFeign查询指令分页列表异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult list(NoticeCommandFeignReq req) {
        log.error("NoticeCommandFeign查询指令列表异常", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult deleteByIds(NoticeCommandFeignReq req) {
        log.error("NoticeCommandFeign根据ID批量删除", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult deleteByCondition(NoticeCommandFeignReq req) {
        log.error("NoticeCommandFeign根据条件删除", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }

    @Override
    public BaseResult insertOrUpdate(NoticeCommandFeignReq req) {
        log.error("NoticeCommandFeign新增或更新记录", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
