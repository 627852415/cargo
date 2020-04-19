package com.lxtx.im.admin.web.admin;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import com.lxtx.im.admin.service.cargo.req.SaveReq;
import com.lxtx.im.admin.service.request.BasePageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 跳转关于我们
     * @return
     */
    @RequestMapping("/toAbout")
    public String toAbout(){
        return "acargo/about";
    }

    /**
     * 新增文章
     * @return
     */
    @RequestMapping("/save/paper")
    @ResponseBody
    public BaseResult  savePapaer(@RequestBody SaveReq req){
        cargoService.savePapaer(req.getRefId(),req.getName(),req.getContent());
        return BaseResult.success();
    }

    /**
     * 跳转保存
     * @return
     */
    @RequestMapping("/toSave")
    public String toSave(){
        return "acargo/save";
    }

}
