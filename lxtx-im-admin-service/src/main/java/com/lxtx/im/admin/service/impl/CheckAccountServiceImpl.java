package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.dao.model.AccountCheckMistake;
import com.lxtx.im.admin.dao.model.Coin;
import com.lxtx.im.admin.feign.feign.CheckAccountFeign;
import com.lxtx.im.admin.feign.feign.CheckAccountNameFeign;
import com.lxtx.im.admin.feign.request.CheckName;
import com.lxtx.im.admin.feign.request.FeignPlatformAllCoinCheckTimeReq;
import com.lxtx.im.admin.feign.request.PlatNameReq;
import com.lxtx.im.admin.service.CheckAccountService;
import com.lxtx.im.admin.service.CoinService;
import com.lxtx.im.admin.service.Constants.Constants;
import com.lxtx.im.admin.service.request.CoinDetailReq;
import com.lxtx.im.admin.service.request.PlatformAllCoinCheckTimeReq;
import com.lxtx.im.admin.service.utils.FastJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 对账数据展示
 *
 * @author pengpai
 */
@Service
public class CheckAccountServiceImpl implements CheckAccountService {

    @Autowired
    private CheckAccountFeign checkAccountFeign;
    @Autowired
    private CheckAccountNameFeign checkAccountNameFeign;

    @Autowired
    private CoinService coinService;

    @Override
    public BaseResult getPlatformlCoinBatchDetail(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        if (platformAllCoinAccount != null) {
            if (StringUtils.isBlank(platformAllCoinAccount.getCheckTime())) {
                return BaseResult.error(Constants.CHECK_TIME_NULL, null);
            }
            if (StringUtils.isBlank(platformAllCoinAccount.getCoinId())) {
                return BaseResult.error(Constants.CHECK_COIN_NULL, null);
            }
            FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
            BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
            BaseResult platformlCoinBatchDetail = checkAccountFeign
                    .getPlatformlCoinBatchDetail(feignPlatformAllCoinCheckTimeReq);
            Map<String, Object> map = (Map<String, Object>) platformlCoinBatchDetail.getData();
            if (CollectionUtils.isEmpty(map)) {
                map.put("billTime", platformAllCoinAccount.getCheckTime());
                map.put("totalAmount", "0");
                map.put("currencyAmount", "0");
                map.put("diffBillNum", "0");
                map.put("diffTotalAmount", "0");
                CoinDetailReq coinDetailReq = new CoinDetailReq();
                coinDetailReq.setCoinId(platformAllCoinAccount.getCoinId());
                map.put("coinId", platformAllCoinAccount.getCoinId());
                BaseResult baseResult = coinService.selectOne(coinDetailReq);
                Map<String, Object> data = (Map<String, Object>) baseResult.getData();
                if (CollectionUtils.isEmpty(data)) {
                    map.put("coinName", "数据有误");
                } else {
                    String coinName = (String) data.get("coinName");
                    map.put("coinName", coinName);
                }
                platformlCoinBatchDetail.setData(map);
            }
            return platformlCoinBatchDetail;
        }
        return BaseResult.error(Constants.CHECK_REQUEST_NULL, null);
    }

    @Override
    public BaseResult getCoinMistakeRecords(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        if (platformAllCoinAccount != null) {
            if (StringUtils.isBlank(platformAllCoinAccount.getCheckTime())) {
                return BaseResult.error(Constants.CHECK_TIME_NULL, null);
            }
            if (StringUtils.isBlank(platformAllCoinAccount.getCoinId())) {
                return BaseResult.error(Constants.CHECK_COIN_NULL, null);
            }
            FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
            BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
            BaseResult coinMistakeRecords = checkAccountFeign.getCoinMistakeRecords(feignPlatformAllCoinCheckTimeReq);
            if (coinMistakeRecords.isSuccess()) {
                Object data = coinMistakeRecords.getData();
                if (data != null) {
                    String jsonString = FastJsonUtil.toJSONString(data, false);
                    JSONObject parseObject = JSON.parseObject(jsonString);
                    Object object = parseObject.get("records");
                    String recordStr = FastJsonUtil.toJSONString(object, false);
                    List<AccountCheckMistake> records = FastJsonUtil.getList(recordStr, AccountCheckMistake.class);
                    for (AccountCheckMistake accountCheckMistake : records) {
                        String merchantNo = accountCheckMistake.getMerchantNo();
                        PlatNameReq platNameReq = new PlatNameReq();
                        platNameReq.setId(merchantNo);
                        BaseResult platIdById = checkAccountFeign.getPlatIdById(platNameReq);
                        if (platIdById.isSuccess()) {
                            Object plat = platIdById.getData();
                            if (plat != null) {
                                String jsonString2 = FastJsonUtil.toJSONString(plat, false);
                                CheckName user = new CheckName();
                                user.setAccount(jsonString2);
                                String selectByUser = checkAccountNameFeign.selectByUser(user);
                                accountCheckMistake.setMerchantName(selectByUser);
                            }
                        }
                    }
                    parseObject.put("records", records);
                    return BaseResult.success(parseObject);
                }
            }
        }
        return BaseResult.error(Constants.CHECK_REQUEST_NULL, null);
    }

    @Override
    public BaseResult getMistakeAmountAndMistakeCount(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        if (platformAllCoinAccount != null) {
            if (StringUtils.isBlank(platformAllCoinAccount.getCheckTime())) {
                return BaseResult.error(Constants.CHECK_TIME_NULL, null);
            }
            if (StringUtils.isBlank(platformAllCoinAccount.getCoinId())) {
                return BaseResult.error(Constants.CHECK_COIN_NULL, null);
            }
            FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
            BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
            return checkAccountFeign.getMistakeAmountAndMistakeCount(feignPlatformAllCoinCheckTimeReq);
        }
        return BaseResult.error(Constants.CHECK_REQUEST_NULL, null);
    }

    @Override
    public BaseResult getTrendData(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        if (platformAllCoinAccount != null) {
            if (StringUtils.isBlank(platformAllCoinAccount.getCheckTime())) {
                return BaseResult.error(Constants.CHECK_TIME_NULL, null);
            }
            if (StringUtils.isBlank(platformAllCoinAccount.getCoinId())) {
                return BaseResult.error(Constants.CHECK_COIN_NULL, null);
            }
            FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
            BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
            return checkAccountFeign.getTrendData(feignPlatformAllCoinCheckTimeReq);
        }
        return BaseResult.error(Constants.CHECK_REQUEST_NULL, null);
    }

    @Override
    public List<Coin> getAllCoin() {
        BaseResult allCoin = checkAccountFeign.getAllCoin();
        if (allCoin.isSuccess()) {
            Object data = allCoin.getData();
            if (data != null) {
                String jsonString = FastJsonUtil.toJSONString(data, false);
                List<Coin> list = FastJsonUtil.getList(jsonString, "list", Coin.class);
                return list;
            }
        }
        return null;
    }

    @Override
    public BaseResult getFlowDetail(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
        BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
        BaseResult flowDetail = checkAccountFeign.getFlowDetail(feignPlatformAllCoinCheckTimeReq);
        return flowDetail;
    }

    @Override
    public BaseResult getChartTotalData(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
        BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
        BaseResult chartTotalData = checkAccountFeign.getChartTotalData(feignPlatformAllCoinCheckTimeReq);
        return chartTotalData;
    }

    @Override
    public BaseResult getChartCurrencyData(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
        BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
        BaseResult chartCurrencyData = checkAccountFeign.getChartCurrencyData(feignPlatformAllCoinCheckTimeReq);
        return chartCurrencyData;
    }

    @Override
    public BaseResult getChartDiffData(PlatformAllCoinCheckTimeReq platformAllCoinAccount) {
        FeignPlatformAllCoinCheckTimeReq feignPlatformAllCoinCheckTimeReq = new FeignPlatformAllCoinCheckTimeReq();
        BeanUtils.copyProperties(platformAllCoinAccount, feignPlatformAllCoinCheckTimeReq);
        BaseResult chartDiffData = checkAccountFeign.getChartDiffData(feignPlatformAllCoinCheckTimeReq);
        return chartDiffData;
    }
}
