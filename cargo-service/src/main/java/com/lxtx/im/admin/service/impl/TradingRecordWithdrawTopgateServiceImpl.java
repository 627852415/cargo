package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.TradingRecordWithdrawTopgateFeign;
import com.lxtx.im.admin.feign.feign.TransferFriendsFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.TopgateWithdrawPayWayService;
import com.lxtx.im.admin.service.TradingRecordWithdrawTopgateService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.AmountValue;
import com.lxtx.im.admin.service.request.TradingRecordWithdrawTopgateDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordWithdrawTopgateReq;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hechizhi
 * @since 2019-11-23
 */
@Slf4j
@Service
public class TradingRecordWithdrawTopgateServiceImpl implements TradingRecordWithdrawTopgateService {

    private static final String APPLY_LIST_FILENAME = "Topgate提现订单列表";

    @Autowired
    private TradingRecordWithdrawTopgateFeign tradingRecordWithdrawTopgateFeign;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private WalletUserFeign walletUserFeign;

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);

    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private TransferFriendsFeign transferFriendsFeign;

    @Autowired
    private DictService dictService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopgateWithdrawPayWayService topgateWithdrawPayWayService;

    @Autowired
    private ApplicationContext context;

    private static Integer EX_PAGE_SIZE = 50000;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;
    private static final String ADMIN_TOPGATE_WITHDRAW_STATISTICS="ADMIN_TOPGATE_WITHDRAW_STATISTICS";


    @Override
    public BaseResult listPage(TradingRecordWithdrawTopgateReq req, HttpSession session, Locale locale) {

        FeignTradingRecordWithdrawTopgateReq feignTradingRecordWithdrawTopgateReq = new FeignTradingRecordWithdrawTopgateReq();
        BeanUtils.copyProperties(req, feignTradingRecordWithdrawTopgateReq);
        feignTradingRecordWithdrawTopgateReq.setKHShowAccount(userService.isShowAccount());

        BaseResult baseResult = tradingRecordWithdrawTopgateFeign.listPage(feignTradingRecordWithdrawTopgateReq);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<TradingRecordWithdrawTopgateResp> respList = JSONObject.parseArray(jsonObject.getString("records"),
                    TradingRecordWithdrawTopgateResp.class);
            if (!CollectionUtils.isEmpty(respList)) {
                // 设置数据内部属性
                resetRespList(respList,locale);
            }
            jsonObject.put("records", respList);
            baseResult.setData(jsonObject);
        }

        return baseResult;
    }

    @Override
    public BaseResult downloadLock(TradingRecordWithdrawTopgateReq req, HttpSession session, Locale locale) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_FAST_GET_LOCK.concat(userId);
        String requestId = session.getId();
        boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!getRechargeDownloadLock) {
            String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
            if (requestId.equals(lockId)) {
                log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
            }
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }
        try {
            //判断搜索结果total值
            req.setSize(1);
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(listPage(req, session, locale).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            Integer maxPageSizeLong = maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize);
            if (total > maxPageSizeLong) {
                if (getRechargeDownloadLock)
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if (getRechargeDownloadLock)
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }
        return BaseResult.success(true);
    }

    @Override
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordWithdrawTopgateReq req, Locale locale) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_FAST_GET_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            List<TradingRecordWithdrawTopgateResp> respList = null;
            FeignTradingRecordWithdrawTopgateReq feignTradingRecordWithdrawTopgateReq = new FeignTradingRecordWithdrawTopgateReq();
            BeanUtils.copyProperties(req, feignTradingRecordWithdrawTopgateReq);

            feignTradingRecordWithdrawTopgateReq.setKHShowAccount(userService.isShowAccount());
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignTradingRecordWithdrawTopgateReq.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));
            BaseResult baseResult = tradingRecordWithdrawTopgateFeign.listPage(feignTradingRecordWithdrawTopgateReq);
            if (baseResult.isSuccess() && baseResult.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"),
                        TradingRecordWithdrawTopgateResp.class);
                if (!CollectionUtils.isEmpty(respList)) {
                    // 设置数据内部属性
                    resetRespList(respList,locale);
                }
            }
            ExcelUtil.exportExcel(response, respList
                    , getPlatformWithdrawApplyDownloadFileName(req.getCreateTimeStart(), req.getCreateTimeEnd())
                    , APPLY_LIST_FILENAME);
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);

        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     * @Description 命名文件名
     */
    private String getPlatformWithdrawApplyDownloadFileName(Date startTime, Date endTime) {
        String fileName = APPLY_LIST_FILENAME;
        if (startTime != null) {
            if (endTime != null) {
                fileName = fileName + DATEFORMAT.format(startTime) + "至" + DATEFORMAT.format(endTime);
            } else {
                fileName = fileName + DATEFORMAT.format(startTime) + "至" + DATEFORMAT.format(new Date());
            }
        } else {
            if (endTime != null) {
                fileName = fileName + "至" + DATEFORMAT.format(endTime);
            } else {
                fileName = fileName + DATEFORMAT.format(new Date());
            }
        }
        return fileName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String detail(TradingRecordWithdrawTopgateDetailReq req, Model model, Locale locale) {

        FeignTradingRecordWithdrawTopgateDetailReq feignTradingRecordWithdrawTopgateDetailReq = new FeignTradingRecordWithdrawTopgateDetailReq();
        feignTradingRecordWithdrawTopgateDetailReq.setId(req.getId());
        BaseResult withdrawTopgateResult = tradingRecordWithdrawTopgateFeign.detail(feignTradingRecordWithdrawTopgateDetailReq);

        Map<String, Object> dataMap = (Map<String, Object>) withdrawTopgateResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        TradingRecordWithdrawTopgateResp detailResp = JSONObject.parseObject(jsonResult, TradingRecordWithdrawTopgateResp.class);
        HashSet<String> userIds = new HashSet<>();
        // 获取钱包ID去重集合
        userIds.add(detailResp.getUserId());
        // 根据钱包ID集合获取用户昵称集合
        Map<String, String> userNameMap = getUserNameMap(userIds);
        setStatusValue(detailResp, locale);
        detailResp.setUserName(userNameMap.get(detailResp.getUserId()));
        Map<Integer, String> payWayMap = getPayWayMap(locale);
        String message = context.getMessage(payWayMap.get(detailResp.getPayWay()), null, locale);
        detailResp.setPayWayName(message);
        detailResp.setImMerchantOut(detailResp.getImMerchantOut() == null?BigDecimal.ZERO:detailResp.getImMerchantOut());
        detailResp.setTopgateMerchantOut(detailResp.getTopgateMerchantOut() == null?BigDecimal.ZERO:detailResp.getTopgateMerchantOut());
        model.addAttribute("detail", detailResp);
        return "wallet/tradingRecord-withdrawTopgate-detail";
    }

    @Override
    public WithdrawTopgateOrderStatisticsResp getAllStatistics(boolean refresh) {
        if(refresh){
            redisCacheUtils.delete(ADMIN_TOPGATE_WITHDRAW_STATISTICS);
        }
        Object object = redisCacheUtils.getCacheObject(ADMIN_TOPGATE_WITHDRAW_STATISTICS);
        if(object == null){
            FeignStatisticsOrderReq req = new FeignStatisticsOrderReq();
            req.setIsAll(true);
            WithdrawTopgateOrderStatisticsResp resp = getStatistics(req);
            redisCacheUtils.setCacheObject(ADMIN_TOPGATE_WITHDRAW_STATISTICS,resp,60*20);
            return resp;
        }else{
            if(object instanceof WithdrawTopgateOrderStatisticsResp){
                return (WithdrawTopgateOrderStatisticsResp) object;
            }else{
                redisCacheUtils.delete(ADMIN_TOPGATE_WITHDRAW_STATISTICS);
                return new WithdrawTopgateOrderStatisticsResp();
            }
        }
    }

    @Override
    public WithdrawTopgateOrderStatisticsResp getStatistics(FeignStatisticsOrderReq req) {
        BaseResult result = tradingRecordWithdrawTopgateFeign.getStatisticsOrder(req);
        if (!result.isSuccessAndDataNotNull()) {
            return new WithdrawTopgateOrderStatisticsResp();
        }
        JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(result.getData()));
        List<TradingRecordWithdrawTopgateResp> list = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), TradingRecordWithdrawTopgateResp.class);

        Integer successOrders = 0;
        ArrayList<AmountValue> totalAmount = new ArrayList<>();
        ArrayList<AmountValue> totalActualAmount = new ArrayList<>();
        ArrayList<AmountValue> totalFee = new ArrayList<>();
        ArrayList<AmountValue> totalInnerFee = new ArrayList<>();
        ArrayList<AmountValue> totalThirdFee = new ArrayList<>();
        ArrayList<AmountValue> totalImMerchantOut = new ArrayList<>();
        ArrayList<AmountValue> totalTopgateMerchantOut = new ArrayList<>();

        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(list)) {
            HashMap<String, BigDecimal> map = new HashMap<>();
            successOrders = list.size();
            for (TradingRecordWithdrawTopgateResp detail : list) {
                String coinName = detail.getCoinName();
                setMapValue(map, detail.getAmount(), coinName,"totalAmount_");
                setMapValue(map, detail.getActualAmount(), coinName,"totalActualAmount_");
                setMapValue(map, detail.getFee(), coinName,"totalFee_");
                setMapValue(map, detail.getInnerFee(), coinName,"totalInnerFee_");
                setMapValue(map, detail.getThirdFee(), coinName,"totalThirdFee_");
                setMapValue(map, detail.getImMerchantOut(), coinName,"totalImMerchantOut_");
                setMapValue(map, detail.getTopgateMerchantOut(), coinName,"totalTopgateMerchantOut_");
            }

            Set<String> set = map.keySet();
            for (String key : set) {
                if(key.contains("_")){
                    AmountValue amountValue = new AmountValue();
                    String[] split = key.split("_");
                    amountValue.setCoinName(split[1]);
                    amountValue.setValue(map.get(key));

                    if("totalAmount".equals(split[0])){
                        totalAmount.add(amountValue);
                    }
                    if("totalActualAmount".equals(split[0])){
                        totalActualAmount.add(amountValue);
                    }
                    if("totalFee".equals(split[0])){
                        totalFee.add(amountValue);
                    }
                    if("totalInnerFee".equals(split[0])){
                        totalInnerFee.add(amountValue);
                    }
                    if("totalThirdFee".equals(split[0])){
                        totalThirdFee.add(amountValue);
                    }
                    if("totalImMerchantOut".equals(split[0])){
                        totalImMerchantOut.add(amountValue);
                    }
                    if("totalTopgateMerchantOut".equals(split[0])){
                        totalTopgateMerchantOut.add(amountValue);
                    }
                }
            }
        }
        WithdrawTopgateOrderStatisticsResp resp = new WithdrawTopgateOrderStatisticsResp();
        resp.setSuccessOrders(successOrders);
        resp.setTotalActualAmount(totalActualAmount);
        resp.setTotalAmount(totalAmount);
        resp.setTotalFee(totalFee);
        resp.setTotalImMerchantOut(totalImMerchantOut);
        resp.setTotalInnerFee(totalInnerFee);
        resp.setTotalThirdFee(totalThirdFee);
        resp.setTotalTopgateMerchantOut(totalTopgateMerchantOut);
        return resp;
    }

    /**
     * 在map中按（属性+币种）累加数值
     * @param map 币种名：数值
     * @param value 新值
     * @param coinName 币种名
     * @param propertyName 属性名
     */
    private void setMapValue(HashMap<String, BigDecimal> map, BigDecimal value, String coinName,String propertyName) {
        BigDecimal calValue;
        if(value != null){
            //map中的旧值
            BigDecimal amount = map.get(propertyName + coinName);
            if(amount == null){
                calValue = BigDecimal.ZERO.add(value);
            }else{
                calValue = amount.add(value);
            }
            //更新值
            map.put(propertyName + coinName,calValue);
        }
    }

    /**
     * @param userIds
     * @return
     * @Description 获取用户名集合
     */
    private Map<String, String> getUserNameMap(HashSet<String> userIds) {
        Map<String, String> userNameMap = new HashMap<>();
        //根据钱包ID兑换accountId
        HashSet<String> accountIds = new HashSet<>();
        Map<String, String> accountIdsMap = new HashMap<>();
        FeignTransferUserReq req = new FeignTransferUserReq();
        req.setUserIdList(new ArrayList<>(userIds));
        BaseResult coreResult = transferFriendsFeign.listTransferUserNames(req);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
            List<WalletUserResp> walletUserResps = JSONObject.parseArray(jsonObject.getString("list"), WalletUserResp.class);
            if (!CollectionUtils.isEmpty(walletUserResps)) {
                walletUserResps.stream().forEach(record -> {
                    accountIdsMap.put(record.getId(), record.getPlatformUserId());
                    accountIds.add(record.getPlatformUserId());
                });
                // 查询符合条件的用户信息并转Map
                Map<String, String> userIdsMap = new HashMap<>();
                FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
                queryUserListReq.setIds(new ArrayList<>(accountIds));
                coreResult = userFeign.queryList(queryUserListReq);
                if (coreResult.isSuccess() && coreResult.getData() != null) {
                    UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(coreResult.getData()),
                            UserListResp.class);
                    List<UserDetailResp> userDetailResps = userListResp.getList();
                    if (!CollectionUtils.isEmpty(userDetailResps)) {
                        userDetailResps.stream().forEach(record -> {
                            userIdsMap.put(record.getAccount(), record.getName());
                        });
                        userIds.stream().forEach(userId -> {
                            userNameMap.put(userId, userIdsMap.get(accountIdsMap.get(userId)));
                        });
                    }
                }
            }
        }
        return userNameMap;
    }

    private void setStatusValue(TradingRecordWithdrawTopgateResp resp, Locale locale) {
        if (resp.getStatus() == null) {
            resp.setStatusValue("");
        } else if (resp.getStatus() == 1) {
            String message = context.getMessage("text.processing", null, locale);
            resp.setStatusValue(message);
        } else if (resp.getStatus() == 2) {
            String message = context.getMessage("text.success", null, locale);
            resp.setStatusValue(message);
        } else if (resp.getStatus() == 3) {
            String message = context.getMessage("text.failure", null, locale);
            resp.setStatusValue(message);
        }
    }

    /**
     * @return
     * @Description 获取支付方法集合
     */
    private Map<Integer, String> getPayWayMap(Locale locale) {
        BaseResult baseResult = topgateWithdrawPayWayService.listAll(locale);
        if (!baseResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("查询支付方法列表失败或记录不存在");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(baseResult.getData()));
        String list = jsonObject.getString("list");
        List<TopgateRechargePayWayResp> resps = JSONObject.parseArray(list, TopgateRechargePayWayResp.class);
        Map<Integer, String> map = resps.stream().collect(Collectors.toMap(TopgateRechargePayWayResp::getPayWay, TopgateRechargePayWayResp::getPayWayDescKey, (key1, key2) -> key2));
        return map;
    }

    /**
     * @param respList
     * @Description 设置数据内部属性
     */
    private void resetRespList(List<TradingRecordWithdrawTopgateResp> respList, Locale locale) {
        HashSet<String> userIds = new HashSet<>();
        // 获取钱包ID去重集合
        respList.stream().forEach(resp -> {
            if (!StringUtils.isEmpty(resp.getUserId())) {
                userIds.add(resp.getUserId());
            }
        });
        // 根据钱包ID集合获取用户昵称集合
        Map<String, String> userNameMap = getUserNameMap(userIds);
        Map<Integer, String> payWayMap = getPayWayMap(locale);
        respList.stream().forEach(resp -> {
            setStatusValue(resp, locale);
            resp.setUserName(userNameMap.get(resp.getUserId()));
            String message = context.getMessage(payWayMap.get(resp.getPayWay()), null, locale);
            resp.setPayWayName(message);
            resp.setImMerchantOut(resp.getImMerchantOut() == null?BigDecimal.ZERO:resp.getImMerchantOut());
            resp.setTopgateMerchantOut(resp.getTopgateMerchantOut() == null?BigDecimal.ZERO:resp.getTopgateMerchantOut());
        });
    }
}
