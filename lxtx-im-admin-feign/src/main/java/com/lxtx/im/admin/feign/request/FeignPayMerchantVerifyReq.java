package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: liyunhua
 * @Date: 2019/2/19
 */
@Getter
@Setter
@ToString
public class FeignPayMerchantVerifyReq {

    /**
     * ID
     */
    private String id;

    /**
     * 拒绝理由
     */
    private String rejectReason;

//    /**
//     * 账单周期
//     */
//    private String billCycle;

    private String lastName;

    private String firstName;

    private String genderName;

    private String countryCode;

    private String certificateNo;


    /**
     * 审核状态
     */
    private String certificateStatus;

    /**
     * 商家结算的币种手续费
     * @since 2019-04-17
     */
    private List<Object> coinFeeList;

}