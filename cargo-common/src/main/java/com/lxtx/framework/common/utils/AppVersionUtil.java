package com.lxtx.framework.common.utils;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * @Title: app版本判断
 * @Description: app版本判断
 * @author: LiuLP
 * @date: 2018-11-23
 */
@Slf4j
public class AppVersionUtil {

    /**
     * 获取版本号纯数字
     *
     * @param version
     * @return
     */
    public static Integer getAppVersion(String version) {
        if (StringUtils.isNotBlank(version)) {
            try {
                version = version.replace(".", "");
            } catch (Exception e) {
                throw LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE, "版本号不正确");
            }
            return Integer.valueOf(version);
        }
        return null;
    }

    /**
     * 比较版本号
     * true: curVersion >= minVersion
     * false: curVersion < minVersion
     *
     * @param curVersion 当前版本号 1.3.3
     * @param minVersion 最低版本号 1.4.0
     * @return
     */
    public static boolean greaterThen(String curVersion, String minVersion) {
        boolean bool = false;
        try {
            if (StringUtils.isNotBlank(curVersion) && StringUtils.isNotBlank(minVersion)) {
                Integer curr = Integer.valueOf(curVersion.replace(".", ""));
                Integer min = Integer.valueOf(minVersion.replace(".", ""));

                if (curr >= min) {
                    bool = true;
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }

        return bool;
    }

    public static void main(String[] args) {
        System.out.println(greaterThen("1.4.0", "1.4.0"));
        System.out.println(greaterThen("1.3.0", "1.4.0"));
        System.out.println(greaterThen("1.5.0", "1.4.0"));
    }

}
