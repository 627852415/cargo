package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 新增空投
 * @author: CXM
 * @create: 2018-09-03 10:32
 **/
@Setter
@Getter
public class FeignAirdropToSavePageReq implements Serializable {
    /**
     * 空投id
     */
    private String id;
}
