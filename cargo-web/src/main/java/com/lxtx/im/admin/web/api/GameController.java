package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.GameService;
import com.lxtx.im.admin.service.request.GameDeleteReq;
import com.lxtx.im.admin.service.request.GameListPageReq;
import com.lxtx.im.admin.service.request.GameSaveReq;
import com.lxtx.im.admin.service.request.GameSelectReq;
import com.lxtx.im.admin.web.aop.SysLogAop;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * 游戏
 *
 * @author CaiRH
 */
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * 游戏首页
     *
     * @return
     */
    @SysLogAop("游戏首页")
    @GetMapping("/index")
    @RequiresPermissions("game:index")
    public String index() {
        return "game/game-index";
    }

    /**
     * 列表
     *
     * @param req
     * @return
     */
    @SysLogAop("游戏列表数据")
    @PostMapping("/listPage")
    @ResponseBody
    @RequiresPermissions("game:index")
    public BaseResult listPage(GameListPageReq req) {
        return gameService.listPage(req);
    }

    /**
     * 跳转到新增或编辑页面
     *
     * @return
     */
    @SysLogAop("游戏新增或编辑页面")
    @GetMapping("/modifyPage")
    @RequiresPermissions("game:index")
    public String modifyPage(GameSelectReq req, Model model) {
        gameService.info(req, model);
        return "game/game-save";
    }

    /**
     * 新增或编辑
     *
     * @return
     */
    @SysLogAop(value = "游戏新增或编辑", monitor = true)
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions("game:index")
    public BaseResult save(@Valid @RequestBody GameSaveReq req) {
        return gameService.save(req);
    }

    /**
     * 删除
     *
     * @param req
     * @return
     */
    @SysLogAop(value = "删除游戏", monitor = true)
    @PostMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("game:index")
    public BaseResult delete(@Valid @RequestBody GameDeleteReq req) {
        return gameService.delete(req);
    }

    /**
     * 上传图标/图片
     *
     * @param file
     * @return
     */
    @SysLogAop(value = "上传图标/图片", monitor = true)
    @PostMapping("/upload")
    @ResponseBody
    @RequiresPermissions("game:index")
    public BaseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        return gameService.upload(file);
    }
}
