package com.lxtx.framework.common.utils.bcbbank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lxtx.framework.common.utils.bcbbank.model.*;

/**
 　　* @description: BCB银行接口调用类
 　　* @author Lin hj
 　　* @redoDateTimes 2019/4/23 15:22
 */
public class BcbBankClient  extends  BaseBcbBankClient{

    /**
     * 持卡人注册
     *
     * @param registerWithCardReq
     * @return
     * @throws Exception
     */
    public BcbRegisterWithCardResp registerWithCard(BcbRegisterWithCardReq registerWithCardReq) throws Exception {
        String url = "/CommonServices/RegisterWithCard";
        StringBuilder body = builderBodyPre();
        appendInt("Application_ID",getAppId(),body);
        appendString("Name",registerWithCardReq.getName(),body);
        appendString("MiddleName",(registerWithCardReq.getMiddleName() == null ? "" : registerWithCardReq.getMiddleName()),body);
        appendString("Email",registerWithCardReq.getEmail(),body);
        appendString("Password",registerWithCardReq.getPassword(),body);
        appendString("CellPhone",registerWithCardReq.getCellPhone(),body);
        appendString("CountryCode",registerWithCardReq.getCountryCode(),body);
        appendString("AccountId",registerWithCardReq.getAccountId(),body);
        appendString("BirthDate",registerWithCardReq.getBirthDate(),body);
        appendString("CountryBirthId",registerWithCardReq.getCountryBirthId(),body);
        appendString("GenderId",registerWithCardReq.getGenderId(),body);
        appendString("LastName",registerWithCardReq.getLastName(),body);
        appendString("SecondLastName",(registerWithCardReq.getSecondLastName() == null ? "" : registerWithCardReq.getSecondLastName()) ,true,body);
        appendString("Street",registerWithCardReq.getStreet(),body);
        appendString("NumStreet", registerWithCardReq.getNumStreet(),body);
        appendString("NumApt",registerWithCardReq.getNumApt(),body);
        appendString("CountryId",registerWithCardReq.getCountryId(),body);
        appendString("Country",registerWithCardReq.getCountry(),body);
        appendString("StateId",registerWithCardReq.getStateId(),body);
        appendString("State",registerWithCardReq.getState(),body);
        appendString("City",registerWithCardReq.getCity(),body);
        appendString("ZipCode",registerWithCardReq.getZipCode(),body);
        appendString("Suburb",registerWithCardReq.getSuburb(),body);
        appendInt("Agent_code",registerWithCardReq.getAgent_code()+"",body);
        appendInt("Sub_Agent_code",registerWithCardReq.getSub_Agent_code()+"",body);
        appendEndString("Client_Agent_SubAgent_Name",registerWithCardReq.getClient_Agent_SubAgent_Name(),body);
        body = builderBodyEnd(body);
        String result =  post(url,body);
        return transferToResultDto(result, new TypeReference<BcbRegisterWithCardResp>() {
        });
    }


    /**
     * 发送验证码
     * @param countryCode
     * @param mobileNumber
     * @return
     * @throws Exception
     */
    public BcbMobileOtpResp mobileotpSend(String countryCode,String mobileNumber) throws Exception {
        String url = "/CommonServices/MobileotpSend";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"CountryCode\":\"" + countryCode+ "\"");
        body.append(",\"MobileNumber\":\"" + mobileNumber + "\"");
        body = builderBodyEnd(body);
        String result = executeHttp(url, body);
        return transferToResultDto(result, new TypeReference<BcbMobileOtpResp>() {});
    }

    /**
     * 校验kyc资料
     * @param bcbCheckIDReq
     * @return
     * @throws Exception
     */
    public BcbCheckIDResp checkIDV(BcbCheckIDReq bcbCheckIDReq) throws Exception {
        String url = "/KYC/IDVCheck";
        StringBuilder body =new StringBuilder();
        bcbCheckIDReq.setApplication_ID(getAppId());
        body.append(JSON.toJSONString(bcbCheckIDReq));
        String result = executeHttp(url, body);
        return transferToResultDto(result, new TypeReference<BcbCheckIDResp>() {});
    }

    /**
     * 校验sis pep
     * @param bcbCheckSisPepReq
     * @return
     * @throws Exception
     */
    public BcbCheckSisPepResp checkSisPep(BcbCheckSisPepReq bcbCheckSisPepReq) throws Exception {
        String url = "/KYC/SISPlusPEPCheck";
        StringBuilder body = new StringBuilder();
        bcbCheckSisPepReq.setApplication_ID(Integer.parseInt(getAppId()));
        body.append(JSON.toJSONString(bcbCheckSisPepReq));
        String result = executeHttp(url, body);
        return transferToResultDto(result, new TypeReference<BcbCheckSisPepResp>() {});
    }


    /**
     * 手机验证码校验
     * @param sessionId 会话ID
     * @param otp 验证码
     * @param userId BCB 用户ID
     * @return
     * @throws Exception
     */
    public BcbMobileOtpCheckResp mobileOtpCheck(String sessionId,String otp,String userId) throws Exception {
        String url = "/CommonServices/Mobileotp_check";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"session_id\":\"" + sessionId+ "\"");
        body.append(",\"otp\":\"" + otp + "\"");
        body.append(",\"MemberID\":" + userId);
        body = builderBodyEnd(body);
        String result = post(url, body);
        return transferToResultDto(result, new TypeReference<BcbMobileOtpCheckResp>() {});
    }

    /**
     * 匹配手机号码和用户ID
     * @param userId
     * @return
     * @throws Exception
     */
    public BcbUpdateMobileVeriResp updateMobileVeri(String userId) throws Exception {
        String url = "/CommonServices/UpdateMobileVerifyFlag";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"MemberID\":" + userId);
        body = builderBodyEnd(body);
        String result = post(url, body);
        return transferToResultDto(result, new TypeReference<BcbUpdateMobileVeriResp>() {});
    }


    /**
     * 设置卡的pin密码
     * @param userId
     * @param accountId
     * @param validThru
     * @param securityCode
     * @param pinPassword
     * @return
     * @throws Exception
     */
    public BcbCardPinResp setCardPinPassword(String userId, String accountId, String validThru, String securityCode, String pinPassword) throws Exception {
        String url = "/Card/PIN";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"UserId\":\"" + userId  + "\"");
        body.append(",\"AccountId\":\"" + accountId + "\"");
        body.append(",\"ValidThru\":\"" + validThru + "\"");
        body.append(",\"SecurityCode\":\"" + securityCode  + "\"");
        body.append(",\"PIN\":\"" + pinPassword + "\"");
        body = builderBodyEnd(body);
        String result = post(url, body);
        return transferToResultDto(result, new TypeReference<BcbCardPinResp>() {});
    }



    /**
     * 激活银行卡片
     * @param userId 用户ID
     * @param accountId 账号ID
     * @param validThru 月份+年份 的有效期
     * @param securityCode 安全码
     * @return
     * @throws Exception
     */
    public BcbAvtiveInfoResp activeCard(String userId,String accountId,String validThru,String securityCode) throws Exception {
        String url = "/Card/Activation";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"UserId\":\"" + userId+ "\"");
        body.append(",\"AccountId\":\"" + accountId+ "\"");
        body.append(",\"ValidThru\":\"" +validThru+ "\"");
        body.append(",\"SecurityCode\":\"" + securityCode + "\"");
        body = builderBodyEnd(body);
        String result = executeHttp(url, body);
        return transferToResultDto(result, new TypeReference<BcbAvtiveInfoResp>() {});
    }


    /**
     * 根据AccountID获取卡号信息
     * @param userId
     * @param accountId
     * @return
     * @throws Exception
     */
    public BcbCardInfoResp getCardInfomationByAccountId(String userId,String accountId) throws Exception {
        String url = "/Card/Information";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"UserId\":" + userId);
        body.append(",\"AccountId\":" + accountId);
        body = builderBodyEnd(body);
        String result = executeHttp(url, body);
        return transferToResultDto(result, new TypeReference<BcbCardInfoResp>() {});
    }


    /**
     * KYC 资料上传
     * @param documentData  base64解码 图片
     * @param kycType 1 身份证 ；2住址证明
     * @param kycType 文件类型 PNG JPG
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    public BcbUploadDocumentResp uploadDocument(String documentData,String documentType,int kycType,String  userId ) throws Exception {
        String type = kycType==1?"ID":kycType==2?"POA":"";
        String url = "/KYC/UploadDocument";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"DocumentData\":\"" + documentData+ "\"");
        body.append(",\"DocumentReference\":\"" + type + "\"");
        body.append(",\"DocumentType\":\"" + documentType + "\"");
        body.append(",\"MemberID\":" + userId );
        body = builderBodyEnd(body);
        String result = executeHttp(url, body);
        return transferToResultDto(result, new TypeReference<BcbUploadDocumentResp>() {});
    }


    /**
     * 根据卡号获取账号
     *
     * @param cardNum 卡号
     * @return accountId
     * @throws Exception
     */
    public BcbGetAccountIdResp getAccountId(String cardNum) throws Exception {
        String url = "/CommonServices/GetAccountIDFromCardNum";
        StringBuilder body = this.builderBodyPre();
        body.append("\"Application_ID\":" + getAppId());
        body.append(",\"CardNum\":\"" + cardNum + "\"");
        body = builderBodyEnd(body);
        String result = executeHttp(url, body);
        return transferToResultDto(result, new TypeReference<BcbGetAccountIdResp>() {});
    }



    /**
     　　* @description: 充值In Bcb
     　　* @author Lin hj
     　　* @redoDateTimes 2019/4/24 10:15
     */
    public BcbTransferResp transferAmountIn(String cardNo,String amount, String concept, String currency) throws Exception {

        String url  = "/MoneyTransfer/SendToWallet";
        StringBuilder body =  this.builderBodyPre();
        body.append("\"UserId\":"+"\""+getHeapUserId()+"\", ");
        body.append("\"Application_ID\":"+getAppId()+",");
        body.append("\"AccountId\":"+"\""+getHeapAccountId()+"\",");
        body.append("\"AccountNum\":"+"\""+cardNo+"\",");
        body.append("\"Amount\":"+"\""+amount+"\",");
        body.append("\"Currency\":"+"\""+currency+"\",");
        body.append("\"Concept\":"+"\""+concept+"\"");
        body = builderBodyEnd(body);
        String result = executeHttp(url,body);
        BcbTransferResp bcbTransferResp = transferToResultDto(result,
                new TypeReference<BcbTransferResp>() {
                });
        return bcbTransferResp;
    }

    /**
     　　* @description: 资金转移,BCB提现到秘密钱包
     　　* @author Lin hj
     　　* @redoDateTimes 2019/4/24 10:15
     */
    public BcbTransferResp transferAmountOut(String userId, String accountId, String amount, String concept, String currency) throws Exception {

        String url  = "/MoneyTransfer/SendToWallet";
        StringBuilder body =  this.builderBodyPre();
        body.append("\"UserId\":"+"\""+userId+"\", ");
        body.append("\"Application_ID\":"+getAppId()+",");
        body.append("\"AccountId\":"+"\""+accountId+"\",");
        body.append("\"AccountNum\":"+"\""+getHeapCardNo()+"\",");//公账卡号
        body.append("\"Amount\":"+"\""+amount+"\",");
        body.append("\"Currency\":"+"\""+currency+"\",");
        body.append("\"Concept\":"+"\""+concept+"\"");
        body = builderBodyEnd(body);
        String result = executeHttp(url,body);
        BcbTransferResp bcbTransferResp = transferToResultDto(result,
                new TypeReference<BcbTransferResp>() {
                });
        return bcbTransferResp;
    }


    /**
     　　* @description: 根据国家查询州
     　　* @author Lin hj
     　　* @redoDateTimes 2019/4/23 15:21
     */
    public BcbStateListResp queryStatesList(String userId,int countryId) throws Exception {

        String url  = "/Data/StatesList";

        StringBuilder body =  this.builderBodyPre();
        body.append("\"UserId\":"+"\""+userId+"\", ");
        body.append("\"Application_ID\":"+getAppId()+",");
        body.append("\"CountryId\":"+countryId);
        body = builderBodyEnd(body);
        String result = executeHttp(url,body);
        BcbStateListResp bcbStateListResp = transferToResultDto(result,
                new TypeReference<BcbStateListResp>() {
                });
        return bcbStateListResp;
    }

     /**
     　　* @description: 查询所有国家
     　　* @author Lin hj
     　　* @redoDateTimes 2019/4/23 15:21
     */
    public BcbCountriesListResp queryCountiesList(String userId) throws Exception {

        String url  = "/Data/CountriesList";

        StringBuilder body =  this.builderBodyPre();
        body.append("\"UserId\":"+"\""+userId+"\", ");
        body.append("\"Application_ID\":"+getAppId());
        body = builderBodyEnd(body);
        String result = executeHttp(url,body);
        BcbCountriesListResp bcbCountriesListResp = transferToResultDto(result,
                new TypeReference<BcbCountriesListResp>() {
                });
        return bcbCountriesListResp;
    }






    public void test(){

        String http = this.getHttp();
        String host = this.getHost();
        String appid = this.getAppId();

        System.out.println("haha");
    }


}
