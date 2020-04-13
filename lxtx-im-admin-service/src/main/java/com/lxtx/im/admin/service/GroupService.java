package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */
public interface GroupService {

    /**
     * 群组分页查询
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-11 0011
     */
    BaseResult listPage(GroupListPageReq req);

    BaseResult synchronizeYunxinGroup();

    /**
     * 修改群私聊权限
     *
     * @param groupAlterInformationReq
     * @return
     */
    BaseResult updateInformationFlag(GroupAlterInformationReq groupAlterInformationReq);

    /**
     * 解散群
     *
     * @param groupDisbandReq
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-12-04 0004
     */
    BaseResult disband(GroupDisbandReq groupDisbandReq);

    BaseResult groupActiveList(GroupActiveListReq groupActiveListReq);

    /**
     * 获取群名称
     *
     * @param groupId
     * @return
     */
    String getGroupName(String groupId);

    BaseResult gameListPage(GroupGameListPageReq req);

    BaseResult gameStop(GroupGameStopReq req);

    BaseResult gameCancel(GroupGameCancelReq req);

    /**
     * 功能描述: 系统自动生成头像一键替换
     *
     * @param: req
     * @return:
     * @author: Czh
     * @date: 2019-05-17 16:39
     */
    BaseResult replaceGroupIoc(ReplaceGroupIocReq req);

    void downloadList(HttpServletResponse response, GroupListPageReq groupListPageReq);
}
