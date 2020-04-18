package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * layui上级菜单列表
 * @author
 * @since 2018-10-30
 */
@Setter
@Getter
public class MenuTreeSelectResp {

    private String id;

    private String name;

    private boolean open = true;

    List<MenuTreeSelectChild> children;
}
