package com.lxtx.im.admin.service.impl;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.AdviceFeedbackFeign;
import com.lxtx.im.admin.feign.request.FeignAdviceFeedbackDetailReq;
import com.lxtx.im.admin.feign.request.FeignAdviceFeedbackPageReq;
import com.lxtx.im.admin.service.AdviceFeedbackService;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.AdviceFeedbackListPageReq;
import com.lxtx.im.admin.service.response.AdviceFeedbackRecordResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: ZhangZhongWu
 * @date: 2019/7/4 16:54
 */
@Service
public class AdviceFeedbackServiceImpl implements AdviceFeedbackService {

    @Autowired
    private AdviceFeedbackFeign adviceFeedbackFeign;

    private static final String ADVICE_FEEDBACK_EXCEL_NAME = "意见反馈记录";

    /**
     * 查询列表
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult listPage(AdviceFeedbackListPageReq req) {
        FeignAdviceFeedbackPageReq feignReq = new FeignAdviceFeedbackPageReq();
        BeanUtils.copyProperties(req, feignReq);
        return adviceFeedbackFeign.listPage(feignReq);
    }

    /**
     * 详情
     *
     * @return
     */
    @Override
    public BaseResult detail(String id) {
        FeignAdviceFeedbackDetailReq feiReq = new FeignAdviceFeedbackDetailReq();
        feiReq.setId(id);
        return adviceFeedbackFeign.detail(feiReq);
    }

    /**
     * 导出excel
     *
     * @param response
     * @param req
     */
    @Override
    public void createExcel(HttpServletResponse response, AdviceFeedbackListPageReq req) {
        FeignAdviceFeedbackPageReq feignReq = new FeignAdviceFeedbackPageReq();
        BeanUtils.copyProperties(req, feignReq);
        BaseResult baseResult = adviceFeedbackFeign.createExcel(feignReq);
        List<AdviceFeedbackRecordResp> records = new ArrayList<>();
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            List<Map<String, String>> resultList = (List<Map<String, String>>) dataMap.get("records");
            if (CollectionUtils.isNotEmpty(resultList)) {
                records = resultList.stream().map(o -> new AdviceFeedbackRecordResp(
                        o.getOrDefault("accountName", ""),
                        o.getOrDefault("account", ""),
                        o.getOrDefault("phoneCode", ""),
                        o.getOrDefault("telephone", ""),
                        o.getOrDefault("version", ""),
                        o.getOrDefault("issue", ""),
                        new Date(o.getOrDefault("createTime", "")))
                ).collect(Collectors.toList());
            }
        }
        if (CollectionUtils.isEmpty(records)) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        ExcelUtil.exportExcel(response, records, ADVICE_FEEDBACK_EXCEL_NAME,
                ADVICE_FEEDBACK_EXCEL_NAME, DateUtils.DATE_FORMAT_DEFAULT);
    }
}