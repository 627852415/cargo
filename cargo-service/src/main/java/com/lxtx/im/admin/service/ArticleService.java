package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.ArticleListPageReq;


/**
 *  文章
 * @author LiuLP
 */
public interface ArticleService {

    /**
     * 获取文章列表
     * @param req
     * @return
     */
    BaseResult listPage(ArticleListPageReq req);

}
