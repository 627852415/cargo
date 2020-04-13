package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.dao.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: liyunhua
 * @Date: 2019/2/18
 */
@Getter
@Setter
public class BankcardResp extends BaseModel {

    private String id;

    /**
     * 钱包用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 银行卡持有人姓名
     */
    private String realname;

    /**
     * 银行卡号
     */
    private String bankcardAccount;

    /**
     * 银行卡主行
     */
    private String bank;

    /**
     * 银行卡支行
     */
    private String subbranch;

    /**
     * 是否默认银行卡
     */
    private Integer defaultFlag;

    private String coinsName;
    
}
