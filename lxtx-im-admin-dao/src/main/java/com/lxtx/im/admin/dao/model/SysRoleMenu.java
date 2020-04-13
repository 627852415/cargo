package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2018/9/27 下午3:03
 */
@Setter
@Getter
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseModel {

    @TableId
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;
}