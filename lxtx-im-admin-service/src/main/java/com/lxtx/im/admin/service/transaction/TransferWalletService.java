package com.lxtx.im.admin.service.transaction;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.TransferWalletDetailReq;
import com.lxtx.im.admin.service.request.TransferWalletReq;

/**
 * 
 * @Description 钱包转账业务接口
 * @author qing 
 * @date: 2019年11月20日 下午2:58:54
 */
public interface TransferWalletService {
	
	/**
	 * 
	 * @Description 获取列表数据
	 * @param req
	 * @param session
	 * @return
	 */
    BaseResult listPage(TransferWalletReq req, HttpSession session);
    
    /**
     * 
     * @Description 获取导出文件锁
     * @param req
     * @param session
     * @return
     */
    BaseResult downloadLock(TransferWalletReq req, HttpSession session);
    
    /**
     * 
     * @Description 导出文件
     * @param req
     * @param session
     * @param response
     */
    void downloadList(TransferWalletReq req, HttpSession session, HttpServletResponse response);
    
    /**
     * 查看详情
     *
     * @param req
     * @param model
     * @return
     */
	String detail(TransferWalletDetailReq req, Model model);
}
