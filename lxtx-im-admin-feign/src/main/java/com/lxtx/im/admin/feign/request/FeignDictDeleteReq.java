package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  删除字典参数类
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class FeignDictDeleteReq {
    /**
     * 字典ID
     */
    private String gid;
}
