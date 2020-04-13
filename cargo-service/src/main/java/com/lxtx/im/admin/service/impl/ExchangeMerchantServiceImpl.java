package com.lxtx.im.admin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.UserService;
import com.lxtx.im.admin.service.enums.EnumOffsiteExchangeGoodsType;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.im.admin.feign.feign.OffsiteExchangeMerchantFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.service.DictService;
import com.lxtx.im.admin.service.ExchangeMerchantService;
import com.lxtx.im.admin.service.enums.EnumPayMerchantCertificateStatus;
import com.lxtx.im.admin.service.enums.EnumPayMerchantGender;
import com.lxtx.im.admin.service.enums.EnumPayMerchantStatus;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.shiro.ShiroUtils;
import com.lxtx.im.admin.service.utils.ExcelUtil;

/**
 * @Author: liyunhua
 * @Date: 2019/4/23
 */
@Service
public class ExchangeMerchantServiceImpl implements ExchangeMerchantService {

	private static final String OFFSITE_EXCHANGE_MERCHANT_TRANSACTION_STATISTICS_NAME = "商家成交统计";
	
    @Autowired
    private OffsiteExchangeMerchantFeign offsiteExchangeMerchantFeign;
    
    @Autowired
    private UserFeign userFeign;
    
    @Autowired
    private UserService userService;

    @Override
    public BaseResult listPage(ExchangeMerchantListPageReq req) {
        //平台用户查询条件
        String account = req.getAccount();
        String name = req.getName();
        String telephone = req.getTelephone();
        //钱包查询条件

        //返回参数
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);

        //列表以wallet商家表为主，
        //如果平台用户查询条件不为空，先查平台用户id集合，带到wallet查询
        List<String> accountList = new ArrayList<>();
        if (StringUtils.isNotBlank(account) || StringUtils.isNotBlank(name) || StringUtils.isNotBlank(telephone)) {
            FeignMemberListReq feignMemberListReq = new FeignMemberListReq();
            BeanUtils.copyProperties(req, feignMemberListReq);
            BaseResult coreResult = userFeign.list(feignMemberListReq);
            if (coreResult.isSuccess() && coreResult.getData() != null) {
                Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
                String coreJsonResult = JSONArray.toJSONString(coreDataMap);
                UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
                List<UserDetailResp> userDetailResps = userListResp.getList();
                if (CollectionUtils.isEmpty(userDetailResps)) {
                    return BaseResult.success();
                }
                if (CollectionUtils.isNotEmpty(userDetailResps)) {
                    for (UserDetailResp user : userDetailResps) {
                        accountList.add(user.getAccount());
                    }
                }
            }
        }

        FeignExchangeMerchantListPageReq feignReq = new FeignExchangeMerchantListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        feignReq.setField("create_time");
        feignReq.setOrder("desc");

        //平台条件能查询到数据，将平台用户id集合带到钱包查询
        if (CollectionUtils.isNotEmpty(accountList)) {
            feignReq.setUserIds(accountList);
        }

        if(userService.isShowAccount()){
            BaseResult userBaseResult = userFeign.selectKhUser();
            if(userBaseResult.isSuccess()&&null!=userBaseResult.getData()){
                List<String> userAccountList = (List<String>) ((Map<String, Object>) userBaseResult.getData()).get("list");
                feignReq.setKhUserAccountList(userAccountList);
            }
        }

        BaseResult walletResult = offsiteExchangeMerchantFeign.listPage(feignReq);
        if (walletResult.isSuccess() && walletResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) walletResult.getData();
            String jsonResult = JSONArray.toJSONString(dataMap);
            ExchangeMerchantListPageResp listPageResp = JSONObject.parseObject(jsonResult, ExchangeMerchantListPageResp.class);
            List<ExchangeMerchantResp> exchangeMerchantRespList = listPageResp.getRecords();
            //平台用户id集合
            List<String> accountIds = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(exchangeMerchantRespList)) {
                for (ExchangeMerchantResp exchangeMerchantResp : exchangeMerchantRespList) {
                    accountIds.add(exchangeMerchantResp.getAccount());
                }
                if (CollectionUtils.isEmpty(accountIds)) {
                    baseResult.setSuccess(true);
                    baseResult.setData(listPageResp);
                    return baseResult;
                }
                //调用imcore接口查询im用户资料,组装IM用户名、手机号码等信息
                assembleUserInfos(accountIds, exchangeMerchantRespList);
            }
            baseResult.setSuccess(true);
            baseResult.setData(listPageResp);
        }
        return baseResult;
    }

    private void assembleUserInfos(List<String> accountList, List<ExchangeMerchantResp> exchangeMerchantRespList) {
        FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
        queryUserListReq.setIds(accountList);
        BaseResult coreResult = userFeign.queryList(queryUserListReq);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            Map<String, Object> coreDataMap = (Map<String, Object>) coreResult.getData();
            String coreJsonResult = JSONArray.toJSONString(coreDataMap);
            UserListResp userListResp = JSONObject.parseObject(coreJsonResult, UserListResp.class);
            List<UserDetailResp> userDetailResps = userListResp.getList();
            if (CollectionUtils.isNotEmpty(userDetailResps)) {
                Map<String, UserDetailResp> userDetailRespMap = new HashMap<>();
                // 封装用户信息
                for (UserDetailResp userResp : userDetailResps) {
                    userDetailRespMap.put(userResp.getAccount(), userResp);
                }
                for (ExchangeMerchantResp exchangeMerchantResp : exchangeMerchantRespList) {
                    UserDetailResp userDetailResp = userDetailRespMap.get(exchangeMerchantResp.getAccount());
                    if (userDetailResp != null) {
                        exchangeMerchantResp.setTelephone(userDetailResp.getTelephone());
                        exchangeMerchantResp.setAccount(userDetailResp.getAccount());
                        exchangeMerchantResp.setName(userDetailResp.getName());
                    }
                }
            }
        }
    }

    @Override
    public String detail(ExchangeMerchantDetailReq req, Model model) {
        FeignExchangeMerchantDetailReq feignReq = new FeignExchangeMerchantDetailReq();
        feignReq.setId(req.getId());
        BaseResult detailResult = offsiteExchangeMerchantFeign.detail(feignReq);
        if (!detailResult.isSuccess() || detailResult.getData() == null) {
            throw LxtxBizException.newException("获取不到商家详情!");
        }
        Map<String, Object> dataMap = (Map<String, Object>) detailResult.getData();
        String jsonResult = JSONArray.toJSONString(dataMap);
        ExchangeMerchantDetailResp detailResp = JSONObject.parseObject(jsonResult, ExchangeMerchantDetailResp.class);
        detailResp.setCertificateFront(PropertiesUtil.getFileViewUrl(detailResp.getCertificateFront()));
        detailResp.setCertificateBack(PropertiesUtil.getFileViewUrl(detailResp.getCertificateBack()));
        detailResp.setCertificateSignature(PropertiesUtil.getFileViewUrl(detailResp.getCertificateSignature()));
        detailResp.setId(req.getId());
        Integer gender = detailResp.getGender();
        EnumPayMerchantGender enumPayMerchantGender = EnumPayMerchantGender.find(gender);
        if (enumPayMerchantGender != null) {
            detailResp.setGenderName(enumPayMerchantGender.getDescription());
        }
        model.addAttribute("detail", detailResp);

        //根据商家审核状态和帐号冻结状态跳转页面
        Integer status = detailResp.getStatus();
        if (EnumPayMerchantStatus.FORBIDDEN.getCode().equals(status)) {
            return "offsiteExchange/exchange-merchant-detail-read";
        }

        //待审核状态的跳转到审核详情页面，其他状态的跳转到只读页面
        Integer certificateStatus = detailResp.getCertificateStatus();
        if (EnumPayMerchantCertificateStatus.TO_BE_AUDITED.getCode().equals(certificateStatus)) {
            return "offsiteExchange/exchange-merchant-detail";
        }
        //已审核状态跳转到已审核商家祥情页面
        if (EnumPayMerchantCertificateStatus.CERTIFIED.getCode().equals(certificateStatus)) {
            return "offsiteExchange/exchange-merchant-detail-certified";
        }
        return "offsiteExchange/exchange-merchant-detail-read";
    }

    @Override
    public BaseResult verify(ExchangeMerchantVerifyReq req) {
        FeignExchangeMerchantVerifyReq feignReq = new FeignExchangeMerchantVerifyReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeMerchantFeign.verify(feignReq);
    }

    @Override
    public BaseResult cancel(ExchangeMerchantCancelReq req) {
        FeignExchangeMerchantCancelReq feignReq = new FeignExchangeMerchantCancelReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeMerchantFeign.cancel(feignReq);
    }

    @Override
    public BaseResult updateStatus(ExchangeMerchantUpdateStatusReq req) {
        FeignExchangeMerchantUpdateStatusReq feignReq = new FeignExchangeMerchantUpdateStatusReq();
        BeanUtils.copyProperties(req, feignReq);
        return offsiteExchangeMerchantFeign.updateStatus(feignReq);
    }

    @Override
    public BaseResult updateMerchantWaveRate(String id, BigDecimal merchantWaveRate) {
        FeignOffsiteExchangeMerchantUpdateWaveRateReq feignOffsiteExchangeMerchantUpdateWaveRateReq = new FeignOffsiteExchangeMerchantUpdateWaveRateReq();
        feignOffsiteExchangeMerchantUpdateWaveRateReq.setId(id);
        feignOffsiteExchangeMerchantUpdateWaveRateReq.setMerchantWaveRate(NumberUtils.divide(merchantWaveRate,new BigDecimal(100),12));
        return offsiteExchangeMerchantFeign.updateMerchantWaveRate(feignOffsiteExchangeMerchantUpdateWaveRateReq);
    }

	@Override
	public OffsiteExchangeMerchantTransactionStatisticsListPageResp merchantTransactionStatisticsListPage(ExchangeMerchantTransactionStatisticsListPageReq req) {
		OffsiteExchangeMerchantTransactionStatisticsListPageResp resp = new OffsiteExchangeMerchantTransactionStatisticsListPageResp();
		List<String> accountList = null;
        if(StringUtils.isNotBlank(req.getAccount()) || StringUtils.isNotBlank(req.getName()) || StringUtils.isNotBlank(req.getTelephone())){
            FeignMemberListReq userListFeignReq = new FeignMemberListReq();
            BeanUtils.copyProperties(req, userListFeignReq);
            if(StringUtils.isNotBlank(req.getAccount())){
                String[] split = req.getAccount().split(",");
                if(split.length > 1){
                    userListFeignReq.setAccountList(Lists.newArrayList(split));
                    userListFeignReq.setAccount(null);
                }
            }
            BaseResult userListResult = userFeign.list(userListFeignReq);
            if (!userListResult.isSuccessAndDataNotNull()) {
                return resp;
            }
            JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(userListResult.getData()));
            List<UserDetailResp> userListResp = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), UserDetailResp.class);
            accountList = userListResp.stream().map(UserDetailResp::getAccount).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(accountList)){
                return resp;
            }
        }

        FeignExchangeMerchantTransactionStatisticsListPageReq feignReq = new FeignExchangeMerchantTransactionStatisticsListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        feignReq.setAccount(accountList);
        feignReq.setShowAccount(userService.isShowAccount());
        BaseResult baseResult = offsiteExchangeMerchantFeign.merchantTransactionStatisticsListPage(feignReq);
        if(baseResult.isSuccessAndDataNotNull()){
            Map map = (Map) baseResult.getData();
            resp = JSON.parseObject(JSON.toJSONString(map), OffsiteExchangeMerchantTransactionStatisticsListPageResp.class);
            List<OffsiteExchangeMerchantTransactionStatisticsListPageResp.OffsiteExchangeMerchantTransactionStatisticsDTO> records = resp.getRecords();
            if(!CollectionUtils.isEmpty(records)){
                accountList = records.stream().map(OffsiteExchangeMerchantTransactionStatisticsListPageResp.OffsiteExchangeMerchantTransactionStatisticsDTO::getAccount).collect(Collectors.toList());
                FeignQueryUserListReq userListFeignReq = new FeignQueryUserListReq();
                userListFeignReq.setIds(accountList);
                BaseResult userListResult = userFeign.queryList(userListFeignReq);
                if(userListResult.isSuccessAndDataNotNull()){
                    UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListResult.getData()), UserListResp.class);
                    Map<String, UserDetailResp> userListMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, Function.identity()));
                    records.forEach(dto -> {
                        UserDetailResp userDetailResp = userListMap.get(dto.getAccount());
                        if(Objects.nonNull(userDetailResp)){
                            dto.setName(userDetailResp.getName());
                            dto.setTelephone(userDetailResp.getFullTelephone().replace(userDetailResp.getTelephone(), "+"+userDetailResp.getTelephone()));
                        }
                    });
                }
            }
            return resp;
        }
        return resp;
	}

	@Override
	public void exportExcel(HttpServletResponse response, ExchangeMerchantTransactionStatisticsListPageReq req) {
        req.setSize(100000);
        OffsiteExchangeMerchantTransactionStatisticsListPageResp pageResp = merchantTransactionStatisticsListPage(req);
        if(CollectionUtils.isEmpty(pageResp.getRecords())){
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        List<OffsiteExchangeMerchantTransactionStatisticsDownloadResp> downloadRespList = Lists.newArrayList();
        pageResp.getRecords().forEach(dto -> {
        	OffsiteExchangeMerchantTransactionStatisticsDownloadResp downloadResp = new OffsiteExchangeMerchantTransactionStatisticsDownloadResp();
            BeanUtils.copyProperties(dto, downloadResp);
            downloadResp.setWaveRate(dto.getSourceCoin()+" => "+dto.getTargetCoin());
            downloadResp.setCompletedAmountCoinName(dto.getTargetCoin());
            downloadResp.setCompletedVolumeCoinName(dto.getSourceCoin());
            downloadResp.setFloatScopePercent(dto.getFloatScopePercent().toPlainString()+"%");
            downloadRespList.add(downloadResp);
        });
        ExcelUtil.exportExcel(response, downloadRespList, OFFSITE_EXCHANGE_MERCHANT_TRANSACTION_STATISTICS_NAME, OFFSITE_EXCHANGE_MERCHANT_TRANSACTION_STATISTICS_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }


    @Override
    public OffsiteExchangeGoodsListPageResp merchantGoodsStatisticsListPage(ExchangeGoodsListPageReq req) {
        OffsiteExchangeGoodsListPageResp resp = new OffsiteExchangeGoodsListPageResp();
        List<String> accountList = null;
        if(StringUtils.isNotBlank(req.getAccount()) || StringUtils.isNotBlank(req.getName()) || StringUtils.isNotBlank(req.getTelephone())){
            FeignMemberListReq userListFeignReq = new FeignMemberListReq();
            BeanUtils.copyProperties(req, userListFeignReq);
            if(StringUtils.isNotBlank(req.getAccount())){
                String[] split = req.getAccount().split(",");
                if(split.length > 1){
                    userListFeignReq.setAccountList(Lists.newArrayList(split));
                    userListFeignReq.setAccount(null);
                }
            }
            BaseResult userListResult = userFeign.list(userListFeignReq);
            if (!userListResult.isSuccessAndDataNotNull()) {
                return resp;
            }
            JSONObject jsonObjectTmp = JSONObject.parseObject(JSON.toJSONString(userListResult.getData()));
            List<UserDetailResp> userListResp = JSONObject.parseArray(jsonObjectTmp.getString(BaseResult.LIST), UserDetailResp.class);
            accountList = userListResp.stream().map(UserDetailResp::getAccount).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(accountList)){
                return resp;
            }
        }

        FeignExchangeGoodsListPageReq feignReq = new FeignExchangeGoodsListPageReq();
        BeanUtils.copyProperties(req, feignReq);
        feignReq.setAccount(accountList);
        feignReq.setShowAccount(userService.isShowAccount());
        BaseResult baseResult = offsiteExchangeMerchantFeign.merchantGoodsStatisticsListPage(feignReq);
        if(baseResult.isSuccessAndDataNotNull()){
            Map map = (Map) baseResult.getData();
            resp = JSON.parseObject(JSON.toJSONString(map), OffsiteExchangeGoodsListPageResp.class);
            List<OffsiteExchangeGoodsListPageResp.OffsiteExchangeGoodsStatisticsDTO> records = resp.getRecords();
            if(!CollectionUtils.isEmpty(records)){
                accountList = records.stream().map(OffsiteExchangeGoodsListPageResp.OffsiteExchangeGoodsStatisticsDTO::getAccount).collect(Collectors.toList());
                FeignQueryUserListReq userListFeignReq = new FeignQueryUserListReq();
                userListFeignReq.setIds(accountList);
                BaseResult userListResult = userFeign.queryList(userListFeignReq);
                if(userListResult.isSuccessAndDataNotNull()){
                    UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(userListResult.getData()), UserListResp.class);
                    Map<String, UserDetailResp> userListMap = userListResp.getList().stream().collect(Collectors.toMap(UserDetailResp::getAccount, Function.identity()));
                    records.forEach(dto -> {
                        UserDetailResp userDetailResp = userListMap.get(dto.getAccount());
                        if(Objects.nonNull(userDetailResp)){
                            dto.setName(userDetailResp.getName());
                            dto.setTelephone(userDetailResp.getFullTelephone().replace(userDetailResp.getTelephone(), "+"+userDetailResp.getTelephone()));
                        }
                    });
                }
            }
            return resp;
        }
        return resp;
    }


    @Override
    public void goodsTransactionExportExcel(HttpServletResponse response, ExchangeGoodsListPageReq req) {
        req.setSize(100000);
        OffsiteExchangeGoodsListPageResp pageResp = merchantGoodsStatisticsListPage(req);
        if(CollectionUtils.isEmpty(pageResp.getRecords())){
            throw LxtxBizException.newException("没有数据可导出！！");
        }
        List<OffsiteExchangeMerchantGoodsStatisticsDownloadResp> downloadRespList = Lists.newArrayList();
        pageResp.getRecords().forEach(dto -> {
            OffsiteExchangeMerchantGoodsStatisticsDownloadResp downloadResp = new OffsiteExchangeMerchantGoodsStatisticsDownloadResp();
            BeanUtils.copyProperties(dto, downloadResp);
            String targetCoin = dto.getTargetCoin();
            String sourceCoin = dto.getSourceCoin();
            downloadResp.setWaveRate(sourceCoin +" => "+ targetCoin);
            downloadResp.setCompletedAmountCoinName(targetCoin);
            downloadResp.setCompletedVolumeCoinName(sourceCoin);
            downloadResp.setFloatScopePercent(dto.getFloatScopePercent().toPlainString()+"%");

            if(EnumOffsiteExchangeGoodsType.MARGIN.getCode().equals(dto.getGoodsType())){
                downloadResp.setMerchantRebateAmountCoinName(targetCoin);
                downloadResp.setPlatformRebateAmountCoinName(targetCoin);
            }else {
                downloadResp.setMerchantRebateAmountCoinName(sourceCoin);
                downloadResp.setPlatformRebateAmountCoinName(sourceCoin);
            }

            downloadRespList.add(downloadResp);
        });
        ExcelUtil.exportExcel(response, downloadRespList, OFFSITE_EXCHANGE_MERCHANT_TRANSACTION_STATISTICS_NAME, OFFSITE_EXCHANGE_MERCHANT_TRANSACTION_STATISTICS_NAME, DateUtils.DATE_FORMAT_YYYY_MM_DD);
    }


}
