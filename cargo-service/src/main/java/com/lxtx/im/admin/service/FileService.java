package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * APP版本
 *
 * @author tangdy
 */
public interface FileService {

    BaseResult createThumbnails();

    void buildThumb(String path);

    BaseResult uploadUserDefaultAvatar(MultipartFile file) throws IOException;

    /**
     * 用于同步core用户数据和用户钱包数据
     *
     * @return
     */
    BaseResult synchronizeUserData();

}
