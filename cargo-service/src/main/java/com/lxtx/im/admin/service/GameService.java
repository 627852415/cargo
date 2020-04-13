package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.GameDeleteReq;
import com.lxtx.im.admin.service.request.GameListPageReq;
import com.lxtx.im.admin.service.request.GameSaveReq;
import com.lxtx.im.admin.service.request.GameSelectReq;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 游戏
 *
 * @author CaiRH
 */
public interface GameService {
    /**
     * 保存
     *
     * @param req
     * @return
     */
    BaseResult listPage(GameListPageReq req);

    /**
     * 带分页查询
     *
     * @param req
     * @return
     */
    BaseResult save(GameSaveReq req);

    /**
     * 详细信息
     *
     * @param req
     * @param model
     */
    void info(GameSelectReq req, Model model);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    BaseResult delete(GameDeleteReq req);

    /**
     * 上传图标/图片
     *
     * @param file
     * @return
     * @throws IOException
     */
    BaseResult upload(MultipartFile file) throws IOException;
}
