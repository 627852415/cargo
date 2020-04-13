package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.ArticleFeign;
import com.lxtx.im.admin.feign.request.FeignArticleListReq;
import com.lxtx.im.admin.service.ArticleService;
import com.lxtx.im.admin.service.request.ArticleListPageReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *  文章
 * @author LiuLP
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleFeign articleFeign;

    @Override
    public BaseResult listPage(ArticleListPageReq req) {
        FeignArticleListReq feignReq = new FeignArticleListReq();
        BeanUtils.copyProperties(req,feignReq);
        return articleFeign.listPage(feignReq);
    }





}
