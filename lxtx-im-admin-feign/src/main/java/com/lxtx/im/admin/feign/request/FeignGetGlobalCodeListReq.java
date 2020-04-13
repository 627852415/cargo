package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 获取最新的各个国家手机区号列表请求参数封装
 *
 * @Author liyunhua
 * @Date 2018-08-17 0017
 */
@Getter
@Setter
public class FeignGetGlobalCodeListReq {


    /**
     * 前端保存的最新的版本号
     */
    private Integer version;
}
