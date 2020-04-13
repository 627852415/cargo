package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.BankcardFeign;
import com.lxtx.im.admin.feign.feign.LegalCoinFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.BankcardService;
import com.lxtx.im.admin.service.request.BankcardListPageReq;
import com.lxtx.im.admin.service.request.OtcBindBankcardNewReq;
import com.lxtx.im.admin.service.request.OtcBindBankcardUpdateReq;
import com.lxtx.im.admin.service.response.BankcardListPageResp;
import com.lxtx.im.admin.service.response.BankcardResp;
import com.lxtx.im.admin.service.response.LegalCoinResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行卡管理
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Service
public class BankcardServiceImpl implements BankcardService {

    @Autowired
    private BankcardFeign bankcardFeign;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private LegalCoinFeign legalCoinFeign;

    @Override
    public BaseResult listPage(BankcardListPageReq req) {
        //平台用户查询条件
        String account = req.getAccount();
        String name = req.getName();
        String telephone = req.getTelephone();
        //钱包查询条件
        String userId = req.getUserId();
        String bankcardAccount = req.getBankcardAccount();
        String realname = req.getRealname();

        //返回参数
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);

        //银行卡管理列表以wallet银行卡表为主，
        //如果平台用户查询条件不为空，先查平台用户id集合，带到wallet查询
        List<String> accountList = new ArrayList<>();
        if (StringUtils.isNotBlank(account) || StringUtils.isNotBlank(name) || StringUtils.isNotBlank(telephone)) {
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            BeanUtils.copyProperties(req, feignMemberListReq);
            BaseResult coreResult = userFeign.list(feignMemberListReq);
            if (coreResult.isSuccess() && coreResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                List<UserDetailResp> userDetailResps = userListResp.getList();
                if (CollectionUtils.isEmpty(userDetailResps)) {
                    return BaseResult.success();
                }
                if (CollectionUtils.isNotEmpty(userDetailResps)) {
                    for (UserDetailResp user : userDetailResps) {
                        accountList.add(user.getAccount());
                    }
                }
            }
        }


        FeignBankcardListPageReq feignReq = new FeignBankcardListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        //平台条件能查询到数据，将平台用户id集合带到钱包查询
        if (CollectionUtils.isNotEmpty(accountList)) {
            feignReq.setUserIds(accountList);
        }
        BaseResult walletResult = bankcardFeign.listPage(feignReq);
        if (walletResult.isSuccess() && walletResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) walletResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap);
            BankcardListPageResp bankcardListPageResp = JSONObject.parseObject(jsonResult, BankcardListPageResp.class);
            List<BankcardResp> bankcardRespList = bankcardListPageResp.getRecords();
            //平台用户id集合
            List<String> accountIds = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(bankcardRespList)) {
                for (BankcardResp bankcardResp : bankcardRespList) {
                    accountIds.add(bankcardResp.getAccount());
                }

                //调用imcore接口查询im用户资料,组装IM用户名、手机号码等信息
                if (CollectionUtils.isNotEmpty(accountIds)) {
                    FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
                    queryUserListReq.setIds(accountIds);
                    BaseResult coreResult = userFeign.queryList(queryUserListReq);
                    if (coreResult.isSuccess() && coreResult.getData() != null) {
                        Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
                        String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                        UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                        List<UserDetailResp> userDetailResps = userListResp.getList();
                        if (CollectionUtils.isNotEmpty(userDetailResps)) {
                            Map<String, UserDetailResp> userDetailRespMap = new HashMap<>();
                            // 封装用户信息
                            for (UserDetailResp userResp : userDetailResps) {
                                userDetailRespMap.put(userResp.getAccount(), userResp);
                            }
                            for (BankcardResp bankcardResp : bankcardRespList) {
                                UserDetailResp userDetailResp = userDetailRespMap.get(bankcardResp.getAccount());
                                if (userDetailResp != null) {
                                    bankcardResp.setTelephone(userDetailResp.getTelephone());
                                    bankcardResp.setAccount(userDetailResp.getAccount());
                                    bankcardResp.setName(userDetailResp.getName());
                                }
                            }
                        }
                    }
                }
            }
            baseResult.setSuccess(true);
            baseResult.setData(bankcardListPageResp);
        }
        return baseResult;
    }

    @Override
    public BaseResult unbind(OtcBindBankcardUpdateReq req) {
        FeignOtcBindBankcardUpdateReq feignReq = new FeignOtcBindBankcardUpdateReq();
        feignReq.setBankcardId(req.getBankcardId());
        feignReq.setUserAccount(req.getUserAccount());
        feignReq.setUnbind(true);
        return bankcardFeign.bindBankcardUpdate(feignReq);
    }

    @Override
    public BaseResult bind(OtcBindBankcardNewReq req) {
        FeignOtcBindBankcardNewReq feignReq = new FeignOtcBindBankcardNewReq();
        BeanUtils.copyProperties(req, feignReq);
        return bankcardFeign.bind(feignReq);
    }

	@Override
	public BaseResult index(Model model) {
		setLegalCoinData(model);
		return null;
	}

    private void setLegalCoinData(Model model) {
        BaseResult legalCoinListResult = legalCoinFeign.list();
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(legalCoinListResult.getData()));
        if (jsonObject != null) {
            List<LegalCoinResp> coinResps = JSONObject.parseArray(jsonObject.getString("list"), LegalCoinResp.class);
            model.addAttribute("coinList", coinResps);
        }
    }
}
