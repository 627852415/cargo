package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.feign.CallBackFeign;
import com.lxtx.im.admin.feign.feign.ExceptionRecordFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.ExceptionRecordService;
import com.lxtx.im.admin.service.enums.EnumExceptionRecordStatus;
import com.lxtx.im.admin.service.enums.EnumExceptionRecordType;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.ExceptionHandleReq;
import com.lxtx.im.admin.service.request.ExceptionRecordReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author tangdy
 * @since 2018-08-28
 */
@Service
public class ExceptionRecordServiceImpl implements ExceptionRecordService {

    @Autowired
    private ExceptionRecordFeign exceptionRecordFeign;
    @Autowired
    private WalletUserFeign walletUserFeign;
    @Autowired
    private CallBackFeign callBackFeign;

    /**
     * 异常数据列表
     *
     * @param req
     * @param session
     * @return
     */
    @Override
    public BaseResult record(ExceptionRecordReq req, HttpSession session) {
        FeignExceptionRecordReq fereq = new FeignExceptionRecordReq();
        BeanUtils.copyProperties(req, fereq);
        return exceptionRecordFeign.obtainRecordPage(fereq);
    }

    /**
     * 异常处理
     *
     * @param req
     * @param session
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult handle(ExceptionHandleReq req, HttpSession session) {
        //获取详情的信息
        String id = req.getId();
        String params = getParams(id);
        Integer type = req.getType();
        BaseResult baseResult = new BaseResult();
        //注册
        if (EnumExceptionRecordType.REGISTER.getCode() == type) {
            baseResult = registerWalletUser(params);
        }
        //6X回调
        if (EnumExceptionRecordType.SIX_CALLBACK.getCode() == type) {
            baseResult = sixCallback(params);
        }
        if (!baseResult.isSuccess()) {
            throw LxtxBizException.newException("处理失败" + baseResult.getMsg());
        }
        //修改异常记录状态
        BaseResult turnStatus = turnStatus(id, EnumExceptionRecordStatus.SUCCESS.getCode());
        if (!turnStatus.isSuccess()) {
            throw LxtxBizException.newException("处理失败");
        }
        return BaseResult.success("处理成功");
    }

    /**
     * 6X 回调
     *
     * @param params
     * @return
     */
    private BaseResult sixCallback(String params) {
        //将字符串转成jsonObject
        JSONObject jsonObject = trunJsonObject(params);
        FeignSixMerNoticeReq sixMerNoticeReq = new FeignSixMerNoticeReq();
        sixMerNoticeReq.setState(jsonObject.getInteger("state"));
        sixMerNoticeReq.setTransferId(jsonObject.getString("transferId"));
        sixMerNoticeReq.setTransferNum(jsonObject.getString("transferNum"));
        sixMerNoticeReq.setType(jsonObject.getInteger("type"));
        sixMerNoticeReq.setUserId(jsonObject.getInteger("userId"));
        return callBackFeign.sixCallBack(sixMerNoticeReq);
    }

    private JSONObject trunJsonObject(String params) {
        if (StringUtils.isBlank(params)) {
            throw LxtxBizException.newException("参数异常");
        }
        JSONObject jsonObject;
        try {
            jsonObject = JSON.parseObject(params);
        } catch (Exception e) {
            throw LxtxBizException.newException("参数异常");
        }
        if (CollectionUtils.isEmpty(jsonObject)) {
            throw LxtxBizException.newException("参数异常");
        }
        return jsonObject;
    }

    /**
     * 获取异常数据的访问参数
     *
     * @param id
     * @return
     */
    private String getParams(String id) {
        FeignExceptionRecordDetailReq exceptionRecordDetailReq = new FeignExceptionRecordDetailReq();
        exceptionRecordDetailReq.setId(id);
        BaseResult baseResult = exceptionRecordFeign.detail(exceptionRecordDetailReq);
        if (!baseResult.isSuccess()) {
            throw LxtxBizException.newException("数据异常");
        }
        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        if (CollectionUtils.isEmpty(dataMap)) {
            throw LxtxBizException.newException("数据异常");
        }
        return dataMap.get("params") + "";

    }

    /**
     * 修改异常记录状态
     *
     * @param id
     * @param status
     * @return
     */
    private BaseResult turnStatus(String id, int status) {
        FeignExceptionRecordStatusReq req = new FeignExceptionRecordStatusReq();
        req.setId(id);
        req.setStatus(status);
        return exceptionRecordFeign.turnStatus(req);
    }

    /**
     * 钱包用户注册
     *
     * @param params
     * @return
     */
    public BaseResult registerWalletUser(String params) {
        //将字符串转成jsonObject
        JSONObject jsonObject = trunJsonObject(params);
        FeignWallletRegisterUserReq userReq = new FeignWallletRegisterUserReq();
        userReq.setUserId(jsonObject.get("userId") + "");
        userReq.setType(jsonObject.getInteger("type"));
        return walletUserFeign.registerWalletUser(userReq);
    }
}
