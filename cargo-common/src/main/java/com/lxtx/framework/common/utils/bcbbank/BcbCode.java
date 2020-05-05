package com.lxtx.framework.common.utils.bcbbank;

import lombok.Data;

/**
 * BCB银行接口返回状态码
 *
 * @author CaiRH
 * @since 2019-04-26
 */
@Data
public class BcbCode {

    public static final Integer ZERO = 0;
    /**
     * 设置pin密码返回OK状态码
     */
    public static final Integer SET_PIN_CODE_OK = 200000;

}
