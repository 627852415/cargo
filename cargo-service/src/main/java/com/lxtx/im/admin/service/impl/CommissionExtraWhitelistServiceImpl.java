package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CommissionFeign;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraWhitelistAddReq;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraWhitelistListReq;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraWhitelistReq;
import com.lxtx.im.admin.feign.request.FeignCommissionWhitelistReq;
import com.lxtx.im.admin.service.CommissionExtraWhitelistService;
import com.lxtx.im.admin.service.request.CommissionExtraWhitelistAddReq;
import com.lxtx.im.admin.service.request.CommissionExtraWhitelistListReq;
import com.lxtx.im.admin.service.request.CommissionExtraWhitelistReq;
import com.lxtx.im.admin.service.request.CommissionWhitelistReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 额外分佣白名单表 服务类
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
@Slf4j
@Service
public class CommissionExtraWhitelistServiceImpl implements CommissionExtraWhitelistService {

    @Resource
    private CommissionFeign commissionFeign;

    @Override
    public BaseResult selectWhitelist(CommissionWhitelistReq req) {
        FeignCommissionWhitelistReq feignReq = new FeignCommissionWhitelistReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.selectWhitelist(feignReq);
    }

    @Override
    public BaseResult addExtraWhitelist(CommissionExtraWhitelistAddReq req) {
        FeignCommissionExtraWhitelistAddReq feignReq = new FeignCommissionExtraWhitelistAddReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.addExtraWhitelist(feignReq);
    }

    @Override
    public BaseResult delExtraWhitelist(CommissionExtraWhitelistReq req) {
        FeignCommissionExtraWhitelistReq feignReq = new FeignCommissionExtraWhitelistReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.delExtraWhitelist(feignReq);
    }

    @Override
    public BaseResult selectExtraWhitelistPage(CommissionExtraWhitelistListReq req) {
        FeignCommissionExtraWhitelistListReq feignReq = new FeignCommissionExtraWhitelistListReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.selectExtraWhitelistPage(feignReq);
    }

    @Override
    public BaseResult selectExtraConfigList(CommissionExtraWhitelistReq req) {
        FeignCommissionExtraWhitelistReq feignReq = new FeignCommissionExtraWhitelistReq();
        // 1：白名单配置  2：通用配置
        feignReq.setType(1);
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.selectExtraConfigList(feignReq);
    }
}
