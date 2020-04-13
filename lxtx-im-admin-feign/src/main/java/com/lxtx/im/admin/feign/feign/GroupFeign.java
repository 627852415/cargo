package com.lxtx.im.admin.feign.feign;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.factory.GroupFeignFallbackFactory;
import com.lxtx.im.admin.feign.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author liyunhua
 * @Date 2018-09-11 0011
 */
@FeignClient(value = "lxtx-im-core", fallbackFactory = GroupFeignFallbackFactory.class)
public interface GroupFeign {

    /**
     * 查询群组列表
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/group/listPage")
    BaseResult listPage(FeignGroupListPageReq listPageReq);

    /**
     * 查询群组列表，不分页，下载导出用
     *
     * @param listPageReq
     * @return
     */
    @PostMapping(value = "/group/list/download")
    BaseResult listDownload(FeignGroupListPageReq listPageReq);

    @PostMapping(value = "/group/listAllGroup")
    BaseResult listAllGroup();

    @PostMapping(value = "/group/updateYxGroupId")
    BaseResult updateYxGroupId(FeignGroupReq group);

    /**
     * 修改群私聊权限
     *
     * @param feignGroupAlterInformationReq
     * @return
     */
    @PostMapping(value = "/group/information/flag")
    BaseResult updateInformationFlag(@RequestBody FeignGroupAlterInformationReq feignGroupAlterInformationReq);

    @PostMapping(value = "/group/admin/disband")
    BaseResult disband(@RequestBody FeignGroupDisbandReq feignGroupDisbandReq);

    @PostMapping(value = "/group/groupActiveList")
    BaseResult groupActiveList(@RequestBody FeignGroupActiveListReq feignGroupActiveListReq);

    @PostMapping(value = "/group/get/name")
    BaseResult getGroupName(FeignGroupReq feignGroupReq);

    @PostMapping(value = "/group/list/batch")
    BaseResult list(FeignGroupListReq feignGroupListReq);

    @PostMapping(value = "/group/icon/replace")
    BaseResult replaceGroupIoc(FeignReplaceGroupIocReq feign);
}
