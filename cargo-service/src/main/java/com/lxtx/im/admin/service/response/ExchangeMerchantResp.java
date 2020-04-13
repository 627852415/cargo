package com.lxtx.im.admin.service.response;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.dao.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author: liyunhua
 * @Date: 2019/4/3
 */
@Getter
@Setter
public class ExchangeMerchantResp extends BaseModel {

    private String id;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 用户名
     */
    private String name;

    /**
     * 地区
     */
    private String countryPhoneCode;

    /**
     * 钱包用户ID
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
     * 商家保证金信用表id
     */
    private String merchantDepositId;

    /**
     * 商家波动汇率 e.g. 0.05（百分之五）
     * 配合前端显示，在这里需要乘以100
     */
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
    private BigDecimal merchantWaveRate;

    private String merchantWaveRateStr;

    public String getMerchantWaveRateStr() {
        if(null!=merchantWaveRate){
            BigDecimal result = NumberUtils.multiply(merchantWaveRate, BigDecimal.valueOf(100));
            return result.toPlainString() + "%";
        }
        return "-";
    }

}
