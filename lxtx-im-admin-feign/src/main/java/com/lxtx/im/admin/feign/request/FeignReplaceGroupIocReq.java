package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2019-05-17 15:54
 */
@Setter
@Getter
public class FeignReplaceGroupIocReq {

    /**
     * 群id
     */
    private String groupId;
    /**
     * 强制替换 true 是 false 不是
     */
    private Boolean forceReplace;
}