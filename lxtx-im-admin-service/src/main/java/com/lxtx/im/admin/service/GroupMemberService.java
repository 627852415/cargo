package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.GroupListPageReq;
import com.lxtx.im.admin.service.response.Group;

/**
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */
public interface GroupMemberService {

    /**
     * 群成员分页查询
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-11 0011
     */
    BaseResult listPage(GroupListPageReq req);

    void syncYXgroup(Group g);

    BaseResult  initGmId();

    BaseResult yxGroupMemberBotherSyn();

}
