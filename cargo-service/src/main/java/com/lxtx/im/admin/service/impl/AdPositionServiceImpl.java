package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.AdPositionFeign;
import com.lxtx.im.admin.feign.request.FeignAdPositionDetailByIdReq;
import com.lxtx.im.admin.feign.request.FeignAdPositionListPageReq;
import com.lxtx.im.admin.feign.request.FeignAdPositionSaveReq;
import com.lxtx.im.admin.service.AdPositionService;
import com.lxtx.im.admin.service.request.AdPositionDetailByIdReq;
import com.lxtx.im.admin.service.request.AdPositionListReq;
import com.lxtx.im.admin.service.request.AdPositionSaveReq;
import com.lxtx.im.admin.service.request.GameSaveReq;
import com.lxtx.im.admin.service.response.AdPositionDetailResp;
import com.lxtx.im.admin.service.response.AdPositionResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * 广告位
 *
 * @author xufeifei
 */
@Service
public class AdPositionServiceImpl implements AdPositionService {

    @Autowired
    private AdPositionFeign adPositionFeign;

    @Override
    public BaseResult listPage(AdPositionListReq req) {
        FeignAdPositionListPageReq listPageReq = new FeignAdPositionListPageReq();
        BeanUtils.copyProperties(req,listPageReq);
        return adPositionFeign.listPage(listPageReq);
    }


    @Override
    public BaseResult save(AdPositionSaveReq req){
        FeignAdPositionSaveReq feignSaveReq = new FeignAdPositionSaveReq();
        BeanUtils.copyProperties(req,feignSaveReq);
        return adPositionFeign.save(feignSaveReq);
    }

    @Override
    public void detail(AdPositionDetailByIdReq req, Model model) {
        FeignAdPositionDetailByIdReq feignAdPositionDetailByIdReq = new FeignAdPositionDetailByIdReq();
        BeanUtils.copyProperties(req,feignAdPositionDetailByIdReq);
        BaseResult result = adPositionFeign.detail(feignAdPositionDetailByIdReq);
        String resultJsona = JSONArray.toJSONString(result.getData());
        AdPositionDetailResp adPositionDetailResp = JSONObject.parseObject(resultJsona, AdPositionDetailResp.class);
        model.addAttribute("detail",adPositionDetailResp);
    }

    @Override
    public BaseResult deleteById(AdPositionDetailByIdReq req) {
        FeignAdPositionDetailByIdReq feignReq = new FeignAdPositionDetailByIdReq();
        BeanUtils.copyProperties(req,feignReq);
        return adPositionFeign.deleteById(feignReq);
    }
}
