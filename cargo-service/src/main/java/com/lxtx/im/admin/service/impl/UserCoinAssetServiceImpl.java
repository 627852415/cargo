package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.PlatformWithdrawApplyFeign;
import com.lxtx.im.admin.feign.feign.UserCoinAssetFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.UserCoinAssetService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.UserCoinAssetDetailReq;
import com.lxtx.im.admin.service.request.UserCoinAssetDiffReq;
import com.lxtx.im.admin.service.request.UserCoinAssetListReq;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 资金流水
 * @author: CXM
 * @create: 2018-08-31 09:58
 **/
@Slf4j
@Service
public class UserCoinAssetServiceImpl implements UserCoinAssetService {

    private static final String APPLY_LIST_FILENAME = "资金流水列表";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private PlatformWithdrawApplyFeign platformFeign;
    @Resource
    private UserCoinAssetFeign assetFeign;
    @Resource
    private WalletUserFeign walletUserFeign;
    @Resource
    private UserFeign userFeign;


    @Override
    public BaseResult listPage(UserCoinAssetListReq req) {
        //如果是根据名称模糊查询，先调用core换取im id
        List<String> threeUserIdList = null;
        String userName = req.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            threeUserIdList = getUserIdList(userName);
            if (CollectionUtils.isEmpty(threeUserIdList)) {
                return BaseResult.success();
            }
        }

        //调用钱包获取提现列表
        FeignUserCoinAssetListReq listPageReq = new FeignUserCoinAssetListReq();
        BeanUtils.copyProperties(req, listPageReq);
        listPageReq.setThreeUserIdList(threeUserIdList);
        BaseResult baseResult = assetFeign.listPage(listPageReq);
        if (!baseResult.isSuccess() || baseResult.getData() == null) {
            return baseResult;
        }

        //调用钱包获取列表返回
        UserCoinAssetListPageResp resp = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()), UserCoinAssetListPageResp.class);
        List<UserCoinAssetListResp> records = resp.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return baseResult;
        }

        //设置返回参数
        setRespProperties(records);
        resp.setRecords(records);

        return BaseResult.success(resp);
    }

    /**
     * 设置系统提款申请列表返回参数
     *
     * @param records
     * @return
     * @author CXM
     * @date 2018-12-20 15:01
     */
    private void setRespProperties(List<UserCoinAssetListResp> records) {
        //添加im id 进List
        List<String> userIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(records)) {
            for (UserCoinAssetListResp resp : records) {
                if (!userIdList.contains(resp.getPlatformUserId())) {
                    userIdList.add(resp.getPlatformUserId());
                }
            }
        }

        //调用core获取im用户信息
        List<Map<String, Object>> userListResp = null;
        if (!CollectionUtils.isEmpty(userIdList)) {
            FeignQueryUserListReq feignMemberListReq = new FeignQueryUserListReq();
            feignMemberListReq.setIds(userIdList);
            BaseResult userListResult = userFeign.queryList(feignMemberListReq);

            Map<String, Object> dataMap = (Map<String, Object>) userListResult.getData();
            userListResp = (List<Map<String, Object>>) dataMap.get("list");
            if (CollectionUtils.isEmpty(userListResp)) {
                return;
            }
        }

        //设置用户名
        for (UserCoinAssetListResp resp : records) {
            for (Map<String, Object> userMap : userListResp) {
                if (resp.getPlatformUserId().equals(userMap.get("account"))) {
                    resp.setUserName((String) userMap.get("name"));
                }
            }
        }
    }

    /**
     * 根据用户名称模糊查询出所有用户的im id
     *
     * @param userName
     * @return
     * @author CXM
     * @date 2018-12-20 14:18
     */
    private List<String> getUserIdList(String userName) {
        //判断是否根据手机号模糊查询
        List<Map<String, Object>> userList = null;
        List<String> threeUserIdList = null;
        if (StringUtils.isNotBlank(userName)) {
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            feignMemberListReq.setName(userName);
            BaseResult userListResult = userFeign.list(feignMemberListReq);
            if (!userListResult.isSuccess() || userListResult.getData() == null) {
                return threeUserIdList;
            }
            Map<String, Object> dataMap = (Map<String, Object>) userListResult.getData();
            userList = (List<Map<String, Object>>) dataMap.get("list");
            if (CollectionUtils.isEmpty(userList)) {
                return threeUserIdList;
            }
            threeUserIdList = new ArrayList<>();
            for (Map<String, Object> userMap : userList) {
                threeUserIdList.add((String) userMap.get("account"));
            }
        }
        return threeUserIdList;
    }


    @Override
    public String detail(UserCoinAssetDetailReq req, Model model) {
        FeignUserCoinAssetDetailReq detailReq = new FeignUserCoinAssetDetailReq();
        detailReq.setId(req.getId());
        //查询详情信息
        BaseResult detailResult = assetFeign.detail(detailReq);
        if (!detailResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("数据有误");
        }
        Map<String, Object> dataMap = (Map<String, Object>) detailResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        UserCoinAssetInfoResp detailResp = JSONObject.parseObject(jsonResult, UserCoinAssetInfoResp.class);
        //----------测试-----------
//        detailResp.setUserId(null);
        //---------------------------
        boolean getWalletUserFlag = true;
        String userId = detailResp.getUserId();
        if (StringUtils.isNotBlank(userId)) {
            FeignUserDetailReq userDetailReq = new FeignUserDetailReq();
            userDetailReq.setAccount(userId);
            BaseResult userDetail = userFeign.detail(userDetailReq);
            if (userDetail.isSuccessAndDataNotNull()) {
                Map<String, Object> userDataMap = (Map<String, Object>) userDetail.getData();
                String userJsonResult = JSONArray.toJSONString(userDataMap);
                UserDetailResp userDetailResp = JSONObject.parseObject(userJsonResult, UserDetailResp.class);
                detailResp.setUserName(userDetailResp.getName());
                getWalletUserFlag = false;
            }
        }
        if (getWalletUserFlag) {
            String walletUserId = detailResp.getWalletUserId();
            if (StringUtils.isNotBlank(walletUserId)) {
                FeignUserReq feignUserReq = new FeignUserReq();
                feignUserReq.setUserId(walletUserId);
                BaseResult result = walletUserFeign.list(feignUserReq);
                if (result.isSuccessAndDataNotNull()) {
                    Map<String, Object> walletUserMap = (Map<String, Object>) result.getData();
                    String walletUserJson = JSONArray.toJSONString(walletUserMap);
                    UserPageResp userPageResp = JSONObject.parseObject(walletUserJson, UserPageResp.class);
                    //查询IM用户名称、手机号码等信息
                    List<WalletUserResp> userRespList = userPageResp.getRecords();
                    if (!CollectionUtils.isEmpty(userRespList)) {
                        WalletUserResp walletUserResp = userRespList.get(0);
                        detailResp.setUserName(walletUserResp.getName());
                    }
                }
            }
        }
        //设置用户的相关信息
//        FeignUserDetailReq userDetailReq = new FeignUserDetailReq();
//        userDetailReq.setAccount(detailResp.getUserId());
//        BaseResult userDetail = userFeign.detail(userDetailReq);
//        if(!userDetail.isSuccess() || userDetail.getData() == null){
//            throw LxtxBizException.newException("账号有误");
//        }
//        Map<String, Object> userDataMap = (Map<String, Object>) userDetail.getData();
//        String userJsonResult = JSONArray.toJSONString(userDataMap);
//        UserDetailResp userDetailResp = JSONObject.parseObject(userJsonResult, UserDetailResp.class);
//        detailResp.setUserName(userDetailResp.getName());
        detailResp.setUpdateStrTime(DateUtils.getDateFormat(detailResp.getUpdateTime(), DateUtils.DATE_FORMAT_DEFAULT));
        model.addAttribute("detail", detailResp);
        //判断审核的状态,跳转到不同的页面
        return "asset/user-coin-asset-detail";
    }

    /**
     * 设置国际简码 CN/86
     *
     * @param countryCode
     * @param fullTelephone
     * @param telephone
     * @return
     */
    public String setCountryCode(String countryCode, String fullTelephone, String telephone) {
        if (StringUtils.isBlank(fullTelephone)) {
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
    public void downloadList(HttpServletResponse response, UserCoinAssetListReq req) {
        //如果是根据名称模糊查询，先调用core换取im id
        List<String> threeUserIdList = null;
        String userName = req.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            threeUserIdList = getUserIdList(userName);

        }

        //调用钱包获取提现列表
        FeignUserCoinAssetListReq listPageReq = new FeignUserCoinAssetListReq();
        BeanUtils.copyProperties(req, listPageReq);
        listPageReq.setThreeUserIdList(threeUserIdList);
        BaseResult baseResult = assetFeign.downloadList(listPageReq);

        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        if (baseResult.isSuccess() || baseResult.getData() != null) {
            List<UserCoinAssetListResp> respList = JSONObject.parseArray(JSONArray.toJSONString(dataMap.get("list")), UserCoinAssetListResp.class);
            //设置返回参数
            setRespProperties(respList);
            //导出的文件名
            String fileName = setPlatformWithdrawApplyDownloadFileName(req.getStartTime(), req.getEndTime());
            //ExcelUtil.exportExcel(response, respList, fileName);
            ExcelUtil.exportExcel(response, respList, fileName, APPLY_LIST_FILENAME);
        }
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
                fileName = fileName + DATE_FORMAT.format(startTime) + "至" + DATE_FORMAT.format(endTime);
            } else {
                fileName = fileName + DATE_FORMAT.format(startTime) + "至" + DATE_FORMAT.format(new Date());
            }
        } else {
            if (endTime != null) {
                fileName = fileName + "至" + DATE_FORMAT.format(endTime);
            } else {
                fileName = fileName + DATE_FORMAT.format(new Date());
            }
        }
        return fileName;
    }

    @Override
    public BaseResult generateDiffFlowReport(UserCoinAssetDiffReq req) {
        FeignUserCoinAssetDiffReq assetListReq = new FeignUserCoinAssetDiffReq();
        assetListReq.setWalletUserId(req.getWalletUserId());
        return assetFeign.generateFlowReport(assetListReq);
    }


}
