package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.PayBillCheckRecordService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * @description: 对账列表
 * @author: Ppai
 * @create: 2019-03-11 09:35
 **/
@Controller
@RequestMapping("/billcheck")
public class PayBillCheckController {

    @Autowired
    private PayBillCheckRecordService payBillCheckRecordService;

    @SysLogAop("对账列表首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("billcheck:index")
    public String toIndex() {
        return "billcheck/bill-check";
    }

    @SysLogAop("对账账单详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("billcheck:index")
    public String toDetail(String userId, Model model) {
        model.addAttribute("userId", userId);
        return "billcheck/check-detail";
    }

    /**
     * 对账列表数据
     *
     * @param payBillCheckRecordIndexReq
     * @return
     */
    @SysLogAop("对账列表数据")
    @PostMapping(value = "/list")
    @RequiresPermissions("billcheck:index")
    @ResponseBody
    public BaseResult indexList(@RequestBody PayBillCheckRecordIndexReq payBillCheckRecordIndexReq) {
        return payBillCheckRecordService.indexList(payBillCheckRecordIndexReq);
    }

    @SysLogAop(value = "修改汇率的前置操作", monitor = true)
    @PostMapping(value = "/pre/edit")
    @RequiresPermissions("billcheck:index")
    @ResponseBody
    public BaseResult preEdit(@RequestBody PayCoinRateListReq payCoinRateListReq) {
        return payBillCheckRecordService.preEdit(payCoinRateListReq);
    }

    @SysLogAop(value = "对账列表修改结算手续费", monitor = true)
    @PostMapping(value = "/edit")
    @RequiresPermissions("billcheck:index")
    @ResponseBody
    public BaseResult edit(@RequestBody PayCoinRateListReq payCoinRateListReq) {
        return payBillCheckRecordService.editRate(payCoinRateListReq);
    }

    /**
     * 跳转到账单详情页面
     *
     * @param billId
     * @param billTime
     * @param totalCount
     * @param model
     * @return
     */
    @SysLogAop("对账账单详情页面")
    @GetMapping("/bill/info")
    @RequiresPermissions("billcheck:index")
    public String toBillInfo(String billId, String billTime, String totalCount, Model model) {
        if (StringUtils.isBlank(billId)) {
            throw LxtxBizException.newException("账单ID不能为空!");
        }
        if (StringUtils.isBlank(billTime)) {
            throw LxtxBizException.newException("账单日期不能为空!");
        }
        if (StringUtils.isBlank(totalCount)) {
            throw LxtxBizException.newException("收款笔数不能为空!");
        }

        model.addAttribute("billId", billId);
        model.addAttribute("billTime", billTime);
        model.addAttribute("totalCount", totalCount);
        return "billcheck/bill-info";
    }

    /**
     * 分页查询账单列表
     *
     * @param req
     * @return
     */
    @SysLogAop("分页查询账单列表")
    @PostMapping("/bill/list")
    @RequiresPermissions("billcheck:index")
    @ResponseBody
    public BaseResult billList(BillListReq req) {
        return payBillCheckRecordService.billListPage(req);
    }

    /**
     * 分页查询账单订单列表
     *
     * @param req
     * @return
     */
    @SysLogAop("分页查询账单订单列表")
    @PostMapping("/pay/order/list")
    @RequiresPermissions("billcheck:index")
    @ResponseBody
    public BaseResult payOrderList(BillListReq req) {
        return payBillCheckRecordService.payOrderListPage(req);
    }

    @SysLogAop("分页查询对账详情")
    @PostMapping("/check/detail")
    @RequiresPermissions("billcheck:index")
    @ResponseBody
    public BaseResult checkDetail(@RequestBody PayCheckDetailReq payCheckDetailReq) {
        return payBillCheckRecordService.checkDetail(payCheckDetailReq);
    }

    /**
     * 导出excel文档
     *
     * @param response, req
     * @return void
     * @author Ppai
     * @date 2019/03/18
     */
    @SysLogAop(value = "导出商户对账列表", monitor = true)
    @GetMapping(value = "/export/list/excel")
    @RequiresPermissions("billcheck:index")
    public void exportListExcel(HttpServletResponse response, PayBillCheckRecordIndexReq payBillCheckRecordIndexReq) {
        payBillCheckRecordService.exportListExcel(response, payBillCheckRecordIndexReq);
    }

    /**
     * 导出excel文档
     *
     * @param response, req
     * @return void
     * @author Ppai
     * @date 2019/03/18
     */
    @SysLogAop(value = "导出商户对账详情", monitor = true)
    @GetMapping(value = "/export/detail/excel")
    @RequiresPermissions("billcheck:index")
    public void exportDetailExcel(HttpServletResponse response, PayCheckDetailReq req) {
        payBillCheckRecordService.exportDetailExcel(response, req);
    }
}
