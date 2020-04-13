package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.WalletUserCoinService;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeTopgateReq;
import com.lxtx.im.admin.service.request.UserReq;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * @Author liyunhua
 * @Date 2018-09-01 0001
 */
@Controller
@RequestMapping("/user/coin")
public class WalletUserCoinController {

    @Autowired
    private WalletUserCoinService walletUserCoinService;

    /**
     * 钱包详情
     *
     * @param
     * @return java.lang.String
     * @author liyunhua
     * @date 2018-08-30 0030
     */
    @SysLogAop("钱包首页")
    @GetMapping(value = "/index")
    @RequiresPermissions("user:coin:index")
    public String index(Model model) {
        List<CoinResp> coinResps = walletUserCoinService.findAllCoin();
        model.addAttribute("coins", coinResps);
        return "admin/wallet-user-coin";
    }

    @SysLogAop("钱包详情页")
    @GetMapping(value = "/detail")
    @RequiresPermissions("user:coin:index")
    public String detail(Model model, UserReq userReq) {
        model.addAttribute("userId", userReq.getUserId());
        return "admin/wallet-user-coin";
    }

    @SysLogAop("钱包列表数据")
    @PostMapping(value = "/list")
    @RequiresPermissions("user:coin:index")
    @ResponseBody
    public BaseResult list(UserReq userReq, HttpSession session) {
        return walletUserCoinService.list(userReq, session);
    }

    @SysLogAop(value = "钱包列表下载", monitor = true)
    @GetMapping(value = "/download")
    @RequiresPermissions("user:coin:index")
    public void downloadList(HttpServletResponse response, HttpSession session, UserReq userReq, Locale locale) {
        walletUserCoinService.download(response, session, userReq, locale);
    }

    @SysLogAop(value = "钱包列表导出锁")
    @PostMapping(value = "/downloadLock")
    @ResponseBody
    @RequiresPermissions("user:coin:index")
    public BaseResult downloadLockFriends(UserReq userReq, HttpSession session, Locale locale) {
        return walletUserCoinService.downloadLock(userReq, session, locale);
    }

//    /**
//     * 更新用户币种资产
//     *
//     * @param userCoinUpdateReq
//     * @param session
//     * @return
//     * @since CaiRH
//     */
//    @SysLogAop("更新用户币种资产")
//    @PostMapping(value = "/update")
//    @ResponseBody
//    public BaseResult update(@Valid @RequestBody UserCoinUpdateReq userCoinUpdateReq, HttpSession session) {
//        return walletUserCoinService.update(userCoinUpdateReq, session);
//    }

}
