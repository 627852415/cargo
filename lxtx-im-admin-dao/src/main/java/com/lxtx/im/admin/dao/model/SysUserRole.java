package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2018/9/27 下午3:01
 */
@Setter
@Getter
@TableName("sys_user_role")
public class SysUserRole extends BaseModel {

    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

}