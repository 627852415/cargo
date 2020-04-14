package com.lxtx.im.admin.dao.model;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2018/9/27 下午2:27
 */
@Setter
@Getter
@TableName("sys_user")
public class SysUser extends BaseModel {
    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;


}