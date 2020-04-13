package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.GroupFeign;
import com.lxtx.im.admin.feign.feign.RedPacketFeign;
import com.lxtx.im.admin.feign.feign.TransferWalletFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.RedPacketService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.impl.transaction.ScanPayServiceImpl;
import com.lxtx.im.admin.service.request.AdminReceiveListReq;
import com.lxtx.im.admin.service.request.AdminSendListReq;
import com.lxtx.im.admin.service.response.AdminReceiveListResp;
import com.lxtx.im.admin.service.response.AdminSendListResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xumf
 * @date 2019/11/25 15:29
 */
@Slf4j
@Service
public class RedPacketServiceImpl implements RedPacketService {

    // 扫码付款
    private static final String RED_PACK_FILENAME = "红包发送";
    private static final String RECEIVE_RED_PACK_FILENAME = "红包领取";
    // 群 ID key
    private static final String GROUP_ID_KEY = "groupId";
    //锁时间 60秒
    private static final Integer LOCK_TIME = 60000;

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private RedPacketFeign redPacketFeign;
    @Autowired
    private GroupFeign groupFeign;
    @Autowired
    private DictService dictService;
    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private UserService userService;
    
    @Autowired
    private TransferWalletFeign transferWalletFeign;

    @Override
    public BaseResult adminSendList(FeignAdminSendListReq req) {
        req.setKHShowAccount(userService.isShowAccount());
        if(req.getReceiverIds()!=null&&req.getReceiverIds().size()==0) {
        	return BaseResult.success(new Page<>());
        }
        if(req.getUserIds()!=null&&req.getUserIds().size()==0) {
        	return BaseResult.success(new Page<>());
        }
        BaseResult baseResult = redPacketFeign.adminSendList(req);
        if (baseResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<? extends HashMap> list = JSONObject.parseArray(jsonObject.getString("records"), HashMap.class);

            // 获取发送者平台 ID 集合
            final String senderKey = "senderPlatformUserId";
            final String senderNameKey = "senderName";
            final String senderCountryCodeKey = "senderCountryCode";
            final String senderTelephoneKey = "senderTelephone";
            List<String> sendPlatformUserIds = list.stream().map(o -> {
                Object sendPlatformUserId = o.get(senderKey);
                return String.valueOf(sendPlatformUserId);
            }).distinct().collect(Collectors.toList());
            // 获取接收者平台 ID 集合 (过滤群 ID)
            final String receiverKey = "receiverPlatformUserId";
            final String receiverNameKey = "receiverName";
            final String receiverCountryCodeKey = "receiverCountryCode";
            final String receiverTelephoneKey = "receiverTelephone";
            List<String> receivePlatformUserIds = list.stream().filter(o -> {
                String businessStr = String.valueOf(o.get("business"));
                return !Boolean.parseBoolean(businessStr);
            }).map(o -> {
                Object receivePlatformUserId = o.get(receiverKey);
                return String.valueOf(receivePlatformUserId);
            }).distinct().collect(Collectors.toList());
            List<String> platformUserIds = Stream.of(sendPlatformUserIds.stream(), receivePlatformUserIds.stream())
                    .flatMap(Function.identity()).distinct().collect(Collectors.toList());

            // 获取群 ID 集合
            final String groupKey = "group";
            List<String> groupIds = list.stream().filter(o -> {
                String businessStr = String.valueOf(o.get(groupKey));
                return Boolean.parseBoolean(businessStr);
            }).map(o -> {
                Object receivePlatformUserId = o.get(receiverKey);
                return String.valueOf(receivePlatformUserId);
            }).distinct().collect(Collectors.toList());

            // 设置用户昵称
            FeignQueryUserListReq walletRegisterUserReq = new FeignQueryUserListReq();
            walletRegisterUserReq.setIds(platformUserIds);
            BaseResult senderPlatformUserBaseResult = userFeign.queryList(walletRegisterUserReq);
            if (senderPlatformUserBaseResult.isSuccess() && senderPlatformUserBaseResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) senderPlatformUserBaseResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                if(CollectionUtils.isEmpty(userListResp.getList())){
                    return BaseResult.success(new Page<>());
                }
                Map<String, String> userMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, UserDetailResp::getName));
                Map<String, String> countryCodeMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, UserDetailResp::getCountryCode));
                Map<String, String> telephoneMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, UserDetailResp::getTelephone));

                list = list.stream().peek(o -> {
                    String sendPlatformUserId = String.valueOf(o.get(senderKey));
                    String receivePlatformUserId = String.valueOf(o.get(receiverKey));
                    if (userMap.containsKey(sendPlatformUserId)) {
                        o.put(senderNameKey, userMap.get(sendPlatformUserId));
                        o.put(senderCountryCodeKey, countryCodeMap.get(sendPlatformUserId));
                        o.put(senderTelephoneKey, telephoneMap.get(sendPlatformUserId));
                    }
                    if (userMap.containsKey(receivePlatformUserId)) {
                        o.put(receiverNameKey, userMap.get(receivePlatformUserId));
                        o.put(receiverCountryCodeKey, countryCodeMap.get(receivePlatformUserId));
                        o.put(receiverTelephoneKey, telephoneMap.get(receivePlatformUserId));
                    }
                }).collect(Collectors.toList());
            }

            // 设置群昵称
            FeignGroupListReq groupListReq = new FeignGroupListReq();
            groupListReq.setGroupIds(groupIds);
            BaseResult groupBaseResult = groupFeign.list(groupListReq);
            if (groupBaseResult.isSuccess() && groupBaseResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) groupBaseResult.getData();
                List<LinkedHashMap<String, Object>> coreList = (List<LinkedHashMap<String, Object>>) coreDataMap.get("list");
                // 群名称 map
                Map<String, String> groupMap = coreList.stream().distinct().map(o -> {
                    Map<String, String> resMap = Maps.newHashMap();
                    resMap.put(GROUP_ID_KEY, String.valueOf(o.get(GROUP_ID_KEY)));
                    resMap.put("name", String.valueOf(o.get("name")));
                    return resMap;
                }).collect(Collectors.toMap(o -> o.get(GROUP_ID_KEY), o -> o.get("name")));

                list = list.stream().peek(o -> {
                    String receivePlatformUserId = String.valueOf(o.get(receiverKey));
                    if (groupMap.containsKey(receivePlatformUserId)) {
                        o.put(receiverNameKey, groupMap.get(receivePlatformUserId));
                    }
                }).collect(Collectors.toList());
            }

            // 设置 data
            jsonObject.put("records", list);
            Object data = jsonObject.toJavaObject(Object.class);
            baseResult.setData(data);
        }
        return baseResult;
    }

    @Override
    public FeignAdminSendListReq getFeignAdminSendListReq(AdminSendListReq req) {

        // 发送方用户 ID
        List<String> senderIds = null;
        // 接收方用户 ID
        List<String> receiverIds = null;

        // 发送用户条件 已废弃该逻辑结果不对
//        String userKeyword = req.getUserKeyword();
        
        String userId = req.getUserId();
        
        String username = req.getUsername();

        //发送方国际简码和手机号
        String senderCountryCode = req.getSenderCountryCode();
        String senderTelephone = req.getSenderTelephone();
        
        //发送方国际简码和手机号
        String receiverCountryCode = req.getReceiverCountryCode();
        String receiverTelephone = req.getReceiverTelephone();

        if (StringUtils.isNotBlank(username) || StringUtils.isNotBlank(username) || StringUtils.isNotBlank(senderCountryCode) ||  StringUtils.isNotBlank(senderTelephone)) {
        	if(senderIds==null) {
            	senderIds = Lists.newArrayList();
            }
        	// 通过昵称获取用户 ID
//            FeignQueryUsernameReq feignQueryUsernameReq = new FeignQueryUsernameReq();
//            if(StringUtils.isNotBlank(username)){
//                feignQueryUsernameReq.setName(username);
//            }
//            if(StringUtils.isNotBlank(senderCountryCode)){
//                feignQueryUsernameReq.setCountryCode(senderCountryCode);
//            }
//            if(StringUtils.isNotBlank(senderTelephone)){
//                feignQueryUsernameReq.setTelephone(senderTelephone);
//            }
            //TODO 这个不对，id不是钱包id
//            BaseResult nameBaseResult = userFeign.queryByUsername(feignQueryUsernameReq);
            FeignTransferScanPayWrapperReq feignQueryUsernameReq = new FeignTransferScanPayWrapperReq();
	        if(StringUtils.isNotBlank(username)){
	        	feignQueryUsernameReq.setName(username);
		    }
		    if(StringUtils.isNotBlank(senderCountryCode)){
		        feignQueryUsernameReq.setCountryCode(senderCountryCode);
		    }
		    if(StringUtils.isNotBlank(senderTelephone)){
		        feignQueryUsernameReq.setTelephone(senderTelephone);
		    }
            BaseResult nameBaseResult = transferWalletFeign.listTransferScanPayUserIds(feignQueryUsernameReq);
            if (nameBaseResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(nameBaseResult.getData()));
                List<String> accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                senderIds.addAll(accountIds);
            }
        }
        
        // 输入为用户 ID 情况
        if(StringUtils.isNotBlank(userId)){
        	if(senderIds!=null) {
        		List<String> tmpSenderIds = Lists.newArrayList();
            	//做交集运算
        		for (String id : senderIds) {
					if(id.indexOf(userId)>-1) {
						tmpSenderIds.add(id);
					}
				}
        		senderIds = tmpSenderIds;
            }else {
            	senderIds = Lists.newArrayList();
            	senderIds.add(userId);
            }
        }

        // 接收用户条件
        String receiverKeyword = req.getReceiverKeyword();
        if (StringUtils.isNotBlank(receiverKeyword) || StringUtils.isNotBlank(receiverCountryCode) ||  StringUtils.isNotBlank(receiverTelephone) ) {
        	if(receiverIds==null) {
            	receiverIds = Lists.newArrayList();
            }
            // 通过昵称获取用户 ID
            FeignQueryUsernameReq feignQueryUsernameReq = new FeignQueryUsernameReq();
            if(StringUtils.isNotBlank(receiverKeyword)){
                feignQueryUsernameReq.setName(receiverKeyword);
            }
            if(StringUtils.isNotBlank(receiverCountryCode)){
                feignQueryUsernameReq.setCountryCode(receiverCountryCode);
            }
            if(StringUtils.isNotBlank(receiverTelephone)){
                feignQueryUsernameReq.setTelephone(receiverTelephone);
            }
            BaseResult nameBaseResult = userFeign.queryByUsername(feignQueryUsernameReq);
            if (nameBaseResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(nameBaseResult.getData()));
                List<String> accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                receiverIds.addAll(accountIds);
            }

            // 设置群昵称
            FeignGroupListReq groupListReq = new FeignGroupListReq();
            groupListReq.setName(receiverKeyword);
            BaseResult groupBaseResult = groupFeign.list(groupListReq);
            if (groupBaseResult.isSuccess() && groupBaseResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) groupBaseResult.getData();
                List<LinkedHashMap<String, Object>> coreList = (List<LinkedHashMap<String, Object>>) coreDataMap.get("list");
                List<String> groupIds = coreList.stream().map(o -> String.valueOf(o.get(GROUP_ID_KEY))).collect(Collectors.toList());
                if(receiverIds==null) {
                	receiverIds = Lists.newArrayList();
                }
                receiverIds.addAll(groupIds);
            }
	        // 输入为用户 ID 或者群 ID 情况
	        if(StringUtils.isNotBlank(receiverKeyword)){
	        	receiverIds.add(receiverKeyword);
	        }
        }

        FeignAdminSendListReq feignAdminSendListReq = new FeignAdminSendListReq();
        BeanUtils.copyProperties(req, feignAdminSendListReq);
        feignAdminSendListReq.setUserIds(senderIds);
        feignAdminSendListReq.setReceiverIds(receiverIds);
        feignAdminSendListReq.setKHShowAccount(userService.isShowAccount());
        return feignAdminSendListReq;
    }

    @Override
    public BaseResult adminReceiveList(FeignAdminReceiveListReq req) {
        req.setKHShowAccount(userService.isShowAccount());
        if(req.getReceiverIds()!=null&&req.getReceiverIds().size()==0) {
        	return BaseResult.success(new Page<>());
        }
        if(req.getUserIds()!=null&&req.getUserIds().size()==0) {
        	return BaseResult.success(new Page<>());
        }
        BaseResult baseResult = redPacketFeign.adminReceiveList(req);
        if (baseResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<? extends HashMap> list = JSONObject.parseArray(jsonObject.getString("records"), HashMap.class);

            // 获取发送者平台 ID 集合
            final String senderKey = "senderPlatformUserId";
            final String senderNameKey = "senderName";
            final String senderCountryCodeKey = "senderCountryCode";
            final String senderTelephoneKey = "senderTelephone";
            List<String> sendPlatformUserIds = list.stream().map(o -> {
                Object sendPlatformUserId = o.get(senderKey);
                return String.valueOf(sendPlatformUserId);
            }).distinct().collect(Collectors.toList());
            // 获取接收者平台 ID 集合 (过滤群 ID)
            final String receiverKey = "receiverPlatformUserId";
            final String receiverNameKey = "receiverName";
            final String receiverCountryCodeKey = "receiverCountryCode";
            final String receiverTelephoneKey = "receiverTelephone";
            List<String> receivePlatformUserIds = list.stream().filter(o -> {
                String businessStr = String.valueOf(o.get("business"));
                return !Boolean.parseBoolean(businessStr);
            }).map(o -> {
                Object receivePlatformUserId = o.get(receiverKey);
                return String.valueOf(receivePlatformUserId);
            }).distinct().collect(Collectors.toList());
            List<String> platformUserIds = Stream.of(sendPlatformUserIds.stream(), receivePlatformUserIds.stream())
                    .flatMap(Function.identity()).distinct().collect(Collectors.toList());

            // 设置用户昵称
            FeignQueryUserListReq walletRegisterUserReq = new FeignQueryUserListReq();
            walletRegisterUserReq.setIds(platformUserIds);
            BaseResult senderPlatformUserBaseResult = userFeign.queryList(walletRegisterUserReq);
            userIf:
            if (senderPlatformUserBaseResult.isSuccess() && senderPlatformUserBaseResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) senderPlatformUserBaseResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                if (null == userListResp.getList()) {
                    break userIf;
                }
                if(CollectionUtils.isEmpty(userListResp.getList())){
                    return BaseResult.success(new Page<>());
                }
                Map<String, String> userMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, UserDetailResp::getName));
                Map<String, String> countryCodeMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, UserDetailResp::getCountryCode));
                Map<String, String> telephoneMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, UserDetailResp::getTelephone));

                list = list.stream().peek(o -> {
                    String sendPlatformUserId = String.valueOf(o.get(senderKey));
                    String receivePlatformUserId = String.valueOf(o.get(receiverKey));
                    if (userMap.containsKey(sendPlatformUserId)) {
                        o.put(senderNameKey, userMap.get(sendPlatformUserId));
                        o.put(senderCountryCodeKey, countryCodeMap.get(sendPlatformUserId));
                        o.put(senderTelephoneKey, telephoneMap.get(sendPlatformUserId));
                    }
                    if (userMap.containsKey(receivePlatformUserId)) {
                        o.put(receiverNameKey, userMap.get(receivePlatformUserId));
                        o.put(receiverCountryCodeKey, countryCodeMap.get(receivePlatformUserId));
                        o.put(receiverTelephoneKey, telephoneMap.get(receivePlatformUserId));
                    }
                }).collect(Collectors.toList());
            }

            // 设置 data
            jsonObject.put("records", list);
            Object data = jsonObject.toJavaObject(Object.class);
            baseResult.setData(data);
        }

        return baseResult;
    }

    /**
     * 获取领取红包记录条件
     *
     * @param req {@link AdminReceiveListReq}
     * @return {@link FeignAdminReceiveListReq}
     */
    @Override
    public FeignAdminReceiveListReq getFeignAdminReceiveListReq(AdminReceiveListReq req) {
        // 发送方用户 ID
        List<String> senderIds = null;
        // 接收方用户 ID
        List<String> receiverIds = null;

        String senderCountryCode = req.getSenderCountryCode();
        String senderTelephone = req.getSenderTelephone();

        String receiverCountryCode = req.getReceiverCountryCode();
        String receiverTelephone = req.getReceiverTelephone();

        // 发送用户条件
//        String userKeyword = req.getUserKeyword();
        String userId = req.getUserId();
        String username = req.getUsername();
        
        
        if (StringUtils.isNotBlank(username)  || StringUtils.isNotBlank(senderCountryCode) ||  StringUtils.isNotBlank(senderTelephone)) {
        	if(senderIds==null) {
        		senderIds = Lists.newArrayList();
            }
//            // 通过昵称获取用户 ID
//            FeignQueryUsernameReq feignQueryUsernameReq = new FeignQueryUsernameReq();
//            if(StringUtils.isNotBlank(username)) {
//                feignQueryUsernameReq.setName(username);
//            }
//            if(StringUtils.isNotBlank(senderCountryCode)){
//                feignQueryUsernameReq.setCountryCode(senderCountryCode);
//            }
//            if(StringUtils.isNotBlank(senderTelephone)){
//                feignQueryUsernameReq.setTelephone(senderTelephone);
//            }
//            BaseResult nameBaseResult = userFeign.queryByUsername(feignQueryUsernameReq);
        	
        	FeignTransferScanPayWrapperReq feignQueryUsernameReq = new FeignTransferScanPayWrapperReq();
	        if(StringUtils.isNotBlank(username)){
	        	feignQueryUsernameReq.setName(username);
		    }
		    if(StringUtils.isNotBlank(senderCountryCode)){
		        feignQueryUsernameReq.setCountryCode(senderCountryCode);
		    }
		    if(StringUtils.isNotBlank(senderTelephone)){
		        feignQueryUsernameReq.setTelephone(senderTelephone);
		    }
            BaseResult nameBaseResult = transferWalletFeign.listTransferScanPayUserIds(feignQueryUsernameReq);
        	
            if (nameBaseResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(nameBaseResult.getData()));
                List<String> accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                senderIds.addAll(accountIds);
            }
        }
        
     // 输入为用户 ID 情况
        if(StringUtils.isNotBlank(userId)){
        	if(senderIds!=null) {
        		List<String> tmpSenderIds = Lists.newArrayList();
            	//做交集运算
        		for (String id : senderIds) {
					if(id.indexOf(userId)>-1) {
						tmpSenderIds.add(id);
					}
				}
        		senderIds = tmpSenderIds;
            }else {
            	senderIds = Lists.newArrayList();
            	senderIds.add(userId);
            }
        }
        // 接收用户条件
        String receiverKeyword = req.getReceiverKeyword();
        if (StringUtils.isNotBlank(receiverKeyword) || StringUtils.isNotBlank(receiverCountryCode) ||  StringUtils.isNotBlank(receiverTelephone)) {
        	if(receiverIds==null) {
        		receiverIds = Lists.newArrayList();
            }
            // 通过昵称获取用户 ID
            FeignQueryUsernameReq feignQueryUsernameReq = new FeignQueryUsernameReq();
            if(StringUtils.isNotBlank(receiverKeyword)) {
                feignQueryUsernameReq.setName(receiverKeyword);
            }
            if(StringUtils.isNotBlank(receiverCountryCode)){
                feignQueryUsernameReq.setCountryCode(receiverCountryCode);
            }
            if(StringUtils.isNotBlank(receiverTelephone)){
                feignQueryUsernameReq.setTelephone(receiverTelephone);
            }
            BaseResult nameBaseResult = userFeign.queryByUsername(feignQueryUsernameReq);
            if (nameBaseResult.isSuccessAndDataNotNull()) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(nameBaseResult.getData()));
                List<String> accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                receiverIds.addAll(accountIds);
            }
            // 输入为用户 ID 或者群 ID 情况
	        if(StringUtils.isNotBlank(receiverKeyword)){
	        	receiverIds.add(receiverKeyword);
	        }
        }

        FeignAdminReceiveListReq feignAdminSendListReq = new FeignAdminReceiveListReq();
        BeanUtils.copyProperties(req, feignAdminSendListReq);
        feignAdminSendListReq.setUserIds(senderIds);
        feignAdminSendListReq.setReceiverIds(receiverIds);
        return feignAdminSendListReq;
    }

    @Override
    public String sendDetail(String id, Model model) {

        AdminSendListReq adminSendListReq = new AdminSendListReq();
        adminSendListReq.setRedPacketSendId(id);

        FeignAdminSendListReq feignAdminSendListReq = getFeignAdminSendListReq(adminSendListReq);
        BaseResult result = adminSendList(feignAdminSendListReq);
        if (result.isSuccess() && result.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            List<HashMap> records = JSONObject.parseArray(jsonObject.getString("records"), HashMap.class);
            if ((CollectionUtils.isNotEmpty(records)) && records.size() > 0) {
                // 设置数据内部属性
                model.addAttribute("detail", records.get(0));
            }
        }
        return "transaction/send-red-packet-detail";
    }

    @Override
    public String receiveDetail(String id, Model model) {

        AdminReceiveListReq adminReceiveListReq = new AdminReceiveListReq();
        adminReceiveListReq.setRedPacketReceiveId(id);
        FeignAdminReceiveListReq feignAdminSendListReq = getFeignAdminReceiveListReq(adminReceiveListReq);
        BaseResult result = adminReceiveList(feignAdminSendListReq);
        if (result.isSuccess() && result.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            List<HashMap> records = JSONObject.parseArray(jsonObject.getString("records"), HashMap.class);
            if ((CollectionUtils.isNotEmpty(records)) && records.size() > 0) {
                // 设置数据内部属性
                HashMap hashMap = records.get(0);
                hashMap.putIfAbsent("type", -1);
                hashMap.putIfAbsent("senderName", "");
                hashMap.putIfAbsent("receiverName", "");
                hashMap.putIfAbsent("coinName", "");
                model.addAttribute("detail", hashMap);
            }
        }
        return "transaction/receive-red-packet-detail";
    }

    @Override
    public BaseResult sendDownloadLock(AdminSendListReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_REDPACKETS_GIVE_LOCK.concat(userId);
        String requestId = session.getId();
        boolean downloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!downloadLock) {
            String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
            if (requestId.equals(lockId)) {
                log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
            }
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }
        try {

            // 判断条数是否符合
            FeignAdminSendListReq feignAdminSendListReq = getFeignAdminSendListReq(req);
            BaseResult countResult = redPacketFeign.countAdminSendList(feignAdminSendListReq);
            int total = Integer.parseInt(countResult.getData().toString());
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            int maxPageSizeLong = Integer.parseInt(maxPageSize);
            if (total > maxPageSizeLong) {
                if (downloadLock) {
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                }
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if (downloadLock) {
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            }
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }
        return BaseResult.success(true);
    }

    @Override
    public BaseResult receiveDownloadLock(AdminReceiveListReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_REDPACKETS_GET_LOCK.concat(userId);
        String requestId = session.getId();
        boolean downloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!downloadLock) {
            String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
            if (requestId.equals(lockId)) {
                log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
            }
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }

        try {
            // 判断条数是否符合
            final FeignAdminReceiveListReq feignAdminReceiveListReq = getFeignAdminReceiveListReq(req);
            feignAdminReceiveListReq.setKHShowAccount(userService.isShowAccount());
            BaseResult countResult = redPacketFeign.countAdminReceiveList(feignAdminReceiveListReq);
            int total = Integer.parseInt(countResult.getData().toString());
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            int maxPageSizeLong = Integer.parseInt(maxPageSize);
            if (total > maxPageSizeLong) {
                if (downloadLock) {
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                }
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if (downloadLock) {
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            }
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }
        return BaseResult.success(true);
    }

    @Override
    public void sendDownloadList(AdminSendListReq req, HttpSession session, HttpServletResponse response) {

        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_REDPACKETS_GIVE_LOCK.concat(userId);
        String requestId = session.getId();
        try {

            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            List<AdminSendListResp> respList = Lists.newArrayList(0);

            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            req.setSize(maxPageSize == null ? ScanPayServiceImpl.EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            FeignAdminSendListReq feignAdminSendListReq = getFeignAdminSendListReq(req);
            BaseResult result = adminSendList(feignAdminSendListReq);
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"), AdminSendListResp.class);

                List<String> stateAry = Arrays.asList("未领取完", "已领取完", "未领取且已过期");
                List<String> typeAry = Arrays.asList("个人红包", "群抢红包", "群人均红包");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_DEFAULT);

                respList = respList.stream().peek(o -> {
                    o.setStateStr(stateAry.get(o.getState()));
                    o.setTypeStr(typeAry.get(o.getState()));

                    LocalDateTime createDate = LocalDateTime.ofInstant(o.getCreateTime().toInstant(), ZoneId.systemDefault());
                    LocalDateTime updateDate = LocalDateTime.ofInstant(o.getUpdateTime().toInstant(), ZoneId.systemDefault());
                    String createFormattedDate = createDate.format(formatter);
                    String updateFormattedDate = updateDate.format(formatter);
                    o.setCreateTimeStr(createFormattedDate);
                    o.setUpdateTimeStr(updateFormattedDate);
                }).collect(Collectors.toList());
            }

            // 导出文件
            ExcelUtil.exportExcel(response, respList,
                    getDownloadFileName(req.getStartCreateTime(), req.getEndCreateTime(), RED_PACK_FILENAME), RED_PACK_FILENAME);
        } catch (Exception e) {
            log.error("导出失败", e);
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public void receiveDownloadList(AdminReceiveListReq req, HttpSession session, HttpServletResponse response) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_REDPACKETS_GET_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }
            List<AdminReceiveListResp> respList = Lists.newArrayList(0);
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            req.setSize(maxPageSize == null ? ScanPayServiceImpl.EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            FeignAdminReceiveListReq feignAdminSendListReq = getFeignAdminReceiveListReq(req);
            BaseResult result = adminReceiveList(feignAdminSendListReq);
            if (result.isSuccess() && result.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"), AdminReceiveListResp.class);

                List<String> typeAry = Arrays.asList("个人红包", "群抢红包", "群人均红包");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_DEFAULT);

                respList = respList.stream().peek(o -> {
                    if (null != o.getType()) {
                        o.setTypeStr(typeAry.get(o.getType()));
                    }
                    if (o.getType() == 0) {
                        o.setLuckyStr("");
                    } else {
                        o.setLuckyStr(o.getLucky() ? "是" : "否");
                    }

                    LocalDateTime createDate = LocalDateTime.ofInstant(o.getCreateTime().toInstant(), ZoneId.systemDefault());
                    LocalDateTime updateDate = LocalDateTime.ofInstant(o.getUpdateTime().toInstant(), ZoneId.systemDefault());
                    String createFormattedDate = createDate.format(formatter);
                    String updateFormattedDate = updateDate.format(formatter);
                    o.setCreateTimeStr(createFormattedDate);
                    o.setUpdateTimeStr(updateFormattedDate);
                }).collect(Collectors.toList());
            }
            // 导出文件
            ExcelUtil.exportExcel(response, respList,
                    getDownloadFileName(req.getStartCreateTime(), req.getEndCreateTime(), RECEIVE_RED_PACK_FILENAME),
                    RECEIVE_RED_PACK_FILENAME);
        } catch (Exception e) {
            log.error("导出失败", e);
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    /**
     * 获取导出文件名称
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param fileName  文件名
     * @return 文件名
     */
    private static String getDownloadFileName(Date startTime, Date endTime, String fileName) {

        DateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_FORMAT_YYYY_MM_DD);
        if (startTime != null) {
            if (endTime != null) {
                fileName = fileName + dateFormat.format(startTime) + "至" + dateFormat.format(endTime);
            } else {
                fileName = fileName + dateFormat.format(startTime) + "至" + dateFormat.format(new Date());
            }
        } else {
            if (endTime != null) {
                fileName = fileName + "至" + dateFormat.format(endTime);
            } else {
                fileName = fileName + dateFormat.format(new Date());
            }
        }
        return fileName;
    }
}
