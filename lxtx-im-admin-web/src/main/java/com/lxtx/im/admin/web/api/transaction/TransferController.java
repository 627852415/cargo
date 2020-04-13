package com.lxtx.im.admin.web.api.transaction;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.transaction.SalaryService;
import com.lxtx.im.admin.service.transaction.ScanPayService;
import com.lxtx.im.admin.service.transaction.TransferFriendsService;
import com.lxtx.im.admin.service.transaction.TransferWalletService;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @Description 转账控制类
 * @author qing 
 * @date: 2019年11月24日 下午8:58:06
 */
@Controller
@RequestMapping("/transaction/transfer")
public class TransferController {

	//好友转账
	@Autowired
    private TransferFriendsService transferFriendsService;
	
	//钱包转账
	@Autowired
    private TransferWalletService transferWalletService;

	@Autowired
	private ScanPayService scanPayService;

	@Autowired
	private SalaryService salaryService;
	
    /**
     * 
     * @Description 跳转好友转账列表
     * @return
     */
    @SysLogAop("跳转交易流水列表")
    @GetMapping(value = "/friends/index")
    @RequiresPermissions("transaction:friends:index")
    public String toIndexFriends() {
        return "transaction/transfer-friends-index";
    }


    /**
     * 
     * @Description 查询好友转账列表数据
     * @param req
     * @param session
     * @return
     */
    @SysLogAop("查询好友转账列表数据")
    @PostMapping(value = "/friends/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:friends:index")
    public BaseResult listPageFriends(TransferFriendsReq req, HttpSession session) {
        return transferFriendsService.listPage(req, session);
    }
    
    /**
     * 
     * @Description 获取好友转账列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取好友转账列表导出锁")
    @PostMapping(value = "/friends/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:friends:index")
    public BaseResult downloadLockFriends(TransferFriendsReq req, HttpSession session) {
    	return transferFriendsService.downloadLock(req,session);
    }


    /**
     * 
     * @Description 好友转账列表导出
     * @param req
     * @param session
     * @param response
     */
    @SysLogAop(value = "好友转账列表导出")
    @GetMapping(value = "/friends/download/list")
    @RequiresPermissions("transaction:friends:index")
    public void downloadListFriends(TransferFriendsReq req, HttpSession session, HttpServletResponse response) {
    	transferFriendsService.downloadList(req, session, response);
    }
    
    /**
     * 
     * @Description 好友转账列表导出全部
     * @param req
     * @param session
     * @param response
     */
    @SysLogAop(value = "好友转账列表导出全部")
    @GetMapping(value = "/friends/download/listAll")
    @RequiresPermissions("transaction:friends:index")
    public void downloadListAllFriends(TransferFriendsReq req, HttpSession session, HttpServletResponse response) {
    	transferFriendsService.downloadListAll(req, session, response);
    }

    /**
     * 
     * @Description 好友转账详情
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("查询好友转账详情")
    @GetMapping(value = "/friends/detail")
    @RequiresPermissions("transaction:friends:index")
    public String detailFriends(TransferFriendsDetailReq req, Model model) {
        return transferFriendsService.detail(req, model);
    }

      /**
       * 钱包转账列表
       *
       * @return
       */
      @SysLogAop("跳转钱包转账列表")
      @GetMapping(value = "/wallet/index")
      @RequiresPermissions("transaction:wallet:index")
      public String toIndexWallet() {
          return "transaction/transfer-wallet-index";
      }


      /**
       * 钱包转账列表数据
       *
       * @param
       * @param session
       * @return
       */
      @SysLogAop("查询钱包转账列表数据")
      @PostMapping(value = "/wallet/listPage")
      @ResponseBody
      @RequiresPermissions("transaction:wallet:index")
      public BaseResult listPageWallet(TransferWalletReq req, HttpSession session) {
          return transferWalletService.listPage(req, session);
      }
      
      /**
       * 
       * @Description 获取钱包转账表导出锁
       * @param req
       * @param session
       * @return
       */
      @SysLogAop(value = "获取钱包转账列表导出锁")
      @PostMapping(value = "/wallet/downloadLock")
      @ResponseBody
      @RequiresPermissions("transaction:friends:index")
      public BaseResult downloadLockWallet(TransferWalletReq req, HttpSession session) {
      	return transferWalletService.downloadLock(req,session);
      }


      /**
       * 
       * @Description 钱包转账列表导出
       * @param response
       * @param req
       */
      @SysLogAop(value = "钱包转账列表导出")
      @GetMapping(value = "/wallet/download/list")
      @RequiresPermissions("transaction:wallet:index")
      public void downloadListWallet(TransferWalletReq req, HttpSession session, HttpServletResponse response) {
    	  transferWalletService.downloadList(req, session, response);
      }

      /**
       * 
       * @Description 钱包转账
       * @param req
       * @param model
       * @return
       */
      @SysLogAop("查询钱包转账详情")
      @GetMapping(value = "/wallet/detail")
      @RequiresPermissions("transaction:wallet:index")
      public String detailWallet(TransferWalletDetailReq req, Model model) {
          return transferWalletService.detail(req, model);
      }
      
      /**
       * 扫码闪付列表
       *
       * @return
       */
      @SysLogAop("跳转扫码闪付列表")
      @GetMapping(value = "/scan/pay/index")
      @RequiresPermissions("transaction:scan:pay:index")
      public String toIndexScanPay() {
          return "transaction/scan-pay-index";
      }
      
      /**
                       * 扫码闪付数据
       *
       * @return
       */
      @SysLogAop("扫码闪付数据")
      @PostMapping(value = "/scan/pay/listPage")
      @RequiresPermissions("transaction:scan:pay:index")
      @ResponseBody
      public BaseResult listPageScanPay(ScanPayListPageReq req, Model model, HttpSession session) {
    	  BaseResult result = scanPayService.listPage(req,session);
    	  return result;
      }
      
      /**
                        * 扫码闪付数据详情
       *
       * @return
       */
      @SysLogAop("扫码闪付数据详情")
      @GetMapping(value = "/scan/pay/detail")
      @RequiresPermissions("transaction:scan:pay:index")
      public String ScanPayDetail(ScanPayDetailReq req, Model model, HttpSession session) {
    	  return scanPayService.scanPayDetail(req,session,model);
      }
      
      /**
       * @Description 获取扫码付款列表导出锁
       * @param req
       * @param session
       * @return
       */
      @SysLogAop(value = "获取扫码付款列表导出锁")
      @PostMapping(value = "/scan/pay/downloadLock")
      @ResponseBody
      @RequiresPermissions("transaction:scan:pay:index")
      public BaseResult downloadLockScanPay(ScanPayListPageReq req, HttpSession session) {
    	  return scanPayService.downloadLock(req,session);
      }
      
      /**
       * 
       * @Description 钱包转账列表导出
       * @param response
       * @param req
       */
      @SysLogAop(value = "扫码付款列表导出")
      @GetMapping(value = "/scan/pay/download/list")
      @RequiresPermissions("transaction:wallet:index")
      public void downloadListScanPay(ScanPayListPageReq req, HttpSession session, HttpServletResponse response) {
    	  scanPayService.downloadList(req, session, response);
      }

    /**
     * 跳转代发工资转入交易首页
     * @return
     */
    @SysLogAop(value = "跳转代发工资转入交易首页")
    @GetMapping(value = "/salary/in/index")
    @RequiresPermissions("transaction:salary:in:index")
    public String salaryInIndex() {
       return "transaction/transfer-salary-in-index";
    }

    /**
     * 代发工资转入交易列表数据
     * @param req
     * @return
     */
    @SysLogAop(value = "代发工资转入交易列表数据")
    @PostMapping(value = "/salary/in/pageList")
    @RequiresPermissions("transaction:salary:in:index")
    @ResponseBody
    public BaseResult salaryInPageList(SalaryInPageListReq req) {
        return salaryService.salaryInPageList(req);
    }

    /**
     * 代发工资转入交易详情
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("代发工资转入交易详情")
    @GetMapping(value = "/salary/in/detail")
    @RequiresPermissions("transaction:salary:in:index")
    public String salaryInDetail(SalaryInDetailReq req, Model model) {
        return salaryService.salaryInDetail(req,model);
    }

    /**
     * 获取代发工资转入交易列表数据导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取代发工资转入交易列表数据导出锁")
    @PostMapping(value = "/salary/in/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:salary:in:index")
    public BaseResult downloadLockSalaryIn(SalaryInPageListReq req, HttpSession session) {
        return salaryService.salaryInDownloadLock(req,session);
    }

    /**
     * 代发工资转入交易列表导出
     * @param req
     * @param session
     * @param response
     */
    @SysLogAop(value = "代发工资转入交易列表导出")
    @GetMapping(value = "/salary/in/download/list")
    @RequiresPermissions("transaction:salary:in:index")
    public void downloadListSalaryIn(SalaryInPageListReq req, HttpSession session, HttpServletResponse response) {
        salaryService.salaryInDownloadList(req, session, response);
    }

    /**
     * 跳转代发工资转出交易首页
     * @return
     */
    @SysLogAop(value = "跳转代发工资转出交易首页")
    @GetMapping(value = "/salary/out/index")
    @RequiresPermissions("transaction:salary:out:index")
    public String salaryOutIndex() {
        return "transaction/transfer-salary-out-index";
    }

    /**
     * 代发工资转出交易列表数据
     * @param req
     * @return
     */
    @SysLogAop(value = "代发工资转出交易列表数据")
    @PostMapping(value = "/salary/out/pageList")
    @RequiresPermissions("transaction:salary:out:index")
    @ResponseBody
    public BaseResult salaryOutPageList(SalaryInPageListReq req) {
        return salaryService.salaryOutPageList(req);
    }

    /**
     * 代发工资转出交易详情
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("代发工资转出交易详情")
    @GetMapping(value = "/salary/out/detail")
    @RequiresPermissions("transaction:salary:out:index")
    public String salaryOutDetail(SalaryInDetailReq req, Model model) {
        return salaryService.salaryOutDetail(req,model);
    }

    /**
     * 获取代发工资转出交易列表数据导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取代发工资转出交易列表数据导出锁")
    @PostMapping(value = "/salary/out/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:salary:out:index")
    public BaseResult downloadLockSalaryOut(SalaryInPageListReq req, HttpSession session) {
        return salaryService.salaryOutDownloadLock(req,session);
    }

    /**
     * 代发工资转出交易列表导出
     * @param req
     * @param session
     * @param response
     */
    @SysLogAop(value = "代发工资转出交易列表导出")
    @GetMapping(value = "/salary/out/download/list")
    @RequiresPermissions("transaction:salary:out:index")
    public void downloadListSalaryOut(SalaryInPageListReq req, HttpSession session, HttpServletResponse response) {
        salaryService.salaryOutDownloadList(req, session, response);
    }
}