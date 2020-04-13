package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 解绑银行卡
 *
 * @Author: liyunhua
 * @Date: 2019/2/19
 */
@Getter
@Setter
public class FeignOtcBindBankcardUpdateReq {

    /**
     * 银行卡ID
     */
    private String bankcardId;

    /**
     * 设置默认银行卡【1：是】
     */
    private Integer defaultFlag;

    /**
     * 解除绑定
     */
    private Boolean unbind;

    /**
     * 平台用户账号
     */
    private String userAccount;
}