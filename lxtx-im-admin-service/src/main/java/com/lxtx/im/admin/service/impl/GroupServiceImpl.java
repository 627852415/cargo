package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.GameTaskFeign;
import com.lxtx.im.admin.feign.feign.GroupFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.GroupMemberService;
import com.lxtx.im.admin.service.GroupService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    private static final String  GROUP_LIST_FILENAME = "群列表";

    @Resource
    private GroupFeign groupFeign;

    @Autowired
    private GameTaskFeign gameTaskFeign;

    @Autowired
    private GroupMemberService groupMemberService;

    @Override
    public BaseResult listPage(GroupListPageReq req) {
        FeignGroupListPageReq feignGroupListPageReq = new FeignGroupListPageReq();
        BeanUtils.copyProperties(req, feignGroupListPageReq);
        return groupFeign.listPage(feignGroupListPageReq);
    }

    @Override
    public BaseResult synchronizeYunxinGroup() {

        long startTime = System.currentTimeMillis();

        log.info("************IM群组 同步网易云信开始************");

        BaseResult allGroupResult = groupFeign.listAllGroup();
        GroupListResp groupList = JSONObject.parseObject(JSONArray.toJSONString(allGroupResult.getData()), GroupListResp.class);
        List<Group> groupDetail = groupList.getList();

//        List<Group> groupDetail = new ArrayList<>();
//        Group group = new Group();
//        group.setName("ttttest群");
//        group.setGroupId("1066887962622500811");
//        group.setYxGroupId("1499914277");
//        groupDetail.add(group);
//
//        Group group1 = new Group();
//        group1.setName("ttttest群");
//        group1.setGroupId("1066943917297033218");
//        group1.setYxGroupId("1499794898");
//        groupDetail.add(group1);
//
//        Group group2 = new Group();
//        group2.setName("ttttest群");
//        group2.setGroupId("1066944076156297217");
//        group2.setYxGroupId("1499651614");
//        groupDetail.add(group2);
//
//        Group group3 = new Group();
//        group3.setName("ttttest群");
//        group3.setGroupId("1066944081663418370");
//        group3.setYxGroupId("1499651617");
//        groupDetail.add(group3);
//
//        Group group4 = new Group();
//        group4.setName("ttttest群");
//        group4.setGroupId("1066944087141179394");
//        group4.setYxGroupId("1499720031");
//        groupDetail.add(group4);

        try {
            int i = 0;
            for (Group g : groupDetail) {

                i++;
                if (i % 50 == 0) {
                    log.info("************ IM群组 同步网易云信 休息10秒 ************");
                    Thread.sleep(10000);
                }

                groupMemberService.syncYXgroup(g);

            }
        } catch (Exception e) {
            log.error("************ IM群主同步异常 ************ ", e.getMessage());
        }

        log.info("************ IM群组 同步网易云信 结束 ************");
        log.info("************ 程序运行时间：" + (System.currentTimeMillis() - startTime) + "ms  ************");
        return BaseResult.success();
    }

    @Override
    public BaseResult updateInformationFlag(GroupAlterInformationReq groupAlterInformationReq) {
        FeignGroupAlterInformationReq feignGroupAlterInformationReq = new FeignGroupAlterInformationReq();
        BeanUtils.copyProperties(groupAlterInformationReq, feignGroupAlterInformationReq);
        return BaseResult.success(groupFeign.updateInformationFlag(feignGroupAlterInformationReq));
    }

    @Override
    public BaseResult disband(GroupDisbandReq groupDisbandReq) {
        FeignGroupDisbandReq feignGroupDisbandReq = new FeignGroupDisbandReq();
        BeanUtils.copyProperties(groupDisbandReq, feignGroupDisbandReq);
        return groupFeign.disband(feignGroupDisbandReq);
    }

    @Override
    public BaseResult groupActiveList(GroupActiveListReq groupActiveListReq) {
        FeignGroupActiveListReq feignGroupActiveListReq = new FeignGroupActiveListReq();
        BeanUtils.copyProperties(groupActiveListReq, feignGroupActiveListReq);
        return groupFeign.groupActiveList(feignGroupActiveListReq);
    }

    /**
     * 获取群名称
     *
     * @param groupId
     * @return
     */
    @Override
    public String getGroupName(String groupId) {
        FeignGroupReq feignGroupReq = new FeignGroupReq();
        feignGroupReq.setGroupId(groupId);
        BaseResult result = groupFeign.getGroupName(feignGroupReq);
        if (result.isSuccess() && result.getData() != null) {
            Group group = JSON.parseObject(JSON.toJSONString(result.getData()), new TypeReference<Group>() {
            });
            return group.getName();
        }
        return null;
    }

    @Override
    public BaseResult gameListPage(GroupGameListPageReq req) {
        //群查询条件
        String groupId = req.getGroupId();
        String founder = req.getFounder();
        String name = req.getName();

        //返回参数
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);

        //游戏管理列表以t_wallet_game_task表为主，
        //如果群查询条件不为空，先查群id集合，带到wallet查询
        List<String> groupIds = new ArrayList<>();
        if (StringUtils.isNotBlank(groupId) || StringUtils.isNotBlank(founder) || StringUtils.isNotBlank(name)) {
            FeignGroupListReq feignGroupListReq = new FeignGroupListReq();
            BeanUtils.copyProperties(req, feignGroupListReq);
            BaseResult coreResult = groupFeign.list(feignGroupListReq);
            if (coreResult.isSuccess() && coreResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                GroupListResp groupListResp = JSONObject.parseObject(coreJsonResult, GroupListResp.class);
                List<Group> groupList = groupListResp.getList();
                if (CollectionUtils.isEmpty(groupList)) {
                    return BaseResult.success();
                }
                if (CollectionUtils.isNotEmpty(groupList)) {
                    for (Group group : groupList) {
                        groupIds.add(group.getGroupId());
                    }
                }
            }
        }

        FeignGroupGameListPageReq feignReq = new FeignGroupGameListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        //将群id集合带到钱包查询
        if (CollectionUtils.isNotEmpty(groupIds)) {
            feignReq.setGroupIds(groupIds);
        }
        BaseResult walletResult = gameTaskFeign.listPage(feignReq);
        if (walletResult.isSuccess() && walletResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) walletResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap);
            GroupGameListPageResp groupGameListPageResp = JSONObject.parseObject(jsonResult, GroupGameListPageResp.class);
            List<GroupGameResp> gameList = groupGameListPageResp.getRecords();
            //群id集合
            List<String> groupIdList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(gameList)) {
                for (GroupGameResp gameResp : gameList) {
                    groupIdList.add(gameResp.getGroupId());
                }

                //调用imcore接口查询群资料,组装群名称、群主名称等信息
                if (CollectionUtils.isNotEmpty(groupIdList)) {
                    FeignGroupListReq feignGroupListReq = new FeignGroupListReq();
                    feignGroupListReq.setGroupIds(groupIdList);
                    BaseResult coreResult = groupFeign.list(feignGroupListReq);
                    if (coreResult.isSuccess() && coreResult.getData() != null) {
                        Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
                        String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                        GroupListResp groupListResp = JSONObject.parseObject(coreJsonResult, GroupListResp.class);
                        List<Group> groupList = groupListResp.getList();
                        if (CollectionUtils.isNotEmpty(groupList)) {
                            Map<String, Group> groupMap = new HashMap<>();
                            // 封装群信息
                            for (Group group : groupList) {
                                groupMap.put(group.getGroupId(), group);
                            }
                            for (GroupGameResp groupGameResp : gameList) {
                                Group group = groupMap.get(groupGameResp.getGroupId());
                                if (group != null) {
                                    groupGameResp.setGroupId(group.getGroupId());
                                    groupGameResp.setFounder(group.getFounder());
                                    groupGameResp.setName(group.getName());
                                }
                            }
                        }
                    }
                }
            }
            baseResult.setSuccess(true);
            baseResult.setData(groupGameListPageResp);
        }
        return baseResult;
    }

    @Override
    public BaseResult gameStop(GroupGameStopReq req) {
        FeignGroupGameStopReq feignReq = new FeignGroupGameStopReq();
        feignReq.setAccount(req.getAccount());
        feignReq.setRedPacketId(req.getRedPacketId());
        return gameTaskFeign.stopGame(feignReq);
    }

    @Override
    public BaseResult gameCancel(GroupGameCancelReq req) {
        FeignGroupGameCancelReq feignReq = new FeignGroupGameCancelReq();
        feignReq.setCancelerUserId(req.getCancelerUserId());
        feignReq.setGroupId(req.getGroupId());
        feignReq.setTaskId(req.getTaskId());
        return gameTaskFeign.cancelGame(feignReq);
    }

    @Override
    public BaseResult replaceGroupIoc(ReplaceGroupIocReq req) {
        FeignReplaceGroupIocReq feign = new FeignReplaceGroupIocReq();
        BeanUtils.copyProperties(req,feign);
        return groupFeign.replaceGroupIoc(feign);
    }

    @Override
    public void downloadList(HttpServletResponse response, GroupListPageReq groupListPageReq) {
        FeignGroupListPageReq feignGroupListPageReq = new FeignGroupListPageReq();
        BeanUtils.copyProperties(groupListPageReq, feignGroupListPageReq);
        BaseResult baseResult = groupFeign.listDownload(feignGroupListPageReq);

        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            Object list = dataMap.get("list");
            if (list != null) {
                List<GroupExportResp> groupExportRespList = JSONObject.parseArray(JSONArray.toJSONString(list), GroupExportResp.class);
                if (CollectionUtils.isEmpty(groupExportRespList)) {
                    throw LxtxBizException.newException("未查询到可导出数据，请检查搜索条件！");
                }

                //导出的文件名
                ExcelUtil.exportExcel(response, groupExportRespList, GROUP_LIST_FILENAME + "-" + DateUtils.getDate(), GROUP_LIST_FILENAME);
            }
        }

    }
}
