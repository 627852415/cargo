package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeRebateFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.OffsiteExchangeRebateService;
import com.lxtx.im.admin.service.enums.EnumOffsiteExchangePayType;
import com.lxtx.im.admin.service.request.OffsiteExchangeRebateIdReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeRebateSaveReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeRebateResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * 支付方式/返利
 *
 * @author CaiRH
 * @since 2019-05-28
 **/
@Slf4j
@Service
public class OffsiteExchangeRebateServiceImpl implements OffsiteExchangeRebateService {
	
	@Autowired
    private QiNiuUtil qiNiuUtil;
	
    @Resource
    private OffsiteExchangeRebateFeign offsiteExchangeRebateFeign;

    @Override
    public void add(Model model) {
        model.addAttribute("enumPayTypes", EnumOffsiteExchangePayType.values());
    }

    @Override
    public void detail(OffsiteExchangeRebateIdReq idReq, Model model) {
        //详情
        FeignOffsiteExchangeRebateIdReq feignReq = new FeignOffsiteExchangeRebateIdReq();
        BeanUtils.copyProperties(idReq, feignReq);
        BaseResult result = offsiteExchangeRebateFeign.detail(feignReq);
        OffsiteExchangeRebateResp rebateResp = JSONObject.parseObject(JSON.toJSONString(result.getData()), OffsiteExchangeRebateResp.class);
        if(Objects.isNull(rebateResp.getShowQrCode())){
            rebateResp.setShowQrCode(false);
        }
        model.addAttribute("rebateModel", rebateResp);
        model.addAttribute("enumPayTypes", EnumOffsiteExchangePayType.values());
    }

    @Override
    public BaseResult save(OffsiteExchangeRebateSaveReq req, HttpSession session) {
        FeignOffsiteExchangeRebateSaveReq feignReq = new FeignOffsiteExchangeRebateSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeRebateFeign.save(feignReq);
    }

    @Override
    public BaseResult listPage(BasePageReq req) {
        FeignBasePageReq feignReq = new FeignBasePageReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeRebateFeign.listPage(feignReq);
    }

    @Override
    public BaseResult del(OffsiteExchangeRebateIdReq req) {
        FeignOffsiteExchangeRebateIdReq feignReq = new FeignOffsiteExchangeRebateIdReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeRebateFeign.del(feignReq);
    }

    /**
     * 统计资金账号
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult getRebateAmount(FeignStatisticsOrderReq req) {
        return offsiteExchangeRebateFeign.getRebateAmount(req);
    }

	@Override
	public BaseResult upload(MultipartFile multipartFile) {
        //String path = FileSlicesUploadUtils.slicesUpload(file, EnumRelateType.ADMIN_PAYTYPE_LOGO.getCode());
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_PAYTYPE_LOGO);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(filePath);
	}
}
