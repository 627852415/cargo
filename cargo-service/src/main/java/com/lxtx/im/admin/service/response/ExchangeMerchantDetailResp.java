package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: liyunhua
 * @Date: 2019/4/23
 */
@Getter
@Setter
public class ExchangeMerchantDetailResp implements Serializable {

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
     * 邀请码
     */
    private String inviteCode;

    /**
     * 保证金档位
     */
    private String depositDesc;

    /**
     * 商家波动汇率 e.g. 0.05（百分之五）
     * 配合前端显示，在这里需要乘以100
     */
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal merchantWaveRate;

    public BigDecimal getMerchantWaveRate() {
        if(null!=merchantWaveRate){
            return NumberUtils.multiply(merchantWaveRate,BigDecimal.valueOf(100));
        }
        return merchantWaveRate;
    }
}
