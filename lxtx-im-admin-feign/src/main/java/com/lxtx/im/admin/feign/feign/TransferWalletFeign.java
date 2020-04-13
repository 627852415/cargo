package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.TransferWalletFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 
 * @Description 钱包转账远程接口
 * @author qing 
 * @date: 2019年11月21日 下午6:16:19
 */
@FeignClient(value = "lxtx-im-wallet", fallbackFactory = TransferWalletFeignFallbackFactory.class)
public interface TransferWalletFeign {
	
	/**
     * 
     * @Description 钱包转账列表
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/wallet/listPage")
    BaseResult listPage(@RequestBody FeignTransferWalletReq req);
    
    /**
     * 
     * @Description 好友转账列表数
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/wallet/listCount")
    BaseResult listCount(@RequestBody FeignTransferWalletReq req);

    /**
     * 
     * @Description 好友转账列表
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/wallet/list")
    BaseResult list(@RequestBody FeignTransferWalletReq req);
    
    
    /**
     * 
     * @Description 好友转账列表昵称
     * @param req
     * @return
     */
    @PostMapping(value = "/admin/transfer/userName/list")
    BaseResult listTransferUserNames(@RequestBody FeignTransferUserReq req);
    
    
    @PostMapping(value = "/admin/transfer/scanPayUserIds/list")
    BaseResult listTransferScanPayUserIds(@RequestBody FeignTransferScanPayWrapperReq req);
    
    

    /**
     * 
     * @Description 扫码闪付账列表
     * @param
     * @return
     */
    @PostMapping(value = "/admin/transfer/scan/pay/listPage")
    BaseResult listScanPayPage(@RequestBody FeignScanPayListPageReq feignReq);
    
    /**
     * 
     * @Description 扫码闪付账详情
     * @param
     * @return
     */
    @PostMapping(value = "/admin/transfer/scan/pay/detail")
    BaseResult scanPayDetail(@RequestBody FeignScanPayDetailReq feignReq);

    /**
     *
     * @Description 代发工资转入交易列表数据
     * @param
     * @return
     */
    @PostMapping(value = "/admin/transfer/salary/in")
    BaseResult salaryInPageList(@Valid @RequestBody FeignSalaryInPageListReq feignReq);

    /**
     *
     * @Description 代发工资转出交易列表数据
     * @param
     * @return
     */
    @PostMapping(value = "/admin/transfer/salary/out")
    BaseResult salaryOutPageList(@Valid @RequestBody FeignSalaryInPageListReq feignReq);

    /**
     *
     * @Description 代发工资转入交易详情数据
     * @param
     * @return
     */
    @PostMapping(value = "/admin/transfer/salary/in/detail")
    BaseResult salaryInDetail(@RequestBody FeignSalaryInDetailReq feignReq);

    /**
     *
     * @Description 代发工资转出交易详情数据
     * @param
     * @return
     */
    @PostMapping(value = "/admin/transfer/salary/out/detail")
    BaseResult salaryOutDetail(@RequestBody FeignSalaryInDetailReq feignReq);
}
