package com.lxtx.im.admin.service.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-11-25 15:20
 * @Description
 */
@Data
public class YebOrderDownloadReq {
    /**
     * 付款钱包用户ID
     */
    private String userId;

    /**
     * 付款钱包用户ID
     */
    private String userName;

    /**
     * 第三方生成的订单编号
     */
    private String thirdPartyOrderNo;


    /**
     * 6X提币订单号
     */
    private String sixxOrderNo;


    /**
     * 类型【1、转入余额；2、转出收益；3、转出本金】
     */
    private Integer type;

    /**
     * 订单状态
     * 转入状态【 0、初始化; 1、6X提币申请中冻结用户金额 ；2、6X提币申请失败;3、6X提币申请成功;4、6X提币成功；5、余额宝存款申请成功；6、余额宝存款申请失败；7、订单完成】。
     * 转出状态/提取链上资金【 0、初始化; 1、处理中 ；2、订单完成;3、处理失败;】'
     */
    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date updateTime;

    /**
     * 币种ID
     */
    private String coinName;
    /**
     * 平台用户手机号的国际简码
     */
    private String countryCode;

    /**
     * 电话
     */
    private String telephone;
}
