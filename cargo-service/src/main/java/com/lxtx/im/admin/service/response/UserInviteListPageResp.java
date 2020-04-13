package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户列表分页
 *
 * @author liyunhua
 * @since 2019-01-11
 **/
@Getter
@Setter
public class UserInviteListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<UserInviteResp> records;
}
