package com.lxtx.im.admin.web.api;

import com.lxtx.im.admin.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {


    @Autowired
    private CargoService cargoService;

    @RequestMapping("/")
    public String index()   {

        return "index";

    }


}
