package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @description:  资金流水分页返回
* @author:   CXM
* @create:   2018-12-19 15:16
*/
@Setter
@Getter
public class UserCoinAssetListPageResp {
    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<UserCoinAssetListResp> records;
}
