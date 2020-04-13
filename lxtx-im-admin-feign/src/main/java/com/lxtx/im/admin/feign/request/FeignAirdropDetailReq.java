package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 空投详情
 * @author: CXM
 * @create: 2018-09-01 13:50
 **/
@Setter
@Getter
public class FeignAirdropDetailReq extends BasePageReq{
    private String id;
}
