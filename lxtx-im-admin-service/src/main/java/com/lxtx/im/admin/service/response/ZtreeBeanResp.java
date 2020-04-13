package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Czh
 * Date: 2018/10/8 下午5:40
 */
@Setter
@Getter
public class ZtreeBeanResp {

    private String id;
    private String pId;
    private String name;
    private String open;
    private String chkDisabled;
    private boolean checked;

    public String getpId() {
        return pId;
    }
}