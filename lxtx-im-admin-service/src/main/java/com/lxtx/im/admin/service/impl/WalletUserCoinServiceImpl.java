package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.RedisConstants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.RedisCacheUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.dao.model.Coin;
import com.lxtx.im.admin.feign.feign.WalletUserCoinFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.Constants.PropertiesContants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.WalletUserCoinService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.exception.SysErrorCode;
import com.lxtx.im.admin.service.request.UserCoinUpdateReq;
import com.lxtx.im.admin.service.request.UserReq;
import com.lxtx.im.admin.service.response.CoinResp;
import com.lxtx.im.admin.service.response.TradingRecordFastExchangeTopgateResp;
import com.lxtx.im.admin.service.response.UserCoinDTO;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Author liyunhua
 * @Date 2018-09-01 0001
 */
@Slf4j
@Service
public class WalletUserCoinServiceImpl implements WalletUserCoinService {

    @Resource
    private WalletUserCoinFeign walletUserCoinFeign;
    @Autowired
    @SuppressWarnings("rawtypes")
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private DictService dictService;
    @Autowired
    private CoinService coinService;

    //锁时间 60秒
    private static Integer LOCK_TIME = 60000;

    private static Integer EX_PAGE_SIZE = 50000;

    private static final String WALLET_LIST_FILENAME = "钱包管理-钱包列表";

    @Override
    public List<CoinResp> findAllCoin() {
        return coinService.getAllCoinList();
    }

    @Override
    public BaseResult list(UserReq userReq, HttpSession session) {
        FeignUserReq feignUserReq = new FeignUserReq();
        BeanUtils.copyProperties(userReq, feignUserReq);
        return walletUserCoinFeign.list(feignUserReq);
    }

    @Override
    public BaseResult update(UserCoinUpdateReq userCoinUpdateReq, HttpSession session) {
        boolean walletUserCoinUpdate = PropertiesUtil.getBoolean(PropertiesContants.WALLET_USER_COIN_UPDATE, false);
        if (!walletUserCoinUpdate) {
            throw LxtxBizException.newException("更新[用户资产]功能未开放，请联系管理员");
        }
        FeignUserCoinUpdateReq req = new FeignUserCoinUpdateReq();
        BeanUtils.copyProperties(userCoinUpdateReq, req);
        return walletUserCoinFeign.update(req);
    }

    @Override
    public void download(HttpServletResponse response, HttpSession session, UserReq userReq, Locale locale) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_FAST_GET_LOCK.concat(userId);
        String requestId = session.getId();
        try {
            boolean getRechargeDownloadLock = redisCacheUtils.tryGetDistributedLock(lockKey, requestId, LOCK_TIME);
            if (getRechargeDownloadLock) {
                log.error("非法操作，未先调用获取锁接口，此处获取到锁不允许往下操作");
                throw LxtxBizException.newException("操作不允许,请稍后再试");
            }

            List<UserCoinDTO> respList = null;
            // 设置导出页数
            String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
            userReq.setSize(maxPageSize == null ? EX_PAGE_SIZE : Integer.valueOf(maxPageSize));

            FeignUserReq feignUserReq = new FeignUserReq();
            BeanUtils.copyProperties(userReq, feignUserReq);
            BaseResult baseResult = walletUserCoinFeign.list(feignUserReq);
            if (baseResult.isSuccess() && baseResult.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
                respList = JSONObject.parseArray(jsonObject.getString("records"),
                        UserCoinDTO.class);
            }
            ExcelUtil.exportExcel(response, respList
                    , getFileNameFlagTime()
                    , WALLET_LIST_FILENAME);
        } catch (Exception e) {
            log.error("导出失败", e);
            return;
        } finally {
            redisCacheUtils.releaseDistributedLock(lockKey, requestId);
        }
    }

    @Override
    public BaseResult downloadLock(UserReq userReq, HttpSession session, Locale locale) {
        String userId = ShiroUtils.getUserId();
        String lockKey = RedisConstants.ADMIN_EXPORT_FAST_GET_LOCK.concat(userId);
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
            userReq.setSize(1);
            JSONObject totalObj = JSON.parseObject(JSON.toJSONString(list(userReq, session).getData()));
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

    /**
     * 功能描述: 导出列表文件名<br>
     *
     * @Param: []
     * @Return: java.lang.String
     * @Author: peng
     * @Date: 2020/1/10 11:35
     */
    private String getFileNameFlagTime() {
        return WALLET_LIST_FILENAME + DateUtils.getDateFormat(new Date(), DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }
}
