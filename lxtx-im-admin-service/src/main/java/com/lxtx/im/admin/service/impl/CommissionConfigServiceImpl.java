package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CommissionFeign;
import com.lxtx.im.admin.feign.request.FeignCommissionConfigAddReq;
import com.lxtx.im.admin.feign.request.FeignCommissionConfigAddToStringReq;
import com.lxtx.im.admin.feign.request.FeignCommissionConfigListReq;
import com.lxtx.im.admin.service.CommissionConfigService;
import com.lxtx.im.admin.service.request.CommissionConfigAddReq;
import com.lxtx.im.admin.service.request.CommissionConfigListReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommissionConfigServiceImpl implements CommissionConfigService {

    @Resource
    private CommissionFeign commissionFeign;

    @Override
    public BaseResult addConfig(List<CommissionConfigAddReq> req) {
        FeignCommissionConfigAddToStringReq feignReq = new FeignCommissionConfigAddToStringReq();
        feignReq.setCommissionConfigParm(JSONArray.toJSONString(req));
        return commissionFeign.configAdd(feignReq);
    }

    @Override
    public BaseResult configList(CommissionConfigListReq req) {
        FeignCommissionConfigListReq feignReq = new FeignCommissionConfigListReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.configList(feignReq);
    }
}
