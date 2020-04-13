package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.GlobalCodeFeign;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeListReq;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeModifyReq;
import com.lxtx.im.admin.service.GlobalCodeService;
import com.lxtx.im.admin.service.request.GlobalCodeListPageReq;
import com.lxtx.im.admin.service.request.GlobalCodeModifyReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author liyunhua
 * @Date 2018-11-16 0016
 */
@Service
public class GlobalCodeServiceImpl implements GlobalCodeService {

    @Autowired
    private GlobalCodeFeign globalCodeFeign;

    @Override
    public BaseResult listPage(GlobalCodeListPageReq req) {
        FeignGlobalCodeListReq globalCodeListReq = new FeignGlobalCodeListReq();
        BeanUtils.copyProperties(req, globalCodeListReq);
        return globalCodeFeign.listPage(globalCodeListReq);
    }

    @Override
    public BaseResult add(GlobalCodeModifyReq req) {
        FeignGlobalCodeModifyReq feignGlobalCodeModifyReq = new FeignGlobalCodeModifyReq();
        BeanUtils.copyProperties(req, feignGlobalCodeModifyReq);
        return globalCodeFeign.add(feignGlobalCodeModifyReq);
    }

    @Override
    public BaseResult modify(GlobalCodeModifyReq req) {
        FeignGlobalCodeModifyReq feignGlobalCodeModifyReq = new FeignGlobalCodeModifyReq();
        BeanUtils.copyProperties(req, feignGlobalCodeModifyReq);
        return globalCodeFeign.modify(feignGlobalCodeModifyReq);
    }

    @Override
    public BaseResult delete(GlobalCodeModifyReq req) {
        FeignGlobalCodeModifyReq feignGlobalCodeModifyReq = new FeignGlobalCodeModifyReq();
        BeanUtils.copyProperties(req, feignGlobalCodeModifyReq);
        return globalCodeFeign.delete(feignGlobalCodeModifyReq);
    }

}
