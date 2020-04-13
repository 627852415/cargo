package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lxtx.framework.common.utils.ToStrForBigDecimalSerialize;
import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 商家成交统计下载
 * </p>
 *
 **/
@Getter
@Setter
public class OffsiteExchangeMerchantGoodsStatisticsDownloadResp {

	/**
	 * 用户Id
	 */
	@ExcelField(name = "用户Id", orderBy = "1")
	private String account;

	/**
	 * 商家昵称
	 */
	@ExcelField(name = "商家昵称", orderBy = "2")
	private String name;

	/**
	 * 币对
	 */
	@ExcelField(name = "币对", orderBy = "3")
	private String waveRate;

	/**
	 * 成交量
	 */
	@ExcelField(name = "交易量", orderBy = "4")
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal completedVolume;

	/**
	 * 成交量单位
	 */
	@ExcelField(name = "单位", orderBy = "5")
	private String completedVolumeCoinName;

	/**
	 * 交易额
	 */
	@ExcelField(name = "交易额", orderBy = "6")
	@JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal completedAmount;

	/**
	 * 交易额单位
	 */
	@ExcelField(name = "单位", orderBy = "7")
	private String completedAmountCoinName;

	/**
	 * 上架数量
	 */
	@ExcelField(name = "上架数量", orderBy = "8")
	private Integer pullOnVolume;

	/**
	 * 下架数量
	 */
	@ExcelField(name = "下架数量", orderBy = "9")
	private Integer pullOffVolume;

	/**
	 * 关闭数量
	 */
	@ExcelField(name = "关闭数量", orderBy = "10")
	private Integer pullCloseVolume;

	/**
	 * 平均汇率
	 */
	@ExcelField(name = "平均汇率", orderBy = "11")
    @JsonSerialize(using = ToStrForBigDecimalSerialize.class)
	private BigDecimal avgExchangeRate;

	/**
	 * 平均波动汇率
	 */
	@ExcelField(name = "波动汇率", orderBy = "12")
	private String floatScopePercent;

	/**
	 * 返利
	 */
	@ExcelField(name = "返利", orderBy = "13")
	private BigDecimal merchantRebateAmount ;

	/**
	 * 返利
	 */
	@ExcelField(name = "返利单位", orderBy = "14")
	private String merchantRebateAmountCoinName ;

	/**
	 * 利润
	 */
	@ExcelField(name = "利润", orderBy = "15")
	private BigDecimal platformRebateAmount ;

	/**
	 * 利润
	 */
	@ExcelField(name = "利润单位", orderBy = "16")
	private String platformRebateAmountCoinName ;

	/**
	 * 挂单时间
	 */
	@ExcelField(name = "挂单时间", orderBy = "17")
	private String statisticsTime;
}
