package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author liyunhua
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class UserList implements Serializable {

    private List<UserResp> list;

}