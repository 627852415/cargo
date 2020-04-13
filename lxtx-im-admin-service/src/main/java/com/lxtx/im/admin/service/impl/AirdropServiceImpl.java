package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.AirdropFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.AirdropService;
import com.lxtx.im.admin.service.request.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @description: 空投实现类
 * @author: CXM
 * @create: 2018-08-31 09:58
 **/
@Service
public class AirdropServiceImpl implements AirdropService {
    @Autowired
    private AirdropFeign airdropFeign;

    @Override
    public BaseResult listPage(AirdropReq req, HttpSession session) {
        FeignAirdropReq feignAirdropReq = new FeignAirdropReq();
        BeanUtils.copyProperties(req, feignAirdropReq);
        BaseResult baseResult = airdropFeign.listPage(feignAirdropReq);
        return baseResult;
    }

    @Override
    public BaseResult deleteById(DeleteAirdropReq req) {
        FeignDeleteAirdropReq feignDeleteAirdropReq = new FeignDeleteAirdropReq();
        BeanUtils.copyProperties(req, feignDeleteAirdropReq);
        return airdropFeign.deleteById(feignDeleteAirdropReq);
    }

    @Override
    public BaseResult getAirdropData(AirdropToSavePageReq req) {
        FeignAirdropToSavePageReq feignReq = new FeignAirdropToSavePageReq();
        BeanUtils.copyProperties(req, feignReq);
        return airdropFeign.getAirdropData(feignReq);
    }

    @Override
    public BaseResult saveOrEdit(SaveAirdropReq req) {
        FeignSaveAirdropReq feignReq = new FeignSaveAirdropReq();
        BeanUtils.copyProperties(req, feignReq);
        return airdropFeign.saveOrEdit(feignReq);
    }

    @Override
    public BaseResult detailAirdropList(AirdropDetailReq req) {
        FeignAirdropDetailReq feignReq = new FeignAirdropDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        return airdropFeign.detailAirdropList(feignReq);
    }
}
