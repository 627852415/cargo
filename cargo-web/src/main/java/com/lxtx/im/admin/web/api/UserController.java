package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @ResponseBody
    @RequestMapping("/index")
    public BaseResult listPage() {
        return BaseResult.success("ok");
    }

   /* @Autowired
    private UserService userService;

    *//**
     * 获取用户列表
     *
     * @param
     * @return
     *//*
    @SysLogAop("用户列表首页")
    @GetMapping("/index")
    @RequiresPermissions("user:index")
    public String index() {
        return "user/user-index";
    }

    *//**
     * 获取用户列表
     *
     * @param req
     * @return
     *//*
    @SysLogAop("获取用户列表数据")
    @PostMapping("/listPage")
    @RequiresPermissions("user:index")
    @ResponseBody
    public BaseResult listPage(UserListPageReq req) {
        return userService.listPage(req);
    }

*/
}
