package com.lxtx.im.admin.service.response;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-12-02 18:06
 * @Description
 */
@Data
public class UserDeviceResp {
    private String id;
    /**
     * 用户昵称
     */
    private String  userName;
    /**
     * 用户头像
     */
    private String  userAvatarUrl;
    /**
     * 用户账号
     */
    private String  accid;
    /**
     * 客户端ip
     */
    private String clientIp;
    /**
     * 事件类型1:会话 2:登入 3:登出
     */
    private String eventType;
    /**
     * 客户端类型 AOS、IOS、PC、WINPHONE、WEB、REST
     */
    private String clientType;
    /**
     * 发生时的时间戳
     */
    private Long timestamp;
    /**
     * 当前sdk的版本信息
     */
    private String sdkVersion;
}
