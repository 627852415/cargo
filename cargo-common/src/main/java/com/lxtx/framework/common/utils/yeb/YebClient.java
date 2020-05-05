package com.lxtx.framework.common.utils.yeb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.framework.common.utils.yeb.model.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * author lin hj on 2019/3/29
 * 余额宝接口
 */
public class YebClient extends BaseYebClient {


    public static void main(String[] args) {

        String ss = "{\n" +
                "    \"code\": 0,\n" +
                "    \"message\": \"ok\",\n" +
                "    \"result\":\n" +
                "    {\n" +
                "        \"principle\":\"10100.52\",\n" +
                "        \"interest\":\"20.52\",\n" +
                "        \"chain\":\"123\"\n" +
                "    }\n" +
                "}";

        YebResultDTO<YebBalanceRes> ddd =
                JSON.parseObject(ss, new TypeReference<YebResultDTO<YebBalanceRes>>() {
                });

        YebBalanceRes yebBalanceRes = ddd.getData();
        System.out.println("sds");

    }

    /**
     * 存入余额<br>
     * 从托管钱包存入余额宝本金<br>
     * 将指定数量的资金从热钱包存入余额宝
     *
     * @param yebParameter
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebOrderRes> deposit(YebParameter yebParameter) throws Exception {

        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("&mchId=" + getMchId());
        paramsBuilder.append("&userId=" + yebParameter.getUserId());
        if (StringUtils.isNotEmpty(yebParameter.getReferAddr())) {
            paramsBuilder.append("&referAddr=" + yebParameter.getReferAddr());
        }
        paramsBuilder.append("&coinType=" + yebParameter.getCoinType());
        paramsBuilder.append("&amount=" + yebParameter.getAmount());
        paramsBuilder.append("&serial=" + yebParameter.getSerial());
        paramsBuilder.append("&apikey=" + getApiKey());

        String signInfo = getMchId() + yebParameter.getUserId() + yebParameter.getReferAddr() + yebParameter.getCoinType()
                + yebParameter.getAmount() + yebParameter.getSerial() + getApiKey();
        String result = executeHttp("/merchant/v1/deposit", paramsBuilder, false, signInfo);
        return transferToResultDto(result, new TypeReference<YebResultDTO<YebOrderRes>>() {
        });
    }


    /**
     * 实现设置指定余额宝托管地址的上级
     *
     * @return
     * @throws Exception
     */
    public YebResultDTO<String> referId(String userId, String referUserId) throws Exception {

        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("&mchId=" + getMchId());
        paramsBuilder.append("&userId=" + userId);
        paramsBuilder.append("&referUserId=" + referUserId);
        paramsBuilder.append("&apikey=" + getApiKey());

        String signInfo = getMchId() + userId + referUserId + getApiKey();
        String result = executeHttp("/merchant/v1/referId", paramsBuilder, false, signInfo);
        return transferToResultDto(result, new TypeReference<YebResultDTO<String>>() {
        });
    }

    /**
     * 提取余额宝存款资金
     *
     * @param yebParameter
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebOrderRes> withdrawYeb(YebParameter yebParameter) throws Exception {

        //去除无效0
        yebParameter.setAmount(NumberUtils.formatBigDecimalString(
                new BigDecimal(yebParameter.getAmount())));

        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("&mchId=" + getMchId());
        paramsBuilder.append("&userId=" + yebParameter.getUserId());
        paramsBuilder.append("&coinType=" + yebParameter.getCoinType());
        paramsBuilder.append("&accountType=" + yebParameter.getAccountType());
        paramsBuilder.append("&amount=" + yebParameter.getAmount());
        paramsBuilder.append("&serial=" + yebParameter.getSerial());
        paramsBuilder.append("&receiver=" + yebParameter.getReceiver());
        paramsBuilder.append("&apikey=" + getApiKey());

        String signInfo = getMchId() + yebParameter.getUserId() + yebParameter.getCoinType()
                + yebParameter.getAccountType() + yebParameter.getAmount() + yebParameter.getSerial()
                + yebParameter.getReceiver() + getApiKey();

        String result = executeHttp("/merchant/v1/withdraw/yeb", paramsBuilder, false, signInfo);
        return transferToResultDto(result, new TypeReference<YebResultDTO<YebOrderRes>>() {
        });
    }


    /**
     * 提取托管钱包链上资金
     * 提取托管在余额宝后台的钱包链上资金，注意只是链上的资金，非余额宝合约资金
     *
     * @param yebParameter
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebOrderRes> withdrawChain(YebParameter yebParameter) throws Exception {

        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("&mchId=" + getMchId());
        paramsBuilder.append("&userId=" + yebParameter.getUserId());
        paramsBuilder.append("&coinType=" + yebParameter.getCoinType());
        paramsBuilder.append("&amount=" + yebParameter.getAmount());
        paramsBuilder.append("&serial=" + yebParameter.getSerial());
        paramsBuilder.append("&receiver=" + yebParameter.getReceiver());
        paramsBuilder.append("&apikey=" + getApiKey());

        String signInfo = getMchId() + yebParameter.getUserId() + yebParameter.getCoinType()
                + yebParameter.getAmount() + yebParameter.getSerial() + yebParameter.getReceiver() + getApiKey();

        String result = executeHttp("/merchant/v1/withdraw/chain", paramsBuilder, false, signInfo);
        return transferToResultDto(result, new TypeReference<YebResultDTO<YebOrderRes>>() {
        });
    }


    /**
     * 查询托管钱包余额
     * 获取托管在余额宝后台的钱包资金余额状态
     *
     * @param userId
     * @param coinType 币种名称，例如DC
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebBalanceRes> selectbalance(String userId, String coinType) throws Exception {
        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("?mchId=" + getMchId());
        paramsBuilder.append("&userId=" + userId);
        paramsBuilder.append("&coinType=" + coinType);
        paramsBuilder.append("&apikey=" + getApiKey());

        String result = executeSimpleHttp("/merchant/v1/balance", paramsBuilder, true);
        return transferToResultDto(result, new TypeReference<YebResultDTO<YebBalanceRes>>() {
        });
    }

    /**
     * 查询商户的用户存取交易明细
     * 获取商户的某个用户历史余额宝存取明细
     *
     * @param yebParameter
     * @return
     * @throws Exception
     */
    public YebResultDTO transactions(YebParameter yebParameter) throws Exception {
        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("?mchId=" + getMchId());
        paramsBuilder.append("&userId=" + yebParameter.getUserId());
        paramsBuilder.append("&beginTime=" + yebParameter.getBeginTime());
        paramsBuilder.append("&endTime=" + yebParameter.getEndTime());
        paramsBuilder.append("&pageNo=" + yebParameter.getPageNo());
        paramsBuilder.append("&pageSize=" + yebParameter.getPageSize());
        paramsBuilder.append("&apikey=" + getApiKey());

        return executeSimple("/merchant/v1/balance", paramsBuilder);
    }

    /**
     * 查询指定流水号的结果
     * 查询指定一笔流水的当前状态
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebOrderDetailRes> detail(String orderId) throws Exception {
        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("?mchId=" + getMchId());
        paramsBuilder.append("&orderId=" + orderId);
        paramsBuilder.append("&apikey=" + getApiKey());
        String result = executeSimpleHttp("/merchant/v1/detail", paramsBuilder, true);
        return transferToResultDto(result, new TypeReference<YebResultDTO<YebOrderDetailRes>>() {
        });
    }

    /**
     * 查询历史利率信息
     * 获取余额宝历史利率信息（最新收益/收益明细、7日年化、万份收益）
     *
     * @param address
     * @param type    获取统计类型信息0：最新收益、7日年化、万份收益 1：收益明细 2：7日年化 3：万份收益
     * @param pageNum 请求页数
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebRateHistoryRes> statistic(String address, Integer type, Integer pageNum, String coin) throws Exception {
        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("?type=" + type);
        paramsBuilder.append("&pageNum=" + pageNum);
        paramsBuilder.append("&coin=" + coin);
        String result = executeHttpGet("/user/statistice/" + address, paramsBuilder);

        return transferToResultDto(result, new TypeReference<YebResultDTO<YebRateHistoryRes>>() {
        });
    }

    /**
     * 获取代理返佣收益
     *
     * @param address
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebUserProxyEaringsRes> statisticProxy(String address, Integer page, Integer size, String coin) throws Exception {
        StringBuilder paramsBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(coin)) {
            paramsBuilder.append("?coin=" + coin);
        }
        String result = executeHttpGet("/user/proxyRebateDetail/" + address + "/" + page + "/" + size, paramsBuilder);
        return transferToResultDto(result, new TypeReference<YebResultDTO<YebUserProxyEaringsRes>>() {
        });
    }


    /**
     * 注册新用户
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public YebResultDTO<YebUserRes> addUser(String userId) throws Exception {
        StringBuilder paramsBuilder = new StringBuilder();
        paramsBuilder.append("&mchId=" + getMchId());
        paramsBuilder.append("&userId=" + userId);
        paramsBuilder.append("&apikey=" + getApiKey());


        String signInfo = getMchId() + userId + getApiKey();
        String result = executeHttp("/merchant/v1/add", paramsBuilder, true, signInfo);
        return transferToResultDto(result, new TypeReference<YebResultDTO<YebUserRes>>() {
        });
    }


}
