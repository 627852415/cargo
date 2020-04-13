package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 对账管理
 * @author: Ppai
 * @create: 2018-03-12 09:55
 **/
public interface PayBillCheckRecordService {

    /**
     * 取得商户列表
     *
     * @param payBillCheckRecordIndexReq
     * @return
     */
    BaseResult indexList(PayBillCheckRecordIndexReq payBillCheckRecordIndexReq);

    /**
     * 修改结算手续费
     *
     * @param payCoinRateListReq
     * @return
     */
    BaseResult editRate(PayCoinRateListReq payCoinRateListReq);

    /**
     * 修改汇率的前置操作
     *
     * @param payBillCheckRecordReq
     * @return
     */
    BaseResult preEdit(PayCoinRateListReq payBillCheckRecordReq);

    /**
     * 分页查询对账单列表
     *
     * @param req
     * @return
     */
    BaseResult billListPage(BillListReq req);

    /**
     * 分页查询对账单订单列表
     *
     * @param req
     * @return
     */
    BaseResult payOrderListPage(BillListReq req);

    /**
     * 分页查询对账详情
     *
     * @return
     */
    BaseResult checkDetail(PayCheckDetailReq req);

    /**
     * 导出对账列表信息
     * @param response
     * @param payBillCheckRecordIndexReq
     */
    void exportListExcel(HttpServletResponse response,PayBillCheckRecordIndexReq payBillCheckRecordIndexReq);

    /**
     * 导出对账详情信息
     * @param response
     * @param req
     */
    void exportDetailExcel(HttpServletResponse response,PayCheckDetailReq req);
}
