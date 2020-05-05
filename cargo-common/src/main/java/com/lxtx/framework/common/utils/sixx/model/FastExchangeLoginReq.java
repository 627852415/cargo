package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录闪兑
 *
 * @author CaiRH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastExchangeLoginReq {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 只有loginType为1 时有用 1 买2 卖
     */
    private Integer type = 1;
    /**
     * 登陆类型 1 为币宝 2 为秘密和钱包
     */
    private Integer loginType = 2;
    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 当前可支持兑换的币种["BCB","USDX","DC","ETH"]
     */
    private String exchangeCoin;
    /**
     * 当前用户可用于支付的币种和金额。一个数组结构的json字符串,样例:<br />[{"coin":"BCB","amount":99.99},{"coin":"USDX","amount":88.88}]
     */
    private String coinList;

    /**
     * {‘USDX’: 别名, 'DC': 别名}
     */
    private String coinAlias;
}