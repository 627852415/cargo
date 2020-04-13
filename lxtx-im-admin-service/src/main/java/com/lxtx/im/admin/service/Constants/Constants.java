package com.lxtx.im.admin.service.Constants;

/**
 * @author tangdy
 */
public interface Constants {

    /**
     * 管理员会话sessionKey
     */
    String SESSION_ADMIN_KEY = "session_admin_key";

    /**
     * 跳转到登陆页面
     */
    String TO_LOGIN = "login";
    /**
     * 默认密码
     */
    String DEFAULT_PSD = "m123456";
    /**
     * 默认发送者
     */
    String SENDER_DEFAULT = "system";
    /**
     * 超级管理员ID
     */
    String SUPER_ADMIN = "1";

    /**
     * 是否首次登录
     */
    int FIRST_LOGIN_FALSE = 0;

    /**
     * 对应平台类型(1: IM)
     */
    Integer USER_TYPE_IM = 1;
    /**
     * 对账时间为空
     */
    String CHECK_TIME_NULL = "100";
    /**
     * 对账币种为空
     */
    String CHECK_COIN_NULL = "101";
    /**
     * 对账信息查询请求
     */
    String CHECK_REQUEST_NULL = "102";

}
