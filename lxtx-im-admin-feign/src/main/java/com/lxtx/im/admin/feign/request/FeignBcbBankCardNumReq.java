package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * @author Lin hj
 * @title: BcbBankCardNumReq
 * @projectName git_work
 * @description: TODO
 * @date 2019/6/209:36
 */
@Data
public class FeignBcbBankCardNumReq extends  BasePageReq {

    /**
     * 银行卡状态
     */
    private Integer  status;

    /**
     * 银行卡
     */
    private String cardNo;

}
