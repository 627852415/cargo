package com.lxtx.im.admin.service.impl.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.YebFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumYebOrderType;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.YebOrderDetailReq;
import com.lxtx.im.admin.service.request.YebOrderDownloadReq;
import com.lxtx.im.admin.service.request.YebOrderListReq;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserList;
import com.lxtx.im.admin.service.response.UserResp;
import com.lxtx.im.admin.service.response.YebOrderResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.transaction.YebOrderService;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-11-25 15:25
 * @Description
 */
@Service
@Slf4j
public class YebOrderServiceImpl implements YebOrderService {
    @Resource
    private UserFeign userFeign;
    @Resource
    private YebFeign yebFeign;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheUtils redisCacheUtils;
    private static final String APPLY_LIST_FILENAME = "交易流水-理财宝列表";

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;
    // 导出数据要加数据数量限制，数量值字典全局配置（暂定50000）条
    // 导出数据条限制
    private static Integer EX_PAGE_SIZE = 50000;

    @Override
    public BaseResult listPage(YebOrderListReq req) {

        // 查询符合条件的用户信息
        List<UserDetailResp> userDetailRespList = null;
        if (StringUtils.isNotBlank(req.getUserName())) {
            FeignMemberListReq queryUserListReq = new FeignMemberListReq();
            queryUserListReq.setName(req.getUserName());
            BaseResult coreResult = userFeign.list(queryUserListReq);
            if (!coreResult.isSuccessAndDataNotNull()) {
                return BaseResult.success();
            }
            JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
            userDetailRespList = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), UserDetailResp.class);
            if (CollectionUtils.isEmpty(userDetailRespList)) {
                return BaseResult.success();
            }
        }

        List<String> accountIds = new ArrayList<>();
        Map<String, String> accountMap = new HashMap<>(0);
        if (CollectionUtils.isNotEmpty(userDetailRespList)) {
            userDetailRespList.forEach(userDetailResp -> {
                accountIds.add(userDetailResp.getAccount());
                accountMap.put(userDetailResp.getAccount(), userDetailResp.getName());
            });
        }


        FeignYebOrderListReq feignYebOrderListReq = new FeignYebOrderListReq();
        feignYebOrderListReq.setAccountList(accountIds);
        BeanUtils.copyProperties(req, feignYebOrderListReq);
        feignYebOrderListReq.setKHShowAccount(userService.isShowAccount());
        BaseResult baseResult = yebFeign.listPage(feignYebOrderListReq);
        if (!baseResult.isSuccessAndDataNotNull()) {
            return baseResult;
        }
        // 封装返回数据结果
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
        List<YebOrderResp> yebOrderRespList = JSONObject.parseArray(jsonObject.getString("records"), YebOrderResp.class);
        yebOrderRespList(yebOrderRespList, accountMap);
        jsonObject.put("records", yebOrderRespList);
        baseResult.setData(jsonObject);

        return baseResult;
    }

    /**
     * 封装资金入账用户昵称信息
     *
     * @param yebOrderRespList
     * @param accountMap
     */
    private void yebOrderRespList(List<YebOrderResp> yebOrderRespList, Map<String, String> accountMap) {
        if (CollectionUtils.isEmpty(yebOrderRespList)) {
            return;
        }
        if (accountMap.isEmpty()) {
            List<String> userIds = yebOrderRespList.stream().map(YebOrderResp::getPlatformUserId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(userIds)) {
                throw LxtxBizException.newException("资金转入交易记录用户账号信息有误！");
            }

            BaseResult userListResult = userFeign.selectBatchIds(new FeignQueryUserListByIdReq(userIds));
            if (userListResult.isSuccessAndDataNotNull()) {
                UserList userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListResult.getData()), UserList.class);
                accountMap = userListResp.getList().stream().collect(Collectors.toMap(UserResp::getAccount, UserResp::getName));
            }
        }
        Map<String, String> finalAccountMap = accountMap;
        for (YebOrderResp resp : yebOrderRespList) {
            resp.setUserName(finalAccountMap.get(resp.getPlatformUserId()));
            // 类型 1、转入余额；2、转出收益；3、转出本金；4、提取链上资金
            // 转入状态【 0、初始化; 1、6X提币申请中冻结用户金额 ；2、6X提币申请失败;3、6X提币申请成功;4、6X提币成功；5、余额宝存款申请成功；6、余额宝存款申请失败；7、订单完成】。
            if(EnumYebOrderType.TRANSFER_AMOUNT_IN_LEFT.getCode().equals(resp.getType())){
                switch (resp.getStatus()) {
                    case 0:
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                        resp.setStatusName("处理中");
                        break;
                    case 2:
                    case 6:
                        resp.setStatusName("失败");
                        break;
                    case 7:
                        resp.setStatusName("成功");
                        break;
                    default:
                        break;
                }
            }else if(EnumYebOrderType.TRANSFER_AMOUNT_OUT_EARNINGS.getCode().equals(resp.getType())|| EnumYebOrderType.TRANSFER_AMOUNT_OUT_PRICIPAL.getCode().equals(resp.getType()) || EnumYebOrderType.TRANSFER_AMOUNT_TAKE_CHAIN.getCode().equals(resp.getType()) ){
                switch (resp.getStatus()) {
                    case 0:
                    case 1:
                        resp.setStatusName("处理中");
                        break;
                    case 2:
                        resp.setStatusName("成功");
                        break;
                    case 3:
                        resp.setStatusName("失败");
                        break;
                    default:
                        break;
                }
            }
            resp.setTypeName(EnumYebOrderType.getDescription(resp.getType()));
        }
    }

    @Override
    public void downloadList(HttpServletResponse response, YebOrderDownloadReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_YEB_LOCK.concat(userId);
		String requestId = session.getId();
        // 查询符合条件的用户信息
        List<UserDetailResp> userDetailRespList = null;
        try {
        	boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            if (StringUtils.isNotBlank(req.getUserName())) {
                FeignMemberListReq queryUserListReq = new FeignMemberListReq();
                queryUserListReq.setName(req.getUserName());
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

            FeignYebOrderDownloadReq feignYebOrderDownloadReq = new FeignYebOrderDownloadReq();
            BeanUtils.copyProperties(req, feignYebOrderDownloadReq);
            feignYebOrderDownloadReq.setAccountList(accountIds);
            feignYebOrderDownloadReq.setKHShowAccount(userService.isShowAccount());

            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            if (StringUtils.isNotBlank(maxPageSize)) {
               Integer maxSize = Integer.parseInt(maxPageSize) + 1;
                feignYebOrderDownloadReq.setMaxPageSize(maxSize.toString());
            }
            BaseResult baseResult = yebFeign.downloadList(feignYebOrderDownloadReq);
            if (!baseResult.isSuccessAndDataNotNull()) {
                return;
            }

            // 封装查询结果用户昵称信息
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<YebOrderResp> yebOrderRespList = JSONObject.parseArray(jsonObject.getString(BaseResult.LIST), YebOrderResp.class);

            // 判断是否大于字典中配置的最大值
            if(yebOrderRespList.size() > Integer.parseInt(maxPageSize)){
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

            yebOrderRespList(yebOrderRespList, accountMap);

            // 导出文件
            ExcelUtil.exportExcel(response, yebOrderRespList, getExcelOutFileName(req.getCreateTime(), req.getUpdateTime()), APPLY_LIST_FILENAME);
            return;
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public String detail(YebOrderDetailReq req, Model model) {
        FeignYebOrderDetailReq feignReq = new FeignYebOrderDetailReq();
        feignReq.setId(req.getId());
        BaseResult rechargeDetailResult = yebFeign.detail(feignReq);
        if (!rechargeDetailResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("查询该记录详情失败或记录不存在");
        }
        YebOrderResp resp = JSONObject.parseObject(JSON.toJSONString(rechargeDetailResult.getData()), YebOrderResp.class);

        // 封装返回资金入账用户昵称信息
        List<YebOrderResp> yebOrderRespList = new ArrayList<>(1);
        yebOrderRespList.add(resp);
        yebOrderRespList(yebOrderRespList, new HashMap<>(0));
        model.addAttribute("detail", resp);
        return "transaction/yeb-order-detail";
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
    public BaseResult downloadLock(YebOrderListReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_YEB_LOCK.concat(userId);
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
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(listPage(req).getData()));
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
            log.error("导出异常:{}",e);
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }

        return BaseResult.success(true);
    }
}
