package com.lxtx.im.admin.service.transaction;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.SalaryInDetailReq;
import com.lxtx.im.admin.service.request.SalaryInPageListReq;
import com.lxtx.im.admin.service.request.ScanPayListPageReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2019/12/13 15:07
 */
public interface SalaryService {
    /**
     * 获取列表数据
     *
     * @param req
     * @param
     * @return
     */
    BaseResult salaryInPageList(SalaryInPageListReq req);

    BaseResult salaryOutPageList(SalaryInPageListReq req);

    String salaryInDetail(SalaryInDetailReq req, Model model);

    BaseResult salaryInDownloadLock(SalaryInPageListReq req, HttpSession session);

    void salaryInDownloadList(SalaryInPageListReq req, HttpSession session, HttpServletResponse response);

    String salaryOutDetail(SalaryInDetailReq req, Model model);

    BaseResult salaryOutDownloadLock(SalaryInPageListReq req, HttpSession session);

    void salaryOutDownloadList(SalaryInPageListReq req, HttpSession session, HttpServletResponse response);
}
