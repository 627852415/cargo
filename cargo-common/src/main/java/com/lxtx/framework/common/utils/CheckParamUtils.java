package com.lxtx.framework.common.utils;

import com.lxtx.framework.common.constants.Constants;
import com.lxtx.framework.common.exception.LxtxException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 参数校验工具
 *
 * @author lch
 */
public final class CheckParamUtils {
    private CheckParamUtils() {
    }

    /**
     * 断言目标为 true
     */
    public static void checkTrue(boolean target, String message) {
        if (!target) {
            throwException(message);
        }
    }

    /**
     * 断言目标为 false
     */
    public static void checkFalse(boolean target, String message) {
        if (target) {
            throwException(message);
        }
    }

    /**
     * 断言目标为空
     */
    public static void checkNull(Object target, String message) {
        if (target != null) {
            throwException(message);
        }
    }

    /**
     * 断言目标非空
     */
    public static void checkNotNull(Object target, String message) {
        if (target == null) {
            throwException(message);
        }
    }

    /**
     * 断言字符串为空（空字符串）
     */
    public static void checkEmpty(String target, String message) {
        if (!isEmpty(target)) {
            throwException(message);
        }
    }

    /**
     * 断言字符串非空（空字符串）
     */
    public static void checkNotEmpty(String target, String message) {
        if (isEmpty(target)) {
            throwException(message);
        }
    }

    /**
     * 断言集合为空
     */
    public static void checkEmpty(Collection collection, String message) {
        if (collection != null && !collection.isEmpty()) {
            throwException(message);
        }
    }

    /**
     * 断言集合非空
     */
    public static void checkNotEmpty(Collection collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throwException(message);
        }
    }

    /**
     * 断言数据大于零
     */
    public static void checkBigDecimalGreaterThanZero(BigDecimal bigDecimal, String message) {
        if (bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
            throwException(message);
        }
    }

    /**
     * 断言数据小于零
     */
    public static void checkBigDecimalLessThanZero(BigDecimal bigDecimal, String message) {
        if (bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
            throwException(message);
        }
    }


    /********以下为国际化*********/

    /**
     * 断言目标为 true
     */
    public static void checkTrueWithLocale(boolean target, String key) {
        if (!target) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言目标为 false
     */
    public static void checkFalseWithLocale(boolean target, String key) {
        if (target) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言目标为空
     */
    public static void checkNullWithLocale(Object target, String key) {
        if (target != null) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言目标非空
     */
    public static void checkNotNullWithLocale(Object target, String key) {
        if (target == null) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言字符串为空（空字符串）
     */
    public static void checkEmptyWithLocale(String target, String key) {
        if (!isEmpty(target)) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言字符串非空（空字符串）
     */
    public static void checkNotEmptyWithLocale(String target, String key) {
        if (isEmpty(target)) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言集合为空
     */
    public static void checkEmptyWithLocale(Collection collection, String key) {
        if (collection != null && !collection.isEmpty()) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言集合非空
     */
    public static void checkNotEmptyWithLocale(Collection collection, String key) {
        if (collection == null || collection.isEmpty()) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言数据大于零
     */
    public static void checkBigDecimalGreaterThanZeroWithLocale(BigDecimal bigDecimal, String key) {
        if (bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
            throwExceptionWithLocale(key);
        }
    }

    /**
     * 断言数据小于零
     */
    public static void checkBigDecimalLessThanZeroWithLocale(BigDecimal bigDecimal, String key) {
        if (bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
            throwExceptionWithLocale(key);
        }
    }


    private static boolean isEmpty(String value) {
        int length;
        if (value != null && (length = value.length()) != 0) {
            for (int index = 0; index < length; ++index) {
                char ch = value.charAt(index);
                if (ch != 32 && ch != 160 && !Character.isISOControl(ch)) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    private static void throwException(String message) {
        throw LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE,message);
    }

    /**
     * 加入国际化
     * @param key
     */
    private static void throwExceptionWithLocale(String key) {
        throw LxtxException.newException(Constants.SYSERROR_PARAM_ERROR_CODE, LocaleUtils.getMessage(key));
    }

}
