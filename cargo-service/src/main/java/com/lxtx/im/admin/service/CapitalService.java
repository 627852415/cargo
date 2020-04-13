package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.CapitalDetailReq;
import com.lxtx.im.admin.service.request.CapitalExcelOutputReq;
import com.lxtx.im.admin.service.request.CapitalReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 交易流水模块业务接口
 *
 * @author CaiRH
 * @since 2018-09-27
 */
public interface CapitalService {

    /**
     * 获取交易流水列表数据
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult listPage(CapitalReq req, HttpSession session);

    /**
     * 导出文件
     *
     * @param response
     * @param capitalReq
     */
    void downloadList(HttpServletResponse response, CapitalExcelOutputReq capitalReq);

    /**
     * 查看交易流水详情
     *
     * @param req
     * @param model
     * @return
     */
    String detail(CapitalDetailReq req, Model model);
}
