package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

/**
 * 登录闪兑
 *
 * @author CaiRH
 */
@Data
public class FastExchangeLoginPojo {
    /**
     * 跳转到OTC的url的token
     */
    private String token;
    /**
     * 跳转到OTC的url
     */
    private String url;

}
