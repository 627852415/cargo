package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/index")
public class IndexController {


    @Autowired
    private CargoService cargoService;


    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public BaseResult uploadFile(@RequestParam(value = "file" ,required=false) MultipartFile file )   {
        if (file==null||file.isEmpty()) {
            Map<String,Object> map=new HashMap<>();
            map.put("imageUrl",
                    "http://img1.base.yxdown.com/2015-1/120x170_474286746314246.jpg"
            );
            map.put("fileName","sds");
            return BaseResult.success(map);
            //throw LxtxBizException.newException("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "/user/pro/file"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("imageUrl","https://img-ask.csdn.net/upload/201805/06/1525583694_476523.png");
        map.put("fileName",fileName);
        return BaseResult.success(map);

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
        return mav;
    }


    @RequestMapping("/paper/list")
    @ResponseBody
    public BaseResult paperList() {
        return cargoService.paperList();
    }

}
