package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * @author Czh
 * Date: 2020/1/20 3:46 下午
 */
@Data
public class FeignQueryUserIdByImUserReq {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 国际区号
     */
    private String countryCode;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * wallet用户ID
     */
    private String userId;
}