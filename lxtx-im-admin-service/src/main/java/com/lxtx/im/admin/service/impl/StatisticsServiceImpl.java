package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.StatisticsFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.StatisticsService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.ChargeListPageInfoResp;
import com.lxtx.im.admin.service.response.StatisticsDownloadResp;
import com.lxtx.im.admin.service.response.StatisticsFeeDownloadResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author tnagdy
 * @Date 2018-09-10
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsFeign statisticsFeign;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private RedisCacheUtils redisCacheUtils;


    private static final String DOWNLOAD_FILENAME = "资产统计";


    /**
     * 手续费统计
     */
    private static final String FEE_STATISTICS_FILENAME = "手续费统计";

    private static final String FEE_LIST_FILENAME = "手续费列表";

    /**
     * 币种资产统计（不含商户资产）
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult listCoin(StatisticsCoinReq req) {
        FeignStatisticsCoinReq coinReq = new FeignStatisticsCoinReq();
        BeanUtils.copyProperties(req, coinReq);
        return statisticsFeign.listCoinAdmin(coinReq);
    }

    /**
     * 币种资产统计（喊商户资产）
     * @param req
     * @return
     */
    @Override
    public BaseResult listCoinContainMerchantAssets(StatisticsCoinContainMerchantAssetsReq req) {
        FeignStatisticsCoinContainMerchantAssetsReq feignReq = new FeignStatisticsCoinContainMerchantAssetsReq();
        BeanUtils.copyProperties(req, feignReq);
        return statisticsFeign.listCoinContainMerchantAssets(feignReq);
    }

    @Override
    public BaseResult redoStatistics(StatisticsCoinReq req) {
        BaseResult baseResult = new BaseResult();
        if(redisCacheUtils.exists(RedisConstants.ASSET_STATISTICS_LOCK)){
            baseResult.setSuccess(false);
            baseResult.setMsg("快照正在生成中，请勿重复点击!");
            return baseResult;
        }
        FeignStatisticsCoinReq coinReq = new FeignStatisticsCoinReq();
        BeanUtils.copyProperties(req, coinReq);
        statisticsFeign.redoStatistics(coinReq);
        baseResult.setSuccess(true);
        baseResult.setMsg("快照生成已提交，请耐心等待完成!");
        return baseResult;
    }

    @Override
    public void download(HttpServletResponse response, StatisticsDownloadReq req) {
        FeignStatisticsCoinContainMerchantAssetsReq feignReq = new FeignStatisticsCoinContainMerchantAssetsReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult baseResult = statisticsFeign.listCoinContainMerchantAssets(feignReq);
        Map<String, Object> map = new HashMap<>(0);
        if (baseResult.isSuccess()) {
            map = (Map<String, Object>) baseResult.getData();
        }

        List<StatisticsDownloadResp> respList = new ArrayList<>();
        if (map.get("records") != null) {
            respList = JSONObject.parseArray(JSON.toJSONString(map.get("records")), StatisticsDownloadResp.class);
        }
        //导出的文件名
        String fileName = DOWNLOAD_FILENAME + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ExcelUtil.exportExcel(response, respList, fileName, DOWNLOAD_FILENAME);
    }

    @Override
    public BaseResult coinChargeListPage(StatisticsCoinChargeListPageReq req) {
        //判断是否根据用户名称模糊查询
        String userName = req.getUserName();
        List<Map<String, Object>> userList =  null;
        List<String> threeUserIdList = null;
        if(StringUtils.isNotBlank(userName)){
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            feignMemberListReq.setName(userName);
            BaseResult userlistResult = userFeign.list(feignMemberListReq);
            if(!userlistResult.isSuccess() || userlistResult.getData() == null){
                return BaseResult.success();
            }
            Map<String, Object> dataMap = (Map<String, Object>) userlistResult.getData();
            userList = (List<Map<String, Object>>) dataMap.get("list");
            if(CollectionUtils.isEmpty(userList)){
                return  BaseResult.success();
            }
            threeUserIdList = new ArrayList<>();
            for(Map<String, Object> umap : userList){
                threeUserIdList.add((String) umap.get("account"));
            }
        }
        FeignStatisticsCoinChargeListPageReq listPageReq = new FeignStatisticsCoinChargeListPageReq();
        BeanUtils.copyProperties(req, listPageReq);
        listPageReq.setThreeUserIdList(threeUserIdList);
        BaseResult baseResult = statisticsFeign.feeStatementListPage(listPageReq);
        if(!baseResult.isSuccess() || baseResult.getData() == null){
            return baseResult;
        }
        //将用户ID转为用户名称
        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        List<Map<String, Object>> records = (List<Map<String, Object>>) dataMap.get("records");
        if(CollectionUtils.isEmpty(records)){
            return  baseResult;
        }
        // 根据ID查用户信息
        if(CollectionUtils.isEmpty(userList)){
            List<String> userIdList = new ArrayList<>();
            for(Map<String, Object> map : records){
                userIdList.add((String)map.get("threeUserId"));
            }
            FeignQueryUserListReq feignQueryUserListReq = new FeignQueryUserListReq();
            feignQueryUserListReq.setIds(userIdList);
            BaseResult userResult = userFeign.queryList(feignQueryUserListReq);
            if(!baseResult.isSuccess() || baseResult.getData() == null){
                return baseResult;
            }
            Map<String, Object> userMap = (Map<String, Object>) userResult.getData();
            userList = (List<Map<String, Object>>) userMap.get("list");
            if(CollectionUtils.isEmpty(userList)){
                return  baseResult;
            }
        }
        //设置用户名称
        for(Map<String, Object> map : records){
            for(Map<String, Object> umap : userList){
                String userId = (String) umap.get("account");
                if(userId.equals((String)map.get("threeUserId"))){
                    map.put("userName", umap.get("name"));
                }
            }
        }
        return baseResult;
    }

    @Override
    public BaseResult coinChargeList(StatisticsCoinChargeListReq req) {
        FeignStatisticsCoinChargeListReq coinReq = new FeignStatisticsCoinChargeListReq();
        BeanUtils.copyProperties(req, coinReq);
        return statisticsFeign.feeStatementList(coinReq);
    }

    /**
     * 币种手续费导出
     * @param response
     * @param req
     * @return void
     * @auther LiuLP
     * @date 2018/12/10 0010
     */
    @Override
    public void coinChargeDownload(HttpServletResponse response, StatisticsCoinChargeListReq req) {
        FeignStatisticsCoinChargeListReq coinReq = new FeignStatisticsCoinChargeListReq();
        BeanUtils.copyProperties(req, coinReq);
        BaseResult baseResult = statisticsFeign.feeStatementList(coinReq);
        Map<String, Object> map = new HashMap<>(0);
        if (baseResult.isSuccess()) {
            map = (Map<String, Object>) baseResult.getData();
        }
        List<StatisticsFeeDownloadResp> respList = new ArrayList<>();
        if (map.get("records") != null) {
            respList = JSONObject.parseArray(JSON.toJSONString(map.get("records")), StatisticsFeeDownloadResp.class);
        }
        //导出的文件名
        String fileName = FEE_STATISTICS_FILENAME + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ExcelUtil.exportExcel(response, respList, fileName, FEE_STATISTICS_FILENAME);
    }

    /**
     * 币种手续费列表导出
     * @param response
     * @param req
     * @return void
     * @auther LiuLP
     * @date 2018/12/10 0010
     */
    @Override
    public void coinChargeListPageDownload(HttpServletResponse response, StatisticsCoinChargeListPageReq req) {
        //全部下载
        req.setSize(100000);

        //判断是否根据用户名称模糊查询
        String userName = req.getUserName();
        List<Map<String, Object>> userList =  null;
        List<String> threeUserIdList = null;
        if(StringUtils.isNotBlank(userName)){
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            feignMemberListReq.setName(userName);
            BaseResult userlistResult = userFeign.list(feignMemberListReq);
            if(!userlistResult.isSuccess() || userlistResult.getData() == null){
                throw LxtxBizException.newException("获取用户id失败");
            }
            Map<String, Object> dataMap = (Map<String, Object>) userlistResult.getData();
            userList = (List<Map<String, Object>>) dataMap.get("list");
            if(CollectionUtils.isEmpty(userList)){
                throw LxtxBizException.newException("用户id为空");
            }
            threeUserIdList = new ArrayList<>();
            for(Map<String, Object> umap : userList){
                threeUserIdList.add((String) umap.get("account"));
            }
        }
        FeignStatisticsCoinChargeListPageReq listPageReq = new FeignStatisticsCoinChargeListPageReq();
        BeanUtils.copyProperties(req, listPageReq);
        listPageReq.setThreeUserIdList(threeUserIdList);
        BaseResult baseResult = statisticsFeign.feeStatementListPage(listPageReq);
        if(!baseResult.isSuccess() || baseResult.getData() == null){
            throw LxtxBizException.newException("获取手续费失败");
        }
        //将用户ID转为用户名称
        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        List<Map<String, Object>> records = (List<Map<String, Object>>) dataMap.get("records");
        if(CollectionUtils.isEmpty(records)){
            throw LxtxBizException.newException("手续费列表为空");
        }
        // 根据ID查用户信息
        if(CollectionUtils.isEmpty(userList)){
            List<String> userIdList = new ArrayList<>();
            for(Map<String, Object> map : records){
                userIdList.add((String)map.get("threeUserId"));
            }
            FeignQueryUserListReq feignQueryUserListReq = new FeignQueryUserListReq();
            feignQueryUserListReq.setIds(userIdList);
            BaseResult userResult = userFeign.queryList(feignQueryUserListReq);
            if(!baseResult.isSuccess() || baseResult.getData() == null){
                throw LxtxBizException.newException("获取用户信息失败");
            }
            Map<String, Object> userMap = (Map<String, Object>) userResult.getData();
            userList = (List<Map<String, Object>>) userMap.get("list");
            if(CollectionUtils.isEmpty(userList)){
                throw LxtxBizException.newException("用户信息为空");
            }
        }
        List<ChargeListPageInfoResp> list = new ArrayList<>();
        //设置用户名称
        for(Map<String, Object> map : records){
            for(Map<String, Object> umap : userList){
                String userId = (String) umap.get("account");
                if(userId.equals(map.get("threeUserId"))){
                    map.put("userName", umap.get("name"));
                }
            }
            ChargeListPageInfoResp resp = JSON.parseObject(JSON.toJSONString(map), ChargeListPageInfoResp.class);
            resp.setAllFee(resp.getInnerFee().add(resp.getThreeFee()));
            if(resp.getStatus() == 4){
                resp.setFeeStatus("成功");
            }else{
                resp.setFeeStatus("处理中");
            }
            resp.setUpdateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resp.getUpdateTime()));
            list.add(resp);
        }

        //导出的文件名
        String fileName = FEE_LIST_FILENAME + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ExcelUtil.exportExcel(response, list, fileName, FEE_LIST_FILENAME);
    }

    @Override
    public BaseResult syncOldYebOutWithdrawFee() {
        return statisticsFeign.syncOldYebOutWithdrawFee();
    }
}
