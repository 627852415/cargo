package com.lxtx.im.admin.web.admin;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import com.lxtx.im.admin.service.SysUserService;
import com.lxtx.im.admin.service.cargo.req.PaperListPage;
import com.lxtx.im.admin.service.cargo.req.SaveReq;
import com.lxtx.im.admin.service.request.SysUserModifyPwdReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/manager")
public class AdminController {

    @Autowired
    private CargoService cargoService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/index")
    public String listPage() {
        return "admin-index";
    }

    @PostMapping("/listPage")
    @ResponseBody
    public BaseResult listPage( PaperListPage req){
        return cargoService.listPage(req);
    }

    /**
     * 跳转关于我们-首页
     * @return
     */
    @RequestMapping("/toAbout")
    public ModelAndView toAbout(String typeId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acargo/about-index");
        mav.addObject("typeId", typeId);
        return mav;
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

    @RequestMapping("/del/paper")
    @ResponseBody
    public BaseResult  delPapaer(@RequestBody SaveReq req){
        cargoService.delPapaer(req.getId());
        return BaseResult.success();
    }

    /**
     * 编辑文章
     * @return
     */
    @RequestMapping("/update/paper")
    @ResponseBody
    public BaseResult  update(@RequestBody SaveReq req){
        cargoService.update(req.getId(),req.getName(),req.getContent());
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
        mav.setViewName("acargo/save");
        mav.addObject("obj", detail.getData());
        mav.addObject("typeId", typeId);
        return mav;
    }

    /**
     * 跳转保存
     * @return
     */
    @RequestMapping("/toSave")
    public ModelAndView toSave(String typeId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("acargo/save");
        mav.addObject("typeId", typeId);
        return mav;
    }

    @GetMapping(value = "/to/update/pass")
    public String toUpdatePassword() {
        return "updatePassword";
    }

    @PostMapping(value = "/update/pwd")
    @ResponseBody
    public BaseResult updatePwd(@Validated @RequestBody SysUserModifyPwdReq sysUserModifyPwdReq) {
        return sysUserService.updatePwd(sysUserModifyPwdReq);
    }

}
