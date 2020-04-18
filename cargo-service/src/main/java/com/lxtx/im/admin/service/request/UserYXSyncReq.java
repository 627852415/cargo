package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 网易云信用户同步请求类
 *
 * @Author zkj
 * @Date 2018-11-21
 */
@Getter
@Setter
public class UserYXSyncReq extends BasePageReq {

    /**
     * 帐号
     */
    private String account;

    /**
     * 是否强制同步  no 否  yes 是
     */
    private String syncType;

    /**
     * 同步方式   part 部分用户  all 所有用户
     */
    private String userType;

}
