package com.lxtx.im.admin.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.FileUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.FeignWalletUserSynchronizeReq;
import com.lxtx.im.admin.service.FileService;
import com.lxtx.im.admin.service.utils.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 文件service
 *
 * @author tangdy
 */
@Slf4j
@Service
public class FileServiceimpl implements FileService {


    @Autowired
    private QiNiuUtil qiNiuUtil;

    @Resource
    private UserFeign userFeign;

    @Resource
    private WalletUserFeign walletUserFeign;

    private String getViewUrl() {
        return PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_SAVE_PATH);
    }

    @Override
    public BaseResult createThumbnails() {

        this.buildThumb(getViewUrl());

        return BaseResult.success();
    }


    @Override
    public void buildThumb(String path) {

        File file = new File(path);

        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                log.info("************ 文件夹为空 ************");
                return;
            } else {
                for (File f : files) {
                    if (f.isDirectory()) {
                        log.info("************ 进入文件夹：{} ************", f.getAbsolutePath());
                        buildThumb(f.getAbsolutePath());
                    } else {

                        ThreadPool.getInstance().doJob(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    if (!f.getName().contains(FileUtils.THUMB_PREFIX)) {

                                        //获取文件的目录
                                        String parent = f.getParent();
                                        String thumbPath = parent + "/thumb_" + f.getName();
                                        String name = f.getName();
                                        String fileSuffix = name.substring(name.lastIndexOf(".") + 1);

                                        boolean contains = FileUtils.PICTURE_SUFFIX.contains(fileSuffix);
                                        boolean exist = !new File(thumbPath).exists();

                                        if (contains && exist) {
                                            Thumbnails.of(f.getAbsolutePath()).scale(1f).outputQuality(0.5f).toFile(thumbPath);
                                            log.info("************ 文件：{} 生成缩略图成功 ************", f.getAbsolutePath());
                                        }
                                    }
                                } catch (IOException e) {
                                    log.error("生成缩略图异常", e);
                                }
                            }
                        });
                    }
                }
            }
        } else {
            log.info("************ 文件不存在 ************");
        }
    }

    @Override
    public BaseResult uploadUserDefaultAvatar(MultipartFile multipartFile){
        //String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_OTHER.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_OTHER);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(filePath);
    }

    /**
     * 用于同步core用户数据和用户钱包数据
     *
     * @return
     */
    @Override
    public BaseResult synchronizeUserData(){
        BaseResult baseResult = userFeign.queryListToWalletUser();
        String jsonResult =null;
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            dataMap=(Map<String, Object>)dataMap.get("data"); ;
            jsonResult = JSONArray.toJSONString(dataMap.get("list"));
        }

        FeignWalletUserSynchronizeReq feignWalletUserSynchronizeReq = new FeignWalletUserSynchronizeReq();
        feignWalletUserSynchronizeReq.setJsonUserList(jsonResult);
        walletUserFeign.synchronizeUserData(feignWalletUserSynchronizeReq);

        return BaseResult.success();
    }
}
