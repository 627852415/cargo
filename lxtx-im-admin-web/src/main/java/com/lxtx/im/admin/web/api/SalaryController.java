package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.SalaryBindService;
import com.lxtx.im.admin.service.request.SalaryBindEmplyueeReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryBindService salaryService;

    @SysLogAop("代发工资自动绑定员工")
    @RequestMapping("/bind/emplyee")
    @ResponseBody
    public BaseResult bindEmployee(@RequestBody SalaryBindEmplyueeReq req) {
        return salaryService.bindEmolyee(req);
    }
}
