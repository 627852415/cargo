package com.lxtx.im.admin.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Czh
 * Date: 2019/12/13 3:26 下午
 */
@Controller
public class LocaleController {

    /**
     * 功能描述: 国际化切换
     *
     * @param request
     * @return
     * @author Czh
     * @date 2019/12/13 3:35 下午
     */
    @GetMapping(value = "/locale")
    public String localeHandler(HttpServletRequest request) {
        String lastUrl = request.getHeader("referer");
        return "redirect:" + lastUrl;
    }
}