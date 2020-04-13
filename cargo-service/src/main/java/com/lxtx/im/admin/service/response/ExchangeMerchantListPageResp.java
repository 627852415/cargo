package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: liyunhua
 * @Date: 2019/4/22
 */
@Getter
@Setter
public class ExchangeMerchantListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<ExchangeMerchantResp> records;
}
