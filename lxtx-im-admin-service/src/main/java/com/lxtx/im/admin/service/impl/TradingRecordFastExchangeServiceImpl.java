package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.TradingRecordFastExchangeFeign;
import com.lxtx.im.admin.feign.feign.TransferFriendsFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.TradingRecordFastExchangeService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeDetailReq;
import com.lxtx.im.admin.service.request.TradingRecordFastExchangeReq;
import com.lxtx.im.admin.service.response.TradingRecordFastExchangeResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.response.WalletUserResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xufeifei
 * @since 2019-11-23
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class TradingRecordFastExchangeServiceImpl implements TradingRecordFastExchangeService {

    private static final String APPLY_LIST_FILENAME = "闪兑充值交易流水列表";

    @Autowired
    private TradingRecordFastExchangeFeign tradingRecordFastExchangeFeign;

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

    private static Integer EX_PAGE_SIZE = 50000;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;


    @Override
    public BaseResult listPage(TradingRecordFastExchangeReq req, HttpSession session) {

//        List<String> accountIds = null;
//        List<String> userIdsByUserName = null;
//        //根据用户名称查询出用户的账号
//        if (StringUtils.isNotBlank(req.getUserName())) {
//            FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
//            usernameReq.setName(req.getUserName());
//            BaseResult queryByUserName = userFeign.queryByUsername(usernameReq);
//            if (queryByUserName.isSuccess() && queryByUserName.getData() != null) {
//                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUserName.getData()));
//                accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
//                if (CollectionUtils.isEmpty(accountIds)) {
//                    return BaseResult.success(new Page<>());
//                }
//            } else {
//                return BaseResult.success(new Page<>());
//            }
//        }

        //根据用户账号查询出钱包ID
//        if (!CollectionUtils.isEmpty(accountIds)) {
//            FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
//            registerUserReq.setAccounts(accountIds);
//            BaseResult queryWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
//            if (queryWalletUser.isSuccess() && queryWalletUser.getData() != null) {
//                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryWalletUser.getData()));
//                userIdsByUserName = JSONObject.parseArray(jsonObject.getString("list"), String.class);
//                if (CollectionUtils.isEmpty(userIdsByUserName)) {
//                    return BaseResult.success(new Page<>());
//                }
//            } else {
//                return BaseResult.success(new Page<>());
//            }
//        }

        FeignTradingRecordFastExchangeReq feignTradingRecordFastExchangeReq = new FeignTradingRecordFastExchangeReq();
        BeanUtils.copyProperties(req, feignTradingRecordFastExchangeReq);
//        feignTradingRecordFastExchangeReq.setUserIdsByUserName(userIdsByUserName);
        feignTradingRecordFastExchangeReq.setKHShowAccount(userService.isShowAccount());

        BaseResult baseResult = tradingRecordFastExchangeFeign.listPage(feignTradingRecordFastExchangeReq);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<TradingRecordFastExchangeResp> respList = JSONObject.parseArray(jsonObject.getString("records"),
                    TradingRecordFastExchangeResp.class);
            if (!CollectionUtils.isEmpty(respList)) {
            	// 设置数据内部属性
				resetRespList(respList);
            }
            jsonObject.put("records", respList);
            baseResult.setData(jsonObject);
        }

        return baseResult;
    }

	@Override
    public BaseResult downloadLock(TradingRecordFastExchangeReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_FAST_GET_LOCK.concat(userId);
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

    @Override
    public void downloadList(HttpServletResponse response, HttpSession session, TradingRecordFastExchangeReq req) {
        String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_FAST_GET_LOCK.concat(userId);
		String requestId = session.getId();
        try {
        	boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            List<TradingRecordFastExchangeResp> respList = null;
            List<String> accountIds = null;
            List<String> userIdsByUserName = null;
            //根据用户名称查询出用户的账号
            if (StringUtils.isNotBlank(req.getUserName())) {
                FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
                usernameReq.setName(req.getUserName());
                BaseResult queryByUserName = userFeign.queryByUsername(usernameReq);
                if (queryByUserName.isSuccess() && queryByUserName.getData() == null) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUserName.getData()));
                    accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                    if (CollectionUtils.isEmpty(accountIds)) {
                        respList = new ArrayList<TradingRecordFastExchangeResp>();
                    }
                } else {
                    respList = new ArrayList<TradingRecordFastExchangeResp>();
                }
            }

            //根据用户账号查询出钱包ID
            if (!CollectionUtils.isEmpty(accountIds)) {
                FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
                registerUserReq.setAccounts(accountIds);
                BaseResult queryWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
                if (queryWalletUser.isSuccess() && queryWalletUser.getData() != null) {
                    JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryWalletUser.getData()));
                    userIdsByUserName = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                    if (CollectionUtils.isEmpty(userIdsByUserName)) {
                        respList = new ArrayList<TradingRecordFastExchangeResp>();
                    }
                } else {
                    respList = new ArrayList<TradingRecordFastExchangeResp>();
                }
            }

            FeignTradingRecordFastExchangeReq feignTradingRecordFastExchangeReq = new FeignTradingRecordFastExchangeReq();
            BeanUtils.copyProperties(req, feignTradingRecordFastExchangeReq);
            feignTradingRecordFastExchangeReq.setUserIdsByUserName(userIdsByUserName);
            feignTradingRecordFastExchangeReq.setKHShowAccount(userService.isShowAccount());
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignTradingRecordFastExchangeReq.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));
            BaseResult baseResult = tradingRecordFastExchangeFeign.listPage(feignTradingRecordFastExchangeReq);
            if (baseResult.isSuccess() && baseResult.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"),
                        TradingRecordFastExchangeResp.class);
                if (!CollectionUtils.isEmpty(respList)) {
                	// 设置数据内部属性
    				resetRespList(respList);
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
    public String detail(TradingRecordFastExchangeDetailReq req, Model model) {

        FeignTradingRecordFastExchangeDetailReq feignTradingRecordFastExchangeDetailReq = new FeignTradingRecordFastExchangeDetailReq();
        feignTradingRecordFastExchangeDetailReq.setId(req.getId());
        BaseResult fastExchangeResult = tradingRecordFastExchangeFeign.detail(feignTradingRecordFastExchangeDetailReq);
        String jsonResult = JSONArray.toJSONString(fastExchangeResult.getData());
        TradingRecordFastExchangeResp detailResp = JSONObject.parseObject(jsonResult, TradingRecordFastExchangeResp.class);
        setStatusValue(detailResp);
        model.addAttribute("detail", detailResp);
        return "wallet/tradingRecord-fastExchange-detail";
    }
    
	/**
	 * 
	 * @Description 获取用户名集合
	 * @param userIds
	 * @return
	 */
	private Map<String, String> getUserNameMap(HashSet<String> userIds) {
		Map<String, String> userNameMap = new HashMap<>();
		//根据钱包ID兑换accountId
		HashSet<String> accountIds =  new HashSet<>();
		Map<String, String> accountIdsMap = new HashMap<>();
		FeignTransferUserReq req = new FeignTransferUserReq();
		req.setUserIdList(new ArrayList<>(userIds));
		BaseResult coreResult = transferFriendsFeign.listTransferUserNames(req);
		if (coreResult.isSuccess() && coreResult.getData() != null) {
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
			List<WalletUserResp> walletUserResps = JSONObject.parseArray(jsonObject.getString("list"), WalletUserResp.class);
			if (!CollectionUtils.isEmpty(walletUserResps)) {
				walletUserResps.stream().forEach(record -> {
					accountIdsMap.put(record.getId(),record.getPlatformUserId());
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
						userIds.stream().forEach(userId->{
							userNameMap.put(userId, userIdsMap.get(accountIdsMap.get(userId)));
						});
					}
				}
			}
		}
		return userNameMap;
	}

    private Map<String, UserDetailResp> getUserMap(HashSet<String> userIds) {
        Map<String, UserDetailResp> userMap = new HashMap<>();
        //根据钱包ID兑换accountId
        HashSet<String> accountIds =  new HashSet<>();
        Map<String, String> accountIdsMap = new HashMap<>();
        FeignTransferUserReq req = new FeignTransferUserReq();
        req.setUserIdList(new ArrayList<>(userIds));
        BaseResult coreResult = transferFriendsFeign.listTransferUserNames(req);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
            List<WalletUserResp> walletUserResps = JSONObject.parseArray(jsonObject.getString("list"), WalletUserResp.class);
            if (!CollectionUtils.isEmpty(walletUserResps)) {
                walletUserResps.stream().forEach(record -> {
                    accountIdsMap.put(record.getId(),record.getPlatformUserId());
                    accountIds.add(record.getPlatformUserId());
                });
                // 查询符合条件的用户信息并转Map
                Map<String, UserDetailResp> userIdsMap = new HashMap<>();
                FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
                queryUserListReq.setIds(new ArrayList<>(accountIds));
                coreResult = userFeign.queryList(queryUserListReq);
                if (coreResult.isSuccess() && coreResult.getData() != null) {
                    UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(coreResult.getData()),
                            UserListResp.class);
                    List<UserDetailResp> userDetailResps = userListResp.getList();
                    if (!CollectionUtils.isEmpty(userDetailResps)) {
                        userDetailResps.stream().forEach(record -> {
                            userIdsMap.put(record.getAccount(), record);
                        });

                        userIds.stream().forEach(userId->{
                            userMap.put(userId, userIdsMap.get(accountIdsMap.get(userId)));
                        });
                    }
                }
            }
        }
        return userMap;
    }

    private void setStatusValue(TradingRecordFastExchangeResp resp) {
        if (
                resp.getStatus() == null
        ) {
            resp.setStatusValue("");
        } else if (
                resp.getStatus() == 0
                        || resp.getStatus() == 1
                        || resp.getStatus() == 7
        ) {
            resp.setStatusValue("处理中");
        } else if (
                resp.getStatus() == 2
        ) {
            resp.setStatusValue("成功");
        } else if (
                resp.getStatus() == 4
                        || resp.getStatus() == 9
        ) {
            resp.setStatusValue("失败");
        } else if (
                resp.getStatus() == 3
        ) {
            resp.setStatusValue("部分成功");
        }
    }
    
    /**
     * 
     * @Description 设置数据内部属性
     * @param respList
     */
    private void resetRespList(List<TradingRecordFastExchangeResp> respList) {
    	HashSet<String> userIds = new HashSet<>();
		// 获取钱包ID去重集合
		respList.stream().forEach(resp -> {
			if(!StringUtils.isEmpty(resp.getUserId())) {
				userIds.add(resp.getUserId());
			}
		});
		// 根据钱包ID集合获取用户昵称集合
        Map<String, UserDetailResp> userMap = getUserMap(userIds);

        respList.stream().forEach(resp -> {
            setStatusValue(resp);
            UserDetailResp userDetailResp = userMap.get(resp.getUserId());
            if (userDetailResp != null) {
                resp.setUserName(userDetailResp.getName());
                resp.setCountryCode(userDetailResp.getCountryCode());
                resp.setTelephone(userDetailResp.getTelephone());
            }
        });
	}
}
