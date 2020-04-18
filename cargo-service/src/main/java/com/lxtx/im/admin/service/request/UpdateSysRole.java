package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Czh
 * Date: 2018/10/8 下午6:31
 */
@Setter
@Getter
public class UpdateSysRole {
    /**
     * 角色ID
     */
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

    private List<String> menuIdList;
}