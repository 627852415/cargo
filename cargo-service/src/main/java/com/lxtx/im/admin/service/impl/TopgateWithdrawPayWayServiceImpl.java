package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.TopgateWithdrawPayWayFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.TopgateWithdrawPayWayService;
import com.lxtx.im.admin.service.enums.EnumTopgateWithdrawPayway;
import com.lxtx.im.admin.service.request.DictDeleteReq;
import com.lxtx.im.admin.service.request.DictInfoReq;
import com.lxtx.im.admin.service.request.DictListPageReq;
import com.lxtx.im.admin.service.request.DictSaveReq;
import com.lxtx.im.admin.service.response.BasePageResp;
import com.lxtx.im.admin.service.response.DictInfoResp;
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
public class TopgateWithdrawPayWayServiceImpl implements TopgateWithdrawPayWayService {

    @Autowired
    private QiNiuUtil qiNiuUtil;
    @Resource
    private TopgateWithdrawPayWayFeign payWayFeign;
    @Autowired
    private DictService dictService;
    @Autowired
    private ApplicationContext context;

    private String TOPGATE_DICT_DOMAIN = "topgate";
    private String TOPGATE_DICT_IKEY = "topgate_withdrawal_date_limit_amount";

    @Override
    public List<DictInfoResp> selectListDict() {
        DictListPageReq req = new DictListPageReq();
        req.setDomain(TOPGATE_DICT_DOMAIN);
        req.setIkey(TOPGATE_DICT_IKEY);
        BaseResult baseResult = dictService.listPage(req);

        List<DictInfoResp> resps = new ArrayList<>();
        if (baseResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(baseResult.getData()));
            String records = jsonObject.getString("records");
            resps = JSONObject.parseArray(records, DictInfoResp.class);
        }
        return resps;
    }

    @Override
    public void deleteDict(DictDeleteReq req) {
        dictService.delete(req);
    }

    @Override
    public void setDictModel(DictInfoReq req, Model model) {
        BaseResult info = dictService.info(req);
        DictInfoResp dictInfoResp = JSONObject.parseObject(JSON.toJSONString(info.getData()), DictInfoResp.class);
        model.addAttribute("dictModel", dictInfoResp);
    }

    @Override
    public BaseResult saveOrUpdateDict(DictSaveReq req) {
        return dictService.saveOrUpdate(req);
    }

    @Override
    public BaseResult listPageDict(DictListPageReq req) {
        return dictService.listPage(req);
    }

    @Override
    public void updateEnableWithdrawPayWay(TopGateWithdrawPaywayOnOrOffReq req) {
        payWayFeign.updateEnable(req);
    }

    @Override
    public void add(Model model, Locale locale) {
        List<EnumTopgateWithdrawPayway> list = new ArrayList<>();
        for (EnumTopgateWithdrawPayway wayType : EnumTopgateWithdrawPayway.values()) {
            String message = context.getMessage(wayType.getDescription(), null, locale);
            wayType.setValue(message);
            list.add(wayType);
        }
        model.addAttribute("enumPayTypes", list);
    }

    @Override
    public BaseResult pageWithdrawPayWay(TopGateWithdrawPaywayPageReq req, Locale locale) {
        BaseResult page = payWayFeign.page(req);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(page.getData()));
        String records = jsonObject.getString("records");
        final Integer total = jsonObject.getInteger("total");
        final Integer size = jsonObject.getInteger("size");
        final Integer pages = jsonObject.getInteger("pages");
        final Integer current = jsonObject.getInteger("current");
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
    public BaseResult saveWithdrawPayWay(TopGateWithdrawPaywaySaveReq req) {
        Integer payWay = req.getPayWay();
        EnumTopgateWithdrawPayway enumTopgatePayWayType = EnumTopgateWithdrawPayway.find(payWay);
        if (enumTopgatePayWayType != null) {
            req.setPayWayDescKey(enumTopgatePayWayType.getDescription());
        } else {
            req.setPayWayDescKey(EnumTopgateWithdrawPayway.find(1).getDescription());
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
    public BaseResult removeWithdrawPayWay(TopGateWithdrawPaywayRemoveReq req) {
        BaseResult remove = payWayFeign.remove(req);
        return remove;
    }

    @Override
    public void selectOneWithdrawPayWay(TopGateWithdrawPaywayFindOneReq req, Model model, Locale locale) {
        BaseResult one = payWayFeign.findOne(req);
        TopgateRechargePayWayResp rebateResp = JSONObject.parseObject(JSON.toJSONString(one.getData()), TopgateRechargePayWayResp.class);
        rebateResp.setStrMaxAmount(rebateResp.getMaxAmount() != null ? rebateResp.getMaxAmount().stripTrailingZeros().toPlainString() : "0");
        rebateResp.setStrMinAmount(rebateResp.getMinAmount() != null ? rebateResp.getMinAmount().stripTrailingZeros().toPlainString() : "0");
        rebateResp.setStrInnerFee(rebateResp.getInnerFee() != null ? rebateResp.getInnerFee().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() : "0");
        rebateResp.setStrThirdFee(rebateResp.getThirdFee() != null ? rebateResp.getThirdFee().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() : "0");
        model.addAttribute("rebateModel", rebateResp);
        List<EnumTopgateWithdrawPayway> list = new ArrayList<>();
        for (EnumTopgateWithdrawPayway wayType : EnumTopgateWithdrawPayway.values()) {
            String message = context.getMessage(wayType.getDescription(), null, locale);
            wayType.setValue(message);
            list.add(wayType);
        }
        model.addAttribute("enumPayTypes", list);
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

    @Override
    public BaseResult listAll(Locale locale) {
        BaseResult baseResult = payWayFeign.listAll();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(baseResult.getData()));
        String list = jsonObject.getString("list");
        List<TopgateRechargePayWayResp> resps = JSONObject.parseArray(list, TopgateRechargePayWayResp.class);
        for (TopgateRechargePayWayResp resp : resps) {
            String message = context.getMessage(resp.getPayWayDescKey(), null, locale);
            resp.setPayName(message);
        }
        return BaseResult.success(resps);
    }
}
