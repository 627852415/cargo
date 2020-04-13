package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @since 2019-06-03
 */
@Getter
@Setter
public class OffsiteExchangeWaveRateAreaUpdateReq {

    @NotNull(message = "WaveRate不能为空")
    private String waveRateId;
    @NotNull(message = "waveRateArea update list不能为空")
    private List<WaveRateAreaUpdate> list;

    @Getter
    @Setter
    public static class WaveRateAreaUpdate {
        private String countryCode;
        private BigDecimal waveRate;
        @NotNull(message = "汇率类型不能为空")
        private Integer rateType;
        private List<String> rebateIds;
    }

}
