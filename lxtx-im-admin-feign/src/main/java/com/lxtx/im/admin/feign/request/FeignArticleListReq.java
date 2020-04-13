package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 文章列表
 *
 * @author LiuLP
 * @since 2018-08-27
 */
@Getter
@Setter
public class FeignArticleListReq extends BasePageReq{
    /**
     *  账号,主键
     */
    private String account;
    /**
     * 文章Id
     */
    private String articleId;



    /**
     * 文章类型
     */
    private Integer type;



}
