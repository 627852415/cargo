package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 游戏列表
 *
 * @author CaiRH
 */
@Getter
@Setter
public class FeignGameListPageReq extends BasePageReq {

    /**
     * 游戏名称
     */
    private String name;
}
