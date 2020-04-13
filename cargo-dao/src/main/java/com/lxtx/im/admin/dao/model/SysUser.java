package com.lxtx.im.admin.dao.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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


    /**
     * 是否谷歌验证  0：否   1：是
     */
    private Integer googleCertification;

    /**
     * 谷歌密文
     */
    private String googleCipher;

    /**
     * usbToken编号
     */
    private String usbTokenNo;

    /**
     * 是否首次登陆：0 否 1 是
     */
    private Integer firstLogin;

    /**
     * IP
     */
    private String ip;

    /**
     * 角色
     */
    @TableField(exist = false)
    private String roleName;

    /**
     * 用户所属角色
     */
    @TableField(exist = false)
    private List<String> roleIdList;

}