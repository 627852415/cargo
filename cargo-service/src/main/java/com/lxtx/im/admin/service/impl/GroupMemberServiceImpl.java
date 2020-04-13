package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.GroupFeign;
import com.lxtx.im.admin.feign.feign.GroupMemberFeign;
import com.lxtx.im.admin.feign.feign.YunxinFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.GroupMemberService;
import com.lxtx.im.admin.service.request.GroupListPageReq;
import com.lxtx.im.admin.service.response.Group;
import com.lxtx.im.admin.service.response.GroupMember;
import com.lxtx.im.admin.service.response.GroupMemberListResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */
@Slf4j
@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Autowired
    private GroupMemberFeign groupMemberFeign;

    @Autowired
    private YunxinFeign yunxinFeign;

    @Autowired
    private GroupFeign groupFeign;


    @Override
    public BaseResult listPage(GroupListPageReq req) {
        FeignGroupListPageReq feignGroupListPageReq = new FeignGroupListPageReq();
        BeanUtils.copyProperties(req, feignGroupListPageReq);
        return groupMemberFeign.listPage(feignGroupListPageReq);
    }

    @Override
    @Async
    public void syncYXgroup(Group g) {

        try {

            FeignGroupListPageReq feignGroupListPageReq = new FeignGroupListPageReq();
            String groupId = g.getGroupId();
            feignGroupListPageReq.setGroupId(groupId);
            BaseResult baseResult = groupMemberFeign.getHostByGroupId(feignGroupListPageReq);
            GroupMember gmember = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()), GroupMember.class);


            if (baseResult.isSuccess() && CollectionUtils.isEmpty((Map)baseResult.getData())) {
                log.info("************IM群组 群号：{} 没有群主 ************",groupId);
                return;
            }

            String yxGroupId = g.getYxGroupId();
            String ower = gmember.getAccount();
            String name = g.getName();
            String oldyxGroupId = yxGroupId;

            FeignYunXinCreateGroupReq groupReq = new FeignYunXinCreateGroupReq();
            groupReq.setTname(name);
            groupReq.setOwner(ower);
            groupReq.setMsg("加群");
            groupReq.setMembers("[]");
            groupReq.setJoinmode(0);
            groupReq.setMagree(0);
            BaseResult groupResult = yunxinFeign.createGroup(groupReq);

            if (!groupResult.isSuccess()) {
                log.info("************IM群组  创群失败  {} ************",JSONObject.toJSONString(groupResult));
                return;
            }

            Map<String,Object> data = (HashMap)groupResult.getData();
            yxGroupId = (String)data.get("tid");
            int code = (int)data.get("code");
            if (200 == code) {
                log.info("************IM群组 群主ID：{} 成功创群, 网易云信群号:{} ************",ower,yxGroupId);
            } else {
                log.error("************IM群组  创群失败  {} ************",JSONObject.toJSONString(data));
            }

            FeignGroupReq feignGroupReq = new FeignGroupReq();
            feignGroupReq.setGroupId(g.getGroupId());
            feignGroupReq.setYxGroupId(yxGroupId);

            BaseResult result = groupFeign.updateYxGroupId(feignGroupReq);
            if (result.isSuccess()) {
                log.info("************IM群组 更新网易云信YXGroupId成功 ************");
            }

            FeignGroupListPageReq req = new FeignGroupListPageReq();
            req.setGroupId(g.getGroupId());
            BaseResult groupMemberResult = groupMemberFeign.listByGroupId(req);
            GroupMemberListResp groupMemberList = JSONObject.parseObject(JSONArray.toJSONString(groupMemberResult.getData()), GroupMemberListResp.class);
            List<GroupMember> groupMembersList = groupMemberList.getList();

            log.info(" ************** IM群组 网易云信群号：{} 入群开始 *****************",yxGroupId);

            for (GroupMember gm :groupMembersList ) {

                try {
                    FeignYunXinAddGroupMemberReq memberReq = new FeignYunXinAddGroupMemberReq();
                    memberReq.setTid(yxGroupId);
                    memberReq.setOwner(ower);
                    JSONArray array = new JSONArray();
                    array.add(gm.getAccount());
                    memberReq.setMembers(array.toJSONString());
                    memberReq.setMagree(0);
                    memberReq.setMsg("入群");
                    BaseResult memberResult = yunxinFeign.addGroupMember(memberReq);
                    if (!memberResult.isSuccess()) {
                        log.error("************ IM群组 入群失败: {} ************",JSONObject.toJSONString(memberResult));
                        continue;
                    }


                    Map<String,Object> memberData = (HashMap)memberResult.getData();
                    int memberCode = (int)memberData.get("code");
                    if (memberCode==200) {
                        log.info("************ IM群组 帐号: {} 入群成功 ************",gm.getAccount());
                    } else {
                        log.error("************IM群组 入群失败: {} ************",JSONObject.toJSONString(memberData));
                    }

                } catch (Exception e) {
                    log.error("IM群组 成员入群失败",e);
                }
            }
            log.info("************ IM群组 入群结束 ************");

            log.info("************ IM群组 网易云信群号：{} 解散群开始  ************",oldyxGroupId);
            FeignYunXinDisbandGroupReq disbandGroupReq = new FeignYunXinDisbandGroupReq();
            disbandGroupReq.setOwner(ower);
            disbandGroupReq.setTid(oldyxGroupId);
            BaseResult disbandGroupResult = yunxinFeign.disbandGroup(disbandGroupReq);
            if (disbandGroupResult.isSuccess()) {
                log.info("************ IM群组 网易云信群号：{} 解散群成功  ************",oldyxGroupId);
            } else {
                log.error("************ IM群组 网易云信群号：{} 解散群失败 原因: {}  ************",oldyxGroupId,JSONObject.toJSONString(disbandGroupResult));
            }
            log.info("************ IM群组 解散群结束  ************");

        } catch (Exception e) {
            log.error("同步IM群组异常",e);
        }
    }

    @Override
    public BaseResult  initGmId(){
        return groupMemberFeign.initGmId();
    }

    @Override
    public BaseResult yxGroupMemberBotherSyn() {
        return groupMemberFeign.yxGroupMemberBotherSyn();
    }
}
