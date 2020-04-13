package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 法币新增保存参数封装
 *
 * @Author: liyunhua
 * @Date: 2019/4/3
 */
@Getter
@Setter
public class FeignLegalCoinSaveReq {

    /**
     * 表主键ID
     */
    private String id;

    /**
     * 法币币种名称
     */
    private String legalCoinName;

    /**
     * 法币图标url
     */
    private String icoUrl;

    /**
     * 关联币种ID
     */
    private String relationCoinId;

    /**
     * 关联币种名称
     */
    private String relationCoinName;

    /**
     * 币种描述
     */
    private String description;

}
