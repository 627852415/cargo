package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 获取在线用户带分页
 * @author: CXM
 * @create: 2018-09-05 16:19
 **/
@Setter
@Getter
public class UserDevicePageReq extends BasePageReq {
    /**
     * 用户账号
     */
    private String accid;

    /**
     * 用户账号
     */
    private String name;


    /**
     * 事件类型1:会话 2:登入 3:登出
     */
    private String eventType;
    /**
     * 客户端类型 AOS、IOS、PC、WINPHONE、WEB、REST
     */
    private String clientType;
}
