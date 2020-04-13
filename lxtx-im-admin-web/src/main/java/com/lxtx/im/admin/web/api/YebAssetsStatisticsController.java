package com.lxtx.im.admin.web.api;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.YebAssetsStatisticsService;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsCreateReq;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsDownReq;
import com.lxtx.im.admin.service.request.YebAssetsStatisticsPageReq;
import com.lxtx.im.admin.service.request.YebUserEarningSyncReq;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 * 余额宝资产统计列表
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-09-12
 **/
@RequestMapping("/yeb/assets/statistics")
@Controller
public class YebAssetsStatisticsController {

    @Autowired
    private YebAssetsStatisticsService yebAssetsStatisticsService;

    @GetMapping(value = "/index")
    @RequiresPermissions("yeb:assets:statistics:index")
    public String index() {
        return "yeb/assets-statistics-index";
    }


    @PostMapping("/sync/user/earning")
    @ResponseBody
    @RequiresPermissions("yeb:assets:statistics:index")
    public BaseResult syncYebUserRebate(@Valid @RequestBody YebUserEarningSyncReq req){
        return yebAssetsStatisticsService.doSyncYebUserEearning(req);
    }

    /**
     * 分页查询余额宝资产统计列表
     * @return
     */
    @PostMapping("/page")
    @ResponseBody
    @RequiresPermissions("yeb:assets:statistics:index")
    public BaseResult page(YebAssetsStatisticsPageReq req){
        return yebAssetsStatisticsService.page(req);
    }

    /**
     * 新建余额宝资产统计表
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    @RequiresPermissions("yeb:assets:statistics:index")
    public BaseResult create(YebAssetsStatisticsCreateReq req, HttpServletResponse response){
        return yebAssetsStatisticsService.create(req, response);
    }

    /**
     * 下载余额宝资产统计表
     * @return
     */
    @GetMapping("down")
    @ResponseBody
    @RequiresPermissions("yeb:assets:statistics:index")
    public void down(YebAssetsStatisticsDownReq req, HttpServletResponse response){
        yebAssetsStatisticsService.down(req, response);
    }

}
