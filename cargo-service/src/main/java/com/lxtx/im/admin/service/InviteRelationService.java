package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;

import javax.servlet.http.HttpServletResponse;

public interface InviteRelationService {

    /**
     * 获取用户列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(InviteRelationListPageReq req);


    BaseResult addRelation(InviteRelationReq inviteRelationReq);


    BaseResult checkInviteRelation(InviteRelationReq inviteRelationReq);

    BaseResult higherList(ViewRelationReq req);

    BaseResult lowerList(ViewRelationPageReq req);

    BaseResult directList(InviteDirectRelationReq req);

    BaseResult eachLevelNumList(ViewRelationReq req);

    void exportExcel(HttpServletResponse response, InviteRelationListPageReq req);

    BaseResult directHigher(InviteRelationListPageReq req);

    BaseResult bindLowerList(InviteRelationListPageReq req);

    BaseResult relationChange(RelationChangeReq relationChangeReq);

}
