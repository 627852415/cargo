package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @description:  sdk模块业务接口
* @author:   CXM
* @create:   2018-11-30 11:29
*/
public interface SdkService {

    /**
     * 获取币种列表数据
     *
     * @param req
     * @return
     */
    BaseResult listPage(SdkThirdGameListReq req);

    /**
     * 获取订单列表
     *
     * @param req
     * @return
     */
    BaseResult orderListPage(SdkThirdGameOrderListReq req);

    /**
     * 上传游戏图标
     *
     * @param file
     * @return
     * @throws IOException
     */
    BaseResult upload(MultipartFile file) throws IOException;

    /**
     * 保存第三方游戏
     *
     * @param req
     * @return
     */
    BaseResult save(SdkSaveThirdGameReq req);

    /**
     * 第三方游戏订单审核
     *
     * @param req
     * @return
     */
    BaseResult orderAudit(SdkThirdGameOrderAuditReq req);

    /**
     * 根据id获取游戏信息
     *
     * @param req
     * @return
     */
    BaseResult gameInfo(SdkThirdGameInfoReq req);

    /**
     * 删除游戏
     *
     * @param req
     * @return
     */
    BaseResult delete(SdkThirdGameDeleteReq req);

    /**
     * 修改游戏状态
     *
     * @param req
     * @return
     */
    BaseResult updateGameStatus(SdkUpdateThirdGameStatusReq req);
}
