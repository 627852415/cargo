package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.AdviceFeedbackListPageReq;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/4 16:54
 */
public interface AdviceFeedbackService {

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    BaseResult listPage(AdviceFeedbackListPageReq req);

    /**
     * 根据ID获取详情
     *
     * @param id
     * @return
     */
    BaseResult detail(String id);

    /**
     * 导出excel
     *
     * @param response
     * @param req
     */
    void createExcel(HttpServletResponse response, AdviceFeedbackListPageReq req);
}
