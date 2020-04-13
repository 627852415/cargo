package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
  * @description: 银行申请表审核详情
  * @author Lin hj
  * @date 2019/4/28 14:50
*/
@Getter
@Setter
public class BcbBankApplyAuditDetailResp implements Serializable {


    /**
     * 银行卡号
     */
    private String cardNo;

   /**
    * 表主键ID
    */
   private String id;
   /**
    * 钱包用户主键ID
    */
   private String userId;
   /**
    * 名字
    */
   private String firstName;
   /**
    * 姓氏
    */
   private String lastName;
   /**
    * 姓名
    */
   private String userName;
   /**
    * 护照号
    */
   private String passportNum;
    /**
     * 用户手机的国际编码
     */
    private String phoneCountryCode;
   /**
    * 用户手机的国家ID
    */
   private String phoneCountryId;
   /**
    * 手机号码
    */
   private String cellPhone;
   /**
    * 电子邮箱
    */
   private String email;
   /**
    * 出生日期yyyyMMdd
    */
   private String birthDate;

   /**
    * 住宅城市
    */
   private String cityName;
   /**
    * 街道地址
    */
   private String street;
   /**
    * 邮政编码
    */
   private String zipCode;
   /**
    * 处理状态【 0、在填资料 1、提交审核 2、初审通过 3、BCB申办 4、BCB激活 5、复审成功 6、审核失败/打回 7、复审失败/打回】
    */
   private Integer status;

   /**
    * 提交审核/申请时间
    */
   private Date applyTime;

   private Boolean firstAudit;

    /**
     * 出生国家
     */
    private String countryBirthId;
    /**
     * 性别：1、男 2、女
     */
    private String genderId;
    /**
     * 用户地址的国家ID
     */
    private String countryId;
    /**
     * 用户地址的州ID
     */
    private String stateId;

    /**
     * 出生国家
     */
    private String countryBirth;
    /**
     * 性别：1、男 2、女
     */
    private String gender;
    /**
     * 用户地址的国家ID
     */
    private String country;
    /**
     * 用户地址的州ID
     */
    private String state;

    private String remarks;
    /**
     * 卡种类型的名称
     */
    private String bankCardTypeName;

    /**
     * 登录密码
     */
    private String loginPassword;
    /**
     * KYC图片地址
     */
    private String kycImg;
    /**
     * kyc住址证明URL
     */
    private String kycImgAddr;
    /**
     * 更新时间
     */
    public Date updateTime;
    /**
     * 更新人
     */
    public String updateBy;

}
