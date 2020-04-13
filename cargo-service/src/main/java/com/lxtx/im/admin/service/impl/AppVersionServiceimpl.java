package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.AppVersionFeign;
import com.lxtx.im.admin.feign.request.FeignAppVersionDeleteReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionListPageReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionSaveReq;
import com.lxtx.im.admin.feign.request.FeignAppVersionSelectReq;
import com.lxtx.im.admin.service.AppVersionService;
import com.lxtx.im.admin.service.request.AppVersionDeleteReq;
import com.lxtx.im.admin.service.request.AppVersionListPageReq;
import com.lxtx.im.admin.service.request.AppVersionSaveReq;
import com.lxtx.im.admin.service.request.AppVersionSelectReq;
import com.lxtx.im.admin.service.response.AppVersionInfoResp;
import com.lxtx.im.admin.service.response.DictInfoResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * APP版本
 * @author tangdy
 */
@Service
public class AppVersionServiceimpl implements AppVersionService {

    @Autowired
    private AppVersionFeign appVersionFeign;

    @Override
    public BaseResult listPage(AppVersionListPageReq req) {
        FeignAppVersionListPageReq feignAppVersionListPageReq = new FeignAppVersionListPageReq();
        BeanUtils.copyProperties(req, feignAppVersionListPageReq);
        return appVersionFeign.listPage(feignAppVersionListPageReq);
    }

    @Override
    public BaseResult save(AppVersionSaveReq req) {
        FeignAppVersionSaveReq saveReq = new FeignAppVersionSaveReq();
        BeanUtils.copyProperties(req, saveReq);
        return appVersionFeign.save(saveReq);
    }

    @Override
    public void info(AppVersionSelectReq req, Model model) {
        FeignAppVersionSelectReq selectReq = new FeignAppVersionSelectReq();
        BeanUtils.copyProperties(req,selectReq);
        BaseResult result = appVersionFeign.info(selectReq);
        if(result.isSuccess() && result.getData() != null){
            model.addAttribute("appversion", JSONObject.parseObject(JSON.toJSONString(result.getData()), AppVersionInfoResp.class));
        }
    }

    @Override
    public BaseResult delete(AppVersionDeleteReq req) {
        FeignAppVersionDeleteReq delReq = new FeignAppVersionDeleteReq();
        BeanUtils.copyProperties(req, delReq);
        return appVersionFeign.delete(delReq);
    }
}
