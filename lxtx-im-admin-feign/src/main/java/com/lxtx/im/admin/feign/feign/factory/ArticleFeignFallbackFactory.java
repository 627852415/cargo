package com.lxtx.im.admin.feign.feign.factory;

import com.lxtx.im.admin.feign.feign.ArticleFeign;
import com.lxtx.im.admin.feign.feign.fallback.ArticleFeignFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-12 17:11
 * @Description
 */
@Component
public class ArticleFeignFallbackFactory implements FallbackFactory<ArticleFeign> {
    @Override
    public ArticleFeign create(Throwable throwable) {
        ArticleFeignFallback feignFallback = new ArticleFeignFallback();
        feignFallback.setCause(throwable);
        return feignFallback;
    }
}
