package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    /**
     * 获取用户列表
     *
     * @param req
     * @return
     */
    BaseResult listPage(UserListPageReq req);

    /**
     *
     * @Description 获取导出文件锁
     * @param req
     * @param session
     * @return
     */
    BaseResult downloadLock(UserListPageReq req, HttpSession session);

    /**
     *
     * @Description 导出文件
     * @param req
     * @param session
     * @param response
     */
    void downloadList(UserListPageReq req, HttpSession session, HttpServletResponse response);


    /**
     * 停用或禁用账号
     *
     * @param req
     * @return
     */
    BaseResult operateState(UserStateOperateReq req);

    /**
     * 重置密码
     *
     * @param req
     * @return
     */
    BaseResult resetPsd(UserResetPsdReq req);

    /**
     * 修改账号信息
     *
     * @param req
     * @return
     */
    BaseResult modify(UserModifyReq req);

    /**
     * 获取最新的各个国家手机区号列表
     *
     * @return
     */
    BaseResult getGlobalCodeList();

    /**
     * IM用户同步到网易云信
     *
     * @return
     */
    BaseResult synchronizeYunxinUser(UserYXSyncReq req);

    public List<String> getUserAccountByUserInfo(UserInfoReq req);

    BaseResult initAgoraUid();

    BaseResult initUserUid();

    /**
     * 判断是否是演示账号
     * @return
     */
    boolean isShowAccount();

    BaseResult kickUserOffline(UserReq userReq);

}
