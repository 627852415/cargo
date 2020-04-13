package com.lxtx.im.admin.web.api;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.lxtx.im.admin.service.OffsiteExchangeUserStatisticsService;
import com.lxtx.im.admin.service.request.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.ExchangeMerchantService;
import com.lxtx.im.admin.web.aop.SysLogAop;

/**
 * 换汇商家管理
 *
 * @Author: liyunhua
 * @Date: 2019/4/23
 */
@Controller
@RequestMapping("/exchange/merchant")
public class OffsiteExchangeMerchantController {

    @Autowired
    private ExchangeMerchantService exchangeMerchantService;
    @Autowired
    private OffsiteExchangeUserStatisticsService offsiteExchangeUserStatisticsService;

    /**
     * 换汇商家首页
     *
     * @param
     * @return java.lang.String
     * @author liyunhua
     * @date 2019/4/22
     */
    @SysLogAop("换汇商家首页")
    @GetMapping("/index")
    @RequiresPermissions("exchange:merchant:index")
    public String index() {
        return "offsiteExchange/exchange-merchant-index";
    }

    /**
     * 列表
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/4/23
     */
    @SysLogAop("换汇商家列表数据")
    @PostMapping(value = "/list/page")
    @ResponseBody
    @RequiresPermissions("exchange:merchant:index")
    public BaseResult listPage(ExchangeMerchantListPageReq req) {
        return exchangeMerchantService.listPage(req);
    }

    /**
     * 商家详情
     *
     * @param req
     * @param model
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/04/23
     */
    @SysLogAop("换汇商家详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("exchange:merchant:index")
    public String detail(ExchangeMerchantDetailReq req, Model model) {
        return exchangeMerchantService.detail(req, model);
    }

    /**
     * 商家审核
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/04/23
     */
    @SysLogAop(value = "换汇商家审核", monitor = true)
    @PostMapping(value = "/verify")
    @ResponseBody
    @RequiresPermissions("exchange:merchant:index")
    public BaseResult verify(ExchangeMerchantVerifyReq req) {
        return exchangeMerchantService.verify(req);
    }

    /**
     * 商家注销
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2019/04/23
     */
    @SysLogAop(value = "换汇商家注销", monitor = true)
    @PostMapping(value = "/cancel")
    @ResponseBody
    @RequiresPermissions("exchange:merchant:cancel")
    public BaseResult cancel(@RequestBody ExchangeMerchantCancelReq req) {
        return exchangeMerchantService.cancel(req);
    }

    /**
     * 换汇商家帐号启用或禁用
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "换汇商家帐号启用或禁用", monitor = true)
    @PostMapping(value = "/update/status")
    @ResponseBody
    @RequiresPermissions("exchange:merchant:update:status")
    public BaseResult updateStatus(@Valid @RequestBody ExchangeMerchantUpdateStatusReq req) {
        return exchangeMerchantService.updateStatus(req);
    }

    /**
     * 更新商家汇率
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "更新商家汇率", monitor = true)
    @PostMapping(value = "/update/merchantwaverate")
    @ResponseBody
    @RequiresPermissions("exchange:merchant:waverate:update")
    public BaseResult updateMerchantWaveRate(@RequestParam(name = "id", required = true) String id, @RequestParam(name = "merchantWaveRate", required = true)BigDecimal merchantWaveRate) {
        return exchangeMerchantService.updateMerchantWaveRate(id, merchantWaveRate);
    }

	/**
	 * 商家成交统计首页
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@SysLogAop("商家成交统计首页")
	@GetMapping(value = "/transaction/statistics/index")
	@RequiresPermissions("offsite:exchange:merchant:transaction:statistics:index")
	public String transactionStatisticsIndex(Model model) {
        offsiteExchangeUserStatisticsService.waveRateList(model);
        return "offsiteExchange/exchange-merchant-transaction-statistics-index";
	}

	/**
	 * 商家统计列表分页数据
	 * 
	 * @param req
	 * @return
	 */
	@SysLogAop("商家成交统计列表分页数据")
	@PostMapping(value = "/transaction/statistics/list/page")
	@ResponseBody
	@RequiresPermissions("offsite:exchange:merchant:transaction:statistics:index")
	public BaseResult merchantTransactionStatisticsListPage(ExchangeMerchantTransactionStatisticsListPageReq req) {
		return BaseResult.success(exchangeMerchantService.merchantTransactionStatisticsListPage(req));
	}
	
	/**
	 * 商家统计列表分页数据
	 * 
	 * @param req
	 * @return
	 */
	@SysLogAop(value = "商家成交统计列表下载", monitor = true)
	@GetMapping(value = "/transaction/statistics/export/excel")
	@ResponseBody
	@RequiresPermissions("offsite:exchange:merchant:transaction:statistics:index")
	public void merchantTransactionstatisticsExportExcel(HttpServletResponse response, ExchangeMerchantTransactionStatisticsListPageReq req) {
		exchangeMerchantService.exportExcel(response,req);
	}


    /**
     * 商家成交统计
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("商家挂单统计首页")
    @GetMapping(value = "/goods/statistics/index")
    @RequiresPermissions("offsite:exchange:merchant:goods:statistics:index")
    public String goodsStatisticsIndex(Model model) {
        offsiteExchangeUserStatisticsService.waveRateList(model);
        return "offsiteExchange/exchange-merchant-goods-statistics-index";
    }


    @SysLogAop("商家挂单列表分页数据")
    @PostMapping(value = "/goods/statistics/list/page")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:merchant:goods:statistics:index")
    public BaseResult goodsTransaction(ExchangeGoodsListPageReq req) {
        return BaseResult.success(exchangeMerchantService.merchantGoodsStatisticsListPage(req));
    }

    /**
     * 商家挂单列表分页下载
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "商家挂单列表分页下载", monitor = true)
    @GetMapping(value = "/goods/statistics/export/excel")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:merchant:goods:statistics:index")
    public void goodsTransactionExportExcel(HttpServletResponse response, ExchangeGoodsListPageReq req) {
        exchangeMerchantService.goodsTransactionExportExcel(response,req);
    }


}
