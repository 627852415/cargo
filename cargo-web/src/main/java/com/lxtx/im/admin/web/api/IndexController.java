package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController {


    @Autowired
    private CargoService cargoService;


    @RequestMapping("/service/list")
    public ModelAndView toServiceList(String id,String current) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("service-list");
        mav.addObject("pid", id);
        mav.addObject("newPaper",cargoService.newPaper());
        mav.addObject("serviceList",cargoService.serviceRange(id,current));
        mav.addObject("menuList",cargoService.paperList());
        return mav;
    }


    /**
     * @return
     */
    @RequestMapping("/front")
    public String login() {
        return "index";
    }


    /**
     * @return
     */
    @RequestMapping("/paper")
    public ModelAndView showPaper(String id) {
        BaseResult detail = cargoService.detail(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("paper");
        mav.addObject("obj", detail.getData());
        mav.addObject("newPaper",cargoService.newPaper());
        mav.addObject("menuList",cargoService.paperList());
        return mav;
    }


    @RequestMapping("/paper/list")
    @ResponseBody
    public BaseResult paperList() {
        return cargoService.paperList();
    }

}
