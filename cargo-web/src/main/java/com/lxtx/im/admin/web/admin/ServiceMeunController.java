package com.lxtx.im.admin.web.admin;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.cargo.req.PaperListPage;
import com.lxtx.im.admin.service.cargo.req.SaveMenuReq;
import com.lxtx.im.admin.service.cargo.req.SaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/manager/service")
public class ServiceMeunController {

    @Autowired
    private CargoService cargoService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/del/paper")
    @ResponseBody
    public BaseResult  delPapaer(@RequestBody SaveReq req){
        cargoService.deleteMenu(req.getId());
        return BaseResult.success();
    }

    @RequestMapping("/index")
    public String listPage() {
        return "admin-index";
    }

    @PostMapping("/listPage")
    @ResponseBody
    public BaseResult listPage( PaperListPage req){
        return cargoService.listPage(req);
    }

    @PostMapping("/menu/listPage")
    @ResponseBody
    public BaseResult listMenuPage( PaperListPage req){
        return cargoService.menuListPages(req);
    }

    @RequestMapping("/menu/index")
    public String tolistMenuPage() {
        return "acargo/servicemenu/menu-index";
    }


    @RequestMapping("/menu/toEditor")
    public ModelAndView toMenuEditor(String id) {
        BaseResult detail = cargoService.detailMenu(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acargo/servicemenu/edit-menu");
        mav.addObject("obj", detail.getData());
        return mav;
    }


    /**
     * 更新菜单
     * @param req
     * @return
     */
    @RequestMapping("/update/menu")
    @ResponseBody
    public BaseResult  updateMenu(@RequestBody SaveReq req){
        cargoService.updateMenu(req);
        return BaseResult.success();
    }

    /**
     * 编辑文章
     * @return
     */
    @RequestMapping("/update/paper")
    @ResponseBody
    public BaseResult  updatePaper(@RequestBody SaveReq req){
        cargoService.updateServicePaper(req);
        return BaseResult.success();
    }

    /**
     * 跳转编辑
     * @return
     */
    @RequestMapping("/toEditor")
    public ModelAndView toEditor(String id,String typeId) {
        BaseResult detail = cargoService.detail(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acargo/service-edit");
        mav.addObject("obj", detail.getData());
        mav.addObject("list", cargoService.selectOneMeunList(typeId));
        mav.addObject("typeId", typeId);
        return mav;
    }


    @RequestMapping("/save/paper")
    @ResponseBody
    public BaseResult  savePapaer(@RequestBody SaveReq req){
        cargoService.saveServicePapaer(req);
        return BaseResult.success();
    }


    /**
     * 跳转保存
     * @return
     */
    @RequestMapping("/toSave")
    public ModelAndView toServiceSave(String typeId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acargo/service-save");
        mav.addObject("list", cargoService.selectOneMeunList(typeId));
        mav.addObject("typeId", typeId);
        return mav;
    }

    @RequestMapping("/toMenuSave")
    public ModelAndView toAbout(String typeId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acargo/servicemenu/onemeun");
        mav.addObject("typeId", typeId);
        return mav;
    }

    @RequestMapping("/toMenuTwo")
    public ModelAndView oneMenunList(String typeId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acargo/servicemenu/two-meun");
        mav.addObject("list", cargoService.selectOneMeunList(typeId));
        return mav;
    }



    @PostMapping("/twoMenunList")
    @ResponseBody
    public BaseResult twoMenunList(String pid){
        return BaseResult.success(cargoService.selectOneMeunList(pid));
    }

    @RequestMapping("/save/menu")
    @ResponseBody
    public BaseResult  savemenu(@RequestBody SaveMenuReq req){
        Integer topLevel = null;
        if(req.getTopLevel().equals("on")){
            topLevel = 1;
        }
        cargoService.saveServiceOneMeun(req.getTypeId(),req.getName(),topLevel);
        return BaseResult.success();
    }

    @RequestMapping("/save/twomenu")
    @ResponseBody
    public BaseResult  saveTwomenu(@RequestBody SaveMenuReq req){
        cargoService.saveServiceOneMeun(req.getPid(),req.getName(),null);
        return BaseResult.success();
    }

}
