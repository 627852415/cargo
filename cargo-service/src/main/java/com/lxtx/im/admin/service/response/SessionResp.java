package com.lxtx.im.admin.service.response;

import lombok.Data;

import java.util.Set;

/**
 * @author lijiangwen
 * @version 1.0
 * @date 2020/1/13 15:18
 */
@Data
public class SessionResp {
    private Set<String> permsSet;
}
