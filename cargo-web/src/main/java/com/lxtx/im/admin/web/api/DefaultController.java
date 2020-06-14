package com.lxtx.im.admin.web.api;

import com.lxtx.im.admin.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DefaultController {


    @Autowired
    private CargoService cargoService;

    @RequestMapping("/")
    public ModelAndView index()   {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("newPaper",cargoService.newPaper());
        return modelAndView;

    }


}
