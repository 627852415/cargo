package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.InviteFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.YebFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.InviteRelationService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumCountryCodeType;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.UserInviteListPageResp;
import com.lxtx.im.admin.service.response.UserInviteResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author liyunhua
 * @Date 2019/1/11
 */
@Service
public class InviteRelationServiceImpl implements InviteRelationService {

    @Resource
    private InviteFeign inviteFeign;
    @Resource
    private UserFeign userFeign;
    @Resource
    private YebFeign yebFeign;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;

    private static final String INVITE_RELATION_EXCEL_NAME = "邀请关系列表";

    @Override
    public BaseResult listPage(InviteRelationListPageReq req) {
        FeignUserListReq userListReq = new FeignUserListReq();
        BeanUtils.copyProperties(req, userListReq);
        if (userService.isShowAccount()) {
            userListReq.setCountryCode(EnumCountryCodeType.KH.getCode());
        }
        return inviteFeign.listPage(userListReq);
    }

    @Override
    public BaseResult addRelation(InviteRelationReq inviteRelationReq) {
        FeignInviteRelationReq feignInviteRelationReq = new FeignInviteRelationReq();
        BeanUtils.copyProperties(inviteRelationReq, feignInviteRelationReq);
        BaseResult baseResult = inviteFeign.addRelation(feignInviteRelationReq);
//        if(!baseResult.isSuccess())
//            return baseResult;
        FeignYebUserReferReq feignYebUserReferReq = new FeignYebUserReferReq();
        feignYebUserReferReq.setAccount(inviteRelationReq.getAccount());
        feignYebUserReferReq.setParentAccount(inviteRelationReq.getParentAccount());
        return yebFeign.refer(feignYebUserReferReq);
    }

    @Override
    public BaseResult checkInviteRelation(InviteRelationReq inviteRelationReq) {
        FeignInviteRelationReq feignInviteRelationReq = new FeignInviteRelationReq();
        BeanUtils.copyProperties(inviteRelationReq, feignInviteRelationReq);
        return inviteFeign.checkInviteRelation(feignInviteRelationReq);
    }

    @Override
    public BaseResult higherList(ViewRelationReq req) {
        FeignViewRelationReq feignViewRelationReq = new FeignViewRelationReq();
        BeanUtils.copyProperties(req, feignViewRelationReq);
        return inviteFeign.higherList(feignViewRelationReq);
    }

    @Override
    public BaseResult lowerList(ViewRelationPageReq req) {
        FeignViewRelationPageReq feignViewRelationReq = new FeignViewRelationPageReq();
        BeanUtils.copyProperties(req, feignViewRelationReq);
        return inviteFeign.lowerList(feignViewRelationReq);
    }

    @Override
    public BaseResult directList(InviteDirectRelationReq req) {
        FeignInviteDirectRelationReq feignInviteDirectRelationReq = new FeignInviteDirectRelationReq();
        BeanUtils.copyProperties(req, feignInviteDirectRelationReq);
        BaseResult result = inviteFeign.directList(feignInviteDirectRelationReq);
        if (userService.isShowAccount()) {
            if (result.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                List<UserInviteResp> records = JSONObject.parseArray(jsonObject.getString("records"), UserInviteResp.class);
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(records)) {
                    List<UserInviteResp> collect = records.stream()
                            .filter(userInviteResp -> EnumCountryCodeType.KH.getCode().equalsIgnoreCase(userInviteResp.getCountryCode()))
                            .collect(Collectors.toList());
                    Map<String, List<UserInviteResp>> data = new HashMap<>();
                    data.put("records", collect);
                    return BaseResult.success(data);
                }
            }
        }
        return result;
    }

    @Override
    public BaseResult eachLevelNumList(ViewRelationReq req) {
        FeignViewRelationReq feignViewRelationReq = new FeignViewRelationReq();
        BeanUtils.copyProperties(req, feignViewRelationReq);
        return inviteFeign.eachLevelNumList(feignViewRelationReq);
    }

    @Override
    public void exportExcel(HttpServletResponse response, InviteRelationListPageReq req) {
        int maxSize = 50000;
        String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
        if (StringUtils.isNumeric(maxPageSize)) {
            maxSize = Integer.valueOf(maxPageSize);
        }
        req.setSize(maxSize);
        FeignUserListReq userListReq = new FeignUserListReq();
        BeanUtils.copyProperties(req, userListReq);
        if (userService.isShowAccount()) {
            userListReq.setCountryCode(EnumCountryCodeType.KH.getCode());
        }
        BaseResult baseResult = inviteFeign.listPage(userListReq);
        List<UserInviteResp> records = new ArrayList<>();
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            UserInviteListPageResp userInviteListPageResp = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<UserInviteListPageResp>() {
            });
            records = userInviteListPageResp.getRecords();
        }

        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        ExcelUtil.exportExcel(response, records, INVITE_RELATION_EXCEL_NAME, INVITE_RELATION_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }

    @Override
    public BaseResult directHigher(InviteRelationListPageReq req) {
        String account = req.getAccount();
        String telephone = req.getTelephone();
        if (StringUtils.isBlank(account) && StringUtils.isBlank(telephone)) {
            return BaseResult.success();
        }
        //如果有输入电话号码，先根据电话号码查询到帐号，再带过去查询
        if (StringUtils.isNotBlank(telephone)) {
            FeignUserInfoReq feignUserInfoReq = new FeignUserInfoReq();
            feignUserInfoReq.setTelephone(telephone);
            BaseResult userResult = userFeign.getUserAccountByUserInfo(feignUserInfoReq);
            if (!userResult.isSuccess()) {
                return BaseResult.success();
            }
            if (userResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(userResult.getData()));
                List<String> accountList = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                if (CollectionUtils.isEmpty(accountList)) {
                    return BaseResult.success();
                }
                req.setAccount(accountList.get(0));
            }
        }
        FeignViewRelationReq feignViewRelationReq = new FeignViewRelationReq();
        BeanUtils.copyProperties(req, feignViewRelationReq);

        BaseResult result = inviteFeign.directHigher(feignViewRelationReq);
        if (userService.isShowAccount()) {
            if (result.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                List<UserInviteResp> records = JSONObject.parseArray(jsonObject.getString("records"), UserInviteResp.class);
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(records)) {
                    List<UserInviteResp> collect = records.stream()
                            .filter(userInviteResp -> EnumCountryCodeType.KH.getCode().equalsIgnoreCase(userInviteResp.getCountryCode()))
                            .collect(Collectors.toList());
                    Map<String, List<UserInviteResp>> data = new HashMap<>();
                    data.put("records", collect);
                    return BaseResult.success(data);
                }
            }
        }
        return result;
    }

    @Override
    public BaseResult bindLowerList(InviteRelationListPageReq req) {
        String account = req.getAccount();
        String telephone = req.getTelephone();
        if (StringUtils.isBlank(account) && StringUtils.isBlank(telephone)) {
            return BaseResult.success();
        }
        //如果有输入电话号码，先根据电话号码查询到帐号，再带过去查询
        if (StringUtils.isNotBlank(telephone)) {
            FeignUserInfoReq feignUserInfoReq = new FeignUserInfoReq();
            feignUserInfoReq.setTelephone(telephone);
            BaseResult userResult = userFeign.getUserAccountByUserInfo(feignUserInfoReq);
            if (!userResult.isSuccess()) {
                return BaseResult.success();
            }
            if (userResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(userResult.getData()));
                List<String> accountList = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                if (CollectionUtils.isEmpty(accountList)) {
                    return BaseResult.success();
                }
                req.setAccount(accountList.get(0));
            }
        }
        ViewRelationPageReq viewRelationPageReq = new ViewRelationPageReq();
        BeanUtils.copyProperties(req, viewRelationPageReq);

        BaseResult result = this.lowerList(viewRelationPageReq);
        if (userService.isShowAccount()) {
            if (result.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                List<UserInviteResp> records = JSONObject.parseArray(jsonObject.getString("records"), UserInviteResp.class);
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(records)) {
                    List<UserInviteResp> collect = records.stream()
                            .filter(userInviteResp -> EnumCountryCodeType.KH.getCode().equalsIgnoreCase(userInviteResp.getCountryCode()))
                            .collect(Collectors.toList());
                    Map<String, List<UserInviteResp>> data = new HashMap<>();
                    data.put("records", collect);
                    return BaseResult.success(data);
                }
            }
        }
        return result;
    }

    @Override
    public BaseResult relationChange(RelationChangeReq relationChangeReq) {
        FeignRelationChangeReq feignReq = new FeignRelationChangeReq();
        BeanUtils.copyProperties(relationChangeReq, feignReq);
        return inviteFeign.relationChange(feignReq);
    }
}
