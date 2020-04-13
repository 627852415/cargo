package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.dao.model.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 系统用户列表resp
 *
 * @author liyunhua
 * @date 2018-09-28 0028
 */
@Getter
@Setter
public class SysUserListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<SysUser> records;
}
