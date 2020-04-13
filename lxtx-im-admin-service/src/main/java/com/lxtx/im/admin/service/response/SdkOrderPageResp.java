package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * 币种列表分页
 *
 * @author CaiRH
 * @since 2018-08-29
 **/
@Getter
@Setter
public class SdkOrderPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<SdkGameOrderResp> records;
}
