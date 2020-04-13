package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.GlobalCodeService;
import com.lxtx.im.admin.service.request.GlobalCodeListPageReq;
import com.lxtx.im.admin.service.request.GlobalCodeModifyReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 国际简码
 *
 * @Author liyunhua
 * @Date 2018-09-28 0028
 */
@Controller
@RequestMapping("/globalCode")
public class GlobalCodeController {

    @Autowired
    private GlobalCodeService globalCodeService;

    @GetMapping("/index")
    @RequiresPermissions("globalCode:index")
    public String index() {
        return "globalCode/globalCode-index.html";
    }

    /**
     * 获取国际简码列表
     *
     * @param req
     * @return
     */
    @SysLogAop("国际简码列表数据")
    @PostMapping("/listPage")
    @ResponseBody
    @RequiresPermissions("globalCode:index")
    public BaseResult listPage(GlobalCodeListPageReq req) {
        return globalCodeService.listPage(req);
    }

    /**
     * 新增国际简码
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "新增国际简码", monitor = true)
    @PostMapping("/add")
    @ResponseBody
    @RequiresPermissions("globalCode:index")
    public BaseResult add(GlobalCodeModifyReq req) {
        return globalCodeService.add(req);
    }

    /**
     * 修改国际简码
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "修改国际简码", monitor = true)
    @PostMapping("/modify")
    @ResponseBody
    @RequiresPermissions("globalCode:index")
    public BaseResult modify(GlobalCodeModifyReq req) {
        return globalCodeService.modify(req);
    }

    /**
     * 删除国际简码
     *
     * @param req
     * @return com.lxtx.framework.common.base.BaseResult
     * @author liyunhua
     * @date 2018-09-29 0029
     */
    @SysLogAop(value = "删除国际简码", monitor = true)
    @PostMapping("/delete")
    @ResponseBody
    @RequiresPermissions("globalCode:index")
    public BaseResult delete(GlobalCodeModifyReq req) {
        return globalCodeService.delete(req);
    }

}
