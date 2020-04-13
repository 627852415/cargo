package com.lxtx.im.admin.web.api.transaction;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.TransferFriendsReq;
import com.lxtx.im.admin.service.request.YebOrderDetailReq;
import com.lxtx.im.admin.service.request.YebOrderDownloadReq;
import com.lxtx.im.admin.service.request.YebOrderListReq;
import com.lxtx.im.admin.service.transaction.YebOrderService;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 理财宝交易记录
 * </p>
 *
 * @author liboyan
 * @Date 2019-11-22 18:05
 * @Description
 */
@Controller
@RequestMapping("/transaction/yeb/order/")
public class YebOrderController {

    @Autowired
    private YebOrderService yebOrderService;
    /**
     * 理财宝订单列表
     *
     * @return
     */
    @SysLogAop("理财宝订单列表")
    @GetMapping(value = "/index")
    @RequiresPermissions("transaction:yeb:order:index")
    public String toIndex() {
        return "transaction/yeb-order-index";
    }


    /**
     * 理财宝订单列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("理财宝订单列表数据")
    @PostMapping(value = "/listPage")
    @ResponseBody
    @RequiresPermissions("transaction:yeb:order:index")
    public BaseResult listPage(YebOrderListReq req) {
        return yebOrderService.listPage(req);
    }


    /**
     * 理财宝订单列表导出
     */
    @SysLogAop(value = "理财宝订单列表导出", monitor = true)
    @GetMapping(value = "/download")
    @RequiresPermissions("transaction:yeb:order:index")
    public void downloadList(HttpServletResponse response, YebOrderDownloadReq req,HttpSession session) {
        yebOrderService.downloadList(response, req,session);
    }

    /**
     * 查看详情
     *
     * @param req
     * @param model
     * @return
     */
    @SysLogAop("查询理财宝订单详情")
    @GetMapping(value = "/detail")
    @RequiresPermissions("transaction:yeb:order:index")
    public String detail(YebOrderDetailReq req, Model model) {
        return yebOrderService.detail(req, model);
    }

    /**
     *
     * @Description 获取好友转账列表导出锁
     * @param req
     * @param session
     * @return
     */
    @SysLogAop(value = "获取理财宝订单列表导出锁", monitor = true)
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("transaction:yeb:order:index")
    public BaseResult downloadLock(YebOrderListReq req, HttpSession session) {
        return yebOrderService.downloadLock(req,session);
    }
}
