package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录OTC
 *
 * @author CaiRH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtcLoginReq {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 请求类型【0：查看订单，1：买币，2：卖币】
     */
    private Integer type;
    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 币种【nullable】
     */
    private String coin;
    /**
     * 卖/买币数量【nullable】
     */
    private String amount;
    /**
     * 商户限制的支付方式【bankcard：银行卡；AliPay：支付宝；WeChatPay：微信；PayPal：PayPal】【nullable】
     */
    private List<String> payMethods;

    /**
     * 模式 1 淘宝模式  2-滴滴模式， 默认滴滴滴模式
     */
    private Integer pattern;

    /**
     * {‘USDX’: 别名, 'DC': 别名}
     */
    private String coinAlias;


    /**
     *  限定用户使用的组编号 , 如果不带参，就匹配公开的商家广告
     */
    private List<Integer> usableGroup;


}