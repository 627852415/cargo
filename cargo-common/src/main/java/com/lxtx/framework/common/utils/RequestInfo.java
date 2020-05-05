package com.lxtx.framework.common.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @since 2019-04-10
 */
@Getter
@Setter
public class RequestInfo {

    /**
     * 版本号名称， 如：1.4.0
     */
    private String versionName;

    public RequestInfo(){
    }

    public RequestInfo(String versionName){
        this.versionName = versionName;
    }
}