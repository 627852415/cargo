package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeOrderFeign;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeWaveRateFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.OffsiteExchangeOrderService;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumOffsiteExchangeOrderStatus;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.OffisteExchangeOrderEndReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderDetailReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderListPageReq;
import com.lxtx.im.admin.service.request.OffsiteExchangeOrderThawBuyerMargin;
import com.lxtx.im.admin.service.response.*;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 线下汇换订单实现类
 * @author: CXM
 * @create: 2019-04-22 15:05
 */
@Service
public class OffsiteOrderServiceImpl implements OffsiteExchangeOrderService {
    private static final String OFFSITE_EXCHANGE_ORDER_EXCEL_NAME = "线下汇换订单报表";

    @Autowired
    private OffsiteExchangeOrderFeign offsiteExchangeOrderFeign;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private OffsiteExchangeWaveRateFeign offsiteExchangeWaveRateFeign;
    @Autowired
    private UserService userService;

    @SuppressWarnings("Duplicates")
    @Override
    public OffsiteExchangeOrderListPageResp listPage(OffsiteExchangeOrderListPageReq req) {
        OffsiteExchangeOrderListPageResp resp = new OffsiteExchangeOrderListPageResp();

        // 买家accountList
        List<String> accountList = null;
        if(StringUtils.isNotBlank(req.getBuyerAccount()) || StringUtils.isNotBlank(req.getBuyerNickName()) || StringUtils.isNotBlank(req.getBuyerTelephone())){
            List<UserDetailResp> userListResp = getUserDetailRespList(req.getBuyerAccount(), req.getBuyerTelephone(), req.getBuyerNickName());
            if (CollectionUtils.isEmpty(userListResp)) {
                return resp;
            }
            accountList = userListResp.stream().map(UserDetailResp::getAccount).collect(Collectors.toList());
        }

        // 商家accountList
        List<String> merchantAccountList = null;
        if(StringUtils.isNotBlank(req.getMerchantAccount()) || StringUtils.isNotBlank(req.getMerchantNickName()) || StringUtils.isNotBlank(req.getMerchantTelephone())){
            List<UserDetailResp> userListResp = getUserDetailRespList(req.getMerchantAccount(), req.getMerchantTelephone(), req.getMerchantNickName());
            if (CollectionUtils.isEmpty(userListResp)) {
                return resp;
            }

            merchantAccountList = userListResp.stream().map(UserDetailResp::getAccount).collect(Collectors.toList());
        }

        FeignOffsiteExchangeOrderListPageReq feign = new FeignOffsiteExchangeOrderListPageReq();
        BeanUtils.copyProperties(req, feign);
        feign.setAccountList(accountList);
        feign.setMerchantAccountList(merchantAccountList);
        feign.setShowAccount(userService.isShowAccount());
        OffsiteExchangeOrderListPageResp orderList = null;

        //钱包分页查询订单
        BaseResult baseResult = offsiteExchangeOrderFeign.listPage(feign);
        if (!baseResult.isSuccessAndDataNotNull()) {
            return orderList;
        }

        Map<String, Object> resultMap = (Map<String, Object>) baseResult.getData();
        orderList = JSONObject.parseObject(JSONArray.toJSONString(resultMap), OffsiteExchangeOrderListPageResp.class);
        FeignQueryUserListReq userListReq = new FeignQueryUserListReq();
        userListReq.setIds(orderList.getAccountIdList());
        BaseResult userListResult = userFeign.queryList(userListReq);
        if(!userListResult.isSuccessAndDataNotNull()){
            return orderList;
        }
        UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListResult.getData()), UserListResp.class);
        Map<String, UserDetailResp> userListMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, Function.identity()));
        orderList.getRecords().forEach(dto -> {
            UserDetailResp buyer = userListMap.get(dto.getBuyerAccountId());
            UserDetailResp merchant = userListMap.get(dto.getMerchantAccountId());
            dto.setBuyerName(buyer.getName());
            dto.setBuyerTelephone(buyer.getFullTelephone().replace(buyer.getTelephone(), "+"+buyer.getTelephone()));
            dto.setMerchantName(merchant.getName());
            dto.setMerchantTelephone(merchant.getFullTelephone().replace(merchant.getTelephone(), "+"+merchant.getTelephone()));
            if(Objects.nonNull(dto.getGoodsType()) && (EnumOffsiteExchangeOrderStatus.COMPLETED.getCode().equals(dto.getStatus()) || EnumOffsiteExchangeOrderStatus.CANCELLED.getCode().equals(dto.getStatus()))){
                if(dto.getGoodsType().equals(0)){
                    dto.setMerchantRebateAmountCurrency(dto.getSourceCurrency());
                    dto.setPlatformRebateAmountCurrency(dto.getSourceCurrency());
                }else{
                    dto.setMerchantRebateAmountCurrency(dto.getTargetCurrency());
                    dto.setPlatformRebateAmountCurrency(dto.getTargetCurrency());
                }
            }
            if(dto.getPlatformRebateAmount()==null) {
            	dto.setPlatformRebateAmount(BigDecimal.ZERO);
            }
//            dto.setTotalRebateAmount(NumberUtils.add(dto.getInviteRebateAmount(),dto.getMerchantRebateAmount()));
        });
        return orderList;
    }

    @Override
    public void orderDownload(HttpServletResponse response, OffsiteExchangeOrderListPageReq req) {
        req.setSize(100000);
        OffsiteExchangeOrderListPageResp pageResp = listPage(req);
        if (CollectionUtils.isEmpty(pageResp.getRecords())) {
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        List<OffsiteExchangeOrderDownloadResp> list = new ArrayList<>();
        pageResp.getRecords().forEach(dto -> {
            OffsiteExchangeOrderDownloadResp resp = new OffsiteExchangeOrderDownloadResp();
            BeanUtils.copyProperties(dto, resp);
            resp.setSourceAmountCurrency(dto.getSourceCurrency());
            resp.setTargetAmountCurrency(dto.getTargetCurrency());
            EnumOffsiteExchangeOrderStatus status = EnumOffsiteExchangeOrderStatus.find(dto.getStatus());
            if(Objects.nonNull(status)){
                resp.setStatus(status.getDescription());
            }
            list.add(resp);
        });
        ExcelUtil.exportExcel(response, list, OFFSITE_EXCHANGE_ORDER_EXCEL_NAME, OFFSITE_EXCHANGE_ORDER_EXCEL_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }

    @Override
    public OffsiteExchangeOrderManageDetail detail(OffsiteExchangeOrderDetailReq req) {
        FeignOffsiteExchangeOrderDetailReq feign = new FeignOffsiteExchangeOrderDetailReq();
        feign.setOrderId(req.getOrderId());
        BaseResult baseResult = offsiteExchangeOrderFeign.detail(feign);
        if (!baseResult.isSuccessAndDataNotNull()) {
            throw LxtxBizException.newException("订单不存在");
        }

        Map<String, Object> resultMap = (Map<String, Object>) baseResult.getData();
        OffsiteExchangeOrderManageDetail detail = JSONObject.parseObject(JSONArray.toJSONString(resultMap), OffsiteExchangeOrderManageDetail.class);

        if (null == detail.getRealExchangeRate()) {
            detail.setRealExchangeRate(BigDecimal.ZERO);
        }

        //拼接字汇率： 1USD=1.6DC
        if(!detail.getUsePlus()){
            detail.setRate("1".concat(detail.getSourceCurrency().concat("=").concat(detail.getExchangeRate().toString()).concat(detail.getTargetCurrency())));
        }else{
            detail.setRate("1".concat(detail.getTargetCurrency().concat("=").concat(detail.getExchangeRate().toString()).concat(detail.getSourceCurrency())));
        }
        //实际汇率
        detail.setRealExchangeRateStr("1".concat(detail.getTargetCurrency().concat("=").concat(detail.getRealExchangeRate().toString()).concat(detail.getSourceCurrency())));

        if (null == detail.getPlatformRebateTotal()) {
            detail.setPlatformRebateTotal(BigDecimal.ZERO);
        }


        //平台利润: 30 CNY ≈ 5 USD
        StringBuilder sb = new StringBuilder();
        sb.append(detail.getPlatformRebateTotal().multiply(detail.getRealExchangeRate()));
        sb.append(" ").append(detail.getSourceCurrency());
        sb.append(" ≈ ").append(detail.getPlatformRebateTotal());
        sb.append(" ").append(detail.getTargetCurrency());
        detail.setPlatformRebateTotalStr(sb.toString());


        //拼接字符串： 1DC
//        detail.setSourceCurrency(detail.getSourceAmount().toString().concat(detail.getSourceCurrency()));
//        detail.setTargetCurrency(detail.getTargetAmount().toString().concat(detail.getTargetCurrency()));


        //设置status中文
        Integer status = detail.getStatus();
        if (EnumOffsiteExchangeOrderStatus.UNPAID.getCode().equals(status)) {
            detail.setStatusStr(EnumOffsiteExchangeOrderStatus.UNPAID.getDescription());
        } else if (EnumOffsiteExchangeOrderStatus.COMPLETED.getCode().equals(status)) {
            detail.setStatusStr(EnumOffsiteExchangeOrderStatus.COMPLETED.getDescription());
            //设置支付状态
            detail.setPayed(true);
        } else if (EnumOffsiteExchangeOrderStatus.CANCELLED.getCode().equals(status)) {
            detail.setStatusStr(EnumOffsiteExchangeOrderStatus.CANCELLED.getDescription());
        } else if (EnumOffsiteExchangeOrderStatus.BUYER_PAID.getCode().equals(status)) {
            detail.setStatusStr(EnumOffsiteExchangeOrderStatus.BUYER_PAID.getDescription());
        }

        //设置手机号码
        setTelephoneForDetail(detail);
        return detail;
    }

    @Override
    public BaseResult endOrder(OffisteExchangeOrderEndReq req) {
        FeignOffisteExchangeOrderEndReq feign = new FeignOffisteExchangeOrderEndReq();
        BeanUtils.copyProperties(req, feign);
        return offsiteExchangeOrderFeign.endOrder(feign);
    }

    /**
     * 订单详情设置手机号码
     *
     * @param detail
     */
    private void setTelephoneForDetail(OffsiteExchangeOrderManageDetail detail) {
        List<String> accountList = new ArrayList<>();
        accountList.add(detail.getSellerUserId());
        accountList.add(detail.getUserId());

        List<Map<String, Object>> userList = getUserByIds(accountList);
        if (!CollectionUtils.isEmpty(userList)) {
            for (Map<String, Object> map : userList) {
                String account = (String) map.get("account");
                String fullTelephone = (String) map.get("fullTelephone");
                if (detail.getUserId().equals(account)) {
                    detail.setBuyerTelephone(fullTelephone);
                }
                if (detail.getSellerUserId().equals(account)) {
                    detail.setSellerTelephone(fullTelephone);
                }
            }
        }
    }

    /**
     * 根据id集合批量获取用户
     *
     * @param accountList
     * @return
     */
    private List<Map<String, Object>> getUserByIds(List<String> accountList) {
        FeignQueryUserListReq feignQueryUserListReq = new FeignQueryUserListReq();
        feignQueryUserListReq.setIds(accountList);
        BaseResult userResult = userFeign.queryList(feignQueryUserListReq);
        if (!userResult.isSuccessAndDataNotNull()) {
            return null;
        }
        Map<String, Object> userMap = (Map<String, Object>) userResult.getData();
        return (List<Map<String, Object>>) userMap.get("list");
    }

    /**
     * 获取统计订单数据
     *
     * @param req
     * @return
     */
    @Override
    public BaseResult getOrder(FeignStatisticsOrderReq req) {
        return offsiteExchangeOrderFeign.getStatisticsOrder(req);
    }

    @Override
    public void waveRateList(Model model) {
        BaseResult baseResult = offsiteExchangeWaveRateFeign.list();
        List list = null;
        if(baseResult.isSuccessAndDataNotNull()){
            Map<String, Object> map = (Map<String, Object>) baseResult.getData();
            list = (List) map.get("list");
        }
        model.addAttribute("waveRateList", list);
    }

    private List<UserDetailResp> getUserDetailRespList(String account, String telephone, String name) {
        FeignMemberListReq userListFeignReq = new FeignMemberListReq();
        userListFeignReq.setAccount(account);
        userListFeignReq.setTelephone(telephone);
        userListFeignReq.setName(name);
        BaseResult buyerUserListResult = userFeign.list(userListFeignReq);

        if (buyerUserListResult.isSuccessAndDataNotNull()) {
            JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(buyerUserListResult.getData()));
            List<UserDetailResp> userListResp = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), UserDetailResp.class);

            return userListResp;
        }

        return null;
    }

    @Override
    public BaseResult thawOffsiteExchangeOrderMargin(OffsiteExchangeOrderThawBuyerMargin req) {
        FeignOffsiteExchangeOrderThawBuyerMargin feignReq = new FeignOffsiteExchangeOrderThawBuyerMargin();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeOrderFeign.thawOffsiteExchangeOrderMargin(feignReq);
    }
}
