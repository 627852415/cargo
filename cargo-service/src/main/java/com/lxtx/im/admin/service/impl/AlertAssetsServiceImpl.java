package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.AlertAssetsFeign;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsDetailReq;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsReq;
import com.lxtx.im.admin.feign.request.FeignAlertAssetsSaveReq;
import com.lxtx.im.admin.service.AlertAssetsService;
import com.lxtx.im.admin.service.request.AlertAssetsDetailReq;
import com.lxtx.im.admin.service.request.AlertAssetsReq;
import com.lxtx.im.admin.service.request.AlertAssetsSaveReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/6/19 15:06
 */
@Service
public class AlertAssetsServiceImpl implements AlertAssetsService {

    @Autowired
    private AlertAssetsFeign alertAssetsFeign;

    /**
     * 查询报警列表
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult listPage(AlertAssetsReq req) {
        FeignAlertAssetsReq feiReq = new FeignAlertAssetsReq();
        BeanUtils.copyProperties(req, feiReq);
        return alertAssetsFeign.listPage(feiReq);
    }

    /**
     * 资产报警保存
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult save(AlertAssetsSaveReq req) {
        FeignAlertAssetsSaveReq feignReq = new FeignAlertAssetsSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return alertAssetsFeign.save(feignReq);
    }

    /**
     * 启用或禁用
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult update(AlertAssetsSaveReq req) {
        FeignAlertAssetsSaveReq feignReq = new FeignAlertAssetsSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return alertAssetsFeign.update(feignReq);
    }

    /**
     * 根据ID获取对象
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult getById(AlertAssetsDetailReq req) {
        FeignAlertAssetsDetailReq feignReq = new FeignAlertAssetsDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        return alertAssetsFeign.getById(feignReq);
    }
}
