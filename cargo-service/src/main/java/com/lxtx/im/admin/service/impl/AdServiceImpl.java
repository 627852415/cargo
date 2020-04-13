package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.file.enums.EnumProjectType;
import com.lxtx.framework.common.file.enums.EnumRelateType;
import com.lxtx.framework.common.file.utils.QiNiuUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.AdFeign;
import com.lxtx.im.admin.feign.feign.AdPositionFeign;
import com.lxtx.im.admin.feign.feign.GlobalCodeFeign;
import com.lxtx.im.admin.feign.request.FeignAdDetailByIdReq;
import com.lxtx.im.admin.feign.request.FeignAdListReq;
import com.lxtx.im.admin.feign.request.FeignAdSaveReq;
import com.lxtx.im.admin.feign.request.FeignGlobalCodeByCountryReq;
import com.lxtx.im.admin.service.AdService;
import com.lxtx.im.admin.service.request.AdDetailByIdReq;
import com.lxtx.im.admin.service.request.AdListReq;
import com.lxtx.im.admin.service.request.AdSaveReq;
import com.lxtx.im.admin.service.response.AdDetailResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 广告
 *
 * @author map
 */
@Slf4j
@Service
public class AdServiceImpl implements AdService {

    // 中国','泰国','柬埔寨','缅甸','菲律宾','新加坡','马来西亚
    private static List<String> DEFAULT_COUNTRY_CODES = Lists.newArrayList("CN", "TH", "KH", "MY", "PH", "SG", "MM");

    @Resource
    private AdFeign adFeign;

    @Resource
    private GlobalCodeFeign globalCodeFeign;

    @Resource
    private AdPositionFeign adPositionFeign;

    @Autowired
    private QiNiuUtil qiNiuUtil;

    @Override
    public BaseResult listPage(AdListReq req) {
        FeignAdListReq feignReq = new FeignAdListReq();
        BeanUtils.copyProperties(req, feignReq);
        return adFeign.listPage(feignReq);
    }

    @Override
    public BaseResult upload(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = null;
        try {
            filePath = qiNiuUtil.upload(multipartFile.getBytes(),prefix, EnumProjectType.FINCY, EnumRelateType.ADMIN_ADVERT);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return BaseResult.success(PropertiesUtil.getFileViewUrl(filePath));
    }

    @Override
    public void add(Model model,String id) {
        BaseResult baseResult = adPositionFeign.selectList();
        Map<String, Object> map = (Map<String, Object>)baseResult.getData();
        model.addAttribute("adPositionList", map.get("list"));

        // 国际简码
        BaseResult globalCodeResult = globalCodeFeign.list(new FeignGlobalCodeByCountryReq(DEFAULT_COUNTRY_CODES));
        Map<String, Object> globalCodeMap = (Map<String, Object>)globalCodeResult.getData();
        model.addAttribute("globalCodeList", globalCodeMap.get("list"));
        model.addAttribute("adPositionId",id);
    }

    @Override
    public BaseResult save(AdSaveReq req) {
        FeignAdSaveReq feignReq = new FeignAdSaveReq();
        BeanUtils.copyProperties(req, feignReq);
        return adFeign.save(feignReq);
    }

    @Override
    public void detail(AdDetailByIdReq req, Model model) {
        BaseResult baseResult = adPositionFeign.selectList();
        Map<String, Object> map = (Map<String, Object>)baseResult.getData();
        model.addAttribute("adPositionList", map.get("list"));

        // 国际简码
        BaseResult globalCodeResult = globalCodeFeign.list(new FeignGlobalCodeByCountryReq(DEFAULT_COUNTRY_CODES));
        Map<String, Object> globalCodeMap = (Map<String, Object>)globalCodeResult.getData();
        model.addAttribute("globalCodeList", globalCodeMap.get("list"));

        FeignAdDetailByIdReq feignAdDetailByIdReq = new FeignAdDetailByIdReq();
        BeanUtils.copyProperties(req,feignAdDetailByIdReq);
        BaseResult result = adFeign.detail(feignAdDetailByIdReq);
        String resultJsona = JSONArray.toJSONString(result.getData());
        AdDetailResp adDetailResp = JSONObject.parseObject(resultJsona, AdDetailResp.class);
        model.addAttribute("detail",adDetailResp);
    }

    @Override
    public void initVal(Model model, String id) {

    }
}
