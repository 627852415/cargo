package com.lxtx.im.admin.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.CommissionFeign;
import com.lxtx.im.admin.feign.request.FeignAdminCommissionUserUpdateReq;
import com.lxtx.im.admin.feign.request.FeignCommissionUserDetailReq;
import com.lxtx.im.admin.feign.request.FeignCommissionUserListReq;
import com.lxtx.im.admin.feign.request.FeignCommissionUserUpdateReq;
import com.lxtx.im.admin.service.CommissionUserService;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.CommissionUserDetailReq;
import com.lxtx.im.admin.service.request.CommissionUserListReq;
import com.lxtx.im.admin.service.request.CommissionUserUpdateReq;
import com.lxtx.im.admin.service.response.AdminCommissionUserUpdateListResp;
import com.lxtx.im.admin.service.response.AdminCommissionUserUpdateResp;
import com.lxtx.im.admin.service.response.CommissionUserDetailResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 返佣伙伴
 *
 * @author xufeifei
 */
@Slf4j
@Service
public class CommissionUserServiceImpl implements CommissionUserService {
    @Autowired
    private CommissionFeign commissionFeign;

    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private DictService dictService;

    private static Integer EX_PAGE_SIZE = 50000;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);

    private static final String APPLY_LIST_FILENAME = "返佣伙伴列表";

    @Override
    public BaseResult listPage(CommissionUserListReq req) {
        FeignCommissionUserListReq feignReq = new FeignCommissionUserListReq();
        BeanUtils.copyProperties(req,feignReq);
        return commissionFeign.listPage(feignReq);
    }

    @Override
    public void detail(CommissionUserDetailReq req, Model model) {
        FeignCommissionUserDetailReq feignCommissionUserDetailReq = new FeignCommissionUserDetailReq();
        BeanUtils.copyProperties(req,feignCommissionUserDetailReq);
        BaseResult result = commissionFeign.detail(feignCommissionUserDetailReq);
        String resultJsona = JSON.toJSONString(result.getData());
        CommissionUserDetailResp commissionUserDetailResp = JSONObject.parseObject(resultJsona, CommissionUserDetailResp.class);
        FeignAdminCommissionUserUpdateReq commissionUserUpdateListReq = new FeignAdminCommissionUserUpdateReq();
        commissionUserUpdateListReq.setId(req.getId());
        BaseResult resultCommissionUserUpdateList = commissionFeign.getCommissionUserUpdateList(commissionUserUpdateListReq);
        String listStr = JSON.parseObject(JSON.toJSONString(resultCommissionUserUpdateList.getData())).getString("list");
        List<AdminCommissionUserUpdateListResp> list = JSON.parseArray(listStr, AdminCommissionUserUpdateListResp.class);
        List<AdminCommissionUserUpdateResp> adminCommissionUserUpdateListResp = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);
        list.forEach(item -> {
        	AdminCommissionUserUpdateResp tmp = new AdminCommissionUserUpdateResp();
        	tmp.setCreateTime(sdf.format(item.getCreateTime()));
        	tmp.setLevelName(item.getLevelName());
        	adminCommissionUserUpdateListResp.add(tmp);
        });
        commissionUserDetailResp.setAdminCommissionUserUpdateListResp(adminCommissionUserUpdateListResp);
        model.addAttribute("detail",commissionUserDetailResp);
    }

    @Override
    public BaseResult update(CommissionUserUpdateReq req) {
        FeignCommissionUserUpdateReq feignResp = new FeignCommissionUserUpdateReq();
        BeanUtils.copyProperties(req,feignResp);
        return commissionFeign.update(feignResp);
    }

    @Override
    public BaseResult downloadLock(CommissionUserListReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.COMMISSION_USER_EXPORT_LOCK.concat(userId);
        String requestId = session.getId();
        boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
        if (!getRechargeDownloadLock) {
            String lockId = redisCacheUtils.getDistributedLockRequestId(lockKey);
            if (requestId.equals(lockId)) {
                log.error("正在导出,请稍后...");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "正在导出,请稍后...");
            }
            log.error("有用户正在使用导出功能,请稍后再试");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "有用户正在使用导出功能,请稍后再试");
        }
        try {
            //判断搜索结果total值
            req.setSize(1);
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(listPage(req).getData()));
            Integer total = totalObj.getInteger("total");
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            Integer maxPageSizeLong = maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize);
            if (total > maxPageSizeLong) {
                if (getRechargeDownloadLock)
                    redisCacheUtils.releaseDistributedLock(lockKey, requestId);
                log.error("导出数据是否大于数据字典");
                return BaseResult.error(SysErrorCode.CONFLICT.getCode(),
                        String.format("导出数据不能大于%s条，可筛选条件重新导出", maxPageSizeLong));
            }
        } catch (Exception e) {
            if (getRechargeDownloadLock)
                redisCacheUtils.releaseDistributedLock(lockKey, requestId);
            log.error("导出异常");
            return BaseResult.error(SysErrorCode.CONFLICT.getCode(), "导出失败");
        }
        return BaseResult.success(true);
    }

    @Override
    public void downloadList(HttpServletResponse response, HttpSession session, CommissionUserListReq req) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.COMMISSION_USER_EXPORT_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            FeignCommissionUserListReq feignReq = new FeignCommissionUserListReq();
            BeanUtils.copyProperties(req,feignReq);
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignReq.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            List<CommissionUserDetailResp> respList = Lists.newArrayList();
            BaseResult baseResult = commissionFeign.listPage(feignReq);
            if (baseResult.isSuccess() && baseResult.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"),
                        CommissionUserDetailResp.class);
            }
            String fileName = APPLY_LIST_FILENAME + DATEFORMAT.format(new Date());
            ExcelUtil.exportExcel(response, respList
                    , fileName
                    , APPLY_LIST_FILENAME);

        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public BaseResult adNotify() {
        return commissionFeign.adNotify();
    }

}
