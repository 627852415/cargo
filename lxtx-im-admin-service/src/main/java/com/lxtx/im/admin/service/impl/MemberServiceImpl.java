package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.GlobalCodeFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserCoinFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.Constants;
import com.lxtx.im.admin.service.MemberService;
import com.lxtx.im.admin.service.enums.EnumPlatformType;
import com.lxtx.im.admin.service.request.MemberReq;
import com.lxtx.im.admin.service.request.TurnUserStatusReq;
import com.lxtx.im.admin.service.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author liyunhua
 * @Date 2018-08-30 0030
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Resource
    private WalletUserFeign walletUserFeign;

    @Resource
    private UserFeign userFeign;

    @Resource
    private WalletUserCoinFeign walletUserCoinFeign;

    @Resource
    private GlobalCodeFeign globalCodeFeign;

    @Override
    public BaseResult list(MemberReq userReq, HttpSession session) {
        String account = userReq.getAccount();
        if (StringUtils.isNotBlank(account)) {
            FeignQueryUserListReq req = new FeignQueryUserListReq();
            req.setIds(Arrays.asList(account));
            BaseResult result = userFeign.queryList(req);
            if (!result.isSuccessAndDataNotNull()) {
                return BaseResult.success();
            }
            Map<String, Object> coreDataMap = (Map<String, Object>) result.getData();
            String coreJsonResult = JSONArray.toJSONString(coreDataMap);
            UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
            List<UserDetailResp> userDetailResps = userListResp.getList();
            if (CollectionUtils.isEmpty(userDetailResps)) {
                return BaseResult.success();
            }
        }
        FeignUserReq req = new FeignUserReq();
        BeanUtils.copyProperties(userReq, req);
        req.setPlatformUserId(userReq.getAccount());
        BaseResult result = walletUserFeign.list(req);
        if (!result.isSuccessAndDataNotNull()) {
            return BaseResult.success();
        }
        Map<String, Object> dataMap = (Map<String, Object>) result.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        UserPageResp userPageResp = JSONObject.parseObject(jsonResult, UserPageResp.class);
        List<WalletUserResp> userRespList = userPageResp.getRecords();
        if (CollectionUtils.isEmpty(userRespList)) {
            return BaseResult.success();
        }

        List<String> platformUserId = userRespList.parallelStream().map(WalletUserResp::getPlatformUserId).collect(Collectors.toList());
        FeignQueryUserListReq listReq = new FeignQueryUserListReq();
        listReq.setIds(platformUserId);
        BaseResult queryList = userFeign.queryList(listReq);
        if (queryList.isSuccessAndDataNotNull()) {
            Map<String, Object> coreDataMap = (Map<String, Object>) queryList.getData();
            String coreJsonResult = JSONArray.toJSONString(coreDataMap);
            UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
            List<UserDetailResp> userDetailResps = userListResp.getList();
            List<UserDetailResp> detailResps = Optional.ofNullable(userDetailResps).orElse(new ArrayList<>());
            userRespList.parallelStream()
                    .forEach(walletUserResp -> {
                        List<UserDetailResp> details = detailResps.stream().filter(detail -> detail.getAccount().
                                equals(walletUserResp.getPlatformUserId())).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(details)) {
                            UserDetailResp userDetailResp = details.get(0);
                            walletUserResp.setCountryCode(userDetailResp.getCountryCode());
                            walletUserResp.setName(userDetailResp.getName());
                            walletUserResp.setTelephone(userDetailResp.getTelephone());
                        }
                    });
        }
        return BaseResult.success(userPageResp);

    }


    @Override
    public BaseResult turnUserStatus(TurnUserStatusReq turnUserStatusReq) {
        FeignTurnUserStatusReq req = new FeignTurnUserStatusReq();
        BeanUtils.copyProperties(turnUserStatusReq, req);
        return walletUserFeign.turnUserStatus(req);
    }

    @Override
    public BaseResult synchronizeUser() {
        // 查询IM激活的用户
        BaseResult coreResult = userFeign.queryActive();

        if (coreResult.isSuccess() && coreResult.getData() != null) {
            UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(coreResult.getData()), UserListResp.class);
            List<UserDetailResp> userDetailResps = userListResp.getList();
            if (CollectionUtils.isEmpty(userDetailResps)) {
                return BaseResult.success();
            }

            // 注册钱包用户
            for (UserDetailResp resp : userDetailResps) {
                try {
                    FeignWallletRegisterUserReq userReq = new FeignWallletRegisterUserReq();
                    userReq.setUserId(resp.getAccount());
                    userReq.setType(1);
                    BaseResult walletUser = walletUserFeign.registerWalletUser(userReq);

                    // 获取钱包用户ID
                    Map<String, String> strMap = (Map<String, String>) walletUser.getData();
                    String userId = strMap.get("userId");

                    // 模拟用户查询钱包余额
                    FeignQueryBalanceReq feignQueryBalanceReq = new FeignQueryBalanceReq();
                    feignQueryBalanceReq.setUserId(userId);
                    walletUserCoinFeign.queryBalance(feignQueryBalanceReq);
                } catch (Exception e) {
                    log.info("同步IM钱包用户异常[{}]，IM用户ID[{}]", e.getMessage(), resp.getAccount());
                }
            }

        }
        return BaseResult.success();
    }
}
