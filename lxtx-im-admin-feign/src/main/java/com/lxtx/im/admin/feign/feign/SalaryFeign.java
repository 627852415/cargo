package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.SalaryFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignSalaryBindEmployeeReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lxtx-salary", fallbackFactory = SalaryFeignFallbackFactory.class)
public interface SalaryFeign {

    /**
     * 余额宝设置代理上级关系
     * @return
     */
    @PostMapping(value = "/v1/employee/auto/bind")
    BaseResult bindEmoloyee(FeignSalaryBindEmployeeReq req);


}
