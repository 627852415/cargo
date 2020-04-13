package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.SalaryFeign;
import com.lxtx.im.admin.feign.request.FeignSalaryBindEmployeeReq;
import com.lxtx.im.admin.service.SalaryBindService;
import com.lxtx.im.admin.service.request.SalaryBindEmplyueeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/4 16:54
 */
@Service
public class SalaryBindServiceImpl implements SalaryBindService {

    @Autowired
    private SalaryFeign salaryFeign;

    @Override
    public BaseResult bindEmolyee(SalaryBindEmplyueeReq req) {
        FeignSalaryBindEmployeeReq feignSalaryBindEmployeeReq = new FeignSalaryBindEmployeeReq();
        feignSalaryBindEmployeeReq.setCompanyId(req.getCompanyId());
        return salaryFeign.bindEmoloyee(feignSalaryBindEmployeeReq);
    }
}