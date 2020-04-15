package com.lxtx.im.admin.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/index")
public class IndexController {


    @RequestMapping("/front")
    public String login() {
        return "frontindex";
    }

}
