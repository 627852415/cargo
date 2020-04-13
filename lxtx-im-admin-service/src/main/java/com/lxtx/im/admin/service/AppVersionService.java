package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.AppVersionDeleteReq;
import com.lxtx.im.admin.service.request.AppVersionListPageReq;
import com.lxtx.im.admin.service.request.AppVersionSaveReq;
import com.lxtx.im.admin.service.request.AppVersionSelectReq;
import org.springframework.ui.Model;

/**
 * APP版本
 * @author tangdy
 */
public interface AppVersionService {

    BaseResult listPage(AppVersionListPageReq req);

    BaseResult save(AppVersionSaveReq req);

    void info(AppVersionSelectReq req, Model model);

    BaseResult delete(AppVersionDeleteReq req);
}
