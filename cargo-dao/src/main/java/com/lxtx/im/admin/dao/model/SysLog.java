package com.lxtx.im.admin.dao.model;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志
 *
 * @author liyunhua
 * @date 2018-10-26 0026
 */
@Setter
@Getter
@TableName("sys_log")
public class SysLog extends BaseModel {

    public static final String IP = "ip";
    public static final String OPERATION = "operation";

    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * ip地址
     */
    private String ip;

    private String url;
}