package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 更改用户状态请求参数
 *
 * @author CaiRH
 * @since 2018-09-11
 */
@Setter
@Getter
public class FeignTurnUserStatusReq {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 是否禁用
     */
    private Boolean forbiddenFlag;

}