package com.lxtx.im.admin.feign.request;

import lombok.Data;

import java.util.Date;
import java.util.List;
/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2019-11-22 18:38
 * @Description
 */
@Data
public class FeignYebOrderListReq extends BasePageReq{
    /**
     * 付款钱包用户ID
     */
    private String userId;

    private List<String> accountList;

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

    /**
     * 币种ID
     */
    private String coinName;

    private Date createTime;
    private Date updateTime;
    /**
     * 是否柬埔寨演示账号
     */
    private boolean isKHShowAccount;
    /**
     * 手机号码
     * 2020-01-20
     */
    private String telephone;
    /**
     * 国际简码
     * 2020-01-20
     */
    private String countryCode;

}
