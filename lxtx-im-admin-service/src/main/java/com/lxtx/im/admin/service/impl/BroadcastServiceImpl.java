package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.constants.DictConstants;
import com.lxtx.framework.common.utils.MessageUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.MessageFeign;
import com.lxtx.framework.common.utils.message.FeignMessageOption;
import com.lxtx.framework.common.utils.message.FeignMessageReq;
import com.lxtx.framework.common.utils.message.FeignSendMsgReq;
import com.lxtx.im.admin.service.BroadcastService;
import com.lxtx.im.admin.service.Constants.PropertiesContants;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.BroadcastPushReq;
import com.lxtx.im.admin.service.request.RequestHeaderInfo;
import com.lxtx.im.admin.service.request.SendBroadcastSwitchMessageReq;
import com.lxtx.im.admin.service.response.BroadcastSwitchMessageResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CaiRH
 */
@Service
@Slf4j
@DependsOn("propertiesUtil")
public class BroadcastServiceImpl implements BroadcastService {
    @Resource
    private MessageFeign messageFeign;
    @Autowired
    private DictServiceImpl dictService;

    private String detailUrl =  PropertiesUtil.getString(PropertiesContants.BROADCAST_DETAIL_URL);

    @Override
    public void index(ModelMap map) {
        // 获取广播消息发送者列表
        String dictValue = dictService.getDictValue(DictConstants.DICT_DOMAIN_GLOBAL, DictConstants.DICT_KEY_BROADCAST_ACCOUNT_LIST);
        map.addAttribute("userList",JSON.parseArray(dictValue));
    }

    @Override
    public BaseResult push(BroadcastPushReq req) throws Exception {
        FeignMessageReq feign = new FeignMessageReq();
        feign.setAction(Constants.MessageAction.ACTION_1001);
        feign.setSender(req.getSendAccount());
        feign.setTitle(req.getTitle());
        Map<String, String> extraMap = new HashMap<>(1);
        extraMap.put("senderName", req.getSendName());
        feign.setExtra(JSON.toJSONString(extraMap));
        feign.setReceiver(Constants.SYSTEM);
        Map<String, String> contentMap = new HashMap<>(3);
        contentMap.put("key", req.getContent());
        contentMap.put("html", req.getHtml());
        contentMap.put("detailUrl", detailUrl);
        feign.setContent(JSON.toJSONString(contentMap));
        FeignSendMsgReq yunXinSendMsgReq = new FeignSendMsgReq();
        yunXinSendMsgReq.setBody(feign);
        yunXinSendMsgReq.setOpe(0);
        yunXinSendMsgReq.setFrom(req.getSendAccount());
        yunXinSendMsgReq.setTo(Constants.SYSTEM);
        FeignMessageOption messageOption = new FeignMessageOption();

        if(req.getType().equals(1)){
            messageOption.setPersistent(false);
        }else {
            messageOption.setPersistent(true);
        }
        yunXinSendMsgReq.setMessageOption(messageOption);
        return MessageUtil.sendBroadcastMsg(yunXinSendMsgReq);
    }

    @Override
    public BaseResult sendBroadcastOfflineMessage() throws Exception {
        FeignMessageReq feign = new FeignMessageReq();
        feign.setAction(Constants.MessageAction.ACTION_999);
        feign.setSender(Constants.SYSTEM);
        feign.setReceiver(Constants.SYSTEM);
        feign.setContent("{}");

        FeignSendMsgReq yunXinSendMsgReq = new FeignSendMsgReq();
        yunXinSendMsgReq.setBody(feign);
        yunXinSendMsgReq.setOpe(0);
        yunXinSendMsgReq.setFrom(Constants.SYSTEM);
        yunXinSendMsgReq.setTo(Constants.SYSTEM);
        yunXinSendMsgReq.setMessageOption(new FeignMessageOption());
        return MessageUtil.sendBroadcastMsg(yunXinSendMsgReq);
    }


    @Override
    public BaseResult sendBroadcastSwitchMessage(SendBroadcastSwitchMessageReq req) throws Exception {
        int type = req.getType();

        // 获取渠道号版本号
        String channelVersion = null;
        // 特殊国际列表
        String[] countryCodeArr = null;
        switch (type) {
            case 1: {
                // 余额宝：
                channelVersion = dictService.getDictValue(DictConstants.DICT_DOMAIN_YEB, DictConstants.DICT_KEY_NOT_SHOWING_YEB);
                break;
            }
            case 2: {
                // BCB银行
                channelVersion = dictService.getDictValue(DictConstants.DICT_DOMAIN_BCB_BANK, DictConstants.DICT_KEY_NOT_SHOWING_BANK_SERVICE);
                break;
            }
            case 3: {
                // 换汇
                channelVersion = dictService.getDictValue(DictConstants.DICT_DOMAIN_OFFSITE_EXCHANGE, DictConstants.DICT_KEY_NOT_SHOWING_OFFSITE_EXCHANGE);
                break;
            }
            case 4: {
                // 钱包
                channelVersion = dictService.getDictValue(DictConstants.DICT_DOMAIN_GLOBAL, DictConstants.DICT_KEY_NOT_SHOWING_WALLET);
                break;
            }
            case 5: {
                //游戏
                channelVersion = dictService.getDictValue(DictConstants.DICT_DOMAIN_GAME, DictConstants.DICT_KEY_NOT_SHOWING_GAME);
                //查询特殊国家编码
                countryCodeArr = dictService.getDictArrayValue(DictConstants.DICT_DOMAIN_SPECIAL_COUNTRY, DictConstants.DICT_KEY_COUNTRY_CODE_LIST);
                break;
            }
            case 6: {
                // 群多人语音开关
                channelVersion = dictService.getDictValue(DictConstants.DICT_DOMAIN_GLOBAL, DictConstants.DICT_NOT_SHOWING_VOICE_CHAT);
                break;
            }
            default: {
                throw LxtxBizException.newException("未知开关类型");
            }
        }
        List<RequestHeaderInfo> requestHeaderInfos = JSON.parseArray(channelVersion, RequestHeaderInfo.class);
        BroadcastSwitchMessageResp broadcastSwitchMessageResp = new BroadcastSwitchMessageResp(req.getType(), requestHeaderInfos);
        if (countryCodeArr != null) {
            broadcastSwitchMessageResp.setCountryCodeArr(countryCodeArr);
        }

        FeignMessageReq feign = new FeignMessageReq();
        feign.setAction(Constants.MessageAction.ACTION_1000);
        feign.setSender(Constants.SYSTEM);
        feign.setReceiver(Constants.SYSTEM);
        feign.setContent(JSON.toJSONString(broadcastSwitchMessageResp));
        FeignSendMsgReq yunXinSendMsgReq = new FeignSendMsgReq();
        yunXinSendMsgReq.setBody(feign);
        yunXinSendMsgReq.setOpe(0);
        yunXinSendMsgReq.setFrom(Constants.SYSTEM);
        yunXinSendMsgReq.setTo(Constants.SYSTEM);
        yunXinSendMsgReq.setMessageOption(new FeignMessageOption());
        return MessageUtil.sendBroadcastMsg(yunXinSendMsgReq);
    }
}
