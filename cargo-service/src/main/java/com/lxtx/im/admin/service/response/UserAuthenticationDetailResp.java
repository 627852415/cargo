package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户验证商户详情类
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-22
 **/
@Getter
@Setter
public class UserAuthenticationDetailResp {

    /**
     * Id
     */
    private String id;

    /**
     * 姓氏
     */
    private String lastName;

    /**
     * 名字
     */
    private String firstName;

    /**
     * 性别【0:女 1：男，2：未知】
     */
    @JsonIgnore
    private Integer gender;

    /**
     * 性别
     */
    private String genderName;

    /**
     * 状态【0：正常， 1：禁用】
     */
    private Integer status;

    /**
     * 认证状态【1:未认证、2:待审核、3:已认证、4:已拒绝】
     */
    private Integer certificateStatus;

    /**
     * 国际简码
     */
    private String countryCode;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 证件正面图片地址
     */
    private String certificateFront;

    /**
     * 证件反面图片地址
     */
    private String certificateBack;

    /**
     * 证件签名图片地址
     */
    private String certificateSignature;

    /**
     * 拒绝理由
     */
    private String rejectReason;

}
