package com.lxtx.im.admin.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CommissionFeign;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraConfigAddToStringReq;
import com.lxtx.im.admin.feign.request.FeignCommissionExtraWhitelistReq;
import com.lxtx.im.admin.service.CommissionExtraConfigService;
import com.lxtx.im.admin.service.request.CommissionExtraConfigAddReq;
import com.lxtx.im.admin.service.request.CommissionExtraConfigAddToStringReq;
import com.lxtx.im.admin.service.request.CommissionExtraConfigListReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 额外分佣配置表 服务类
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
@Slf4j
@Service
public class CommissionExtraConfigServiceImpl implements CommissionExtraConfigService {

    @Resource
    private CommissionFeign commissionFeign;

    @Override
    public BaseResult selectExtraConfigList() {
        FeignCommissionExtraWhitelistReq feignReq = new FeignCommissionExtraWhitelistReq();
        // 1：白名单配置  2：通用配置
        feignReq.setType(2);
        return commissionFeign.selectExtraConfigList(feignReq);
    }

    @Override
    public BaseResult addExtraConfig(List<CommissionExtraConfigAddReq> req) {
        FeignCommissionExtraConfigAddToStringReq feignReq = new FeignCommissionExtraConfigAddToStringReq();
        feignReq.setCommissionExtraConfigParm(JSONArray.toJSONString(req));
        return commissionFeign.addExtraConfig(feignReq);
    }
}
