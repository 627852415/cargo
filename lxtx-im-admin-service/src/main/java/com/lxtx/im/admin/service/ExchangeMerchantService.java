package com.lxtx.im.admin.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.OffsiteExchangeGoodsListPageResp;
import org.springframework.ui.Model;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.response.OffsiteExchangeMerchantTransactionStatisticsListPageResp;

/**
 * 线下换汇商家管理接口
 *
 * @Author: liyunhua
 * @Date: 2019/4/23
 */
public interface ExchangeMerchantService {

	BaseResult listPage(ExchangeMerchantListPageReq req);

	String detail(ExchangeMerchantDetailReq req, Model model);

	BaseResult verify(ExchangeMerchantVerifyReq req);

	BaseResult cancel(ExchangeMerchantCancelReq req);

	BaseResult updateStatus(ExchangeMerchantUpdateStatusReq req);

	BaseResult updateMerchantWaveRate(String id, BigDecimal merchantWaveRate);

	OffsiteExchangeMerchantTransactionStatisticsListPageResp merchantTransactionStatisticsListPage(ExchangeMerchantTransactionStatisticsListPageReq req);

	OffsiteExchangeGoodsListPageResp merchantGoodsStatisticsListPage(ExchangeGoodsListPageReq req);

	/**
	 * 商家成交统计列表下载
	 * 
	 * @param response
	 * @param req
	 */
	void exportExcel(HttpServletResponse response, ExchangeMerchantTransactionStatisticsListPageReq req);

	void goodsTransactionExportExcel(HttpServletResponse response, ExchangeGoodsListPageReq req);

}
