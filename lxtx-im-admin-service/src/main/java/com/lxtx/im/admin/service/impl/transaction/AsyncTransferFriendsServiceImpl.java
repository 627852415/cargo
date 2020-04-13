package com.lxtx.im.admin.service.impl.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.TransferFriendsFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.FeignQueryUserListReq;
import com.lxtx.im.admin.feign.request.FeignQueryUsernameReq;
import com.lxtx.im.admin.feign.request.FeignQueryWalletRegisterUserReq;
import com.lxtx.im.admin.feign.request.FeignTransferFriendsReq;
import com.lxtx.im.admin.feign.request.FeignTransferUserReq;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.request.TransferFriendsReq;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.service.response.TransferFriendsResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.response.WalletUserResp;
import com.lxtx.im.admin.service.transaction.AsyncTransferFriendsService;
import com.lxtx.im.admin.service.utils.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpSession;

/**
 * 
 * @Description 转账异步接口 考虑因素：内存、时间、cpu(线程数)
 * @author qing
 * @date: 2019年11月20日 下午6:23:27
 */
@Slf4j
@Service
@Async
public class AsyncTransferFriendsServiceImpl implements AsyncTransferFriendsService {


	// 表状态：status=（【1：待处理，2：处理中，3：已提交，4：成功，5:失败】）
	// 页面展示状态：status=（1：处理中（1,2,3），2：成功（4），3：失败（5））
	private static final String[] STATUSVALUES = { "处理中", "处理中", "处理中", "成功", "失败" };

	@Autowired
	private UserFeign userFeign;

	@Autowired
	private WalletUserFeign walletUserFeign;

	@Autowired
	private CoinService coinService;

	@Autowired
	private TransferFriendsFeign transferFriendsFeign;

	@Override
	@Async
	public void asyncListPage(TransferFriendsReq req, File segmentedFile, CountDownLatch cdl, HttpSession session) {
		try {
			FeignTransferFriendsReq feignReq = new FeignTransferFriendsReq();
			// 转出用户昵称换取用户钱包ID 且与 转出用户钱包ID进行条件过滤
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
			if (!StringUtils.isEmpty(req.getReceiverUserName())) {
				List<String> receiverIds = getUserIds(req.getReceiverUserName());
				if (CollectionUtils.isEmpty(receiverIds)) {
					return;
				}
				if (!StringUtils.isEmpty(req.getReceiverId())) {
					if (!receiverIds.contains(req.getReceiverId())) {
						return;
					}
					receiverIds = new ArrayList<>();
					receiverIds.add(req.getReceiverId());
				}
				feignReq.setReceiverIds(receiverIds);
			}
			// 有接收用户钱包ID，无接收用户昵称
			if (CollectionUtils.isEmpty(feignReq.getReceiverIds()) && !StringUtils.isEmpty(req.getReceiverId())) {
				List<String> receiverIds = new ArrayList<>();
				receiverIds.add(req.getReceiverId());
				feignReq.setReceiverIds(receiverIds);
			}
			BeanUtils.copyProperties(req, feignReq);
			BaseResult result = transferFriendsFeign.list(feignReq);
			if (result.isSuccess() && result.getData() != null) {
				JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
				List<TransferFriendsResp> respList = JSONObject.parseArray(jsonObject.getString("list"), TransferFriendsResp.class);
				if (!CollectionUtils.isEmpty(respList)) {
					// 设置数据内部属性
					resetRespList(respList);
					// 存储临时分片
					ExcelUtil.segmentedDatas(respList, segmentedFile);
				}
			}
		}catch(Exception e) {
			log.error("获取异步分片异常",e);
		}finally {
			if(cdl!=null) {
				cdl.countDown();
				log.info("剩余分片：{}",cdl.getCount());
			}
		}
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

	/**
	 * 
	 * @Description 获取用户名集合
	 * @param userIds
	 * @return
	 */
	private Map<String, String> getUserNameMap(HashSet<String> userIds) {
		Map<String, String> userNameMap = new HashMap<>();
		// 根据钱包ID兑换accountId
		HashSet<String> accountIds = new HashSet<>();
		Map<String, String> accountIdsMap = new HashMap<>();
		FeignTransferUserReq req = new FeignTransferUserReq();
		req.setUserIdList(new ArrayList<>(userIds));
		BaseResult coreResult = transferFriendsFeign.listTransferUserNames(req);
		if (coreResult.isSuccess() && coreResult.getData() != null) {
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(coreResult.getData()));
			List<WalletUserResp> walletUserResps = JSONObject.parseArray(jsonObject.getString("list"),
					WalletUserResp.class);
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
	 * @Description 设置数据内部属性
	 * @param respList
	 */
	private void resetRespList(List<TransferFriendsResp> respList) {
		HashSet<String> userIds = new HashSet<>();
		// 获取钱包ID去重集合
		respList.stream().forEach(resp -> {
			if (!StringUtils.isEmpty(resp.getUserId())) {
				userIds.add(resp.getUserId());
			}
			if (!StringUtils.isEmpty(resp.getReceiverId())) {
				userIds.add(resp.getReceiverId());
			}
		});
		// 根据钱包ID集合获取用户昵称集合
		Map<String, String> userNameMap = getUserNameMap(userIds);
		// 获取币种名称集合
		Map<String, String> coinNameMap = getCoinNameMap();
		// 设置转出用户昵称、接收用户昵称
		respList.stream().forEach(resp -> {
			resp.setUserName(userNameMap.get(resp.getUserId()));
			resp.setReceiverUserName(userNameMap.get(resp.getReceiverId()));
			resp.setCoinName(coinNameMap.get(resp.getCoinId()));
			resp.setStatusVaule(STATUSVALUES[resp.getStatus() - 1]);
			;
		});
	}
}
