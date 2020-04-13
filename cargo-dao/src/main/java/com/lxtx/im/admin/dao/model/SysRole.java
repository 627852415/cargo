package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2018/9/27 下午2:58
 */
@Setter
@Getter
@TableName("sys_role")
public class SysRole extends BaseModel {

    /**
     * 角色ID
     */
    @TableId
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String roleCode;

    /**
     * 状态  0：禁用   1：正常
     */
    private String status;

}