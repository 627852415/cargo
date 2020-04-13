package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author LiuLP
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class FeignUpdateOrderCheckStateReq extends BasePageReq {

    /**
     * 主键
     */
    private String id;

    /**
     * 审核状态
     */
    private Integer checkState;




}
