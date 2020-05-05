package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;

/**
 * 持卡人注册
 *
 * @author CaiRH
 * @since 2019-04-26
 */
@Data
public class BcbRegisterWithCardResp {

    private BcbBaseResp status;
    private String userId;

}
