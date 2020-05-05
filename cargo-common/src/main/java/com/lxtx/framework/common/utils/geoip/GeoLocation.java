package com.lxtx.framework.common.utils.geoip;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author tangdy
 * @since 2019-01-08
 */
@Setter
@Getter
public class GeoLocation {

    /**
     * 国家编码
     */
    private String countryCode;
    /**
     * 国家名称
     */
    private String countryName;
    private String region;
    private String regionName;
    private String city;
    private String postalCode;
    private String latitude;
    private String longitude;

}
