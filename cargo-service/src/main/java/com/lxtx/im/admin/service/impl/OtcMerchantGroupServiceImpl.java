package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.im.admin.feign.feign.DictFeign;
import com.lxtx.im.admin.feign.request.FeignDictListPageReq;
import com.lxtx.im.admin.feign.request.FeignDictSaveReq;
import com.lxtx.im.admin.service.OtcMerchantGroupService;
import com.lxtx.im.admin.service.request.OtcMerchantGroupDictSaveReq;
import com.lxtx.im.admin.service.request.OtcMerchantGroupListPageReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liyunhua
 * @Date: 2019/4/2
 */
@Service
public class OtcMerchantGroupServiceImpl implements OtcMerchantGroupService {

    @Autowired
    private DictFeign dictFeign;


    @Override
    public BaseResult listPage(OtcMerchantGroupListPageReq req) {
        FeignDictListPageReq feignDictListPageReq = new FeignDictListPageReq();
        BeanUtils.copyProperties(req, feignDictListPageReq);
        feignDictListPageReq.setDomainName(DictConstants.DICT_DOMAIN_OTC_LOGIN_USABLEGROUP);
        return dictFeign.listPage(feignDictListPageReq);
    }

    @Override
    public BaseResult saveOrUpdate(OtcMerchantGroupDictSaveReq req) {
        FeignDictSaveReq feignDictSaveReq = new FeignDictSaveReq();
        BeanUtils.copyProperties(req, feignDictSaveReq);
        return dictFeign.saveOrUpdate(feignDictSaveReq);
    }

}
