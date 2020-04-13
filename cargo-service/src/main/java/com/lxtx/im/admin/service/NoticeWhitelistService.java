package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.NoticeWhiteListDeleteReq;
import com.lxtx.im.admin.service.request.NoticeWhiteListListReq;
import com.lxtx.im.admin.service.request.NoticeWhiteListSaveReq;

public interface NoticeWhitelistService {

    BaseResult listPage(NoticeWhiteListListReq req);

    BaseResult save(NoticeWhiteListSaveReq req);

    BaseResult delete(NoticeWhiteListDeleteReq req);
}
