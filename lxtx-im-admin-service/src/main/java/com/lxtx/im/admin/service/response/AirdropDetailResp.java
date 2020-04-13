package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
* @description:  空投详情records返回
* @author:   CXM
* @create:   2018-09-04 17:42
*/
@Getter
@Setter
public class AirdropDetailResp {

    private String id;
    /**
     * 空投id
     */
    private String airdropId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 币种名称
     */
    private String coinId;
    /**
     * 类型【1：注册空投、2：全站空投】
     */
    private Integer type;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 创建时间
     */
    public Date createTime;
}