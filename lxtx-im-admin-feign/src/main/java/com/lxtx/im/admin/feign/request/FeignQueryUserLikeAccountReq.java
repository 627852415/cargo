package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  模糊查询用户
* @author:   CXM
* @create:   2019-03-11 18:15
*/
@Getter
@Setter
public class FeignQueryUserLikeAccountReq {
    /**
     * 账号
     */
    private String account;

}
