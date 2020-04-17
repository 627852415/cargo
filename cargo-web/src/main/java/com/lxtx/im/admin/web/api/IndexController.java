package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {


    @Autowired
    private CargoService cargoService;

    /**
     * sds
     * @return
     */
    @RequestMapping("/front")
    public String login() {
        return "index";
    }



    @RequestMapping("/paper/list")
    @ResponseBody
    public BaseResult paperList() {
        return cargoService.paperList();
    }

}
