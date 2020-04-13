package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.WalletFeign;
import com.lxtx.im.admin.feign.request.FeignRedPacketCompatibleOldDataReq;
import com.lxtx.im.admin.service.request.RedPacketCompatibleOldDataReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/v2/red/packet")
public class RedPacketV2Controller {

    @Resource
    WalletFeign walletFeign;

    /**
     * 修复红包新旧数据
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "修复红包新旧数据", monitor = true)
    @PostMapping(value = "/compatibleOldData")
    @ResponseBody
    public BaseResult compatibleOldData(@Valid @RequestBody RedPacketCompatibleOldDataReq req) {
        FeignRedPacketCompatibleOldDataReq feign = new FeignRedPacketCompatibleOldDataReq();
        feign.setStartDate(req.getStartDate());
        return walletFeign.compatibleOldData(feign);
    }
}
