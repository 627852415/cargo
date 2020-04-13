package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
* @description:  商家列表
* @author:   CXM
* @create:   2019-03-11 16:30
*/
@Getter
@Setter
public class FeignPayMerchantListPageReq extends BasePageReq {
    /**
     * im账号集合
     *
     */
    private List<String> accountList;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态
     */
    private Integer certificateStatus;
    /**
     * 商家状态
     */
    private Integer status;
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isShowAccount;

}
