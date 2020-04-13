package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author liyunhua
 * @Date 2018-09-07 0007
 */
@Getter
@Setter
public class FeignQueryUserListReq {

    /**
     * 用户id集合
     */
    private List<String> ids;

}
