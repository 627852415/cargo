package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 银行卡列表分页
 *
 * @author liyunhua
 * @since 2019-02-18
 **/
@Getter
@Setter
public class BankcardListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<BankcardResp> records;
}
