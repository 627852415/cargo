package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.AdDetailByIdReq;
import com.lxtx.im.admin.service.request.AdListReq;
import com.lxtx.im.admin.service.request.AdSaveReq;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 广告
 *
 * @author map
 */
public interface AdService {

    BaseResult listPage(AdListReq req);

    /**
     * 上传广告位素材
     *
     * @param file
     * @return
     */
    BaseResult upload(MultipartFile file) throws IOException;

    void add(Model model,String id);

    BaseResult save(AdSaveReq req);

    void detail(AdDetailByIdReq req, Model model);

    void initVal(Model model,String id);

}
