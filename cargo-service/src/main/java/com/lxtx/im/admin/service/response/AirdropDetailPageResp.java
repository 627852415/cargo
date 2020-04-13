package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * 空投详情带分页
 *
 * @author CaiRH
 * @since 2018-08-29
 **/
@Getter
@Setter
public class AirdropDetailPageResp {
    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;
    private AirdropResp airdropResp;
    List<AirdropDetailResp> records;
}
