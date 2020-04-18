package com.lxtx.im.admin.web.admin;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import com.lxtx.im.admin.service.request.BasePageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/manager")
public class AdminController {

    @Autowired
    private CargoService cargoService;


    @RequestMapping("/index")
    public String listPage() {
        return "admin-index";
    }

    @RequestMapping("/about")
    @ResponseBody
    public BaseResult about( BasePageReq req){
        return BaseResult.success(cargoService.aboutList(req));
    }

    @RequestMapping("/toAbout")
    public String toAbout(){
        return "acargo/about";
    }


    @RequestMapping("/toSave")
    public String toSave(){
        return "acargo/save";
    }

}
