package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CommissionFeign;
import com.lxtx.im.admin.feign.request.FeignCommissionLevelAddReq;
import com.lxtx.im.admin.feign.request.FeignCommissionLevelByIdReq;
import com.lxtx.im.admin.feign.request.FeignCommissionLevelListReq;
import com.lxtx.im.admin.service.CommissionLevelService;
import com.lxtx.im.admin.service.request.CommissionLevelAddReq;
import com.lxtx.im.admin.service.request.CommissionLevelByIdReq;
import com.lxtx.im.admin.service.request.CommissionLevelListReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class CommissionLevelServiceImpl implements CommissionLevelService {

    @Resource
    private CommissionFeign commissionFeign;

    @Override
    public BaseResult addLevel(CommissionLevelAddReq req) {
        FeignCommissionLevelAddReq feignReq = new FeignCommissionLevelAddReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.levelAdd(feignReq);
    }

    @Override
    public BaseResult getLevel(CommissionLevelByIdReq req) {
        FeignCommissionLevelByIdReq feignReq = new FeignCommissionLevelByIdReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.getLevel(feignReq);
    }

    @Override
    public BaseResult levelList(CommissionLevelListReq req) {
        FeignCommissionLevelListReq feignReq = new FeignCommissionLevelListReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.levelList(feignReq);
    }

    @Override
    public BaseResult levelAll() {
        return commissionFeign.levelAll();
    }
}
