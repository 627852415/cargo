package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.StickerFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author LiuLP
 * @Date 2018-09-04
 * @Description
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = StickerFeignFallbackFactory.class)
public interface StickerFeign {

    /**
     * 查询用户列表
     * @param req
     * @return
     */
    @PostMapping(value = "/sticker/listPage")
    BaseResult listPage(FeignStickerListReq req);

    /**
     * 更新表情包
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @PostMapping(value = "/sticker/update")
    BaseResult update(FeignStickerUpdateReq req);

    /**
     * 删除表情包
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @PostMapping(value = "/sticker/delete")
    BaseResult delete(FeignStickerDeleteReq req);

    /**
     * 表情包详情
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @PostMapping(value = "/sticker/manager/detail")
    BaseResult detail(FeignStickerDetailReq req);

    /**
     * 表情包内表情列表  分页
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @PostMapping(value = "/sticker/manager/detail/list")
    BaseResult detailListPage(FeignStickerDetailListReq req);

    /**
     * 更新表情内容
     *
     * @param  req
     * @return
     * @author  CXM
     * @date   2018-12-21 14:26
     */
    @PostMapping(value = "/sticker/update/detail")
    BaseResult updateDetail(FeignStickerUpdateDetailReq req);


    /**
     * 表情包新增
     * @param req
     * @return
     */
    @PostMapping(value = "/sticker/manager/add")
    BaseResult add(FeignStickerSaveReq req);
}
