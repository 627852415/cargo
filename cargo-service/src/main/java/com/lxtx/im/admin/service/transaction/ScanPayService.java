package com.lxtx.im.admin.service.transaction;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.ScanPayDetailReq;
import com.lxtx.im.admin.service.request.ScanPayListPageReq;

public interface ScanPayService {

	/**
     * 获取列表数据
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult listPage(ScanPayListPageReq req, HttpSession session);
	
    String scanPayDetail(ScanPayDetailReq req, HttpSession session, Model model);
    
    public BaseResult downloadLock(ScanPayListPageReq req, HttpSession session);
    
    /**
     * 导出文件
     *
     * @param response
     * @param req
     */
    void downloadList(ScanPayListPageReq req, HttpSession session, HttpServletResponse response);
}
