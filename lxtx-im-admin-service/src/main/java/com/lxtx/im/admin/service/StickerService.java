package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;

/**
 * @description: 表情包
 * @author: CXM
 * @create: 2018-08-31 09:55
 **/
public interface StickerService {

    /**
     * 列表
     * @param req
     * @return
     */
    BaseResult listPage(StickerListReq req);

    /**
     *
     * @param file
     * @param req
     * @return
     */
    BaseResult add(LinkedList<MultipartFile> file, StickerSaveReq req);

    /**
     * 更新表情包
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    BaseResult update(StickerUpdateReq req);

    /**
     * 删除表情包
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    BaseResult delete(StickerDeleteReq req);

    /**
     * 获取表情包详情
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    BaseResult detail(StickerDetailReq req);

    /**
     * 表情包里的表情列表
     *
     * @param req
     * @return
     */
    BaseResult detailListPage(StickerDetailListReq req);

    /**
     * 更新表情内容
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    BaseResult updateDetail(StickerUpdateDetailReq req);

    /**
     * 表情包封面图片上传
     *
     * @param  file
     * @return
     * @author  CXM
     * @date   2018-12-25 17:20
     */
    BaseResult uploadCover(MultipartFile file) throws IOException;
}
