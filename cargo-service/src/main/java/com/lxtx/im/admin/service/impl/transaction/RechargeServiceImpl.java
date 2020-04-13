package com.lxtx.im.admin.service.impl.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.TransactionFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.FeignMemberListReq;
import com.lxtx.im.admin.feign.request.FeignQueryUserListByIdReq;
import com.lxtx.im.admin.feign.request.FeignTransactionDetailReq;
import com.lxtx.im.admin.feign.request.FeignTransactionRechargePageReq;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.TransactionDetailReq;
import com.lxtx.im.admin.service.request.TransactionRechargeExcelOutputReq;
import com.lxtx.im.admin.service.request.TransactionRechargePageReq;
import com.lxtx.im.admin.service.response.TransactionRechargeResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserList;
import com.lxtx.im.admin.service.response.UserResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.transaction.RechargeService;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CaiRH
 * @since 2019-11-22 11:50
 **/
@Slf4j
@Service
public class RechargeServiceImpl implements RechargeService {

    private static final String PAGE_RESULT_KEY = "records";
    private static final String APPLY_LIST_FILENAME = "交易记录-资金入账列表";
    // 导出数据要加数据数量限制，数量值字典全局配置（暂定50000）条
    // 导出数据条限制
    private static Integer EX_PAGE_SIZE = 50000;
    /**
     * 锁时间 1分钟
     */
    private static final Integer LOCK_TIME = 60 * 1000;

    @Resource
    private UserFeign userFeign;
    @Resource
    private TransactionFeign transactionFeign;
    @Autowired
    private DictService dictService;
    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(TransactionRechargePageReq req, HttpSession session) {
        // 查询符合条件的用户信息
        List<UserDetailResp> userDetailRespList = null;
        if (StringUtils.isNotBlank(req.getUsername())) {
            FeignMemberListReq queryUserListReq = new FeignMemberListReq();
            queryUserListReq.setName(req.getUsername());
            BaseResult coreResult = userFeign.list(queryUserListReq);
            if (!coreResult.isSuccessAndDataNotNull()) {
                return BaseResult.success(new Page<>());
            }
            JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
            userDetailRespList = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), UserDetailResp.class);
            if (CollectionUtils.isEmpty(userDetailRespList)) {
                return BaseResult.success(new Page<>());
            }
        }

        final List<String> accountIds = new ArrayList<>();
        final Map<String, String> accountMap = new HashMap<>(0);
        if (CollectionUtils.isNotEmpty(userDetailRespList)) {
            userDetailRespList.forEach(userDetailResp -> {
                accountIds.add(userDetailResp.getAccount());
                accountMap.put(userDetailResp.getAccount(), userDetailResp.getName());
            });
        }

        // 设置查询wallet符合条件交易记录参数
        FeignTransactionRechargePageReq feignReq = new FeignTransactionRechargePageReq();
        BeanUtils.copyProperties(req, feignReq);
        feignReq.setAccountIds(accountIds);
        if (feignReq.getUpdateTime() != null) {
            feignReq.setUpdateTime(DateUtils.formatDate(DateUtils.getEndDateFormat(feignReq.getUpdateTime()), DateUtils.DATE_FORMAT_DEFAULT));
        }

        if(userService.isShowAccount()){
            BaseResult baseResult = userFeign.selectKhUser();
            if(baseResult.isSuccess()&&null!=baseResult.getData()){
                List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                feignReq.setKhUserAccountList(userAccountList);
            }
        }


        // 查询wallet符合条件交易记录
        BaseResult rechargeListPage = transactionFeign.rechargeListPage(feignReq);
        if (!rechargeListPage.isSuccessAndDataNotNull()) {
            return BaseResult.success(new Page<>());
        }

        // 封装返回数据结果
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(rechargeListPage.getData()));
        List<TransactionRechargeResp> rechargeRespList = JSONObject.parseArray(jsonObject.getString(PAGE_RESULT_KEY), TransactionRechargeResp.class);
        packageRechargeRespList(rechargeRespList, accountMap);
        jsonObject.put(PAGE_RESULT_KEY, rechargeRespList);
        rechargeListPage.setData(jsonObject);
        return rechargeListPage;
    }

    /**
     * 封装资金入账用户昵称信息
     *
     * @param rechargeRespList
     * @param accountMap
     */
    private void packageRechargeRespList(List<TransactionRechargeResp> rechargeRespList, Map<String, String> accountMap) {
        if (CollectionUtils.isEmpty(rechargeRespList)) {
            return;
        }
        if (accountMap.isEmpty()) {
            List<String> userIds = rechargeRespList.stream().map(TransactionRechargeResp::getPlatformUserId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(userIds)) {
                throw LxtxBizException.newException("资金转入交易记录用户账号信息有误！");
            }

            FeignQueryUserListByIdReq req = new FeignQueryUserListByIdReq();
            req.setIds(userIds);
            BaseResult userListResult = userFeign.selectBatchIds(req);
            if (userListResult.isSuccessAndDataNotNull()) {
                UserList userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListResult.getData()), UserList.class);
                List<UserResp> respList = userListResp.getList();
                if (CollectionUtils.isNotEmpty(respList)) {
                    accountMap = respList.stream().collect(Collectors.toMap(UserResp::getAccount, UserResp::getName));
                }
            }
        }
        Map<String, String> finalAccountMap = accountMap;
        rechargeRespList.forEach(resp -> resp.setUsername(finalAccountMap.get(resp.getPlatformUserId())));
    }

    @Override
    public void downloadList(HttpServletResponse response, HttpSession session, TransactionRechargeExcelOutputReq req) {
    	String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_RECHARGE_LOCK.concat(userId);
		String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            // 查询符合条件的用户信息
            List<UserDetailResp> userDetailRespList = null;
            if (StringUtils.isNotBlank(req.getUsername())) {
                FeignMemberListReq queryUserListReq = new FeignMemberListReq();
                queryUserListReq.setName(req.getUsername());
                BaseResult coreResult = userFeign.list(queryUserListReq);
                if (!coreResult.isSuccessAndDataNotNull()) {
                    return;
                }
                JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
                userDetailRespList = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), UserDetailResp.class);
                if (CollectionUtils.isEmpty(userDetailRespList)) {
                    return;
                }
            }

            final List<String> accountIds = new ArrayList<>();
            final Map<String, String> accountMap = new HashMap<>(0);
            if (CollectionUtils.isNotEmpty(userDetailRespList)) {
                userDetailRespList.forEach(userDetailResp -> {
                    accountIds.add(userDetailResp.getAccount());
                    accountMap.put(userDetailResp.getAccount(), userDetailResp.getName());
                });
            }

            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);

            // 设置查询wallet符合条件交易记录参数
            FeignTransactionRechargePageReq feignReq = new FeignTransactionRechargePageReq();
            BeanUtils.copyProperties(req, feignReq);
            feignReq.setSize(StringUtils.isNotBlank(maxPageSize) ? Integer.valueOf(maxPageSize) + 1 : null);
            feignReq.setAccountIds(accountIds);
            if (feignReq.getUpdateTime() != null) {
                feignReq.setUpdateTime(DateUtils.formatDate(DateUtils.getEndDateFormat(feignReq.getUpdateTime()), DateUtils.DATE_FORMAT_DEFAULT));
            }

            if(userService.isShowAccount()){
                BaseResult baseResult = userFeign.selectKhUser();
                if(baseResult.isSuccess()&&null!=baseResult.getData()){
                    List<String> userAccountList = (List<String>) ((Map<String, Object>) baseResult.getData()).get("list");
                    feignReq.setKhUserAccountList(userAccountList);
                }
            }

            // 查询wallet符合条件交易记录
            BaseResult rechargeListPage = transactionFeign.rechargeListPage(feignReq);
            if (!rechargeListPage.isSuccessAndDataNotNull()) {
                return;
            }

            // 封装查询结果用户昵称信息
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(rechargeListPage.getData()));
            List<TransactionRechargeResp> rechargeRespList = JSONObject.parseArray(jsonObject.getString(PAGE_RESULT_KEY), TransactionRechargeResp.class);

            // 判断是否大于字典中配置的最大值
            if(rechargeRespList.size() > Integer.parseInt(maxPageSize)){
                // 设置浏览器字符集编码.
                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE);
                // 设置response的缓冲区的编码.
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                try (PrintWriter writer = response.getWriter();){
                    writer.print(String.format("导出数据不能大于%s条，可筛选条件重新导出",maxPageSize));
                    writer.flush();
                    return;
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                }
            }

            packageRechargeRespList(rechargeRespList, accountMap);

            // 导出文件
            ExcelUtil.exportExcel(response, rechargeRespList, ExcelUtil.getExcelOutFileName(APPLY_LIST_FILENAME, req.getCreateTime(), req.getUpdateTime()), APPLY_LIST_FILENAME);
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }


    @Override
    public String detail(TransactionDetailReq req, Model model) {
        FeignTransactionDetailReq feignReq = new FeignTransactionDetailReq();
        feignReq.setId(req.getId());
        BaseResult rechargeDetailResult = transactionFeign.rechargeDetail(feignReq);
        if (!rechargeDetailResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("查询该记录详情失败或记录不存在");
        }
        TransactionRechargeResp resp = JSONObject.parseObject(JSON.toJSONString(rechargeDetailResult.getData()), TransactionRechargeResp.class);

        // 封装返回资金入账用户昵称信息
        List<TransactionRechargeResp> rechargeRespList = new ArrayList<>(1);
        rechargeRespList.add(resp);
        packageRechargeRespList(rechargeRespList, new HashMap<>(0));
        model.addAttribute("detail", resp);
        return "transaction/recharge-detail";
    }

    @Override
    public BaseResult downloadLock(HttpSession session,TransactionRechargePageReq req) {
    	String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_RECHARGE_LOCK.concat(userId);
		String requestId = session.getId();
        boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!getRechargeDownloadLock) {
        	String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
        	if(requestId.equals(lockId)) {
        		log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
        	}
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }
        try {
            //判断搜索结果total值
            req.setSize(1);
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(listPage(req, session).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            Integer maxPageSizeLong = maxPageSize==null?EX_PAGE_SIZE:Integer.valueOf(maxPageSize);
            if(total>maxPageSizeLong) {
                if(getRechargeDownloadLock)
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if(getRechargeDownloadLock)
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }

        return BaseResult.success(true);
    }

}
