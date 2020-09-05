package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import org.apache.commons.lang3.StringUtils;
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
    public ModelAndView toServiceList(String oneRef,String twoRef,String current) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("service-list");
        if(StringUtils.isNotEmpty(oneRef)){
            mav.addObject("pid", oneRef);
        }else if(StringUtils.isNotEmpty(twoRef)){
            mav.addObject("pid", twoRef);
        }
        mav.addObject("newPaper",cargoService.newPaper());
        mav.addObject("serviceList",cargoService.serviceRange(oneRef,twoRef,current));
        mav.addObject("menuList",cargoService.paperList());
        mav.addObject("guojiaList",cargoService.serviceCountryRange());
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
        mav.addObject("guojiaList",cargoService.serviceCountryRange());
        return mav;
    }


    @RequestMapping("/paper/list")
    @ResponseBody
    public BaseResult paperList() {
        return cargoService.paperList();
    }

}
