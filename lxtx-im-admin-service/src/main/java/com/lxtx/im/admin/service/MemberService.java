package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.MemberReq;
import com.lxtx.im.admin.service.request.TurnUserStatusReq;

import javax.servlet.http.HttpSession;

/**
 * @Author liyunhua
 * @Date 2018-08-30 0030
 */
public interface MemberService {


    BaseResult list(MemberReq memberReq, HttpSession session);

    /**
     * 更改用户状态（禁用/开启）
     *
     * @param turnUserStatusReq
     * @return
     */
    BaseResult turnUserStatus(TurnUserStatusReq turnUserStatusReq);

    /**
     * 同步IM用户-钱包用户
     *
     * @return
     */
    BaseResult synchronizeUser();
}
