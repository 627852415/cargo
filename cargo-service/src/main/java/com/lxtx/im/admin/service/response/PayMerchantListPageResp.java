package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @description:  商家列表返回
* @author:   CXM
* @create:   2019-03-11 17:09
*/
@Setter
@Getter
public class PayMerchantListPageResp {
    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;
    private List<String> accountList;
    List<PayMerchantListResp> records;
}
