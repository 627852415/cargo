package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.im.admin.feign.feign.CommissionFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.CommissionOrderService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.CommissionOrderDetailReq;
import com.lxtx.im.admin.service.request.CommissionOrderListReq;
import com.lxtx.im.admin.service.response.CommissionOrderResp;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommissionOrderServiceImpl implements CommissionOrderService {

    @Resource
    private CommissionFeign commissionFeign;
    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private DictService dictService;

    private static Integer EX_PAGE_SIZE = 50000;
    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;

    private static final DateFormat DATEFORMAT = new SimpleDateFormat(DateUtils.DATE_FORMAT_DEFAULT);

    private static final String APPLY_LIST_FILENAME = "返佣订单列表";

    @Override
    public BaseResult orderList(CommissionOrderListReq req) {
        FeignCommissionOrderListReq feignReq = new FeignCommissionOrderListReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.orderList(feignReq);
    }

    @Override
    public BaseResult detail(CommissionOrderDetailReq req) {
        FeignCommissionOrderDetailReq feignReq = new FeignCommissionOrderDetailReq();
        BeanUtils.copyProperties(req, feignReq);
        return commissionFeign.orderDetail(feignReq);
    }

    @Override
    public BaseResult downloadLock(CommissionOrderListReq req, HttpSession session) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.COMMISSION_ORDER_EXPORT_LOCK.concat(userId);
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
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(orderList(req).getData()));
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
    public void downloadList(HttpServletResponse response, HttpSession session, CommissionOrderListReq req) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.COMMISSION_ORDER_EXPORT_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            FeignCommissionOrderListReq feignReq = new FeignCommissionOrderListReq();
            BeanUtils.copyProperties(req, feignReq);

            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            feignReq.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            List<CommissionOrderResp> respList = Lists.newArrayList();
            BaseResult baseResult = commissionFeign.orderList(feignReq);
            if (baseResult.isSuccess() && baseResult.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"),
                        CommissionOrderResp.class);
            }

            for (int i = 0; i < respList.size(); i++) {
                CommissionOrderResp sp = respList.get(i);
                if(sp.getType().equals(1)){
                    sp.setOrderType("撮合交易");
                }else if(sp.getType().equals(2)){
                    sp.setOrderType("理财宝");
                }else if(sp.getType().equals(3)){
                    sp.setOrderType("提现");
                }else if(sp.getType().equals(4)){
                    sp.setOrderType("支付");
                }else if(sp.getType().equals(5)){
                    sp.setOrderType("购买伙伴");
                }
                respList.set(i,sp);
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
}
