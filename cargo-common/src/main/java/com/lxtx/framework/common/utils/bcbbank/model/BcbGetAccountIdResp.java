package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;

/**
 * 根据卡号获取账号
 *
 * @author CaiRH
 * @since 2019-04-26
 */
@Data
public class BcbGetAccountIdResp {

    private BcbBaseResp status;
    private String accountId;

}
