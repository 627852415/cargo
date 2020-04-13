package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.ChargeReportFeign;
import com.lxtx.im.admin.feign.request.FeignChargeReportListPageReq;
import com.lxtx.im.admin.feign.request.FeignChargeReportListReq;
import com.lxtx.im.admin.service.ChargeReportService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.ChargeReportListPageReq;
import com.lxtx.im.admin.service.request.ChargeReportListReq;
import com.lxtx.im.admin.service.response.ChargeReportDataResp;
import com.lxtx.im.admin.service.response.ChargeReportListAllResp;
import com.lxtx.im.admin.service.response.UserCoinAssetDiffResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 手续费报表
 *
 * @Author: liyunhua
 * @Date: 2018/12/14
 */
@Service
public class ChargeReportServiceImpl implements ChargeReportService {

    @Autowired
    private ChargeReportFeign chargeReportFeign;

    private static final String CHARGE_REPORT_EXCEL_NAME = "币种手续费报表";

    @Override
    public BaseResult listPage(ChargeReportListPageReq req) {
        FeignChargeReportListPageReq listPageReq = new FeignChargeReportListPageReq();
        BeanUtils.copyProperties(req, listPageReq);
        return chargeReportFeign.listPage(listPageReq);
    }

    @Override
    public BaseResult generateReport() {
        return chargeReportFeign.generateReport();
    }

    @Override
    public void exportExcel(HttpServletResponse response, ChargeReportListReq req) {
        FeignChargeReportListReq listReq = new FeignChargeReportListReq();
        BeanUtils.copyProperties(req, listReq);
        BaseResult baseResult = chargeReportFeign.listAll(listReq);
        List<ChargeReportDataResp> records = new ArrayList<>();
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap);
            ChargeReportListAllResp chargeReportListAllResp = JSONObject.parseObject(jsonResult, ChargeReportListAllResp.class);
            records = chargeReportListAllResp.getRecords();
        }

        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }

        //导出的文件名:没输入查询条件，文件名为“币种手续费报表”，如 选择了BCB ，时间2018-12-1 - 2018-12-10
        // 名字就叫 BCB币种手续费报表20181201-20181210.xls
        StringBuffer fileName = new StringBuffer(CHARGE_REPORT_EXCEL_NAME);
        String coinId = req.getCoinId();
        String coinName = req.getCoinName();
        if (StringUtils.isNoneBlank(coinId) && StringUtils.isNoneBlank(coinName)) {
            fileName.insert(0, coinName);
        }
        if (req.getCreateTime() != null && req.getEndTime() == null) {
            req.setEndTime(DateUtils.getDayBefore(new Date()));
        }
        if (req.getCreateTime() != null && req.getEndTime() != null) {
            fileName.append(DateUtils.getDateFormat(req.getCreateTime()) + "-" + DateUtils.getDateFormat(req.getEndTime()));
        }
        ExcelUtil.exportExcel(response, records, fileName.toString(), CHARGE_REPORT_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }

}
