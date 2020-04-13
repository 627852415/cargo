package com.lxtx.im.admin.service.request;

import com.lxtx.im.admin.feign.request.BasePageReq;
import lombok.Getter;
import lombok.Setter;

/**
 * 邀请关系管理请求参数
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Setter
@Getter
public class InviteRelationListPageReq extends BasePageReq {

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 手机号码
     */
    private String telephone;


}
