package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.UserCoinDailyListFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.UserCoinDailyListService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.UserCoinDailyDetailReq;
import com.lxtx.im.admin.service.request.UserCoinDailyListReq;
import com.lxtx.im.admin.service.request.UserCoinDailySnapshotRebuildReq;
import com.lxtx.im.admin.service.response.UserCoinDailyDetalResp;
import com.lxtx.im.admin.service.response.UserCoinDailyResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserCoinDailyListServiceImpl implements UserCoinDailyListService {

    @Autowired
    private UserCoinDailyListFeign userCoinDailyListFeign;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private WalletUserFeign walletUserFeign;
    @Autowired
    private RedisCacheUtils redisCacheUtils;
    private static final String ASSETS_SNAPSHOT_TASK_LOCK = "assets_snapshot_task_lock";
    private static final String USER_COIN_DAILY_DETAIL_EXCEL_NAME = "用户账户钱包快照";

    public void downData(HttpServletResponse response, String id) {
        FeignUserCoinDailyListDownReq feignUserCoinDailyListDownReq = new FeignUserCoinDailyListDownReq();
        feignUserCoinDailyListDownReq.setId(id);
        BaseResult baseResult = userCoinDailyListFeign.downData(feignUserCoinDailyListDownReq);
        String fileName = "";
        String path = "";
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> map = (Map<String, Object>) baseResult.getData();
            fileName = (String) map.get("fileNmae");
            path = (String) map.get("path");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                InputStream is = new BufferedInputStream(new FileInputStream(path));
                // 设置response参数，可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName
                        .getBytes("utf-8"), "ISO8859-1"));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                ServletOutputStream out = response.getOutputStream();
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public BaseResult listPage(UserCoinDailyListReq req) {
        FeignUserCoinDailyListReq feignUserCoinDailyListReq = new FeignUserCoinDailyListReq();
        BeanUtils.copyProperties(req, feignUserCoinDailyListReq);
        return userCoinDailyListFeign.listPage(feignUserCoinDailyListReq);
    }

    @Override
    public BaseResult detail(UserCoinDailyDetailReq req) {
        List<String> userIds = new ArrayList<>();
        List<String> accountIds = new ArrayList<>();
        if (StringUtils.isNotBlank(req.getQueryCondition())) {
            // 根据用户名称查询指定用户
            FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
            usernameReq.setName(req.getQueryCondition());
            BaseResult queryByUsername = userFeign.queryByUsername(usernameReq);
            if (queryByUsername.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUsername.getData()));
                accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
            }
        }
        // 查询对应钱包用户ID
        if (accountIds != null && !accountIds.isEmpty()) {
            FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
            registerUserReq.setAccounts(accountIds);
            BaseResult registerWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
            if (registerWalletUser.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(registerWalletUser.getData()));
                userIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
            }
        }
        FeignUserCoinDailyDetailReq feignUserCoinDailyDetailReq = new FeignUserCoinDailyDetailReq();
        BeanUtils.copyProperties(req, feignUserCoinDailyDetailReq);
        feignUserCoinDailyDetailReq.setUserIds(userIds);
        return userCoinDailyListFeign.detail(feignUserCoinDailyDetailReq);
    }

    @Override
    public void createDetailExcel(HttpServletResponse response, UserCoinDailyDetailReq req) {
        List<String> userIds = new ArrayList<>();
        List<String> accountIds = new ArrayList<>();
        if (StringUtils.isNotBlank(req.getQueryCondition())) {
            // 根据用户名称查询指定用户
            FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
            usernameReq.setName(req.getQueryCondition());
            BaseResult queryByUsername = userFeign.queryByUsername(usernameReq);
            if (queryByUsername.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUsername.getData()));
                accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
            }
        }
        // 查询对应钱包用户ID
        if (accountIds != null && !accountIds.isEmpty()) {
            FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
            registerUserReq.setAccounts(accountIds);
            BaseResult registerWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
            if (registerWalletUser.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(registerWalletUser.getData()));
                userIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
            }
        }
        FeignUserCoinDailyDetailReq feignUserCoinDailyDetailReq = new FeignUserCoinDailyDetailReq();
        BeanUtils.copyProperties(req, feignUserCoinDailyDetailReq);
        feignUserCoinDailyDetailReq.setUserIds(userIds);
        BaseResult baseResult = userCoinDailyListFeign.createDetailExcel(feignUserCoinDailyDetailReq);
        List<UserCoinDailyDetalResp> records = new ArrayList<>();
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap);
            UserCoinDailyResp chargeReportListAllResp = JSONObject.parseObject(jsonResult, UserCoinDailyResp.class);
            records = chargeReportListAllResp.getList();
        }
        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        StringBuffer fileName = new StringBuffer(USER_COIN_DAILY_DETAIL_EXCEL_NAME);
        Date dayBefore = new Date();
        String dateFormat = DateUtils.getDateFormat(dayBefore, DateUtils.DATE_FORMAT_YYYY_MM_DD);
        ExcelUtil.exportExcel(response, records, dateFormat + fileName.toString(), dateFormat +
                USER_COIN_DAILY_DETAIL_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }

    @Override
    public BaseResult rebuildSnapshot(UserCoinDailySnapshotRebuildReq req) {
        boolean isRunning = redisCacheUtils.exists(ASSETS_SNAPSHOT_TASK_LOCK);
        if (isRunning) {
            BaseResult result = BaseResult.success();
            result.setMsg("资金快照已经在生成中！请稍后查看");
            return result;
        }

        FeignUserCoinDailySnapshotRebuildReq feign = new FeignUserCoinDailySnapshotRebuildReq();
        feign.setSnapshotDay(req.getSnapshotDay());
        BaseResult baseresult = userCoinDailyListFeign.rebuildSnapshot(feign);

        if (baseresult == null) {
            baseresult = BaseResult.success();
        }

        if (baseresult.isSuccess()){
            baseresult.setMsg("资金快照任务已提交，请稍后查看");
        }

        return baseresult;
    }

}
