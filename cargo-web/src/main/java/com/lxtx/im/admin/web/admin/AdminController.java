package com.lxtx.im.admin.web.admin;

import com.lxtx.framework.common.base.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangdy
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    @ResponseBody
    @RequestMapping("/index")
    public BaseResult listPage() {
        return BaseResult.success("ok");
    }


}
