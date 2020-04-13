package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.OtcMerchantGroupDictSaveReq;
import com.lxtx.im.admin.service.request.OtcMerchantGroupListPageReq;

/**
 * @Author: liyunhua
 * @Date: 2019/4/2
 */
public interface OtcMerchantGroupService {

    BaseResult listPage(OtcMerchantGroupListPageReq req);

    BaseResult saveOrUpdate(OtcMerchantGroupDictSaveReq req);

}
