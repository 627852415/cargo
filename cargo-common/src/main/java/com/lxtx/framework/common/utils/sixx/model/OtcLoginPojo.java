package com.lxtx.framework.common.utils.sixx.model;

import lombok.Data;

/**
 * 登录OTC
 *
 * @author CaiRH
 */
@Data
public class OtcLoginPojo {
    /**
     * 跳转到OTC的url的token
     */
    private String token;
    /**
     * 跳转到OTC的url
     */
    private String url;

}
