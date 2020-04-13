package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.InviteFeign;
import com.lxtx.im.admin.feign.feign.InviteRelationChangeLogFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.InviteRelationChangeLogService;
import com.lxtx.im.admin.service.InviteRelationService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.UserInviteListPageResp;
import com.lxtx.im.admin.service.response.UserInviteResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
*
* @Author:   liyunhua
* @Date:     2019/4/1
*/
@Service
public class InviteRelationChangeLogServiceImpl implements InviteRelationChangeLogService {

    @Autowired
    private InviteRelationChangeLogFeign inviteRelationChangeLogFeign;

    @Override
    public BaseResult listPage(InviteChangeLogListPageReq req) {
        FeignInviteChangeLogListPageReq feignReq = new FeignInviteChangeLogListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        return inviteRelationChangeLogFeign.listPage(feignReq);
    }
}
