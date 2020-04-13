package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.MessageUtil;
import com.lxtx.framework.common.utils.message.FeignUserDevicePageReq;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.FeignQueryUserListReq;
import com.lxtx.im.admin.feign.request.FeignQueryUsernameReq;
import com.lxtx.im.admin.service.SessionService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.request.UserDevicePageReq;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserDeviceResp;
import com.lxtx.im.admin.service.response.UserListResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description: 在线用户实现类
 * @author: CXM
 * @create: 2018-09-05 16:35
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Resource
    private UserFeign userFeign;

    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(UserDevicePageReq req) throws Exception {
        List<String> accountIds = null;
        if (StringUtils.isNotBlank(req.getAccid())) {
            accountIds = Arrays.asList(req.getAccid());
        }
        FeignUserDevicePageReq feignUserDevicePageReq = new FeignUserDevicePageReq();
        BeanUtils.copyProperties(req, feignUserDevicePageReq);

        if (StringUtils.isNotBlank(req.getName())) {
            FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
            usernameReq.setName(req.getName());
            if(userService.isShowAccount()){
                usernameReq.setCountryCode("KH");
            }

            BaseResult queryByUsername = userFeign.queryByUsername(usernameReq);
            if (!queryByUsername.isSuccessAndDataNotNull()) {
                return BaseResult.success(new Page<>());
            }
            accountIds = (List<String>) ((Map<String, Object>) queryByUsername.getData()).get(BaseResult.LIST);
        }

        if (StringUtils.isBlank(req.getName())) {
            if (userService.isShowAccount()) {
                BaseResult userBaseResult = userFeign.selectKhUser();
                if (!userBaseResult.isSuccessAndDataNotNull()) {
                    return BaseResult.success(new Page<>());
                }
                accountIds = (List<String>) ((Map<String, Object>) userBaseResult.getData()).get(BaseResult.LIST);

            }
        }

        feignUserDevicePageReq.setAccidList(accountIds);
        BaseResult baseResult = MessageUtil.userDevicePage(feignUserDevicePageReq);
        if (!baseResult.isSuccessAndDataNotNull()) {
            return BaseResult.success(new Page<>());
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
        List<UserDeviceResp> userDeviceRespList = JSONObject.parseArray(jsonObject.getString("records"), UserDeviceResp.class);
        userDeviceRespListData(userDeviceRespList);
        jsonObject.put("records", userDeviceRespList);
        baseResult.setData(jsonObject);

        return baseResult;
    }

    private void userDeviceRespListData(List<UserDeviceResp> userDeviceRespList) {
        if (CollectionUtils.isEmpty(userDeviceRespList)) {
            return;
        }
        List<String> userIds = new ArrayList<>();
        for (UserDeviceResp resp : userDeviceRespList) {
            userIds.add(resp.getAccid());

        }

        // 查询符合条件的用户信息并转Map
        Map<String, UserDetailResp> userNameMap = new HashMap<>(userIds.size());
        FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
        queryUserListReq.setIds(userIds);
        BaseResult coreResult = userFeign.queryList(queryUserListReq);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(coreResult.getData()),UserListResp.class);
            List<UserDetailResp> userDetailResps = userListResp.getList();
            if (CollectionUtils.isEmpty(userDetailResps)) {
                return;
            }
            for (UserDetailResp record : userDetailResps) {
                userNameMap.put(record.getAccount(), record);
            }
        }

        // 重装Resp返回用户名称、类型以及状态名称
        for (UserDeviceResp resp : userDeviceRespList) {
            if(!CollectionUtils.isEmpty(userNameMap) && userNameMap.get(resp.getAccid()) != null){
                resp.setUserName(userNameMap.get(resp.getAccid()).getName());
                resp.setUserAvatarUrl(userNameMap.get(resp.getAccid()).getUserAvatarUrl());
            }
        }
    }
}
