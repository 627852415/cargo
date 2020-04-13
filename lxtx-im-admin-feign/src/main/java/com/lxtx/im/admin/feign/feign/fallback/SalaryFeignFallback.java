package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.SalaryFeign;
import com.lxtx.im.admin.feign.request.FeignSalaryBindEmployeeReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Setter
@Slf4j
public class SalaryFeignFallback implements SalaryFeign {
    private Throwable cause;

    @Override
    public BaseResult bindEmoloyee(FeignSalaryBindEmployeeReq req) {
        log.error("feign 代发工资绑定员工失败", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
