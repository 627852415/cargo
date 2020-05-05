package com.lxtx.framework.common.utils.sixx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.beust.jcommander.internal.Maps;
import com.lxtx.framework.common.constants.PropertiesContants;
import com.lxtx.framework.common.exception.LxtxException;
import com.lxtx.framework.common.utils.JsonRpcUtil;
import com.lxtx.framework.common.utils.NumberUtils;
import com.lxtx.framework.common.utils.encrypt.Sha256Hmac;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.lxtx.framework.common.utils.http.method.Post;
import com.lxtx.framework.common.utils.http.okhttp.OkHttpUtil;
import com.lxtx.framework.common.utils.sixx.model.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Czh
 * Date: 2018/8/15 下午5:14
 */
public class SixxClient {
    private static Logger logger = LoggerFactory.getLogger(SixxClient.class);

    private static final String SIGNATURE_METHOD = "HmacSHA256";
    private static final String SIGNATURE_VERSION = "1";
    private static final String REMARK_TMP = ",\"remark\":\"";
    /**
     * 待签名的数据
     */
    private static StringBuilder prefixData = new StringBuilder();

    private String getHost(){
        return  PropertiesUtil.getString(PropertiesContants.X6_CLIENT_HOST);
    }
    private String getHttp(){
        return  PropertiesUtil.getString(PropertiesContants.X6_CLIENT_HTTP);
    }

    private String getApiSecretKey(){
        return  PropertiesUtil.getString(PropertiesContants.X6_CLIENT_API_SECRET_KEY);
    }

    private String getAccessKeyId(){
        return  PropertiesUtil.getString(PropertiesContants.X6_CLIENT_ACCESS_KEY_ID);
    }

    /**
     * 临时前缀
     */
    private static String TEMP_URL_PRE = StringUtils.EMPTY;
    /**
     * 订单不存在
     */
    public static String CODE_ENUM_617 = "617";

    //------------------------------------------------------------用户接口开始---------------------------------------------------------------------------

    /**
     * 新增6x用户
     *
     * @param addUserReq
     * @return ResultDTO（需要根据状态码codeEnum判断）
     * @throws Exception
     * @Author cctv
     */
    public ResultDTO addUserReDto(AddUserReq addUserReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/user/add/" + addUserReq.getMemberId();
        String result = invoke(signUrl, bulidBody());
        ResultDTO<Map<String, Object>> resultDto = new ResultDTO<>();
        resultDto = JSON.parseObject(result, resultDto.getClass());
        return resultDto;
    }


    /**
     * 1.2、获取用户详细信息
     *
     * @param getUserReq
     * @return
     */
    public UserResultPojo getUserDetail(GetUserReq getUserReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/user/detail/" + getUserReq.getUserId();
        String result = invoke(signUrl, bulidBody());

        TypeReference<ResultDTO<UserResultPojo>> typeReference = new TypeReference<ResultDTO<UserResultPojo>>() {
        };
        ResultDTO<UserResultPojo> resultDto = transferToResultDto(result, typeReference);
        return resultDto.getData();
    }

    /**
     * 1.3、获取用户的余额信息
     *
     * @param getUserReq
     * @return
     */
    public List<BalanceAmountResultPojo> getUserBalance(GetUserReq getUserReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/user/balance/" + getUserReq.getUserId();
        String result = invoke(signUrl, bulidBody());

        ResultDTO<List<BalanceAmountResultPojo>> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<List<BalanceAmountResultPojo>>>() {
                });

        return resultDto.getData();
    }

    /**
     * 返回所有的币种信息
     *
     * @return 放回所有的信息
     * @throws Exception
     */
    public List<CoinPojo> getMapAllCoin() throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/coin/list";
        String result = invoke(signUrl, bulidBody());
        ResultDTO<List<CoinPojo>> resultDto = transferToResultDto(result, new TypeReference<ResultDTO<List<CoinPojo>>>() {
        });
        return resultDto.getData();
    }

    /**
     * 1.4、绑定用户指定币种的地址
     *
     * @param bindUserAddressReq
     * @return
     */
    public boolean bindUserAddress(BindUserAddressReq bindUserAddressReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/user/bind/" + bindUserAddressReq.getUserId() + "/" + bindUserAddressReq.getCoin() + "/" + bindUserAddressReq.getAddr();
        String result = invoke(signUrl, bulidBody());

        ResultDTO<Object> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<Object>>() {
                });

        return resultDto.getFlag();
    }

    /**
     * 1.5、获取用户的列表
     *
     * @param getUserListReq
     * @return
     */
    public UserResultListPojo getUserList(GetUserListReq getUserListReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/user/list";

        StringBuilder body = bulidBodyPre();
        if (!StringUtils.isEmpty(getUserListReq.getPhone())) {
            body.append(",\"phone\":\"" + getUserListReq.getPhone() + "\"");
        }

        if (!StringUtils.isEmpty(getUserListReq.getNickname())) {
            body.append(",\"nickname\":\"" + getUserListReq.getNickname() + "\"");
        }

        if (getUserListReq.getEnableState() != null) {
            body.append(",\"enableState\":" + getUserListReq.getEnableState() + "");
        }

        if (getUserListReq.getTradeState() != null) {
            body.append(",\"tradeState\":" + getUserListReq.getTradeState() + "");
        }

        if (getUserListReq.getPage() != null) {
            body.append(",\"page\":" + getUserListReq.getPage() + "");
        }

        if (getUserListReq.getSize() != null) {
            body.append(",\"size\":" + getUserListReq.getSize() + "");
        }
        body.append("}");

        String result = invoke(signUrl, body);


        ResultDTO<UserResultListPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<UserResultListPojo>>() {
                });

        return resultDto.getData();
    }

    //--------------------------------------------------------用户接口结束----------------------------------------------------------------------------------------------


    //--------------------------------------------------------商户接口开始----------------------------------------------------------------------------------------------

    /**
     * 2.1、获取商户详细信息
     *
     * @return
     */
    public MerChantResultPojo getMerchantDetail() throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/platform/detail";
        String result = invoke(signUrl, bulidBody());


        ResultDTO<MerChantResultPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<MerChantResultPojo>>() {
                });
        return resultDto.getData();
    }


    /**
     * 2.2、绑定商户指定币种的地址
     *
     * @param bindMerchantAddressReq
     * @return
     */
    public boolean bindMerchantAddress(BindMerchantAddressReq bindMerchantAddressReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/platform/bind/" + bindMerchantAddressReq.getCoin() + "/" + bindMerchantAddressReq.getAddr();
        String result = invoke(signUrl, bulidBody());

        ResultDTO<Object> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<Object>>() {
                });
        return resultDto.getFlag();
    }

    /**
     * 2.3、获取商户的余额信息
     *
     * @return
     */
    public List<BalanceAmountResultPojo> getMerchantBalance() throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/platform/balance";
        String result = invoke(signUrl, bulidBody());

        ResultDTO<List<BalanceAmountResultPojo>> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<List<BalanceAmountResultPojo>>>() {
                });

        return resultDto.getData();
    }
    //--------------------------------------------------------商户接口结束----------------------------------------------------------------------------------------------


    //--------------------------------------------------------转账接口开始----------------------------------------------------------------------------------------------

    /**
     * 3.1、商户转账给用户
     *
     * @param transferReq
     * @return
     */
    public String transferMerchantToUser(TransferReq transferReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/transferMechantToUser/" + transferReq.getUserId() + "/" + transferReq.getCoin() + "/" + transferReq.getAmount() + "/" + transferReq.getTransferNum();
        StringBuilder body = bulidBodyPre();
        if (!StringUtils.isEmpty(transferReq.getRemark())) {
            body.append(REMARK_TMP + transferReq.getRemark() + "\"");
        }
        body.append("}");

        String result = invoke(signUrl, body);

        ResultDTO<Map<String, Object>> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<Map<String, Object>>>() {
                });

        return resultDto.getData().get("id").toString();
    }

    /**
     * 3.2、商户提币
     *
     * @param withdrawReq
     * @return 实体
     */
    public ResultDTO<Map<String, Object>> withdrawMoneyMerchantResult(WithdrawMerchantReq withdrawReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/withdrawMoneyMechant/" + withdrawReq.getCoin() + "/" + withdrawReq.getAmount() + "/" + withdrawReq.getTransferNum();
        StringBuilder body = bulidBodyPre();

        if (!StringUtils.isEmpty(withdrawReq.getAddr())) {
            body.append(",\"addr\":\"" + withdrawReq.getAddr() + "\"");
        }
        if (null != withdrawReq.getFee()) {
            body.append(",\"fee\":" + withdrawReq.getFee() + "");
        }

        body.append("}");

        String result = invoke(signUrl, body);
        logger.info("提币请求结果：" + result);
        return withdrawMoneyMerchantResultDto(result, new TypeReference<ResultDTO<Map<String, Object>>>() {
        });

    }


    /**
     * 3.3、用户转账给商户
     *
     * @param transferReq
     * @return
     */
    public String transferUserToMerchant(TransferReq transferReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/transferUserToMechant/" + transferReq.getUserId() + "/" + transferReq.getCoin() + "/" + transferReq.getAmount() + "/" + transferReq.getTransferNum();
        StringBuilder body = bulidBodyPre();
        if (!StringUtils.isEmpty(transferReq.getRemark())) {
            body.append(REMARK_TMP + transferReq.getRemark() + "\"");
        }
        body.append("}");

        String result = invoke(signUrl, body);
        ResultDTO<Map<String, Object>> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<Map<String, Object>>>() {
                });

        return resultDto.getData().get("id").toString();
    }

    /**
     * 3.4、用户提币
     *
     * @param withdrawUserReq
     * @return
     */
    public String withdrawMoneyUser(WithdrawUserReq withdrawUserReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/withdrawMoneyUser/" + withdrawUserReq.getUserId() + "/" + withdrawUserReq.getCoin() + "/" + withdrawUserReq.getAmount() + "/" + withdrawUserReq.getTransferNum();
        StringBuilder body = bulidBodyPre();
        if (!StringUtils.isEmpty(withdrawUserReq.getRemark())) {
            body.append(REMARK_TMP + withdrawUserReq.getRemark() + "\"");
        }
        body.append("}");

        String result = invoke(signUrl, body);
        ResultDTO<Map<String, Object>> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<Map<String, Object>>>() {
                });

        return resultDto.getData().get("id").toString();
    }

    /**
     * 3.5、根据交易ID获取交易详情
     *
     * @param transferDetailReq
     * @return
     */
    public TransferResultPojo getTransferDetail(TransferDetailReq transferDetailReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/transfer/detail/" + transferDetailReq.getTransferId();
        String result = invoke(signUrl, bulidBody());

        ResultDTO<TransferResultPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<TransferResultPojo>>() {
                });
        return resultDto.getData();
    }


    /**
     * 3.6、获取交易流水
     *
     * @param flowReq
     * @return
     */
    public TransferResultListPojo getFlow(FlowReq flowReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/flow";

        StringBuilder body = bulidBodyPre();

        if (flowReq.getUserId() != null) {
            body.append(",\"userId\":" + flowReq.getUserId() + "");
        }

        if (flowReq.getType() != null) {
            body.append(",\"type\":" + flowReq.getType() + "");
        }

        if (!StringUtils.isEmpty(flowReq.getStartTime())) {
            body.append(",\"startTime\":\"" + flowReq.getStartTime() + "\"");
        }

        if (!StringUtils.isEmpty(flowReq.getEndTime())) {
            body.append(",\"endTime\":\"" + flowReq.getEndTime() + "\"");
        }

        if (flowReq.getPage() != null) {
            body.append(",\"page\":" + flowReq.getPage() + "");
        }

        if (flowReq.getSize() != null) {
            body.append(",\"size\":" + flowReq.getSize() + "");
        }
        body.append("}");
        String result = invoke(signUrl, body);

        ResultDTO<TransferResultListPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<TransferResultListPojo>>() {
                });
        return resultDto.getData();

    }

    /**
     * 3.7 获取币种的地址
     *
     * @param coinAssignAddressReq
     * @return
     */
    public String getCoinAssignAddress(CoinAssignAddressReq coinAssignAddressReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/getAssignAddr/" + coinAssignAddressReq.getCoin();
        StringBuilder body = bulidBodyPre();
        if (coinAssignAddressReq.getUserId() != null) {
            body.append(",\"userId\":" + coinAssignAddressReq.getUserId() + "");
        }
        body.append("}");
        String result = invoke(signUrl, body);

        ResultDTO<String> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<String>>() {
                });
        return resultDto.getData();
    }

    /**
     * 3.8、获取币种精度
     *
     * @param coins
     * @return
     * @throws Exception
     */
    public List<CoinAccuracyPojo> getAccuracy(String[] coins) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/getAccuracy/" + StringUtils.join(coins, ",");
        StringBuilder body = bulidBodyPre();
        body.append("}");
        String result = invoke(signUrl, body);
        ResultDTO<List<CoinAccuracyPojo>> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<List<CoinAccuracyPojo>>>() {
                });
        return resultDto.getData();
    }

    //--------------------------------------------------------转账接口结束----------------------------------------------------------------------------------------------


    //--------------------------------------------------------用户OTC接口开始----------------------------------------------------------------------------------------------
    /**
     * 4.1、登录OTC
     *
     * @return
     * @throws Exception
     */
    public OtcLoginPojo loginOtc(OtcLoginReq otcLoginReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/otc/login2";
        StringBuilder body = bulidBodyPre();
        if (otcLoginReq.getUserId() != null) {
            body.append(",\"userId\":" + otcLoginReq.getUserId() + "");
        }
        if (otcLoginReq.getType() != null) {
            body.append(",\"type\":" + otcLoginReq.getType() + "");
        }
        if (otcLoginReq.getOrderNum() != null) {
            body.append(",\"orderNum\":\"" + otcLoginReq.getOrderNum() + "\"");
        }
        if (otcLoginReq.getCoin() != null) {
            body.append(",\"coin\":\"" + otcLoginReq.getCoin() + "\"");
        }
        if (otcLoginReq.getAmount() != null) {
            body.append(",\"amount\":\"" + otcLoginReq.getAmount() + "\"");
        }
        if (otcLoginReq.getPayMethods() != null) {
            body.append(",\"payMethods\":\"" + otcLoginReq.getPayMethods() + "\"");
        }
        if (StringUtils.isNotBlank(otcLoginReq.getCoinAlias())) {
            body.append(",\"coinAlias\":" + otcLoginReq.getCoinAlias() + "");
        }
        if (!CollectionUtils.isEmpty(otcLoginReq.getUsableGroup())) {
            body.append(",\"usableGroup\":" + otcLoginReq.getUsableGroup() + "");
        }
        body.append(",\"pattern\":" + otcLoginReq.getPattern() + "");
        body.append("}");
        String result = invoke(signUrl, body);
        ResultDTO<OtcLoginPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<OtcLoginPojo>>() {
                });
        return resultDto.getData();
    }

    /**
     * 4.2、获取订单详情
     *
     * @return
     * @throws Exception
     */
    public OtcDetailPojo otcDetail(OtcDetailReq otcDetailReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/otc/detail";
        StringBuilder body = bulidBodyPre();
        if (otcDetailReq.getUserId() != null) {
            body.append(",\"userId\":" + otcDetailReq.getUserId() + "");
        }
        if (otcDetailReq.getOrderId() != null) {
            body.append(",\"orderId\":\"" + otcDetailReq.getOrderId() + "\"");
        }
        body.append("}");
        String result = invoke(signUrl, body);
        ResultDTO<OtcDetailPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<OtcDetailPojo>>() {
                });
        return resultDto.getData();
    }

    /**
     * 4.3、OTC卖单审核通知接口
     *
     * @return
     * @throws Exception
     */
    public void otcMerchantNotice(OtcMerchantNoticeReq otcMerchantNoticeReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/otc/merchantnotice/approve/sell";
        StringBuilder body = bulidBodyPre();
        if (otcMerchantNoticeReq.getOrderNum() != null) {
            body.append(",\"orderNo\":\"" + otcMerchantNoticeReq.getOrderNo() + "\"");
        }
        if (otcMerchantNoticeReq.getOrderNum() != null) {
            body.append(",\"orderNum\":\"" + otcMerchantNoticeReq.getOrderNum() + "\"");
        }
        if (otcMerchantNoticeReq.getCoin() != null) {
            body.append(",\"coin\":\"" + otcMerchantNoticeReq.getCoin() + "\"");
        }
        if (otcMerchantNoticeReq.getCoinAmount() != null) {
            body.append(",\"coinAmount\":\"" + otcMerchantNoticeReq.getCoinAmount() + "\"");
        }
        if (otcMerchantNoticeReq.getUserId() != null) {
            body.append(",\"userId\":" + otcMerchantNoticeReq.getUserId() + "");
        }
        if (otcMerchantNoticeReq.getCode() != null) {
            body.append(",\"code\":" + otcMerchantNoticeReq.getCode() + "");
        }
        body.append("}");
        String result = invoke(signUrl, body);
        transferToResultDto(result, new TypeReference<ResultDTO>() {
        });
    }

    /**
     * 4.10、 根据业务平台交易编号获取交易详情
     *
     * @param transferNum
     * @return
     */
    public TransferResultPojo orderDetailByTransferNum(String transferNum) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/transfer/getDetailByTransfernum/" + transferNum;
        StringBuilder body = bulidBodyPre();
        body.append("}");
        String result = invoke(signUrl, body);

        ResultDTO<TransferResultPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<TransferResultPojo>>() {
                });
        return resultDto.getData();
    }

    //--------------------------------------------------------用户OTC接口结束----------------------------------------------------------------------------------------------


    //--------------------------------------------------------闪兑接口开始----------------------------------------------------------------------------------------------
    /**
     * 5.1、登录闪兑模块
     *
     * @return
     * @throws Exception
     */
    public FastExchangeLoginPojo loginFastex(FastExchangeLoginReq fastexLoginReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/fastex/login";
        StringBuilder body = bulidBodyPre();
        if (fastexLoginReq.getUserId() != null) {
            body.append(",\"userId\":" + fastexLoginReq.getUserId() + "");
        }
        if (fastexLoginReq.getType() != null) {
            body.append(",\"type\":" + fastexLoginReq.getType() + "");
        }
        if (fastexLoginReq.getLoginType() != null) {
            body.append(",\"loginType\":" + fastexLoginReq.getLoginType() + "");
        }
        if (StringUtils.isNotBlank(fastexLoginReq.getOrderNum())) {
            body.append(",\"orderNum\":\"" + fastexLoginReq.getOrderNum() + "\"");
        }
        if (StringUtils.isNotBlank(fastexLoginReq.getExchangeCoin())) {
            body.append(",\"exchangeCoin\":" + fastexLoginReq.getExchangeCoin() + "");
        }
        if (StringUtils.isNotBlank(fastexLoginReq.getCoinList())) {
            body.append(",\"coinList\":" + fastexLoginReq.getCoinList() + "");
        }
        if (StringUtils.isNotBlank(fastexLoginReq.getCoinAlias())) {
            body.append(",\"coinAlias\":" + fastexLoginReq.getCoinAlias() + "");
        }
        body.append("}");
        String result = invoke(signUrl, body);
        ResultDTO<FastExchangeLoginPojo> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<FastExchangeLoginPojo>>() {
                });
        return resultDto.getData();
    }

    /**
     * 5.2、获取闪兑订单详情
     *
     * @return
     * @throws Exception
     */
    public FastExchangeDetailPojo fexDetail(FastExchangeDetailReq fexDetailReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/fastex/detail";
        StringBuilder body = bulidBodyPre();
        if (fexDetailReq.getUserId() != null) {
            body.append(",\"userId\":" + fexDetailReq.getUserId() + "");
        }
        if (fexDetailReq.getOrderId() != null) {
            body.append(",\"orderId\":\"" + fexDetailReq.getOrderId() + "\"");
        }
        body.append("}");
        String result = invoke(signUrl, body);
        ResultDTO<FastExchangeDetailPojo> resultDto = transferToResultDtoWithCodeEnum(result,
                new TypeReference<ResultDTO<FastExchangeDetailPojo>>() {
                });
        return resultDto.getData();
    }

    /**
     * 5.3、直接下单接口
     *
     * @param generateOrderReq
     * @return 订单ID
     * @throws Exception
     */
    public String generateOrder(FastExchangeGenerateOrderReq generateOrderReq) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/fastex/generateOrder";
        StringBuilder body = bulidBodyPre();
        if (generateOrderReq.getUserId() != null) {
            body.append(",\"userId\":" + generateOrderReq.getUserId() + "");
        }
        if (generateOrderReq.getPayCoin() != null) {
            body.append(",\"payCoin\":\"" + generateOrderReq.getPayCoin() + "\"");
        }
        if (generateOrderReq.getGotCoin() != null) {
            body.append(",\"gotCoin\":\"" + generateOrderReq.getGotCoin() + "\"");
        }
//        if (generateOrderReq.getAmount() != null) {
//            body.append(",\"amount\":" + generateOrderReq.getAmount() + "");
//        }
        if (generateOrderReq.getAmount() != null) {
            body.append(",\"amount\":" + NumberUtils.keepScale(generateOrderReq.getAmount(), 10).toPlainString() + "");
        }
        if (generateOrderReq.getOrderNum() != null) {
            body.append(",\"orderNum\":\"" + generateOrderReq.getOrderNum() + "\"");
        }
        body.append("}");
        String result = invoke(signUrl, body);

        ResultDTO<String> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<String>>() {
                });
        return resultDto.getData();
    }

    //--------------------------------------------------------闪兑接口结束----------------------------------------------------------------------------------------------
    /**
     * 7.1、根据订单号查询是否归集
     *
     * @param orderId
     * @return
     */
    public Boolean checkCollection(String orderId) throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/order/findOrderIdCollection/" + orderId;
        StringBuilder body = bulidBodyPre();
        body.append("}");
        String result = invoke(signUrl, body);

        ResultDTO<Boolean> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<Boolean>>() {
                });
        return resultDto.getData();
    }

    /**
     * 5.3、 获取交易币对的精度和单笔最大最小限额
     *
     * @param
     * @return
     * @throws Exception
     */
    public String symbols() throws Exception {
        String signUrl = TEMP_URL_PRE + "/v1/coin/symbols/";
        StringBuilder body = bulidBodyPre();
        body.append("}");
        String result = invoke(signUrl, body);
        ResultDTO<String> resultDto = transferToResultDto(result,
                new TypeReference<ResultDTO<String>>() {
                });
        return resultDto.getData();
    }

    /**
     * 计算签名和发送HTTP请求
     *
     * @param signUrl
     * @param bodyStringBuilder
     * @return
     */
    private String invoke(String signUrl, StringBuilder bodyStringBuilder) throws Exception {
        //body参数字符串
        String bodyString = bodyStringBuilder.toString();

        //组装待签名的数据
        StringBuilder signInfo = new StringBuilder();
        buildPrefixData();
        signInfo.append(prefixData.toString());
        signInfo.append(signUrl + "\n");
        signInfo.append(bodyString);

        //计算签名
        String signature = Sha256Hmac.sha256HMAC(signInfo.toString(), getApiSecretKey());
        //base64
        signature = new String(Base64.encodeBase64(signature.getBytes()));

        //发送post请求获取数据
        String realUrl = getHttp() + getHost() + signUrl + "?signature=" + signature;
        String result = null;
        try {
            StringEntity s = new StringEntity(bodyString);
            s.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            s.setContentType("application/json");
            Post post = new Post(realUrl);
            post.setEntity(s);

            //20190602，增加jsonRpc签名参数header
            String reSignature = JsonRpcUtil.create6xSign(signature);
            post.setHeader("resig", reSignature);

            // http
//            logger.info("请求6X接口前，URL：[{}]，请求体：[{}]，post：[{}]", signUrl, bodyString, JSONObject.toJSONString(post));
//            result = HttpInvoker.execute(post).getBody();
//            logger.info("请求6X接口后，URL：[{}]，请求体：[{}]，返回结果信息：[{}]", signUrl, bodyString, JSONObject.toJSONString(result));

            // okHttp
            Map<String, Object> headerMap = Maps.newHashMap();
            headerMap.put("resig", reSignature);
            result = OkHttpUtil.post(realUrl, bodyString, headerMap);

            // 校验数据格式
            JSONObject.parseObject(result);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(result);
            } catch (JSONException ex1) {
                throw new Exception("返回JSON格式出错：" + result);
            }
        } catch (Exception e) {
            throw new Exception("网络不通,http请求失败!");
        }
        return result;
    }

    /**
     * 创建复杂的body(这里只是创建body的部分前缀)
     *
     * @return
     */
    private StringBuilder bulidBodyPre() {
        StringBuilder body = new StringBuilder();
        body.append("{");
        body.append("\"accessKeyId\":" + getAccessKeyId() + ",");
        body.append("\"signatureMethod\":\"" + SIGNATURE_METHOD + "\",");
        body.append("\"signatureVersion\":\"" + SIGNATURE_VERSION + "\",");
        body.append("\"timestamp\":\"" + getTimestamp() + "\"");
        return body;
    }

    /**
     * 创建简单的body(只含有四个元素)
     *
     * @return
     */
    private StringBuilder bulidBody() {

        StringBuilder body = new StringBuilder();
        body.append("{");
        body.append("\"accessKeyId\":" + getAccessKeyId() + ",");
        body.append("\"signatureMethod\":\"" + SIGNATURE_METHOD + "\",");
        body.append("\"signatureVersion\":\"" + SIGNATURE_VERSION + "\",");
        body.append("\"timestamp\":\"" + getTimestamp() + "\"");
        body.append("}");
        return body;
    }


    /**
     * 构建签名的前缀
     */
    private void buildPrefixData() {
        prefixData = new StringBuilder();
        prefixData.append("POST\n");
        prefixData.append(getHost() + "\n");
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    private static String getTimestamp() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(new Date());
    }


    private <T> T transferToResultDto(String result, TypeReference<T> typeReference) {
        T t = JSON.parseObject(result, typeReference);

        ResultDTO dto = (ResultDTO) t;

        if (!dto.getFlag()) {
            throw new LxtxException(dto.getCodeEnum(), dto.getMessage());
        }

        return t;
    }

    private <T> T transferToResultDtoWithCodeEnum(String result, TypeReference<T> typeReference) {
        T t = JSON.parseObject(result, typeReference);

        ResultDTO dto = (ResultDTO) t;

        // 查询不到的订单当失败处理，需要返回判断后处理相应逻辑，故不直接抛出异常
        if (CODE_ENUM_617.equals(dto.getCodeEnum())) {
            return t;
        }

        if (!dto.getFlag()) {
            throw new LxtxException(dto.getCodeEnum(), dto.getMessage());
        }

        return t;
    }

    /**
     * 用于商户提币结果转换
     *
     * @param
     * @return
     */
    private <T> T withdrawMoneyMerchantResultDto(String result, TypeReference<T> typeReference) {
        return JSON.parseObject(result, typeReference);
    }

    /**
     * otc签名
     *
     * @param values
     * @return
     */
    public String otcSign(String ... values){
        StringBuilder params = new StringBuilder();
        for (String value : values) {
            params.append(value);
        }
        logger.info("OTC签名秘钥：" +  getApiSecretKey());
        logger.info("OTC签名数据：" + params.toString());

        //计算签名
        String signature = Sha256Hmac.sha256HMAC(params.toString(), getApiSecretKey()).toUpperCase();
        //base64
        signature = new String(Base64.encodeBase64(signature.getBytes()));
        logger.info("OTC签名结果:" + signature);
        return signature;
    }

    /**
     * 闪兑签名
     *
     * @param values
     * @return
     */
    public String fexSign(String ... values){
        String segmentationSymbol = "_";
        StringBuilder params = new StringBuilder();
        for (String value : values) {
            params.append(value).append(segmentationSymbol);
        }
        String paramString = "";
        if (params.lastIndexOf(segmentationSymbol) > 0) {
            paramString = params.substring(0, params.length() - 1);
        }

        logger.info("闪兑签名秘钥：{}，闪兑签名数据：{}", getApiSecretKey(), paramString);

        //计算签名
        String signature = Sha256Hmac.sha256HMAC(paramString, getApiSecretKey()).toUpperCase();
        //base64
        signature = new String(Base64.encodeBase64(signature.getBytes()));
        logger.info("闪兑签名结果:{}", signature);
        return signature;
    }

    private static String otcSignTest(String ... values){
        StringBuilder params = new StringBuilder();
        for (String value : values) {
            params.append(value);
        }
        logger.info("签名数据：" + params.toString());
        //计算签名
        String signature = Sha256Hmac.sha256HMAC(params.toString(), "884d088573cc3ea6f087d6620eadb5e9");
        //base64
        signature = new String(Base64.encodeBase64(signature.getBytes()));
        logger.info("获得签名:" + signature);
        return signature;
    }

    public static void main(String[] args) {
//        SixxClient.otcSignTest("495831", "secret201812251545731184249tmoWZ", "DC", "1694.0200000000", "成功");
//        System.out.println(new String(Base64.encodeBase64(Sha256Hmac.sha256HMAC("495831secret201812251545731184249tmoWZDC1694.02000000成功", "884d088573cc3ea6f087d6620eadb5e9").toUpperCase().getBytes())));
////        System.out.println(new String(Base64.encodeBase64(Sha256Hmac.sha256HMAC("495831secret201812251545731184249tmoWZ电风扇建设2越秀5102956135118270成功", "884d088573cc3ea6f087d6620eadb5e9").toUpperCase().getBytes())));
//        System.out.println(new String(Base64.encodeBase64(Sha256Hmac.sha256HMAC("495831secret201812251545728057338hXISZDC1694.0200000000成功", "884d088573cc3ea6f087d6620eadb5e9").toUpperCase().getBytes())));
//        System.out.println(new String(Base64.encodeBase64(Sha256Hmac.sha256HMAC("495831secret201812251545731184249tmoWZDC1694.0200000000成功", "884d088573cc3ea6f087d6620eadb5e9").toUpperCase().getBytes())));
////        System.out.println(new String(Base64.encodeBase64(Sha256Hmac.sha256HMAC("421secret201810251540466914418ta2vY姓名工商越秀6212262201023557228成功", "7161bb50033ab0e82aefad8d3954d400").getBytes())));
//        logger.info("123123");


//        String result = "{\"codeEnum\":617,\"message\":\"没有此订单：FEX201904081621464254elnDkuS7\",\"data\":null,\"flag\":false}";
//        ResultDTO<FastExchangeDetailPojo> resultDto = transferToResultDtoWithCodeEnum(result,
//                new TypeReference<ResultDTO<FastExchangeDetailPojo>>() {
//                });
//        System.out.println(resultDto);
//        System.out.println(resultDto.getData());
    }


}