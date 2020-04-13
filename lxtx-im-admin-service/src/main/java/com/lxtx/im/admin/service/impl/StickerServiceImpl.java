package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.exception.LxtxException;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.DeCompressUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.StickerFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.StickerService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

/**
 * @description: 表情包
 * @author: CXM
 * @create: 2018-08-31 09:58
 **/
@Service
@Slf4j
public class StickerServiceImpl implements StickerService {
    @Resource
    private StickerFeign stickerFeign;
    @Autowired
    private QiNiuUtil qiNiuUtil;


    @Override
    public BaseResult listPage(StickerListReq req) {
        FeignStickerListReq listReq = new FeignStickerListReq();
        BeanUtils.copyProperties(req, listReq);
        return stickerFeign.listPage(listReq);
    }

    @Override
    public BaseResult add(LinkedList<MultipartFile> files, StickerSaveReq req) {
        String filePath = PropertiesUtil.getString(PropertiesUtil.LOCAL_FILE_SAVE_PATH) + "/sticker/"
                + UUID.randomUUID().toString().replace("-", "");
        File dir = new File(filePath);
        BaseResult baseResult = null;
        try {
            FeignStickerSaveReq saveReq = new FeignStickerSaveReq();
            BeanUtils.copyProperties(req, saveReq);
            for(MultipartFile multipartFile : files){
                if(!multipartFile.isEmpty()){
                    String originalFilename = multipartFile.getOriginalFilename();
                    String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toUpperCase();
                    if(! dir.exists()) {
                        dir.mkdir();
                    }
                    String fileTargetPath = filePath + "/" + originalFilename;
                    File file =  new File(fileTargetPath);
                    try {
                        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
                        org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, file);
                    } catch (IOException e) {
                        throw LxtxBizException.newException("文件拷贝错误{}", e.getMessage());
                    }
                    if(DeCompressUtils.COMPRESS_ZIP.equals(fileType)){
                        saveReq.setDetailFileUrl(fileTargetPath);
                    }else{
                        saveReq.setCoverFileUrl(fileTargetPath);
                    }
                }
            }
            baseResult = stickerFeign.add(saveReq);
            if(!baseResult.isSuccess()){
                //删除文件
                //FileUtils.delDir(filePath);
            }
        } catch (BeansException e) {
            e.printStackTrace();
            throw LxtxBizException.newException("表情包新增异常");
        } catch (LxtxException e) {
            e.printStackTrace();
            throw LxtxBizException.newException("表情包新增异常");
        } finally {
            //删除文件
            //FileUtils.delDir(filePath);
        }
        return baseResult;
    }

    @Override
    public BaseResult update(StickerUpdateReq req) {
        FeignStickerUpdateReq feignReq = new FeignStickerUpdateReq();
        BeanUtils.copyProperties(req, feignReq);
        return stickerFeign.update(feignReq);
    }

    @Override
    public BaseResult delete(StickerDeleteReq req) {
        FeignStickerDeleteReq feignReq = new FeignStickerDeleteReq();
        BeanUtils.copyProperties(req, feignReq);
        return stickerFeign.delete(feignReq);
    }

    @Override
    public BaseResult detail(StickerDetailReq req) {
        FeignStickerDetailReq feignReq = new FeignStickerDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        return stickerFeign.detail(feignReq);
    }

    @Override
    public BaseResult detailListPage(StickerDetailListReq req) {
        FeignStickerDetailListReq feignReq = new FeignStickerDetailListReq();
        BeanUtils.copyProperties(req, feignReq);
        return stickerFeign.detailListPage(feignReq);
    }

    @Override
    public BaseResult updateDetail(StickerUpdateDetailReq req) {
        FeignStickerUpdateDetailReq feignReq = new FeignStickerUpdateDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        return stickerFeign.updateDetail(feignReq);
    }

    @Override
    public BaseResult uploadCover(MultipartFile multipartFile) {
       // String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_STICKER.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_STICKER);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(filePath);
    }
}
