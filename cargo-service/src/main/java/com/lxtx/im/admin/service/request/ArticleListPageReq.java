package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 *  文章
 * @author LiuLP
 */
@Setter
@Getter
public class ArticleListPageReq extends BasePageReq {
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
