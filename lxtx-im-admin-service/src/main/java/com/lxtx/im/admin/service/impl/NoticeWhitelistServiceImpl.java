package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletNoticeWhiteListFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.NoticeWhitelistService;
import com.lxtx.im.admin.service.request.NoticeWhiteListDeleteReq;
import com.lxtx.im.admin.service.request.NoticeWhiteListListReq;
import com.lxtx.im.admin.service.request.NoticeWhiteListSaveReq;
import com.lxtx.im.admin.service.response.BasePageResp;
import com.lxtx.im.admin.service.response.NoticeCommandResp;
import com.lxtx.im.admin.service.response.NoticeWhiteListResp;
import com.lxtx.im.admin.service.response.WalletUserResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
public class NoticeWhitelistServiceImpl implements NoticeWhitelistService {

    @Autowired
    private WalletNoticeWhiteListFeign walletNoticeWhiteListFeign;
    @Autowired
    private WalletUserFeign walletUserFeign;
    @Autowired
    private UserFeign userFeign;

    @Override
    public BaseResult listPage(NoticeWhiteListListReq req) {
        FeignNoticeWhiteListListReq request = new FeignNoticeWhiteListListReq();
        BeanUtils.copyProperties(req,request);
        BaseResult noticeResult = walletNoticeWhiteListFeign.listPage(request);

        BasePageResp<NoticeCommandResp> resp = new BasePageResp<>();
        if (!noticeResult.isSuccessAndDataNotNull()) {
            return BaseResult.success(resp);
        }

        BasePageResp<NoticeWhiteListResp> noticeResp = JSONObject.parseObject(JSONObject.toJSONString(noticeResult.getData()), new TypeReference<BasePageResp<NoticeWhiteListResp>>() {
        });

        if(CollectionUtils.isEmpty(noticeResp.getRecords())){
            return BaseResult.success();
        }
        for(NoticeWhiteListResp noticeWhiteListResp:noticeResp.getRecords()){
            String userId = noticeWhiteListResp.getUserId();

            if (StringUtils.isNotBlank(userId)) {
                FeignWalletUserInfoReq walletUserInfoReq = new FeignWalletUserInfoReq();
                walletUserInfoReq.setUserId(userId);
                BaseResult baseResult = walletUserFeign.queryWalletUserById(walletUserInfoReq);
                if (baseResult.isSuccess()) {
                    String walletUserJson = JSONObject.toJSONString(baseResult.getData());
                    WalletUserResp walletUserResp = JSONObject.parseObject(walletUserJson, WalletUserResp.class);
                    noticeWhiteListResp.setTelephone(walletUserResp.getTelephone());
                    noticeWhiteListResp.setCountryCode(walletUserResp.getCountryCode());
                    noticeWhiteListResp.setName(walletUserResp.getName());
                }
            }
        }
        return BaseResult.success(noticeResp);
    }

    @Override
    public BaseResult save(NoticeWhiteListSaveReq req) {
        FeignNoticeWhiteListSaveReq request = new FeignNoticeWhiteListSaveReq();
        BeanUtils.copyProperties(req,request);
        return walletNoticeWhiteListFeign.save(request);
    }

    @Override
    public BaseResult delete(NoticeWhiteListDeleteReq req) {
        FeignNoticeWhiteListDeleteReq request = new FeignNoticeWhiteListDeleteReq();
        BeanUtils.copyProperties(req,request);
        return walletNoticeWhiteListFeign.delete(request);
    }

}
