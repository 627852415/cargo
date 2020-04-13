package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.ArticleFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.FeignArticleListReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author LiuLP
 * @Date 2018-09-04
 * @Description
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = ArticleFeignFallbackFactory.class)
public interface ArticleFeign {

    /**
     * 查询用户列表
     * @param articleListReq
     * @return
     */
    @PostMapping(value = "/core/article/listPage")
    BaseResult listPage(FeignArticleListReq articleListReq);


}
