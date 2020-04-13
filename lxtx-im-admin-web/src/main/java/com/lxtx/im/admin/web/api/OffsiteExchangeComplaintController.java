package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.OffsiteExchangeComplaintService;
import com.lxtx.im.admin.service.enums.EnumOffsiteExchangeComplaint;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.OffsiteArbitrationDetailResp;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* @description:  线下汇换投诉管理
* @author:   CXM
* @create:   2019-04-22 11:55
*/
@Controller
@RequestMapping("/offsite/exchange/complaint")
public class OffsiteExchangeComplaintController {

    @Autowired
    private OffsiteExchangeComplaintService offsiteExchangeComplaintService;

    /**
     * 跳转订单主页
     *
     * @return
     */
    @SysLogAop("跳转订单主页")
    @GetMapping(value = "/index")
    @RequiresPermissions("offsite:exchange:complaint:index")
    public String toIndex() {
        return "offsiteExchangeComplaint/offsite-exchange-complaint-index";
    }

    /**
     * 投诉列表数据
     *
     * @param req
     * @return
     */
    @SysLogAop("投诉列表数据")
    @PostMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:complaint:index")
    public BaseResult list(OffsiteExchangeArbitrationListPageReq req) {
        return offsiteExchangeComplaintService.listPage(req);
    }

    /**
     * 投诉详情
     *
     * @return
     */
    @SysLogAop("投诉详情")
    @RequestMapping(value = "/detail")
    @RequiresPermissions("offsite:exchange:complaint:index")
    public String detail(OffsiteExchangeComplaintDetailReq req, Model model) {
        OffsiteArbitrationDetailResp detail = offsiteExchangeComplaintService.detail(req);
        model.addAttribute("detail", detail);
        model.addAttribute("order", detail.getOrderDetailResp());
        model.addAttribute("records", detail.getRecords());
        if (EnumOffsiteExchangeComplaint.UNPROCESSED.getCode().equals(detail.getStatus())) {
            return "offsiteExchangeComplaint/offsite-exchange-complaint-unprocessed-detail";
        } else if(EnumOffsiteExchangeComplaint.PROCESSING.getCode().equals(detail.getStatus())) {
            return "offsiteExchangeComplaint/offsite-exchange-complaint-processing-detail";
        } else if (EnumOffsiteExchangeComplaint.PROCESSING_CANCELLED.getCode().equals(detail.getStatus())
                || EnumOffsiteExchangeComplaint.CLOSE.getCode().equals(detail.getStatus())
                || EnumOffsiteExchangeComplaint.REVOCATION.getCode().equals(detail.getStatus())) {
            return "offsiteExchangeComplaint/offsite-exchange-complaint-processed-detail";
        }
        return "";
    }

    /**
     * 投诉记录保存
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "投诉记录保存", monitor = true)
    @PostMapping(value = "/record/save")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:complaint:index")
    public BaseResult saveRecord(@Valid @RequestBody OffsiteExchangeComplaintRecordSaveReq req) {
        return offsiteExchangeComplaintService.saveRecord(req);
    }

    /**
     * 投诉详情
     *
     * @return
     */
    @SysLogAop("投诉详情")
    @RequestMapping(value = "/processing")
    @RequiresPermissions("processing:offsite:exchange:complaint")
    public String processing(OffsiteExchangeComplaintDetailReq req, Model model) {
        OffsiteArbitrationDetailResp detail = offsiteExchangeComplaintService.detail(req);
        model.addAttribute("detail", detail);
        model.addAttribute("order", detail.getOrderDetailResp());
        model.addAttribute("records", detail.getRecords());
        return "offsiteExchangeComplaint/offsite-exchange-complaint-processing-detail";

    }

    /**
     * 上传凭证图片
     *
     * @param file
     * @return
     */
    @SysLogAop(value = "上传凭证图片", monitor = true)
    @PostMapping("/upload/certificate")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:complaint:index")
    public BaseResult uploadCertificate(@RequestParam("file") MultipartFile file) throws IOException {
        return offsiteExchangeComplaintService.uploadCertificate(file);
    }


    /**
     * 投诉处理完成
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/completed")
    @ResponseBody
    @SysLogAop(value = "投诉处理完成", monitor = true)
    @RequiresPermissions("offsite:exchange:complaint:index")
    public BaseResult completed(@Valid @RequestBody OffsiteExchangeComplaintCompletedReq req) {
        return offsiteExchangeComplaintService.completed(req);
    }

    /**
     * 投诉完成跳转
     *
     * @return
     */
    @SysLogAop("投诉完成跳转")
    @RequestMapping(value = "/completed/push")
    @RequiresPermissions("offsite:exchange:complaint:index")
    public String detail(@Valid OffsiteExchangeComplaintResultReq req, Model model) {
        Map<String, String> map = new HashMap<>(0);
        map.put("arbitrationId", req.getArbitrationId());
        model.addAttribute("complaint", map);
        return "offsiteExchangeComplaint/offsite-exchange-complaint-result";
    }

    @SysLogAop("聊天记录")
    @PostMapping(value = "/messageList")
    @ResponseBody
    @RequiresPermissions("offsite:exchange:complaint:index")
    public BaseResult messageList(@Valid @RequestBody SingleHistroyListReq req) throws Exception {
        return offsiteExchangeComplaintService.messageList(req);
    }

    @SysLogAop(value = "撤销投诉", monitor = true)
    @PostMapping(value = "/revocation")
    @RequiresPermissions("offsite:exchange:complaint:index")
    @ResponseBody
    public BaseResult revocation(@RequestBody OffsiteExchangeComplaintRevocationReq req){
        return offsiteExchangeComplaintService.revocation(req);
    }
}


