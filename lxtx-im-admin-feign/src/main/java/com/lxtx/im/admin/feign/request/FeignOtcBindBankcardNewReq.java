package com.lxtx.im.admin.feign.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 绑定银行卡
 *
 * @Author: liyunhua
 * @Date: 2019/2/22
 */
@Getter
@Setter
@ToString
public class FeignOtcBindBankcardNewReq {

    /**
     * 银行卡持有人姓名
     */
    private String realname;

    /**
     * 主行
     */
    private String bank;

    /**
     * 支行
     */
    private String subbranch;

    /**
     * 银行卡号
     */
    private String account;

    /**
     * 用户账号
     */
    private String userAccount;
    
    private List<String> coinIds;
}