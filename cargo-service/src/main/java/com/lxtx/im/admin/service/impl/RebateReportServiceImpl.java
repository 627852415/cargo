package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.RebateReportFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.GroupService;
import com.lxtx.im.admin.service.RebateReportService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumRebateType;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.GroupOwnerRebateDetailResp;
import com.lxtx.im.admin.service.response.RebateDetailPageResp;
import com.lxtx.im.admin.service.response.ReportRebateDailyResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author liyunhua
 * @Date 2019/1/10
 */
@Service
public class RebateReportServiceImpl implements RebateReportService {

    private static final String REBATE_LIST = "返佣报表";

    private static final String GROUP_OWNER_REBATE_DETAIL = "群主返佣详情";

    private static final String SUBORDINATE_REBATE_DETAIL = "下级返佣详情";

    @Autowired
    RebateReportFeign rebateReportFeign;
    @Autowired
    UserFeign userFeign;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Override
    public BaseResult listPage(RebateReportListPageReq req) {
        if (StringUtils.isNotBlank(req.getUserName())) {
            List<String> userNames =  getUserByUserName(req.getUserName());
            if(userNames != null) {
                req.setAccount(String.join(",", userNames));
            } else {
                return BaseResult.success();
            }
        }
        FeignRebateReportListPageReq listPageReq = new FeignRebateReportListPageReq();
        BeanUtils.copyProperties(req, listPageReq);

        BaseResult baseResult = rebateReportFeign.listPage(listPageReq);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Page<ReportRebateDailyResp> page = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<Page<ReportRebateDailyResp>>() {
            });

            page.getRecords().forEach(resp -> {
                String reportDate = DateUtils.getDateFormat(new Date(Long.parseLong(resp.getReportDate())), "yyyy-MM-dd");
                resp.setReportDate(reportDate);
                String account = resp.getAccount();
                UserDetailResp userDetailResp = getUser(account);
                if (userDetailResp != null) {
                    resp.setUserName(userDetailResp.getName());
                    resp.setTelephone(userDetailResp.getTelephone());
                }
            });
            baseResult.setData(page);
        }
        return baseResult;
    }


    /**
     * 获取返佣列表
     * @param req
     * @return
     */
    @Override
    public BaseResult rebateList(RebateReportListPageReq req) {
        UserInfoReq userInfoReq = new UserInfoReq();
        BeanUtils.copyProperties(req,userInfoReq);

        String userAccount = req.getAccount();
        String telephone = req.getTelephone();
        String userName = req.getUserName();

        //查询条件
        if (StringUtils.isNotBlank(userAccount) || StringUtils.isNotBlank(telephone) || StringUtils.isNotBlank(userName)) {
            List<String> userAccountList =  userService.getUserAccountByUserInfo(userInfoReq);
            if(userAccountList != null) {
                req.setAccount(String.join(",", userAccountList));
            } else {
                return BaseResult.success();
            }
        }
        FeignRebateReportListPageReq listPageReq = new FeignRebateReportListPageReq();
        BeanUtils.copyProperties(req, listPageReq);

        BaseResult baseResult = rebateReportFeign.rebateList(listPageReq);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Page<ReportRebateDailyResp> page = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<Page<ReportRebateDailyResp>>() {
            });

            page.getRecords().forEach(resp -> {
                String reportDate = DateUtils.getDateFormat(new Date(Long.parseLong(resp.getReportDate())), "yyyy-MM-dd");
                resp.setReportDate(reportDate);
                String account = resp.getAccount();
                UserDetailResp userDetailResp = getUser(account);
                if (userDetailResp != null) {
                    resp.setUserName(userDetailResp.getName());
                    resp.setTelephone(userDetailResp.getTelephone());
                }
            });
            baseResult.setData(page);
        }
        return baseResult;
    }


    /**
     * 返佣列表下载
     * @param response
     * @param req
     */
    @Override
    public void rebateListDownload(HttpServletResponse response, RebateReportListReq req) {
        UserInfoReq userInfoReq = new UserInfoReq();
        BeanUtils.copyProperties(req,userInfoReq);

        String userAccount = req.getAccount();
        String telephone = req.getTelephone();
        String userName = req.getUserName();

        //查询条件
        if (StringUtils.isNotBlank(userAccount) || StringUtils.isNotBlank(telephone) || StringUtils.isNotBlank(userName)) {
            List<String> userAccountList =  userService.getUserAccountByUserInfo(userInfoReq);
            if(userAccountList != null) {
                req.setAccount(String.join(",", userAccountList));
            } else {
                throw LxtxBizException.newException("没有相关数据");
            }
        }
        FeignRebateReportListPageReq listPageReq = new FeignRebateReportListPageReq();
        BeanUtils.copyProperties(req, listPageReq);
        if(req.getCreateDate() != null){
            String createDate = DateUtils.getDateFormat(req.getCreateDate());
            listPageReq.setCreateDate(createDate);
        }
        if(req.getEndDate() != null){
            String endDate = DateUtils.getDateFormat(req.getEndDate());
            listPageReq.setEndDate(endDate);
        }
        listPageReq.setSize(100000);
        BaseResult baseResult = rebateReportFeign.rebateList(listPageReq);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Page<ReportRebateDailyResp> page = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<Page<ReportRebateDailyResp>>() {
            });

            page.getRecords().forEach(resp -> {
                String reportDate = DateUtils.getDateFormat(new Date(Long.parseLong(resp.getReportDate())), "yyyy-MM-dd");
                resp.setReportDate(reportDate);
                String account = resp.getAccount();
                UserDetailResp userDetailResp = getUser(account);
                if (userDetailResp != null) {
                    resp.setUserName(userDetailResp.getName());
                    resp.setTelephone(userDetailResp.getTelephone());
                }
            });

            List<ReportRebateDailyResp> respList = page.getRecords();
            //导出的文件名
            String fileName = REBATE_LIST + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            ExcelUtil.exportExcel(response, respList, fileName, REBATE_LIST);
        }
    }

    /**
     * 返佣详情下载
     * @param response
     * @param req
     */
    @Override
    public void rebateDetailDownload(HttpServletResponse response, RebateDetailPageReq req) {

        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setAccount(req.getPlayerAccount());
        userInfoReq.setTelephone(req.getPlayerTelephone());
        userInfoReq.setUserName(req.getPlayerUserName());

        String userAccount = req.getPlayerAccount();
        String telephone = req.getPlayerTelephone();
        String userName = req.getUserName();

        FeignRebateDetailPageReq feign = new FeignRebateDetailPageReq();
        BeanUtils.copyProperties(req, feign);

        //查询条件
        if (StringUtils.isNotBlank(userAccount) || StringUtils.isNotBlank(telephone) || StringUtils.isNotBlank(userName)) {
            List<String> userAccountList =  userService.getUserAccountByUserInfo(userInfoReq);
            if(userAccountList == null) {
               throw LxtxBizException.newException("没有相关数据");
            }else{
                feign.setPlayerAccountList(userAccountList);
            }
        }

        feign.setSize(100000);
        BaseResult baseResult = rebateReportFeign.rebateDetail(feign);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Page<RebateDetailPageResp> page = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<Page<RebateDetailPageResp>>() {
            });
            page.getRecords().forEach(resp -> {
                String account = resp.getPlayerAccount();
                String groupName = groupService.getGroupName(resp.getGroupId());
                resp.setGroupName(groupName);
                UserDetailResp userDetailResp = getUser(account);
                if (userDetailResp != null) {
                    resp.setPlayerUserName(userDetailResp.getName());
                    resp.setTelephone(userDetailResp.getTelephone());
                }
            });

            List<RebateDetailPageResp> respList = page.getRecords();
            if(CollectionUtils.isEmpty(respList)){
                throw LxtxBizException.newException("没有相关数据");
            }
            if(EnumRebateType.OWNER_REBATE.getCode().equals(req.getRebateType())){
                String sheetName = GROUP_OWNER_REBATE_DETAIL;
                //封装成表格数据
                List<GroupOwnerRebateDetailResp>  detailPageRespList = new ArrayList<>();
                for(RebateDetailPageResp resp : respList){
                    GroupOwnerRebateDetailResp rebateDetailPageResp = new GroupOwnerRebateDetailResp();
                    BeanUtils.copyProperties(resp,rebateDetailPageResp);
                    detailPageRespList.add(rebateDetailPageResp);
                }

                //导出的文件名
                String fileName = sheetName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

                ExcelUtil.exportExcel(response, detailPageRespList, fileName, sheetName);
            }
            if(EnumRebateType.SUBORDINATE_REBATE.getCode().equals(req.getRebateType())){
                String sheetName = SUBORDINATE_REBATE_DETAIL;
                //导出的文件名
                String fileName = sheetName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

                ExcelUtil.exportExcel(response, respList, fileName, sheetName);
            }

        }

    }

    /**
     * 获取返佣详情
     * @param req
     * @return
     */
    @Override
    public BaseResult rebateDetail(RebateDetailPageReq req) {

        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setAccount(req.getPlayerAccount());
        userInfoReq.setTelephone(req.getPlayerTelephone());
        userInfoReq.setUserName(req.getPlayerUserName());

        String userAccount = req.getPlayerAccount();
        String telephone = req.getPlayerTelephone();
        String userName = req.getUserName();

        FeignRebateDetailPageReq feign = new FeignRebateDetailPageReq();
        BeanUtils.copyProperties(req, feign);

        //查询条件
        if (StringUtils.isNotBlank(userAccount) || StringUtils.isNotBlank(telephone) || StringUtils.isNotBlank(userName)) {
            List<String> userAccountList =  userService.getUserAccountByUserInfo(userInfoReq);
            if(userAccountList == null) {
                return BaseResult.success();
            }else{
                feign.setPlayerAccountList(userAccountList);
            }
        }


        BaseResult baseResult = rebateReportFeign.rebateDetail(feign);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Page<RebateDetailPageResp> page = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<Page<RebateDetailPageResp>>() {
            });
            page.getRecords().forEach(resp -> {
                String account = resp.getPlayerAccount();
                String groupName = groupService.getGroupName(resp.getGroupId());
                resp.setGroupName(groupName);
                UserDetailResp userDetailResp = getUser(account);
                if (userDetailResp != null) {
                    resp.setPlayerUserName(userDetailResp.getName());
                    resp.setTelephone(userDetailResp.getTelephone());
                }
            });
            baseResult.setData(page);
        }
        return baseResult;
    }



    @Override
    public BaseResult userRebateDetailList(RebateDetailPageReq req) {
        if (StringUtils.isNotBlank(req.getPlayerUserName())) {
            List<String> userNames =  getUserByUserName(req.getPlayerUserName());
            if(userNames != null) {
                req.setPlayerAccount(String.join(",", userNames));
            } else {
                return BaseResult.error("", "用户不存在!");
            }
        }

        FeignRebateDetailPageReq feign = new FeignRebateDetailPageReq();
        BeanUtils.copyProperties(req, feign);

        BaseResult baseResult = rebateReportFeign.userRebateDetailList(feign);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Page<RebateDetailPageResp> page = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<Page<RebateDetailPageResp>>() {
            });

            page.getRecords().forEach(resp -> {
                String account = resp.getPlayerAccount();
                UserDetailResp userDetailResp = getUser(account);
                if (userDetailResp != null) {
                    resp.setPlayerUserName(userDetailResp.getName());
                    resp.setTelephone(userDetailResp.getTelephone());
                }
            });
            baseResult.setData(page);
        }
        return baseResult;
    }

    @Override
    public BaseResult playerGameDetail(RebatePlayerGameDetailPageReq req) {
        FeignRebatePlayerGameDetailPageReq feign = new FeignRebatePlayerGameDetailPageReq();
        BeanUtils.copyProperties(req, feign);
        BaseResult baseResult = rebateReportFeign.playerGameDetail(feign);
        if (baseResult.isSuccess() && baseResult.getData() != null) {

        }
        return baseResult;
    }



    private UserDetailResp getUser(String account) {
        FeignUserDetailReq req = new FeignUserDetailReq();
        req.setAccount(account);
        BaseResult baseResult = userFeign.detail(req);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            UserDetailResp userDetailResp = JSON.parseObject(JSON.toJSONString(baseResult.getData()), new TypeReference<UserDetailResp>() {
            });
            return userDetailResp;
        }

        return null;
    }

    private List<String> getUserByUserName(String userName) {
        List<String> accountIds = null;
        FeignQueryUsernameReq req = new FeignQueryUsernameReq();
        req.setName(userName);
        BaseResult baseResult = userFeign.queryByUsername(req);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
        }

        return accountIds;
    }


}
