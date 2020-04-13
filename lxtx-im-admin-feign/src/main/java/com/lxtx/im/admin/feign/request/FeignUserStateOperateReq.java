package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 启用或禁用账号
 *
 * @author tangdy
 * @since 2018-08-27
 */
@Getter
@Setter
public class FeignUserStateOperateReq{
    /**
     *  账号,主键
     */
    private String account;


    /**
     * 账号状态，0:启用，1：禁用，2：冻结
     */
    private String state;

}
