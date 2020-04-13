package com.lxtx.im.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.DictFeign;
import com.lxtx.im.admin.feign.request.FeignDictDeleteReq;
import com.lxtx.im.admin.feign.request.FeignDictGetValueReq;
import com.lxtx.im.admin.feign.request.FeignDictInfoReq;
import com.lxtx.im.admin.feign.request.FeignDictListPageReq;
import com.lxtx.im.admin.feign.request.FeignDictModifyValueReq;
import com.lxtx.im.admin.feign.request.FeignDictSaveReq;
import com.lxtx.im.admin.feign.request.FeignNoticeInfoReq;
import com.lxtx.im.admin.feign.request.FeignNoticeSaveReq;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.DictCoinMaxListPageReq;
import com.lxtx.im.admin.service.request.DictDeleteReq;
import com.lxtx.im.admin.service.request.DictInfoReq;
import com.lxtx.im.admin.service.request.DictListPageReq;
import com.lxtx.im.admin.service.request.DictModifyValueReq;
import com.lxtx.im.admin.service.request.DictSaveReq;
import com.lxtx.im.admin.service.request.NoticeSaveReq;

import static com.lxtx.framework.common.constants.DictConstants.DICT_DOMAIN_SPECIAL_HK_ACCOUNT;


/**
* @description:  字典管理
* @author:   CXM
* @create:   2018-10-12 14:37
*/
@Service
public class DictServiceImpl implements DictService {
	
	public static final String DICT_VALUE_KEY = "value";

    @Autowired
    private DictFeign dictFeign;


    @Override
    public String getKhUserValue() {
        FeignDictGetValueReq feignReq = new
                FeignDictGetValueReq(com.lxtx.framework.common.constants.DictConstants.DICT_DOMAIN_SYSTEM_MANAGER, DICT_DOMAIN_SPECIAL_HK_ACCOUNT);
        BaseResult result = dictFeign.getDictValue(feignReq);
        if(!result.isSuccess()){
            throw LxtxBizException.newException("获取字典失败");
        }
        JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(result.getData()));
        return jsonObject.getString("value");
    }


    @Override
    public BaseResult listPage(DictListPageReq req) {
        FeignDictListPageReq feignDictListPageReq = new FeignDictListPageReq();
        BeanUtils.copyProperties(req, feignDictListPageReq);
        return dictFeign.listPage(feignDictListPageReq);
    }

    @Override
    public BaseResult delete(DictDeleteReq req) {
        FeignDictDeleteReq feignDictDeleteReq = new FeignDictDeleteReq();
        BeanUtils.copyProperties(req, feignDictDeleteReq);
        return  dictFeign.delete(feignDictDeleteReq);
    }

    @Override
    public BaseResult saveOrUpdate(DictSaveReq req) {
        FeignDictSaveReq feignDictSaveReq = new FeignDictSaveReq();
        BeanUtils.copyProperties(req, feignDictSaveReq);
        return dictFeign.saveOrUpdate(feignDictSaveReq);
    }

    @Override
    public BaseResult info(DictInfoReq req) {
        FeignDictInfoReq feignDictInfoReq = new FeignDictInfoReq();
        BeanUtils.copyProperties(req, feignDictInfoReq);
        return dictFeign.info(feignDictInfoReq);
    }

    @Override
    public BaseResult saveNotice(NoticeSaveReq req) {
        FeignNoticeSaveReq feign = new FeignNoticeSaveReq();
        BeanUtils.copyProperties(req, feign);
        return dictFeign.saveNotice(feign);
    }

    @Override
    public BaseResult transactionNoticeInfo() {
        FeignNoticeInfoReq feign = new FeignNoticeInfoReq();
        feign.setDomain(DictConstants.PER_AMOUNT_NOTICE);
        return dictFeign.transactionNoticeInfo(feign);
    }

    @Override
    public BaseResult addCoinNoticeInfo() {
        FeignNoticeInfoReq feign = new FeignNoticeInfoReq();
        feign.setDomain(DictConstants.ADD_COIN_NOTICE);
        return dictFeign.transactionNoticeInfo(feign);
    }

    @Override
    public BaseResult listPageCoinMax(DictCoinMaxListPageReq req) {
        FeignDictListPageReq feignDictListPageReq = new FeignDictListPageReq();
        BeanUtils.copyProperties(req, feignDictListPageReq);
        feignDictListPageReq.setDomainName(DictConstants.COIN_PER_AMOUNT_MAX);
        return dictFeign.listPage(feignDictListPageReq);
    }
    
    @Override
    public String getDictValue(String domain, String ikey) {
        FeignDictGetValueReq feignReq = new FeignDictGetValueReq(domain, ikey);
        BaseResult result = dictFeign.getDictValue(feignReq);
        if(!result.isSuccess()){
            throw LxtxBizException.newException("获取字典失败");
        }

        JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(result.getData()));
        return jsonObject.getString(DICT_VALUE_KEY);
    }

	@Override
	public BaseResult modifyValueByDomainAndKey(DictModifyValueReq req) {
		FeignDictModifyValueReq feignDictModifyValueReq = new FeignDictModifyValueReq();
		BeanUtils.copyProperties(req, feignDictModifyValueReq);
		return dictFeign.modifyValueByDomainAndKey(feignDictModifyValueReq);
	}
	@Override
    public String[] getDictArrayValue(String domain, String ikey) {
        String dictValue = getDictValue(domain, ikey);
        String[] resultArr = {};
        if (StringUtils.isNotBlank(dictValue)) {
            resultArr = dictValue.split(",");
        }
        return resultArr;
    }
}