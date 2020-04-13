package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:  后台线下投诉分页返回
 * @author:   CXM
 * @create:   2019-04-22 17:00
 */
@Setter
@Getter
public class OffsiteExchangeArbitrationListPageResp extends BasePageResp<OffsiteExchangeArbitrationResp>{
    private List<String> accountList;
}