package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.HideDataUtil;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.im.admin.feign.feign.PlatformWithdrawApplyFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.PlatformWithdrawApplyService;
import com.lxtx.im.admin.service.enums.EnumPlatformWithdrawApplyStatus;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyAuditReq;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyDetailReq;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyListDownloadReq;
import com.lxtx.im.admin.service.request.PlatformWithdrawApplyListReq;
import com.lxtx.im.admin.service.response.PlatformWithdrawApplyListPageResp;
import com.lxtx.im.admin.service.response.PlatformWithdrawApplyListResp;
import com.lxtx.im.admin.service.response.PlatformWithdrawApplyResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 系统提现
 * @author: CXM
 * @create: 2018-08-31 09:58
 **/
@Service
public class PlatformWithdrawApplyServiceImpl implements PlatformWithdrawApplyService {
    private static final String APPLY_LIST_FILENAME = "系统提款申请列表";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PlatformWithdrawApplyFeign platformFeign;

    @Autowired
    private UserFeign userFeign;


    @Override
    public BaseResult listPage(PlatformWithdrawApplyListReq req) {
        //如果是根据手机号模糊查询，先调用core换取im id
        List<String> threeUserIdList = null;
        if (StringUtils.isNotBlank(req.getTelephone())) {
            threeUserIdList = getUserIdList(req.getTelephone());
            if (CollectionUtils.isEmpty(threeUserIdList)) {
                return BaseResult.success();
            }
        }

        //调用钱包获取提现列表
        FeignPlatformWithdrawApplyListReq listPageReq = new FeignPlatformWithdrawApplyListReq();
        BeanUtils.copyProperties(req, listPageReq);
        listPageReq.setThreeUserIdList(threeUserIdList);
        BaseResult baseResult = platformFeign.listPage(listPageReq);
        if(!baseResult.isSuccess() || baseResult.getData() == null){
            return baseResult;
        }

        //调用钱包获取列表返回
        PlatformWithdrawApplyListPageResp resp = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()), PlatformWithdrawApplyListPageResp.class);
        List<PlatformWithdrawApplyListResp> records = resp.getRecords();
        if(CollectionUtils.isEmpty(records)){
            return  baseResult;
        }

        //设置返回参数
        setPlatformWithdrawApplyListRespProperties(records);
        resp.setRecords(records);

        return BaseResult.success(resp);
    }

    /**
     * 设置系统提款申请列表返回参数
     *
     * @param  records
     * @return
     * @author  CXM
     * @date   2018-12-20 15:01
     */
    private List<PlatformWithdrawApplyListResp> setPlatformWithdrawApplyListRespProperties(List<PlatformWithdrawApplyListResp> records) {
        //添加im id 进List
        List<String> userIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(records)) {
            for (PlatformWithdrawApplyListResp platformWithdrawApplyListResp: records) {
                if (!userIdList.contains(platformWithdrawApplyListResp.getPlatformUserId())) {
                    userIdList.add(platformWithdrawApplyListResp.getPlatformUserId());
                }
            }
        }

        //调用core获取im用户信息
        List<Map<String, Object>> userListResp =  null;
        if(!CollectionUtils.isEmpty(userIdList)){
            FeignQueryUserListReq feignMemberListReq = new FeignQueryUserListReq();
            feignMemberListReq.setIds(userIdList);
            BaseResult userListResult = userFeign.queryList(feignMemberListReq);

            Map<String, Object> dataMap = (Map<String, Object>) userListResult.getData();
            if (CollectionUtils.isEmpty(dataMap)) {
                return new ArrayList<>();
            }
            userListResp = (List<Map<String, Object>>) dataMap.get("list");
            if(CollectionUtils.isEmpty(userListResp)){
                return new ArrayList<>();
            }
        }

        //设置用户手机号、国际简码
        for (PlatformWithdrawApplyListResp platformWithdrawApplyListResp: records) {
            for(Map<String, Object> userMap : userListResp) {
                if (platformWithdrawApplyListResp.getPlatformUserId().equals(userMap.get("account"))) {
                    platformWithdrawApplyListResp.setTelephone((String)userMap.get("telephone"));
                    String countryCode = setCountryCode((String)userMap.get("countryCode"), (String)userMap.get("fullTelephone"), (String)userMap.get("telephone"));
                    platformWithdrawApplyListResp.setCountryCode(countryCode);
                }
            }
            platformWithdrawApplyListResp.setTotalMoneyStr("￥" + platformWithdrawApplyListResp.getTotalMoney());
            platformWithdrawApplyListResp.setCurrentPriceStr("￥" + platformWithdrawApplyListResp.getCurrentPrice());
            platformWithdrawApplyListResp.setAuditedPriceStr("￥" + platformWithdrawApplyListResp.getAuditedPrice());
            platformWithdrawApplyListResp.setAccount(HideDataUtil.splitCardNo(platformWithdrawApplyListResp.getAccount()));
            EnumPlatformWithdrawApplyStatus statusType = EnumPlatformWithdrawApplyStatus.find(platformWithdrawApplyListResp.getStatus());
            platformWithdrawApplyListResp.setStatusMessage(statusType.getDescription());
        }
        return records;
    }

    /**
     * 根据手机号模糊查询出所有用户的im id
     *
     * @param  telephone
     * @return
     * @author  CXM
     * @date   2018-12-20 14:18
     */
    private List<String> getUserIdList(String telephone) {
        //判断是否根据手机号模糊查询
        List<Map<String, Object>> userList =  null;
        List<String> threeUserIdList = null;
        if(StringUtils.isNotBlank(telephone)){
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            feignMemberListReq.setTelephone(telephone);
            BaseResult userListResult = userFeign.list(feignMemberListReq);
            if(!userListResult.isSuccess() || userListResult.getData() == null){
                return threeUserIdList;
            }
            Map<String, Object> dataMap = (Map<String, Object>) userListResult.getData();
            userList = (List<Map<String, Object>>) dataMap.get("list");
            if(CollectionUtils.isEmpty(userList)){
                return threeUserIdList;
            }
            threeUserIdList = new ArrayList<>();
            for(Map<String, Object> userMap : userList){
                threeUserIdList.add((String) userMap.get("account"));
            }
        }
        return threeUserIdList;
    }

    @Override
    public String detail(PlatformWithdrawApplyDetailReq req, Model model) {
        FeignPlatformWithdrawApplyDetailReq detailReq = new FeignPlatformWithdrawApplyDetailReq();
        detailReq.setId(req.getId());
        //查询详情信息
        BaseResult detailResult = platformFeign.detail(detailReq);
        if(!detailResult.isSuccess() || detailResult.getData() == null){
            throw LxtxBizException.newException("申请数据有误");
        }
        Map<String, Object> dataMap = (Map<String, Object>) detailResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        PlatformWithdrawApplyResp detailResp = JSONObject.parseObject(jsonResult, PlatformWithdrawApplyResp.class);

        //设置用户的相关信息
        FeignUserDetailReq userDetailReq = new FeignUserDetailReq();
        userDetailReq.setAccount(detailResp.getUserId());
        BaseResult userDetail = userFeign.detail(userDetailReq);
        if(!userDetail.isSuccess() || userDetail.getData() == null){
            throw LxtxBizException.newException("申请人账号有误");
        }
        Map<String, Object> userDataMap = (Map<String, Object>) userDetail.getData();
        String userJsonResult = JSONArray.toJSONString(userDataMap);
        UserDetailResp userDetailResp = JSONObject.parseObject(userJsonResult, UserDetailResp.class);
        String countryCode = userDetailResp.getCountryCode();
        String fullTelephone = userDetailResp.getFullTelephone();
        String telephone = userDetailResp.getTelephone();
        detailResp.setTelephone(telephone);
        detailResp.setCountryCode(setCountryCode(countryCode, fullTelephone, telephone));
        detailResp.setCreateFormatTime(DateUtils.getDateFormat(detailResp.getCreateTime(), DateUtils.DATE_FORMAT_DEFAULT));
        detailResp.setUpdateFormatTime(DateUtils.getDateFormat(detailResp.getUpdateTime(), DateUtils.DATE_FORMAT_DEFAULT));

        BigDecimal auditedPrice = detailResp.getAuditedPrice();
        BigDecimal currentPrice = detailResp.getCurrentPrice();
        detailResp.setPriceCompare(NumberUtils.compareTo(currentPrice, auditedPrice));
        model.addAttribute("detail", detailResp);
        //判断审核的状态,跳转到不同的页面
        return "platform/platform-withdraw-apply-detail";
    }

    /**
     * 设置国际简码 CN/86
     * @param countryCode
     * @param fullTelephone
     * @param telephone
     * @return
     */
    public String setCountryCode(String countryCode, String fullTelephone, String telephone){
        if(StringUtils.isBlank(fullTelephone)){
            return countryCode;
        }
        String countryNumCode = fullTelephone.replace(telephone, "");
        StringBuilder builder = new StringBuilder();
        builder.append(countryCode);
        builder.append("/");
        builder.append(countryNumCode);
        return builder.toString();
    }

    @Override
    public BaseResult platformWithdrawApplyAudit(PlatformWithdrawApplyAuditReq req) {
        FeignPlatformWithdrawApplyAuditReq auditReq = new FeignPlatformWithdrawApplyAuditReq();
        BeanUtils.copyProperties(req, auditReq);
        return platformFeign.platformWithdrawApplyAudit(auditReq);
    }

    @Override
    public void download(HttpServletResponse response, PlatformWithdrawApplyListDownloadReq req) {
        List<String> threeUserIdList = null;
        if (StringUtils.isNotBlank(req.getTelephone())) {
            threeUserIdList = getUserIdList(req.getTelephone());
        }

        //调用钱包获取提现列表
        FeignPlatformWithdrawApplyListDownloadReq downloadReq = new FeignPlatformWithdrawApplyListDownloadReq();
        BeanUtils.copyProperties(req, downloadReq);
        downloadReq.setThreeUserIdList(threeUserIdList);
        BaseResult baseResult = platformFeign.list(downloadReq);

        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        List<PlatformWithdrawApplyListResp> respList = null;
        if(baseResult.isSuccess() || baseResult.getData() != null){
            respList = JSONObject.parseArray(JSONArray.toJSONString(dataMap.get("list")), PlatformWithdrawApplyListResp.class);
        }

        //设置返回参数
        respList = setPlatformWithdrawApplyListRespProperties(respList);

        //导出的文件名
        String fileName = setPlatformWithdrawApplyDownloadFileName(req.getStartTime(), req.getEndTime());
        ExcelUtil.exportExcel(response, respList, fileName, APPLY_LIST_FILENAME);
    }

    /**
     * 给导出文件命名
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private String setPlatformWithdrawApplyDownloadFileName(Date startTime, Date endTime) {
        String fileName = APPLY_LIST_FILENAME;
        if (startTime != null) {
            if (endTime != null) {
                fileName = fileName  + DATE_FORMAT.format(startTime) + "至" + DATE_FORMAT.format(endTime);
            } else {
                fileName = fileName  + DATE_FORMAT.format(startTime) + "至" + DATE_FORMAT.format(new Date());
            }
        } else {
            if (endTime != null) {
                fileName = fileName  + "至"+ DATE_FORMAT.format(endTime);
            } else {
                fileName = fileName  + DATE_FORMAT.format(new Date());
            }
        }
        return fileName;
    }
}
