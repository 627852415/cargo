package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: liyunhua
 * @Date: 2019/3/12
 */
@Getter
@Setter
public class MerchantDetailResp implements Serializable {

    private String id;

    /**
     * 钱包用户id
     */
    private String userId;

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
    private Integer gender;

    /**
     * 性别
     */
    private String genderName;

    /**
     * 商家状态【0：正常， 1：禁用】
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
     * 认证类型【0：身份证， 1：护照】
     */
    private Integer certificateType;

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

    /**
     * 审核时间
     */
    private String verifyTime;

    /**
     * 账单周期
     */
    private String billCycle;

    /**
     * 商家结算的币种手续费
     * @since 2019-04-17
     */
    private List<CoinFee> coinFeeList;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CoinFee implements Serializable {
        private String coinId;
        private String coinName;
        private BigDecimal fee;
    }

}
