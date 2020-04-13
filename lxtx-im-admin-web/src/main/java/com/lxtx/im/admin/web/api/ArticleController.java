package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.ArticleService;
import com.lxtx.im.admin.service.request.ArticleListPageReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  文章
 * @author LiuLP
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 文章首页
     * @return
     */
    @SysLogAop("跳转文章首页")
    @GetMapping("/index")
    @RequiresPermissions("article:index")
    public String index(){
        return "article/article-index";
    }

    /**
     * 获取文章列表
     * @param req
     * @return
     */
    @SysLogAop("查询文章列表数据")
    @PostMapping("/listPage")
    @ResponseBody
    @RequiresPermissions("article:index")
    public BaseResult listPage(ArticleListPageReq req){
        return articleService.listPage(req);
    }


}
