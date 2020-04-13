package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.AssetStatisticsListFeign;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsDailyListDownReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsDalyListDetailReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsExportReq;
import com.lxtx.im.admin.feign.request.FeignAssetStatisticsListReq;
import com.lxtx.im.admin.service.AssetStatisticsDailyListService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.AssetStatisticsDalyListDetailReq;
import com.lxtx.im.admin.service.request.AssetStatisticsListExportReq;
import com.lxtx.im.admin.service.request.AssetStatisticsListReq;
import com.lxtx.im.admin.service.response.AssetStatsticsDailyListExportResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AssetStatisticsDailyListServiceImpl implements AssetStatisticsDailyListService {

    @Autowired
    private AssetStatisticsListFeign assetStatisticsListFeign;
    private static final String ASSET_COIN_DAILY_DETAIL_EXCEL_NAME = "资产统计快照";

    @Override
    public BaseResult listPage(AssetStatisticsListReq req) {
        FeignAssetStatisticsListReq feignAssetStatisticsListReq = new FeignAssetStatisticsListReq();
        BeanUtils.copyProperties(req, feignAssetStatisticsListReq);
        return assetStatisticsListFeign.listPage(feignAssetStatisticsListReq);
    }


    public void downData(HttpServletResponse response, String id) {
        FeignAssetStatisticsDailyListDownReq feignAssetStatisticsDailyListDownReq = new FeignAssetStatisticsDailyListDownReq();
        feignAssetStatisticsDailyListDownReq.setId(id);
        BaseResult baseResult = assetStatisticsListFeign.downData(feignAssetStatisticsDailyListDownReq);
        String fileName = "";
        String path = "";
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> map = (Map<String, Object>) baseResult.getData();
            fileName = (String) map.get("fileNmae");
            path = (String) map.get("path");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                InputStream is = new BufferedInputStream(new FileInputStream(path));
                // 设置response参数，可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName
                        .getBytes("utf-8"), "ISO8859-1"));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                ServletOutputStream out = response.getOutputStream();
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    ServletOutputStream out = response.getOutputStream();
                    response.setCharacterEncoding("UTF-8");
                    out.write(JSON.toJSONString(BaseResult.error(Constants.SYSERROR_SYSTEM_ERROR_MSG,null)).getBytes("UTF-8"));
                    out.flush();
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public BaseResult detail(AssetStatisticsDalyListDetailReq req) {
        FeignAssetStatisticsDalyListDetailReq feignAssetStatisticsListReq = new FeignAssetStatisticsDalyListDetailReq();
        BeanUtils.copyProperties(req, feignAssetStatisticsListReq);
        return assetStatisticsListFeign.detail(feignAssetStatisticsListReq);
    }


    @Override
    public void createDetailExcel(HttpServletResponse response, AssetStatisticsListExportReq req) {
        FeignAssetStatisticsExportReq feignUserCoinDailyDetailReq = new FeignAssetStatisticsExportReq();
        BeanUtils.copyProperties(req, feignUserCoinDailyDetailReq);
        BaseResult baseResult = assetStatisticsListFeign.createDetailExcel(feignUserCoinDailyDetailReq);
        List<AssetStatsticsDailyListExportResp> records = new ArrayList<>();
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap.get("list"));
            records= JSONObject.parseArray(jsonResult, AssetStatsticsDailyListExportResp.class);
        }
        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        StringBuffer fileName = new StringBuffer(ASSET_COIN_DAILY_DETAIL_EXCEL_NAME);
        Date dayBefore = new Date();
        String dateFormat = DateUtils.getDateFormat(dayBefore, DateUtils.DATE_FORMAT_YYYY_MM_DD);
        ExcelUtil.exportExcel(response, records, dateFormat + fileName.toString(), dateFormat +
                ASSET_COIN_DAILY_DETAIL_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }
}