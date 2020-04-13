package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 获取在线用户带分页
 * @author: CXM
 * @create: 2018-09-05 16:19
 **/
@Setter
@Getter
public class FeignSessionPageReq extends BasePageReq{
    /**
     * 用户账号 主键
     */
    private String account;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 终端设备类型
     */
    private String channel;

    private List<String> khUserAccountList;

}
