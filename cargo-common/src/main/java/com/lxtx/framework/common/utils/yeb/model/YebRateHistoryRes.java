package com.lxtx.framework.common.utils.yeb.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * 查询历史利率信息
 */
@Getter
@Setter
public class YebRateHistoryRes implements Serializable {
    /**
     * 表示最新收益/收益明细参数列表
     */
    private List<Map<String, BigDecimal>> yesterdayIncoming = null;
    /**
     * 表示七天年化利率参数列表
     */
    private List<Map<String, BigDecimal>> sevenRate = null;
    /**
     * 表示万份收益参数列表
     */
    private List<Map<String, BigDecimal>> tenThousandRate = null;
}
