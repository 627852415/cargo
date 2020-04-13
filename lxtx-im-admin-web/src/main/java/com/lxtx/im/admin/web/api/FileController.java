package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.service.FileService;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 文件controller
 *
 * @author zkj
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    private String getViewUrl() {
        return PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_SAVE_PATH);
    }

    @SysLogAop("文件缩略图首页")
    @RequiresPermissions("thumbIndex:index")
    @GetMapping("/thumbIndex")
    public String index() {
        return "devManage/thumb-index";
    }

//    /**
//     * 批量生成缩略图
//     *
//     * @return
//     */
//    @SysLogAop("批量生成缩略图")
//    @PostMapping("/createThumbnails")
//    @ResponseBody
//    public BaseResult createThumbnails() {
//
//        fileService.buildThumb(getViewUrl());
//
//        return BaseResult.success();
//    }

//    /**
//     * 上传注册用户默认头像
//     *
//     * @param file
//     * @return
//     */
//    @SysLogAop("上传注册用户默认头像")
//    @PostMapping("/uploadUserDefaultAvatar")
//    @ResponseBody
//    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
//        return fileService.uploadUserDefaultAvatar(file);
//    }

    /**
     * 用于同步core用户数据和用户钱包数据
     * @return
     */
    @SysLogAop(value = "用户数据同步至钱包用户", monitor = true)
    @RequiresPermissions("thumbIndex:index")
    @PostMapping(value = "/synchronize/user/data")
    @ResponseBody
    public BaseResult synchronizeUserData() {
        return fileService.synchronizeUserData();
    }

}
