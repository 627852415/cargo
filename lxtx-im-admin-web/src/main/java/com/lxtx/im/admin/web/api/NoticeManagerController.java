package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.NoticeManagerService;
import com.lxtx.im.admin.service.enums.EnumNoticeBroadCastStatus;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.BasePageResp;
import com.lxtx.im.admin.service.response.GlobalCodeSimpleInfo;
import com.lxtx.im.admin.service.response.NoticeBroadCastResp;
import com.lxtx.im.admin.service.response.NoticeCommandResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

/**
 * @author PengPai
 * Date: Created in 14:35 2020/2/24
 */
//@SessionAttributes(names = "globalCodes")
@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeManagerController {

    @Autowired
    private NoticeManagerService noticeManagerService;

    /*
     *指令
     */
    @GetMapping("/command/index")
    public String commandIndex() {
        return "broadcast/notice-command";
    }

    @PostMapping("/command/page")
    @ResponseBody
    public BaseResult commandPage(NoticeCommandPageReq req) {
        BasePageResp<NoticeCommandResp> resp = noticeManagerService.commandPage(req);
        return BaseResult.success(resp);
    }

    @PostMapping("/command/push")
    @ResponseBody
    public BaseResult commandPush(@RequestBody @Valid NoticeCommandPushReq req) throws Exception {
        noticeManagerService.commandPush(req);
        return BaseResult.success();
    }

    /*
     * 广播
     */
    @GetMapping("/broadcast/index")
    public String broadcastIndex(Model model) {
        List<GlobalCodeSimpleInfo> infos = noticeManagerService.globalCodeList();
        model.addAttribute("detail", null);
        model.addAttribute("globalCodes", infos);
        return "broadcast/notice-broadcast";
    }

    @GetMapping("/broadcast/send/page")
    public String broadcastSend(Model model) {
        log.info("发送消息");
        List<GlobalCodeSimpleInfo> infos = noticeManagerService.globalCodeList();
        infos.add(GlobalCodeSimpleInfo.builder().countryCode("ALL").countryName("全部国家").chooseFlag(true).build());
        model.addAttribute("globalCodes", infos);
        return "broadcast/notice-broadcast-send";
    }

    @PostMapping("/broadcast/page")
    @ResponseBody
    public BaseResult broadcastPage(NoticeBroadCastPageReq req) {
        BasePageResp<NoticeBroadCastResp> resp = noticeManagerService.broadcastPage(req);
        return BaseResult.success(resp);
    }

    @GetMapping("/broadcast/{id}")
    public ModelAndView broadcastDetail(@PathVariable String id) {
        ModelAndView mv = detailModel.apply(id);
        mv.setViewName("broadcast/notice-broadcast-detail");
        return mv;
    }

    @GetMapping("/broadcast/edit")
    public ModelAndView broadcastEdit(@RequestParam String id) {
        ModelAndView mv = detailModel.apply(id);
//        mv.addObject("editFlag", true);
        mv.setViewName("broadcast/notice-broadcast-send");
        return mv;
    }

    @PostMapping("/broadcast/delete")
    @ResponseBody
    public BaseResult broadcastDelByIds(@RequestBody @Valid NoticeBroadCastDeleteReq req) {
        noticeManagerService.broadcastDelete(req);
        return BaseResult.success();
    }

    @PostMapping("/broadcast/save")
    @ResponseBody
    public BaseResult broadcastSave(@RequestBody @Valid NoticeBroadCastSaveReq req) {
        noticeManagerService.broadcastSave(req);
        return BaseResult.success();
    }

    @PostMapping("/broadcast/push")
    @ResponseBody
    public BaseResult broadcastPush(@RequestBody @Valid NoticeBroadCastPushReq req) throws Exception {
        noticeManagerService.broadcastPush(req);
        return BaseResult.success();
    }

    @PostMapping("/broadcast/addOrEdit/push")
    @ResponseBody
    public BaseResult broadcastAddOrEditAndPush(@RequestBody @Valid NoticeBroadCastAddOrEditAndPushReq req) throws Exception {
        noticeManagerService.broadcastAddOrEditAndPush(req);
        return BaseResult.success();
    }

    @PostMapping("/broadcast/repeal/push")
    @ResponseBody
    public BaseResult broadcastRepealPush(@RequestBody @Valid NoticeBroadCastPushReq req) throws Exception {
        noticeManagerService.broadcastRepealPush(req);
        return BaseResult.success();
    }


    @PostMapping("/broadcast/push/scheduler")
    @ResponseBody
    public BaseResult broadcastPushScheduler() {
        noticeManagerService.broadcastPushScheduler();
        return BaseResult.success();
    }

    @PostMapping("/broadcast/upload")
    @ResponseBody
    public BaseResult upload(@RequestParam("file") MultipartFile file) {
        return BaseResult.success(noticeManagerService.uploadBroadcast(file));
    }

    //公告详情和国家简码列表
    private Function<String, ModelAndView> detailModel = id -> {
        List<GlobalCodeSimpleInfo> infos = noticeManagerService.globalCodeList();
        ModelAndView mv = new ModelAndView();
        NoticeBroadCastResp resp = noticeManagerService.broadcastDetail(id);

        infos.stream()
                .forEach(info -> {
                    if (resp.getCountryCodes().contains(info.getCountryCode())) {
                        info.setChooseFlag(true);
                    }
                });
        if (resp.getCountryCodes().contains("ALL")) {
            infos.add(GlobalCodeSimpleInfo.builder().countryCode("ALL").countryName("全部国家").chooseFlag(true).build());
        } else {
            infos.add(GlobalCodeSimpleInfo.builder().countryCode("ALL").countryName("全部国家").chooseFlag(false).build());
        }
        if (resp != null) {
            if (EnumNoticeBroadCastStatus.NO_PUBLISH.getCode().equals(resp.getStatus())){
                mv.addObject("editFlag", false);
            }else {
                mv.addObject("editFlag", true);
            }
        }
        mv.addObject("detail", resp);
        mv.addObject("globalCodes", infos);
        return mv;
    };
}
