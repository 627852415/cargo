package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
* @description:  线下汇换投诉分页
* @author:   CXM
* @create:   2019-04-22 13:48
*/
@Getter
@Setter
public class FeignOffsiteExchangeArbitrationListPageReq extends BasePageReq {

    /**
     * 投诉编号/订单编号关键字
     */
    private String keyword;

    /**
     * 卖家/买家账号
     */
    private List<String> merchantAccount;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    /**
     * 状态【0:未处理、1:处理中、2:已处理】
     */
    private Integer status;
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isShowAccount;
}
