package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.CoinFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.CoinResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CaiRH
 * @since 2018-08-29
 */
@Slf4j
@Service
public class CoinServiceImpl implements CoinService {

    @Resource
    private CoinFeign coinFeign;

    @Autowired
    private QiNiuUtil qiNiuUtil;


    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(CoinReq coinReq, HttpSession session) {
        FeignCoinReq req = new FeignCoinReq();
        BeanUtils.copyProperties(coinReq, req);
        BaseResult baseResult;
        if(userService.isShowAccount()){
            baseResult = coinFeign.listPageLegal(req);
        }else{
            baseResult = coinFeign.listPage(req);
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put("dataResult", baseResult);
        map.put("isShowAccount", userService.isShowAccount());
        return BaseResult.success(map);
    }

    @Override
    public BaseResult listPageLegal(CoinReq coinReq, HttpSession session) {
        FeignCoinReq req = new FeignCoinReq();
        BeanUtils.copyProperties(coinReq, req);
        BaseResult baseResult = coinFeign.listPageLegal(req);
        Map<String, Object> map = new HashMap<>(1);
        map.put("dataResult", baseResult);
        return BaseResult.success(map);
    }

    @Override
    public BaseResult selectOne(CoinDetailReq coinDetailReq) {
        FeignCoinDetailReq detailReq = new FeignCoinDetailReq();
        BeanUtils.copyProperties(coinDetailReq, detailReq);
        return coinFeign.selectOne(detailReq);
    }

    @Override
    public BaseResult save(CoinSaveReq coinSaveReq, HttpSession session) {
        FeignCoinSaveReq saveReq = new FeignCoinSaveReq();
        BeanUtils.copyProperties(coinSaveReq, saveReq);
        return coinFeign.save(saveReq);
    }

    @Override
    public BaseResult delete(CoinDeleteReq coinDeleteReq) {
        FeignCoinDeleteReq deleteReq = new FeignCoinDeleteReq();
        BeanUtils.copyProperties(coinDeleteReq, deleteReq);
        return coinFeign.delete(deleteReq);
    }

    @Override
    public BaseResult upload(MultipartFile multipartFile)  {
        //String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_COINICON.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_COINICON);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(filePath);
    }

    @Override
    public BaseResult obtainCoinFee(CoinFeeReq coinFeeReq) {
        FeignCoinFeeReq req = new FeignCoinFeeReq();
        BeanUtils.copyProperties(coinFeeReq, req);
        return coinFeign.obtainCoinFee(req);
    }

    @Override
    public BaseResult listAll(CoinReq coinReq, HttpSession session) {
        FeignCoinReq req = new FeignCoinReq();
        BeanUtils.copyProperties(coinReq, req);
        return coinFeign.listAll(req);
    }

    @Override
    public List<CoinResp> getAllCoinList() {
        BaseResult baseResult = coinFeign.listAll(new FeignCoinReq());
        List<CoinResp> respList = null;
        if (baseResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            respList = JSONObject.parseArray(jsonObject.getString(BaseResult.LIST), CoinResp.class);
        }
        return respList;
    }

    @Override
    public List<CoinResp> getAllLegalCoinList() {
        BaseResult baseResult = coinFeign.listAllLegal(new FeignCoinReq());
        List<CoinResp> respList = null;
        if (baseResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            respList = JSONObject.parseArray(jsonObject.getString(BaseResult.LIST), CoinResp.class);
        }
        return respList;
    }
}
