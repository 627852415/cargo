package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeGoodsFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.Constants.DictConstants;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.OffsiteExchangeGoodsService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumPullOffFlag;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.OffsiteExchangeCloseGoodsByAdminV5Req;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsDownloadReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsEditReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeGoodsPageReq;
import com.lxtx.im.admin.service.response.OffsiteExchangeGoodsDownloadResp;
import com.lxtx.im.admin.service.response.OffsiteExchangeGoodsPageAdminResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-04-24 09:37
 * @Description
 */
@Service
public class OffsiteExchangeGoodsServiceImpl implements OffsiteExchangeGoodsService {
    private static final String OFFSITE_EXCHANGE_ORDER_EXCEL_NAME = "线下汇换商品报表";

    @Resource
    private OffsiteExchangeGoodsFeign goodsFeign;

    @Resource
    private UserFeign userFeign;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(OffsiteExchangeGoodsPageReq req) {
        FeignOffsiteExchangeGoodsPageReq goodsPageReq = new FeignOffsiteExchangeGoodsPageReq();
        BeanUtils.copyProperties(req, goodsPageReq);
        goodsPageReq.setField("create_time");
        goodsPageReq.setOrder("desc");
        //根据电话号码查询平台用户ID集合
        List<String> accountIdList = new ArrayList<>();
        if (StringUtils.isNotBlank(req.getTelephone())) {
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            feignMemberListReq.setTelephone(req.getTelephone());
            BaseResult userResult = userFeign.list(feignMemberListReq);
            if (userResult.isSuccess() && userResult.getData() != null) {
                Map<String, Object> userDataMap = (Map<String, Object>) userResult.getData();
                String userJsonResult = JSONArray.toJSONString(userDataMap);
                UserListResp userListResp = JSONObject.parseObject(userJsonResult, UserListResp.class);
                List<UserDetailResp> userDetailResps = userListResp.getList();
                if (CollectionUtils.isEmpty(userDetailResps)) {
                    return BaseResult.success();
                }
                if (CollectionUtils.isNotEmpty(userDetailResps)) {
                    accountIdList = userDetailResps.stream().map(UserDetailResp::getAccount).collect(Collectors.toList());
                }
            }
            if (CollectionUtils.isNotEmpty(accountIdList)) {
                goodsPageReq.setAccountIdList(accountIdList);
            }
        }
        goodsPageReq.setShowAccount(userService.isShowAccount());
        return goodsFeign.adminPage(goodsPageReq);
    }

    @Override
    public void goodsDownload(HttpServletResponse response, OffsiteExchangeGoodsDownloadReq req) {
//        FeignOffsiteExchangeGoodsDownloadReq goodsDownloadReq = new FeignOffsiteExchangeGoodsDownloadReq();
//        BeanUtils.copyProperties(req, goodsDownloadReq);
//
//        goodsDownloadReq.setSize(100000);
//        BaseResult baseResult = goodsFeign.goodsDownload(goodsDownloadReq);
        OffsiteExchangeGoodsPageReq downloadReq = new OffsiteExchangeGoodsPageReq();
        BeanUtils.copyProperties(req, downloadReq);
        //设置最大导出条数，从字典中查找如果有则使用，没有则默认50000
        int maxSize = 50000;
        String maxPageSize = dictService.getDictValue(DictConstants.DICT_DOMAIN_ADMIN_SYSTEM_MANAGER, DictConstants.TRANSACTION_EXPORT_SIZE);
        if (StringUtils.isNumeric(maxPageSize)) {
            maxSize = Integer.valueOf(maxPageSize);
        }
        downloadReq.setSize(maxSize);
        BaseResult baseResult = listPage(downloadReq);

        if (!baseResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
        List<OffsiteExchangeGoodsPageAdminResp> pageAdminResps = JSON.parseArray(JSON.toJSONString(dataMap.get(BaseResult.RECORDS)), OffsiteExchangeGoodsPageAdminResp.class);
//        List<OffsiteExchangeGoodsDownloadResp> downloadResps = new ArrayList<>();
//        for (OffsiteExchangeGoodsPageAdminResp respPage : pageAdminResps) {
//            OffsiteExchangeGoodsDownloadResp respDownLoad = new OffsiteExchangeGoodsDownloadResp();
//            BeanUtils.copyProperties(respPage, respDownLoad);
//            if (EnumPullOffFlag.PULL_OFF.getCode().equals(respPage.getPullOffFlag())) {
//                respDownLoad.setPullOffFlag(EnumPullOffFlag.PULL_OFF.getDescription());
//            } else if (EnumPullOffFlag.PUSH_ON.getCode().equals(respPage.getPullOffFlag())) {
//                respDownLoad.setPullOffFlag(EnumPullOffFlag.PUSH_ON.getDescription());
//            } else if (EnumPullOffFlag.PUSH_CLOSE.getCode().equals(respPage.getPullOffFlag())) {
//                respDownLoad.setPullOffFlag(EnumPullOffFlag.PUSH_CLOSE.getDescription());
//            }
//            downloadResps.add(respDownLoad);
//        }

        List<OffsiteExchangeGoodsDownloadResp> downloadResps = Optional.ofNullable(pageAdminResps).orElse(new ArrayList<>())
                .parallelStream()
                .map(adminResp -> {
                    OffsiteExchangeGoodsDownloadResp respDownLoad = new OffsiteExchangeGoodsDownloadResp();
                    BeanUtils.copyProperties(adminResp, respDownLoad);
                    if (EnumPullOffFlag.PULL_OFF.getCode().equals(adminResp.getPullOffFlag())) {
                        respDownLoad.setPullOffFlag(EnumPullOffFlag.PULL_OFF.getDescription());
                    } else if (EnumPullOffFlag.PUSH_ON.getCode().equals(adminResp.getPullOffFlag())) {
                        respDownLoad.setPullOffFlag(EnumPullOffFlag.PUSH_ON.getDescription());
                    } else if (EnumPullOffFlag.PUSH_CLOSE.getCode().equals(adminResp.getPullOffFlag())) {
                        respDownLoad.setPullOffFlag(EnumPullOffFlag.PUSH_CLOSE.getDescription());
                    }
                    return respDownLoad;
                })
                .collect(Collectors.toList());

        //导出的文件名:没输入查询条件，文件名为“线下汇换商品报表”，时间2018-12-1 - 2018-12-10
        // 名字就叫 线下汇换订单报表20181201-20181210.xls
        StringBuffer fileName = new StringBuffer(OFFSITE_EXCHANGE_ORDER_EXCEL_NAME);

        if (req.getStartTime() != null && req.getEndTime() == null) {
            req.setEndTime(DateUtils.getDate());
        }
        if (req.getStartTime() != null && req.getEndTime() != null) {
            fileName.append(req.getStartTime().concat("-").concat(req.getEndTime()));
        }
        ExcelUtil.exportExcel(response, downloadResps, fileName.toString(), OFFSITE_EXCHANGE_ORDER_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }

    @Override
    public BaseResult up(OffsiteExchangeGoodsEditReq req) {
        FeignOffsiteExchangeGoodsEditReq feignReq = new FeignOffsiteExchangeGoodsEditReq();
        BeanUtils.copyProperties(req, feignReq);
        return goodsFeign.up(feignReq);
    }

    @Override
    public BaseResult closeGoodsByAdmin(OffsiteExchangeCloseGoodsByAdminV5Req req) {
        FeignOffsiteExchangeCloseGoodsByAdminV5Req feignReq = new FeignOffsiteExchangeCloseGoodsByAdminV5Req();
        BeanUtils.copyProperties(req, feignReq);
        return goodsFeign.closeGoodsByAdmin(feignReq);
    }

    @Override
    public BaseResult down(OffsiteExchangeGoodsEditReq req) {
        FeignOffsiteExchangeGoodsEditReq feignReq = new FeignOffsiteExchangeGoodsEditReq();
        BeanUtils.copyProperties(req, feignReq);
        return goodsFeign.down(feignReq);
    }

    @Override
    public BaseResult syncOldGoodsOutPay() {
        return goodsFeign.syncOldGoodsPay();
    }

	@Override
	public BaseResult deductTheFundsOfTheTotalAccount() {
		 return goodsFeign.deductTheFundsOfTheTotalAccount();
	}
}
