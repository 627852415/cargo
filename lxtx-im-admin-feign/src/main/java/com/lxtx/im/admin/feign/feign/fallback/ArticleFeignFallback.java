package com.lxtx.im.admin.feign.feign.fallback;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.im.admin.feign.feign.ArticleFeign;
import com.lxtx.im.admin.feign.request.FeignArticleListReq;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tangdy
 * @Date 2018-09-04
 * @Description
 */
@Component
@Slf4j
@Setter
public class ArticleFeignFallback implements ArticleFeign {
    private Throwable cause;

    @Override
    public BaseResult listPage(FeignArticleListReq articleListReq) {
        log.error("feign", cause);
        return BaseResult.error(Constants.SYSERROR_INTERNAL_SERVICE_ERROR_CODE, Constants.SYSERROR_INTERNAL_SERVICE_ERROR_MSG);
    }
}
