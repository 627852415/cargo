package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.TopgateRechargePayWayFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.TopgateRechargePayWayService;
import com.lxtx.im.admin.service.enums.EnumTopgateRechargeWay;
import com.lxtx.im.admin.service.response.BasePageResp;
import com.lxtx.im.admin.service.response.TopgateRechargePayWayResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class TopgateRechargePayWayServiceImpl implements TopgateRechargePayWayService {

    @Autowired
    private QiNiuUtil qiNiuUtil;
    @Resource
    private TopgateRechargePayWayFeign payWayFeign;
    @Autowired
    private ApplicationContext context;

    @Override
    public void updateEnable(TopGateWithdrawPaywayOnOrOffReq req) {
        payWayFeign.updateEnable(req);
    }

    @Override
    public void add(Model model, Locale locale) {
        List<EnumTopgateRechargeWay> list = new ArrayList<>();
        for (EnumTopgateRechargeWay wayType : EnumTopgateRechargeWay.values()) {
            String message = context.getMessage(wayType.getDescription(), null, locale);
            wayType.setValue(message);
            list.add(wayType);
        }
        model.addAttribute("enumPayTypes", list);
    }

    @Override
    public BaseResult page(TopGateRechargePaywayPageReq req, Locale locale) {
        BaseResult page = payWayFeign.page(req);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(page.getData()));
        String records = jsonObject.getString("records");
        Integer total = jsonObject.getInteger("total");
        Integer size = jsonObject.getInteger("size");
        Integer pages = jsonObject.getInteger("pages");
        Integer current = jsonObject.getInteger("current");
        BasePageResp pageResp = new BasePageResp();
        pageResp.setCurrent(current);
        pageResp.setPages(pages);
        pageResp.setSize(size);
        pageResp.setTotal(total);
        List<TopgateRechargePayWayResp> resps = JSONObject.parseArray(records, TopgateRechargePayWayResp.class);
        if (!CollectionUtils.isEmpty(resps)) {
            resps.stream().forEach(resp -> {
                String payWayDescKey = resp.getPayWayDescKey();
                String message = context.getMessage(payWayDescKey, null, locale);
                resp.setPayName(message);
                BigDecimal innerFee = resp.getInnerFee();
                BigDecimal thirdFee = resp.getThirdFee();
                BigDecimal hundred = new BigDecimal(100);
                resp.setInnerFee(innerFee != null ? hundred.multiply(innerFee) : null);
                resp.setThirdFee(thirdFee != null ? hundred.multiply(thirdFee) : null);
            });
        }
        pageResp.setRecords(resps);
        page.setData(pageResp);
        return page;
    }

    @Override
    public BaseResult save(TopGateRechargePaywaySaveReq req) {
        Integer payWay = req.getPayWay();
        EnumTopgateRechargeWay enumTopgatePayWayType = EnumTopgateRechargeWay.find(payWay);
        if (enumTopgatePayWayType != null) {
            req.setPayWayDescKey(enumTopgatePayWayType.getDescription());
        } else {
            req.setPayWayDescKey(EnumTopgateRechargeWay.find(1).getDescription());
        }
        BigDecimal innerFee = req.getInnerFee();
        if (innerFee != null) {
            BigDecimal divide = innerFee.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
            req.setInnerFee(divide);
        }
        BigDecimal thirdFee = req.getThirdFee();
        if (thirdFee != null) {
            BigDecimal bigDecimal = thirdFee.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
            req.setThirdFee(bigDecimal);
        }
        BaseResult save = payWayFeign.save(req);
        return save;
    }

    @Override
    public BaseResult remove(TopGateRechargePaywayRemoveReq req) {
        BaseResult remove = payWayFeign.remove(req);
        return remove;
    }

    @Override
    public void findOne(TopGateRechargePaywayFindOneReq req, Model model, Locale locale) {
        BaseResult one = payWayFeign.findOne(req);
        TopgateRechargePayWayResp rebateResp = JSONObject.parseObject(JSON.toJSONString(one.getData()), TopgateRechargePayWayResp.class);
        rebateResp.setStrMaxAmount(rebateResp.getMaxAmount() != null ? rebateResp.getMaxAmount().stripTrailingZeros().toPlainString() : "0");
        rebateResp.setStrMinAmount(rebateResp.getMinAmount() != null ? rebateResp.getMinAmount().stripTrailingZeros().toPlainString() : "0");
        rebateResp.setStrInnerFee(rebateResp.getInnerFee() != null ? rebateResp.getInnerFee().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() : "0");
        rebateResp.setStrThirdFee(rebateResp.getThirdFee() != null ? rebateResp.getThirdFee().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() : "0");
        model.addAttribute("rebateModel", rebateResp);
        List<EnumTopgateRechargeWay> list = new ArrayList<>();
        for (EnumTopgateRechargeWay wayType : EnumTopgateRechargeWay.values()) {
            String message = context.getMessage(wayType.getDescription(), null, locale);
            wayType.setValue(message);
            list.add(wayType);
        }
        model.addAttribute("enumPayTypes", list);
    }

    @Override
    public BaseResult upload(MultipartFile multipartFile) throws IOException {
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
