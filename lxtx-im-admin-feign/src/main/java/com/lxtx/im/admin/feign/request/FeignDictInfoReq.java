package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
* @description:  获取字典信息参数类
* @author:   CXM
* @create:   2018-09-27 20:11
*/
@Getter
@Setter
public class FeignDictInfoReq {
    /**
     * 字典ID
     */
    private String gid;
}
