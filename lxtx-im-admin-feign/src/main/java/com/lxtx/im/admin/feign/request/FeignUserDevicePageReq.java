package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * @description: 获取在线用户带分页
 * @author: liboyan
 * @create: 2019-12-02 16:19
 **/
@Setter
@Getter
public class FeignUserDevicePageReq extends BasePageReq{
    /**
     * 用户账号
     */
    private List<String> accidList;

    /**
     * 事件类型1:会话 2:登入 3:登出
     */
    private String eventType;
    /**
     * 客户端类型 AOS、IOS、PC、WINPHONE、WEB、REST
     */
    private String clientType;
}
