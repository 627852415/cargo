package com.lxtx.im.admin.service.transaction;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.TransferFriendsDetailReq;
import com.lxtx.im.admin.service.request.TransferFriendsReq;

/**
 * 
 * @Description 好友转账业务接口
 * @author qing 
 * @date: 2019年11月20日 下午2:58:54
 */
public interface TransferFriendsService {
	
	/**
     * 获取列表数据
     *
     * @param req
     * @param session
     * @return
     */
    BaseResult listPage(TransferFriendsReq req, HttpSession session);
    
    /**
     * 获取文件导出锁
     *
     * @param req
     * @param session
     */
    BaseResult downloadLock(TransferFriendsReq req, HttpSession session);
    
    /**
     * 导出文件
     *
     * @param response
     * @param req
     */
    void downloadList(TransferFriendsReq req, HttpSession session, HttpServletResponse response);
    
    /**
     * 
     * @Description 导出全部文件
     * @param req
     * @param session
     * @param response
     */
    void downloadListAll(TransferFriendsReq req, HttpSession session, HttpServletResponse response);
    
    /**
     * 查看详情
     *
     * @param req
     * @param model
     * @return
     */
	String detail(TransferFriendsDetailReq req, Model model);

}
