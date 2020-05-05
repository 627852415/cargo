package com.lxtx.framework.common.utils.geoip;

import com.lxtx.framework.common.constants.PropertiesContants;
import com.lxtx.framework.common.utils.IPAddressUtil;
import com.lxtx.framework.common.utils.environment.PropertiesUtil;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * 通过IP获取国家信息
 *
 * @author tangdy
 * @since 2019-01-07
 */
@Slf4j
public class GeoLocationUtil {

    private static DatabaseReader reader;

    /**
     * 初始化数据库 GeoLite2-City.mmdb文件放在resource下(改为放在固定目录下)
     */
    @Autowired
    public void setReader(){
        String filePath = PropertiesUtil.getString(PropertiesContants.GEOLITE2_PATH);
        if (StringUtils.isBlank(filePath)) {
            log.error("Geoip location database is not found - ");
        } else {
            try {
                File database = new File(filePath);
                reader = new DatabaseReader.Builder(database).build();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 获取ip地址映射的国家编码
     *
     * @param request
     * @return
     */
    public static String getCountryCode(HttpServletRequest request, String defaultCountryCode) {
        String ipAddress = IPAddressUtil.getIpAddress(request);
        log.info("当前请求IP为{}", ipAddress);
        if(StringUtils.isBlank(ipAddress) || "0.0.0.1".equals(ipAddress) || "127.0.0.1".equals(ipAddress) || ipAddress.contains("192.168")){
            return defaultCountryCode;
        }
        GeoLocation locationV2 = getLocationV2(ipAddress);
        if(locationV2 == null){
            return defaultCountryCode;
        }
        return locationV2.getCountryCode();
    }


    /**
     * 获取ip地址映射的国家
     * @param request
     * @return
     */
    public static GeoLocation getLocation(HttpServletRequest request) {
        String ipAddress = IPAddressUtil.getIpAddress(request);
        return getLocationV2(ipAddress);
    }


    /**
     * 获取ip地址映射的国家
     * @param ipAddress
     * @return
     */
    public static GeoLocation getLocationV2(String ipAddress) {
        GeoLocation geoLocation = null;
        if (null == reader) {
            return null;
        } else {
            try {
                geoLocation = new GeoLocation();
                InetAddress ipAdd = InetAddress.getByName(ipAddress);
                CityResponse response = reader.city(ipAdd);

                Country country = response.getCountry();
                geoLocation.setCountryCode(country.getIsoCode());
                geoLocation.setCountryName(country.getName());

                Subdivision subdivision = response.getMostSpecificSubdivision();
                geoLocation.setRegionName(subdivision.getName());

                City city = response.getCity();
                geoLocation.setCity(city.getName());
                geoLocation.setPostalCode(response.getPostal().getCode());
                geoLocation.setLatitude(String.valueOf(response.getLocation().getLatitude()));
                geoLocation.setLongitude(String.valueOf(response.getLocation().getLongitude()));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (GeoIp2Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return geoLocation;
    }
}
