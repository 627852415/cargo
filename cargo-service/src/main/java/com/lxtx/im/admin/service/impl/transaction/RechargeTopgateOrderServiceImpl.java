package com.lxtx.im.admin.service.impl.transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.RechargeTopgateFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.RechargeTopgateOrderService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-12-17 15:42
 * @Description
 */
@Service
@Slf4j
public class RechargeTopgateOrderServiceImpl implements RechargeTopgateOrderService {
    @Resource
    private UserFeign userFeign;
    @Resource
    private RechargeTopgateFeign zbpayFeign;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheUtils redisCacheUtils;
    private static final String APPLY_LIST_FILENAME = "Topgate充值订单列表";
    private static final String ADMIN_TOPGATE_RECHARGE_STATISTICS="ADMIN_TOPGATE_RECHARGE_STATISTICS";

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;
    // 导出数据要加数据数量限制，数量值字典全局配置（暂定50000）条
    // 导出数据条限制
    private static Integer EX_PAGE_SIZE = 50000;

    @Override
    public BaseResult listPage(RechargeTopgateOrderPageReq req) {

        // 查询符合条件的用户信息
        FeignRechargeTopgateOrderPageReq feignReq = new FeignRechargeTopgateOrderPageReq();
        BeanUtils.copyProperties(req, feignReq);
        if(userService.isShowAccount()){
            BaseResult baseResult = userFeign.selectKhUser();
            if(baseResult.isSuccess()&&null!=baseResult.getData()){
                List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                feignReq.setKhUserAccountList(userAccountList);
            }
        }
        BaseResult baseResult = zbpayFeign.listPage(feignReq);
        if (!baseResult.isSuccessAndDataNotNull()) {
            return baseResult;
        }
        // 封装返回数据结果
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
        List<RechargeTopgateOrderResp> zbpayOrderRespList = JSONObject.parseArray(jsonObject.getString("records"), RechargeTopgateOrderResp.class);
        zbpayOrderRespList(zbpayOrderRespList);
        jsonObject.put("records", zbpayOrderRespList);
        baseResult.setData(jsonObject);

        return baseResult;
    }

    /**
     * 封装资金入账用户昵称信息
     *
     * @param orderRespList
     */
    private void zbpayOrderRespList(List<RechargeTopgateOrderResp> orderRespList) {
        if (CollectionUtils.isEmpty(orderRespList)) {
            return;
        }
        List<String> userIds = orderRespList.stream().map(RechargeTopgateOrderResp::getPlatformUserId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIds)) {
            throw LxtxBizException.newException("资金转入交易记录用户账号信息有误！");
        }

        BaseResult userListResult = userFeign.selectBatchIds(new FeignQueryUserListByIdReq(userIds));
        Map<String, String> accountMap = null;
        if (userListResult.isSuccessAndDataNotNull()) {
            UserList userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListResult.getData()), UserList.class);
            accountMap = userListResp.getList().stream().collect(Collectors.toMap(UserResp::getAccount, UserResp::getName));
        }
        Map<String, String> finalAccountMap = accountMap;
        for (RechargeTopgateOrderResp resp : orderRespList) {
            resp.setUserName(finalAccountMap.get(resp.getPlatformUserId()));
            resp.setImMerchantIn(resp.getImMerchantIn() == null?BigDecimal.ZERO:resp.getImMerchantIn());
            resp.setTopgateMerchantIn(resp.getTopgateMerchantIn() == null?BigDecimal.ZERO:resp.getTopgateMerchantIn());
        }
    }

    @Override
    public void downloadList(HttpServletResponse response, RechargeTopgateOrderDownloadReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_TOPGATE_RECHARGE_LOCK.concat(userId);
        String requestId = session.getId();
        // 查询符合条件的用户信息
        List<UserDetailResp> userDetailRespList = null;
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            FeignRechargeTopgateOrderDownloadReq feignReq = new FeignRechargeTopgateOrderDownloadReq();
            BeanUtils.copyProperties(req, feignReq);
            if(userService.isShowAccount()){
                BaseResult baseResult = userFeign.selectKhUser();
                if(baseResult.isSuccess()&&null!=baseResult.getData()){
                    List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                    feignReq.setKhUserAccountList(userAccountList);
                }
            }

            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            if (StringUtils.isNotBlank(maxPageSize)) {
                Integer maxSize = Integer.parseInt(maxPageSize) + 1;
                feignReq.setMaxPageSize(maxSize.toString());
            }
            BaseResult baseResult = zbpayFeign.downloadList(feignReq);
            if (!baseResult.isSuccessAndDataNotNull()) {
                return;
            }

            // 封装查询结果用户昵称信息
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<RechargeTopgateOrderResp> orderRespList = JSONObject.parseArray(jsonObject.getString(BaseResult.LIST), RechargeTopgateOrderResp.class);

            // 判断是否大于字典中配置的最大值
            if (orderRespList.size() > Integer.parseInt(maxPageSize)) {
                // 设置浏览器字符集编码.
                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE);
                // 设置response的缓冲区的编码.
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                try (PrintWriter writer = response.getWriter();) {
                    writer.print(String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSize));
                    writer.flush();
                    return;
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }

            zbpayOrderRespList(orderRespList);

            // 导出文件
            ExcelUtil.exportExcel(response, orderRespList, getExcelOutFileName(req.getCreateTime(), req.getUpdateTime()), APPLY_LIST_FILENAME);
            return;
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public String detail(RechargeTopgateOrderDetailReq req, Model model) {
        FeignRechargeTopgateOrderDetailReq feignReq = new FeignRechargeTopgateOrderDetailReq();
        feignReq.setId(req.getId());
        BaseResult rechargeDetailResult = zbpayFeign.detail(feignReq);
        if (!rechargeDetailResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("查询该记录详情失败或记录不存在");
        }
        RechargeTopgateOrderResp resp = JSONObject.parseObject(JSON.toJSONString(rechargeDetailResult.getData()), RechargeTopgateOrderResp.class);

        // 封装返回资金入账用户昵称信息
        zbpayOrderRespList(Arrays.asList(resp));
        model.addAttribute("detail", resp);
        return "transaction/zbpay-order-detail";
    }


    /**
     * 命名文件名
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private String getExcelOutFileName(Date startTime, Date endTime) {
        String fileName = APPLY_LIST_FILENAME;
        if (startTime != null) {
            if (endTime != null) {
                fileName = fileName + DateUtils.getDateFormat(startTime, DateUtils.DATE_FORMAT_YYYY_MM_DD) + "至" + DateUtils.getDateFormat(endTime, DateUtils.DATE_FORMAT_YYYY_MM_DD);
            } else {
                fileName = fileName + DateUtils.getDateFormat(startTime, DateUtils.DATE_FORMAT_YYYY_MM_DD) + "至" + DateUtils.getDateFormat(new Date(), DateUtils.DATE_FORMAT_YYYY_MM_DD);
            }
        } else {
            if (endTime != null) {
                fileName = fileName + "至" + DateUtils.getDateFormat(endTime, DateUtils.DATE_FORMAT_YYYY_MM_DD);
            } else {
                fileName = fileName + DateUtils.getDateFormat(new Date(), DateUtils.DATE_FORMAT_YYYY_MM_DD);
            }
        }
        return fileName;
    }

    @Override
    public BaseResult downloadLock(RechargeTopgateOrderPageReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_TOPGATE_RECHARGE_LOCK.concat(userId);
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
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(listPage(req).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            Integer maxPageSizeLong = maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize);
            if (total > maxPageSizeLong) {
                if (getRechargeDownloadLock) {
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                }
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if (getRechargeDownloadLock) {
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            }
            log.error("导出异常:{}", e);
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }

        return BaseResult.success(true);
    }

    @Override
    public RechargeTopgateOrderStatisticsResp getAllStatistics(boolean refresh) {
        if(refresh){
            redisCacheUtils.delete(ADMIN_TOPGATE_RECHARGE_STATISTICS);
        }
        Object object = redisCacheUtils.getCacheObject(ADMIN_TOPGATE_RECHARGE_STATISTICS);
        if(object == null){
            FeignStatisticsOrderReq req = new FeignStatisticsOrderReq();
            req.setIsAll(true);
            RechargeTopgateOrderStatisticsResp resp = getStatistics(req);
            redisCacheUtils.setCacheObject(ADMIN_TOPGATE_RECHARGE_STATISTICS,resp,60*20);
            return resp;
        }else{
            if(object instanceof RechargeTopgateOrderStatisticsResp){
                return (RechargeTopgateOrderStatisticsResp) object;
            }else{
                redisCacheUtils.delete(ADMIN_TOPGATE_RECHARGE_STATISTICS);
                return new RechargeTopgateOrderStatisticsResp();
            }
        }

    }

 /*   @Override
    public RechargeTopgateOrderStatisticsResp getStatisticsByDate(StatisticsOrderReq req) {
        FeignStatisticsOrderReq feignStatisticsOrderReq = new FeignStatisticsOrderReq();
        feignStatisticsOrderReq.setIsAll(false);
        feignStatisticsOrderReq.setStartDate(req.getStartDate());
        feignStatisticsOrderReq.setEndDate(req.getEndDate());
        return getStatistics(feignStatisticsOrderReq);
    }*/

    @Override
    public RechargeTopgateOrderStatisticsResp getStatistics(FeignStatisticsOrderReq req) {
        BaseResult result = zbpayFeign.getStatisticsOrder(req);
        if (!result.isSuccessAndDataNotNull()) {
            return new RechargeTopgateOrderStatisticsResp();
        }
        JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(result.getData()));
        List<RechargeTopgateOrderResp> list = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), RechargeTopgateOrderResp.class);

        Integer successOrders = 0;
        ArrayList<AmountValue> totalAmount = new ArrayList<>();
        ArrayList<AmountValue> totalActualAmount = new ArrayList<>();
        ArrayList<AmountValue> totalFee = new ArrayList<>();
        ArrayList<AmountValue> totalInnerFee = new ArrayList<>();
        ArrayList<AmountValue> totalThirdFee = new ArrayList<>();
        ArrayList<AmountValue> totalImMerchantIn = new ArrayList<>();
        ArrayList<AmountValue> totalTopgateMerchantIn = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {
            HashMap<String, BigDecimal> map = new HashMap<>();
            successOrders = list.size();
            for (RechargeTopgateOrderResp detail : list) {
                String coinName = detail.getCoinName();
                setMapValue(map, detail.getAmount(), coinName,"totalAmount_");
                setMapValue(map, detail.getActualAmount(), coinName,"totalActualAmount_");
                setMapValue(map, detail.getFee(), coinName,"totalFee_");
                setMapValue(map, detail.getInnerFee(), coinName,"totalInnerFee_");
                setMapValue(map, detail.getThirdFee(), coinName,"totalThirdFee_");
                setMapValue(map, detail.getImMerchantIn(), coinName,"totalImMerchantIn_");
                setMapValue(map, detail.getTopgateMerchantIn(), coinName,"totalTopgateMerchantIn_");
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
                    if("totalImMerchantIn".equals(split[0])){
                        totalImMerchantIn.add(amountValue);
                    }
                    if("totalTopgateMerchantIn".equals(split[0])){
                        totalTopgateMerchantIn.add(amountValue);
                    }
                }
            }
        }
        RechargeTopgateOrderStatisticsResp resp = new RechargeTopgateOrderStatisticsResp();
        resp.setSuccessOrders(successOrders);
        resp.setTotalActualAmount(totalActualAmount);
        resp.setTotalAmount(totalAmount);
        resp.setTotalFee(totalFee);
        resp.setTotalImMerchantIn(totalImMerchantIn);
        resp.setTotalInnerFee(totalInnerFee);
        resp.setTotalThirdFee(totalThirdFee);
        resp.setTotalTopgateMerchantIn(totalTopgateMerchantIn);
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
}
