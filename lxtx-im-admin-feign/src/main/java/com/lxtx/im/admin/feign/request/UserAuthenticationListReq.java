package com.lxtx.im.admin.feign.request;

import lombok.Data;

import java.util.Date;

/**
 * @author PengPai
 * Date: Created in 10:38 2020/1/17
 */
@Data
public class UserAuthenticationListReq {

    /**
     * Id
     */
    private String id;

    /**
     * 钱包用户Id
     */
    private String userId;

    /**
     * 状态【0：正常， 1：禁用】
     */
    private Integer status;

    /**
     * 认证状态【1:未认证、2:待审核、3:已认证、4:已拒绝】
     */
    private Integer certificateStatus;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;
}
