package com.lxtx.im.admin.service.transaction;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.TransactionDetailReq;
import com.lxtx.im.admin.service.request.TransactionRechargeExcelOutputReq;
import com.lxtx.im.admin.service.request.TransactionRechargePageReq;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 资金入账交易记录
 *
 * @author CaiRH
 * @since 2019-11-22 11:48
 **/
public interface RechargeService {

    BaseResult listPage(TransactionRechargePageReq req, HttpSession session);

    String detail(TransactionDetailReq req, Model model);

	void downloadList(HttpServletResponse response, HttpSession session, TransactionRechargeExcelOutputReq req);

	BaseResult downloadLock(HttpSession session,TransactionRechargePageReq req);
}
