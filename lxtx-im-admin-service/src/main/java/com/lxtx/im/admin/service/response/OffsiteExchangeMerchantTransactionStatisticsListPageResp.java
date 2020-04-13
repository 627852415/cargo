package com.lxtx.im.admin.service.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商家成交统计分页返回类
 * </p>
 *
 **/
@Getter
@Setter
public class OffsiteExchangeMerchantTransactionStatisticsListPageResp {

	private Integer total;
	private Integer size;
	private Integer pages;
	private Integer current;

	List<OffsiteExchangeMerchantTransactionStatisticsDTO> records;

	@Getter
	@Setter
	public static class OffsiteExchangeMerchantTransactionStatisticsDTO {

		/**
		 * 用户Id
		 */
		private String account;

		/**
		 * 用户昵称
		 */
		private String name;

		/**
		 * 电话号码
		 */
		private String telephone;

		/**
		 * 买家【我有】币种Id
		 */
		private String sourceCoin;

		/**
		 * 买家【可买】币种Id
		 */
		private String targetCoin;

		/**
		 * 订单完成数量
		 */
		private Integer orderCompletedVolume;

		/**
		 * 订单取消数量
		 */
		private Integer orderCancelVolume;

		/**
		 * 成交量
		 */
		private BigDecimal completedVolume;

		/**
		 * 成交金额
		 */
		private BigDecimal completedAmount;

		/**
		 * 平均汇率
		 */
		private BigDecimal avgExchangeRate;

		/**
		 * 平均波动汇率
		 */
		private BigDecimal floatScopePercent;

		/**
		 * 统计时间
		 */
		private String statisticsTime;

		private String createTime;
	}

}
