package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 群游戏列表分页
 *
 * @author liyunhua
 * @since 2019-02-26
 **/
@Getter
@Setter
public class GroupGameListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<GroupGameResp> records;
}
