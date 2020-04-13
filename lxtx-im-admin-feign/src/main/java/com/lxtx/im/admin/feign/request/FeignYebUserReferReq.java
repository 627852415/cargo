package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class FeignYebUserReferReq implements Serializable {

    /**
     * 下级
     */
    private String account;
    /**
     * 上级
     */
    private String parentAccount;
}
