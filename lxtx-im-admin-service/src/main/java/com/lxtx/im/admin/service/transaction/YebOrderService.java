package com.lxtx.im.admin.service.transaction;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  理财宝订单服务类
 * </p>
 *
 * @author liboyan
 * @Date 2019-11-25 15:10
 * @Description
 */
public interface YebOrderService {

    BaseResult listPage(YebOrderListReq req);

    void downloadList(HttpServletResponse response, YebOrderDownloadReq req ,HttpSession session);

    String detail(YebOrderDetailReq req, Model model);

    public BaseResult downloadLock(YebOrderListReq req, HttpSession session);

}
