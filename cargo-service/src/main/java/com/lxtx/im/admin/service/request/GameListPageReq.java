package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 游戏列表
 *
 * @author CaiRH
 */
@Getter
@Setter
public class GameListPageReq extends BasePageReq {
    /**
     * 游戏名称
     */
    private String name;

}
