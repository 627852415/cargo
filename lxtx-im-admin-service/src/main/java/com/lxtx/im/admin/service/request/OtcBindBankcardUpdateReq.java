package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: liyunhua
 * @Date: 2019/2/19
 */
@Getter
@Setter
public class OtcBindBankcardUpdateReq {

    /**
     * 银行卡ID
     */
    private String bankcardId;

    /**
     * 解除绑定
     */
    private Boolean unbind;


    /**
     * 平台用户账号
     */
    private String userAccount;
}