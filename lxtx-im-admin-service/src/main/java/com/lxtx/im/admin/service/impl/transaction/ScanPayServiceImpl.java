package com.lxtx.im.admin.service.impl.transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.TransferWalletFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.FeignQueryUserListReq;
import com.lxtx.im.admin.feign.request.FeignQueryUsernameReq;
import com.lxtx.im.admin.feign.request.FeignQueryWalletRegisterUserReq;
import com.lxtx.im.admin.feign.request.FeignScanPayDetailReq;
import com.lxtx.im.admin.feign.request.FeignScanPayListPageReq;
import com.lxtx.im.admin.feign.request.FeignTransferScanPayWrapperReq;
import com.lxtx.im.admin.feign.request.FeignTransferUserReq;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.ScanPayDetailReq;
import com.lxtx.im.admin.service.request.ScanPayListPageReq;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.service.response.ScanPayResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.response.WalletUserResp;
import com.lxtx.im.admin.service.transaction.ScanPayService;
import com.lxtx.im.admin.service.utils.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScanPayServiceImpl implements ScanPayService {

	// 扫码付款
	private static final String SCAN_PAY_FILENAME = "扫码付款";
	
	private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_YYYY_MM_DD);
	
	@Autowired
	private UserFeign userFeign;
	
	@Autowired
	private TransferWalletFeign transferWalletFeign;
	
	@Autowired
	private CoinService coinService;
	
	@Autowired
	private WalletUserFeign walletUserFeign;
	
	@Autowired
    private DictService dictService;
	@Autowired
	private UserService userService;
	
	@Autowired
	@SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;
	
	// 导出数据要加数据数量限制，数量值字典全局配置（暂定50000）条
	// 导出数据条限制
	public static Integer EX_PAGE_SIZE = 50000;

	//锁时间 60秒
	public static Integer LOCK_TIME = 60000;
	
	private static final String[] STATUSVALUES = { "处理中", "处理中", "处理中", "成功", "失败" };
	
	@Override
	public BaseResult listPage(ScanPayListPageReq req, HttpSession session) {
		
		FeignScanPayListPageReq feignReq = new FeignScanPayListPageReq();
		if (!StringUtils.isEmpty(req.getUserName())) {
			List<String> userIds = getUserIds(req.getUserName());
			if (CollectionUtils.isEmpty(userIds)) {
				return BaseResult.success(new Page<>());
			}
			if (!StringUtils.isEmpty(req.getUserId())) {
				if (!userIds.contains(req.getUserId())) {
					return BaseResult.success(new Page<>());
				}
				userIds = new ArrayList<>();
				userIds.add(req.getUserId());
			}
			feignReq.setUserIds(userIds);
		}
		// 有转出用户钱包ID，无转出用户昵称
		if (CollectionUtils.isEmpty(feignReq.getUserIds()) && !StringUtils.isEmpty(req.getUserId())) {
			List<String> userIds = new ArrayList<>();
			userIds.add(req.getUserId());
			feignReq.setUserIds(userIds);
		}
		// 接收用户昵称换取用户钱包ID 且与 接收用户钱包ID进行条件过滤
		if (!StringUtils.isEmpty(req.getMerchantUserName())) {
			List<String> receiverIds = getUserIds(req.getMerchantUserName());
			if (CollectionUtils.isEmpty(receiverIds)) {
				return BaseResult.success(new Page<>());
			}
			if (!StringUtils.isEmpty(req.getMerchantUserId())) {
				if (!receiverIds.contains(req.getMerchantUserId())) {
					return BaseResult.success(new Page<>());
				}
				receiverIds = new ArrayList<>();
				receiverIds.add(req.getMerchantUserId());
			}
			feignReq.setMerchantUserIds(receiverIds);
		}
		// 有接收用户钱包ID，无接收用户昵称
		if (CollectionUtils.isEmpty(feignReq.getMerchantUserIds()) && !StringUtils.isEmpty(req.getMerchantUserId())) {
			List<String> receiverIds = new ArrayList<>();
			receiverIds.add(req.getMerchantUserId());
			feignReq.setMerchantUserIds(receiverIds);
		}
		
		BeanUtils.copyProperties(req, feignReq);
		feignReq.setKHShowAccount(userService.isShowAccount());

		if(!StringUtils.isEmpty(req.getUserId())||!StringUtils.isEmpty(req.getUserName())
				||!StringUtils.isEmpty(req.getPayUserPhone())||!StringUtils.isEmpty(req.getPayUserCountryCode())) {
			FeignTransferScanPayWrapperReq payListReq = new FeignTransferScanPayWrapperReq();
			payListReq.setId(req.getUserId());
			payListReq.setName(req.getUserName());
			payListReq.setTelephone(req.getPayUserPhone());
			payListReq.setCountryCode(req.getPayUserCountryCode());
			BaseResult payUserIdsResp = transferWalletFeign.listTransferScanPayUserIds(payListReq);
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(payUserIdsResp.getData()));
			List<String> payUserIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
			if(feignReq.getUserIds()!=null) {
				payUserIds = payUserIds.stream().filter(id -> feignReq.getUserIds().contains(id))
		                .collect(Collectors.toList());
				feignReq.setUserIds(payUserIds);
			}else {
				feignReq.setUserIds(payUserIds);
			}
			if(CollectionUtils.isEmpty(feignReq.getUserIds())) {
				return BaseResult.success(new Page<>());
			}
		}
		
		if(!StringUtils.isEmpty(req.getMerchantUserPhone())||!StringUtils.isEmpty(req.getMerchantUserCountryCode())) {
			FeignTransferScanPayWrapperReq payListReq = new FeignTransferScanPayWrapperReq();
			payListReq.setTelephone(req.getMerchantUserPhone());
			payListReq.setCountryCode(req.getMerchantUserCountryCode());
			BaseResult payUserIdsResp = transferWalletFeign.listTransferScanPayUserIds(payListReq);
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(payUserIdsResp.getData()));
			List<String> merchantUserIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
			if(feignReq.getMerchantUserIds()!=null) {
				merchantUserIds = merchantUserIds.stream().filter(id -> feignReq.getMerchantUserIds().contains(id))
		                .collect(Collectors.toList());
				feignReq.setMerchantUserIds(merchantUserIds);
			}else {
				feignReq.setMerchantUserIds(merchantUserIds);
			}
			if(CollectionUtils.isEmpty(feignReq.getMerchantUserIds())) {
				return BaseResult.success(new Page<>());
			}
		}
		
		BaseResult result = transferWalletFeign.listScanPayPage(feignReq);
		if (result.isSuccess() && result.getData() != null) {
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
			List<ScanPayResp> respList = JSONObject.parseArray(jsonObject.getString("records"),ScanPayResp.class);
			if (!CollectionUtils.isEmpty(respList)) {
				// 设置数据内部属性
				resetRespList(respList);
			}
			// 覆盖返回
			jsonObject.put("records", respList);
			result.setData(jsonObject);
		}
		
		return result;
	}

	@Override
	public String scanPayDetail(ScanPayDetailReq req, HttpSession session, Model model) {
		FeignScanPayDetailReq feignReq = new FeignScanPayDetailReq();
		BeanUtils.copyProperties(req, feignReq);
		BaseResult result = transferWalletFeign.scanPayDetail(feignReq);
		ScanPayResp resp = new ScanPayResp();
		if (result.isSuccess() && result.getData() != null) {
			resp = JSONObject.parseObject(JSONObject.toJSONString(result.getData()), ScanPayResp.class);
			HashSet<String> userIds = new HashSet<>();
			userIds.add(resp.getUserId());
			if(resp.getMerchantUserId()!=null) {
				userIds.add(resp.getMerchantUserId());
			}
			// 根据钱包ID集合获取用户昵称集合
			Map<String, UserDetailResp> userNameMap = getUserMap(userIds);
			// 获取币种名称集合
			Map<String, String> coinNameMap = getAliasCoinNameMap(userService.isShowAccount());
			// 设置转出用户昵称、接收用户昵称
			UserDetailResp payUser = userNameMap.get(resp.getUserId());
			if(payUser!=null) {
				resp.setUserName(payUser.getName());
				resp.setPayUserPhone(payUser.getTelephone());
				resp.setPayUserCountryCode(payUser.getCountryCode()+"/"+payUser.getFullTelephone().replace(payUser.getTelephone(), ""));
			}
			UserDetailResp merchantUser = userNameMap.get(resp.getMerchantUserId());
			if(merchantUser!=null) {
				resp.setMerchantUserPhone(merchantUser.getTelephone());
				resp.setMerchantUserCountryCode(merchantUser.getCountryCode()+"/"+merchantUser.getFullTelephone().replace(merchantUser.getTelephone(), ""));
			}
			resp.setPayCoinName(coinNameMap.get(resp.getPayCoinId()));
			resp.setReceiptCoinName(coinNameMap.get(resp.getReceiptCoinId()));
			if(resp.getStatus()!=null && resp.getStatus() - 1>0) {
				resp.setStatusVaule(STATUSVALUES[resp.getStatus() - 1]);
			}
			result.setData(resp);
		}
		model.addAttribute("detail", resp);
		return "transaction/scan-pay-detail";
	}
	
	/**
	 * 
	 * @Description 设置数据内部属性
	 * @param respList
	 */
	private void resetRespList(List<ScanPayResp> respList) {
		HashSet<String> userIds = new HashSet<>();
		// 获取钱包ID去重集合
		respList.stream().forEach(resp -> {
			if(!StringUtils.isEmpty(resp.getUserId())) {
				userIds.add(resp.getUserId());
				userIds.add(resp.getMerchantUserId());
			}
		});
		// 根据钱包ID集合获取用户昵称集合
		Map<String, UserDetailResp> userMap = getUserMap(userIds);
		// 获取币种名称集合
		Map<String, String> coinNameMap = getAliasCoinNameMap(userService.isShowAccount());
		// 设置转出用户昵称、接收用户昵称
		respList.stream().forEach(resp -> {
			UserDetailResp payUser = userMap.get(resp.getUserId());
			if(payUser!=null) {
				resp.setUserName(payUser.getName());
				resp.setPayUserPhone(payUser.getTelephone());
				resp.setPayUserCountryCode(payUser.getCountryCode()+"/"+payUser.getFullTelephone().replace(payUser.getTelephone(), ""));
			}
			UserDetailResp merchantUser = userMap.get(resp.getMerchantUserId());
			if(merchantUser!=null) {
				resp.setMerchantUserPhone(merchantUser.getTelephone());
				resp.setMerchantUserCountryCode(merchantUser.getCountryCode()+"/"+merchantUser.getFullTelephone().replace(merchantUser.getTelephone(), ""));
			}
			resp.setPayCoinName(coinNameMap.get(resp.getPayCoinId()));
			resp.setReceiptCoinName(coinNameMap.get(resp.getReceiptCoinId()));
			if(resp.getStatus()!=null && resp.getStatus() - 1>0) {
				resp.setStatusVaule(STATUSVALUES[resp.getStatus() - 1]);
			}
			;
		});
	}
	
	/**
	 * 
	 * @Description 获取用户集合
	 * @param userIds
	 * @return
	 */
	private Map<String, UserDetailResp> getUserMap(HashSet<String> userIds) {
		Map<String, UserDetailResp> userMap = new HashMap<>();
		//根据钱包ID兑换accountId
		HashSet<String> accountIds =  new HashSet<>();
		Map<String, String> accountIdsMap = new HashMap<>();
		FeignTransferUserReq req = new FeignTransferUserReq();
		req.setUserIdList(new ArrayList<>(userIds));
		BaseResult coreResult = transferWalletFeign.listTransferUserNames(req);
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
	
	/**
	 * 
	 * @Description 获取币种名称集合
	 * @return
	 */
	private Map<String, String> getCoinNameMap() {
		Map<String, String> coinNameMap = new HashMap<>();
		List<CoinResp> coinResps = coinService.getAllCoinList();
		if (!CollectionUtils.isEmpty(coinResps)) {
			coinResps.stream().forEach(crs -> {
				coinNameMap.put(crs.getId(), crs.getCoinName());
			});
		}
		return coinNameMap;
	}

	/**
	 *
	 * @Description 获取币种别名集合
	 * @return
	 */
	private Map<String, String> getAliasCoinNameMap(boolean isKHAccount) {
		Map<String, String> coinNameMap = new HashMap<>();
		List<CoinResp> coinResps;
		if(isKHAccount){
			coinResps = coinService.getAllLegalCoinList();
		}else{
			return getCoinNameMap();
		}

		if (!CollectionUtils.isEmpty(coinResps)) {
			coinResps.stream().forEach(crs -> {
				coinNameMap.put(crs.getId(), crs.getAliasName());
			});
		}
		return coinNameMap;
	}

	/**
	 * 
	 * @Description 获取用户账号ID换取钱包ID
	 * @param userName
	 * @return
	 */
	private List<String> getUserIds(String userName) {
		// 根据用户名称查询指定用户
		FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
		usernameReq.setName(userName);
		BaseResult queryByUsername = userFeign.queryByUsername(usernameReq);
		if (queryByUsername.isSuccess() && queryByUsername.getData() != null) {
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUsername.getData()));
			List<String> accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
			if (!CollectionUtils.isEmpty(accountIds)) {
				FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
				registerUserReq.setAccounts(accountIds);
				BaseResult registerWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
				if (registerWalletUser.isSuccess() && registerWalletUser.getData() != null) {
					jsonObject = JSONObject.parseObject(JSON.toJSONString(registerWalletUser.getData()));
					List<String> userIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
					if (!CollectionUtils.isEmpty(userIds)) {
						return userIds;
					}
				}
			}
		}
		return null;
	}

	@Override
	public BaseResult downloadLock(ScanPayListPageReq req, HttpSession session) {
		String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_PAY_LOCK.concat(userId);
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
	public void downloadList(ScanPayListPageReq req, HttpSession session, HttpServletResponse response) {
		String userId = ShiroUtils.getUserId();
		String lockKey = RedisConstants.ADMIN_EXPORT_PAY_LOCK.concat(userId);
		String requestId = session.getId();
		try {
			boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            
			List<ScanPayResp> respList = null;
			FeignScanPayListPageReq feignReq = new FeignScanPayListPageReq();
			if (!StringUtils.isEmpty(req.getUserName())) {
				List<String> userIds = getUserIds(req.getUserName());
				if (CollectionUtils.isEmpty(userIds)) {
					return;
				}
				if (!StringUtils.isEmpty(req.getUserId())) {
					if (!userIds.contains(req.getUserId())) {
						return;
					}
					userIds = new ArrayList<>();
					userIds.add(req.getUserId());
				}
				feignReq.setUserIds(userIds);
			}
			// 有转出用户钱包ID，无转出用户昵称
			if (CollectionUtils.isEmpty(feignReq.getUserIds()) && !StringUtils.isEmpty(req.getUserId())) {
				List<String> userIds = new ArrayList<>();
				userIds.add(req.getUserId());
				feignReq.setUserIds(userIds);
			}
			// 接收用户昵称换取用户钱包ID 且与 接收用户钱包ID进行条件过滤
			if (!StringUtils.isEmpty(req.getMerchantUserName())) {
				List<String> receiverIds = getUserIds(req.getMerchantUserName());
				if (CollectionUtils.isEmpty(receiverIds)) {
					return;
				}
				if (!StringUtils.isEmpty(req.getMerchantUserId())) {
					if (!receiverIds.contains(req.getMerchantUserId())) {
						return;
					}
					receiverIds = new ArrayList<>();
					receiverIds.add(req.getMerchantUserId());
				}
				feignReq.setMerchantUserIds(receiverIds);
			}
			// 有接收用户钱包ID，无接收用户昵称
			if (CollectionUtils.isEmpty(feignReq.getMerchantUserIds()) && !StringUtils.isEmpty(req.getMerchantUserId())) {
				List<String> receiverIds = new ArrayList<>();
				receiverIds.add(req.getMerchantUserId());
				feignReq.setMerchantUserIds(receiverIds);
			}
			
			BeanUtils.copyProperties(req, feignReq);
			feignReq.setKHShowAccount(userService.isShowAccount());
			
			// 设置导出页数
			String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
			feignReq.setSize(maxPageSize==null?EX_PAGE_SIZE:Integer.valueOf(maxPageSize));
			
			if(!StringUtils.isEmpty(req.getUserId())||!StringUtils.isEmpty(req.getUserName())
					||!StringUtils.isEmpty(req.getPayUserPhone())||!StringUtils.isEmpty(req.getPayUserCountryCode())) {
				FeignTransferScanPayWrapperReq payListReq = new FeignTransferScanPayWrapperReq();
				payListReq.setId(req.getUserId());
				payListReq.setName(req.getUserName());
				payListReq.setTelephone(req.getPayUserPhone());
				payListReq.setCountryCode(req.getPayUserCountryCode());
				BaseResult payUserIdsResp = transferWalletFeign.listTransferScanPayUserIds(payListReq);
				JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(payUserIdsResp.getData()));
				List<String> payUserIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
				if(feignReq.getUserIds()!=null) {
					payUserIds = payUserIds.stream().filter(id -> feignReq.getUserIds().contains(id))
			                .collect(Collectors.toList());
					feignReq.setUserIds(payUserIds);
				}else {
					feignReq.setUserIds(payUserIds);
				}
				if(CollectionUtils.isEmpty(feignReq.getUserIds())) {
					log.error("导出失败,没符合条件的数据");
					return;
				}
			}
			
			if(!StringUtils.isEmpty(req.getMerchantUserPhone())||!StringUtils.isEmpty(req.getMerchantUserCountryCode())) {
				FeignTransferScanPayWrapperReq payListReq = new FeignTransferScanPayWrapperReq();
				payListReq.setTelephone(req.getMerchantUserPhone());
				payListReq.setCountryCode(req.getMerchantUserCountryCode());
				BaseResult payUserIdsResp = transferWalletFeign.listTransferScanPayUserIds(payListReq);
				JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(payUserIdsResp.getData()));
				List<String> merchantUserIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
				if(feignReq.getMerchantUserIds()!=null) {
					merchantUserIds = merchantUserIds.stream().filter(id -> feignReq.getMerchantUserIds().contains(id))
			                .collect(Collectors.toList());
					feignReq.setMerchantUserIds(merchantUserIds);
				}else {
					feignReq.setMerchantUserIds(merchantUserIds);
				}
				if(CollectionUtils.isEmpty(feignReq.getMerchantUserIds())) {
					log.error("导出失败,没符合条件的数据");
					return;
				}
			}
			
			BaseResult result = transferWalletFeign.listScanPayPage(feignReq);
			if (result.isSuccess() && result.getData() != null) {
				JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
				respList = JSONObject.parseArray(jsonObject.getString("records"), ScanPayResp.class);
				if (!CollectionUtils.isEmpty(respList)) {
					// 设置数据内部属性
					resetRespList(respList);
				}
			}
			if(respList == null) {
				respList = new ArrayList<>();
			}
			// 导出文件
			ExcelUtil.exportExcel(response, respList,
					getScanPayDownloadFileName(req.getCreateTimeStart(), req.getCreateTimeEnd()),
					SCAN_PAY_FILENAME);
		}catch(Exception e){
			log.error("导出失败",e);
			return;
		}finally {
			redisCacheUtils.releaseDistributedLock(lockKey, requestId);
		}
	}
	
	/**
	 * 
	 * @Description 命名文件名
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getScanPayDownloadFileName(Date startTime, Date endTime) {
		String fileName = SCAN_PAY_FILENAME;
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
}
