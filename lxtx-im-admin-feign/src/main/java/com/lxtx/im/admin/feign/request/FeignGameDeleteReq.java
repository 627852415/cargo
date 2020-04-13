package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 游戏删除
 *
 * @author CaiRH
 */
@Setter
@Getter
public class FeignGameDeleteReq {
    /**
     * 主键
     */
    private String id;
}
