package com.lxtx.im.admin.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping("/index")
    public String listPage() {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


}
