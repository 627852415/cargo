package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * layui上级菜单列表子菜单
 * @author
 * @since 2018-10-30
 */
@Setter
@Getter
public class MenuTreeSelectChild {

    private String id;

    private String name;

    private boolean open = true;

    private boolean checked = true;
}
