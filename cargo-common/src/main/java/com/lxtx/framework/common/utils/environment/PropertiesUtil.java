package com.lxtx.framework.common.utils.environment;

import com.lxtx.framework.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;

/**
 * 获取配置文件的参数
 * @author  tangdy
 */
public class PropertiesUtil {
    /**
     * 签名开关
     */
    public static final String SIGNATURE_SERVER_SWITCH = "signature.server.switch";
    public static final String SIGNATURE_CLIENT_SWITCH = "signature.client.switch";
    /**
     * 处理日志拦截器不打印body的url
     */
    public static final String IGNORE_BODY_URL = "ignore.body.url";

    /**
     * 文件相关
     */
    public static final String FILE_MAX_SIZE = "file.max-size";

    //gif最大限制
    public static final String GIF_MAX_SIZE = "gif.max-size";

    public static final String LOCAL_FILE_SAVE_PATH = "file.local-file-save-path";
    public static final String LOCAL_FILE_VIEW_PATH = "file.local-file-view-path";
    public static final String FILE_VIEW_URL = "file.view-url";

    public static final String LOCAL_PIECE_SAVE_PATH = "file.local-piece-save-path";
    public static final String LOCAL_PIECE_VIEW_PATH = "file.local-piece-view-path";
    public static final String FILE_VIEW_URL_V2 = "file.view-url_v2";
    public static final String CLEAN_EXPIRED_DAYS = "file.clean_expired_days";
    public static final String UNDONE_UPLOAD_HOURS = "file.undone_upload_hours";


    public static final String FILE_REPLACE_LOCAL_COMPARE_IMAGE_SOURCE_PATH = "file.replace-local-compare-image-source-path";
    /**
     * 群头像背景图片地址
     */
    public static final String FILE_GROUP_BACKGROUND_ICON_PATH = "file.group-background-icon-path";

    /**
     * 数据库相关
     */
    public static final String DATASOURCE_URL = "spring.datasource.url";
    public static final String DATASOURCE_USERNAME = "spring.datasource.username";
    public static final String DATASOURCE_PASSWORD = "spring.datasource.password";
    public static final String DATASOURCE_DRIVER_CLASS_NAME = "spring.datasource.driver-class-name";
    public static final String DATASOURCE_INITIALSIZE = "spring.datasource.initialSize";
    public static final String DATASOURCE_MINIDLE = "spring.datasource.minIdle";
    public static final String DATASOURCE_MAXACTIVE = "spring.datasource.maxActive";
    public static final String DATASOURCE_MAXWAIT = "spring.datasource.maxWait";
    public static final String DATASOURCE_TIME_BETWEEN_EVICTION_RUNS_MILLIS = "spring.datasource.timeBetweenEvictionRunsMillis";
    public static final String DATASOURCE_MIN_EVICTABLE_IDLE_TIME_MILLIS = "spring.datasource.minEvictableIdleTimeMillis";
    public static final String DATASOURCE_VALIDATION_QUERY = "spring.datasource.validationQuery";
    public static final String DATASOURCE_TEST_WHILE_IDLE = "spring.datasource.testWhileIdle";
    public static final String DATASOURCE_TEST_ON_BORROW = "spring.datasource.testOnBorrow";
    public static final String DATASOURCE_TEST_ON_RETURN = "spring.datasource.testOnReturn";
    public static final String DATASOURCE_FILTERS = "spring.datasource.filters";
    public static final String DATASOURCE_LOG_SLOW_SQL = "spring.datasource.logSlowSql";

    /**
     * 短信相关
     */
    public static final String YUNSMS_URL = "yunSms.url";
    public static final String YUNSMS_ACCOUNT = "yunSms.account";
    public static final String YUNSMS_PASSWORD = "yunSms.password";
    public static final String ISMS_BASEURL = "isms.baseurl";
    public static final String ISMS_USERNAME = "isms.username";
    public static final String ISMS_PASSEORDISM = "isms.passeordism";

    public static final String YP_APIKEY = "yp.apikey";
    public static final String YP_VERSION = "yp.version";
    public static final String YP_SMS_HOST = "yp.sms.host";
    public static final String YP_HTTP_CONN_TIMEOUT = "yp.http.conn.timeout";
    public static final String YP_HTTP_CONN_MAXPERROUTE = "yp.http.conn.maxperroute";
    public static final String YP_HTTP_CONN_MAXTOTAL = "yp.http.conn.maxtotal";
    public static final String YP_HTTP_SO_TIMEOUT = "yp.http.so.timeout";
    public static final String YP_HTTP_CHARSET = "yp.http.charset";

    /**
     * was s3
     */
    public static final String AWS_ACCESS_KEY = "aws.access_key";
    public static final String AWS_SECRET_KEY = "aws.secret_key";
    public static final String AWS_BUCKET_NAME = "aws.bucket_name";
    public static final String AWS_ENDPOINT = "aws.endpoint";
    public static final String AWS_REGIONS = "aws.regions";
    public static final String AWS_VIEW_URL = "aws.view-url";


    /**
     * 牛牛游戏底注最大倍率
     */
    public static final String NIU_NIU_ANTE_MULTI = "game.niuniu.ante-multi";
    public static final String GROUP_MEMBER_MAX_SIZE = "group.member_max_size";
    /**
     * 牛牛游戏最低参与人数
     */
    public static final String GAME_NIUNIU_PLAYER_MINNUM = "game.niuniu.player.minNum";
    /**
     * 牛牛游戏最高参与人数
     */
    public static final String GAME_NIUNIU_PLAYER_MAXNUM = "game.niuniu.player.maxNum";

    /**
     * 牛牛游戏domain
     */
    public static final String GAME_NIUNIU_DOMAIN_STR = "game_niuniu";

    /**
     * 21点游戏domain
     */
    public static final String GAME_BLACKJACK_DOMAIN_STR = "game_blackjack";

    /**
     * 扫雷游戏domain
     */
    public static final String GAME_MINESWEEPER_DOMAIN_STR = "game_minesweeper";

    /**
     * 游戏最大参与次数ikey
     */
    public static final String GAME_MAX_JOIN_NUM_IKEY_STR = "max_join_num";


    /**
     * 游戏获胜是否通知所有人开关
     */
    public static final String GAME_OVER_NOTIFY_ALL_SWITCH ="game.over.notice.switch";

    /**
     * 是否需要推送游戏开始消息给庄家
     */
    public static final String SECRET_APP_RELEASE_SWITCH = "app_release_switch";

    /**
     * 游戏规则地址
     * @since 2018-12-27
     */
    public static final String GAME_RULE_URL = "game.ruleUrl";

    /**
     * api 接口加密
     *
     * */
    public static final String API_AESKEY="api.aesKey";
    public static final String API_AESIV="api.aesIv";
    public static final String API_IGNOREURL="api.ignoreUrl";
    public static final String API_IGNOREDOWNURL="api.ignoreDownUrl";

    /**
     * jsonrpc 非对称性签名加密信息
     */
    public static final String JSONRPC_CODE="jsonrpc.code";

    /**
     * ios,pushKit推送相关
     */
    public static final String PUSH_KIT_PRODUCTION = "ios-pushKit.production";
    public static final String PUSH_KIT_CERTIFICATE_PATH = "ios-pushKit.certificatePath";
    public static final String PUSH_KIT_CERTIFICATE_PASSWORD = "ios-pushKit.certificatePassword";


    /**
     * config.profile
     *
     * */
    public static final String CONFIG_PROFILE="spring.cloud.config.profile";
    private static Environment env;

    @Autowired
    public void set(Environment env) throws IOException {
        PropertiesUtil.env = env;
    }

    /**
     * key不存在则返回null
     * @param key
     * @return
     */
    public static String getString(String key) {
        try {
            return env.getProperty(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * key不存在则返回默认值
     *
     * @param key
     * @return
     */
    public static String getString(String key, String defaultValue) {
        try {
            String value = env.getProperty(key, defaultValue);
            if (StringUtils.isBlank(value)) {
                return defaultValue;
            }
            return value;
        } catch (MissingResourceException e) {
            return defaultValue;
        }
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return Integer.parseInt(env.getProperty(key));
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        String value = env.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        String property = env.getProperty(key);
        return new Boolean(property);
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = env.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return new Boolean(value);
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param  regex 分割符
     * @return
     */
    public static String[] getArray(String key, String regex) {
        String value = env.getProperty(key);
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.split(regex);
    }


    /**
     * 根据key获取值
     *
     * @param key
     * @param  regex 分割符
     * @return
     */
    public static List<String> getList(String key, String regex) {
        String value = env.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return new ArrayList<>();
        }
        String[] split = value.split(regex);
        if(split == null){
            return null;
        }
        for (int i = 0; i< split.length;i++ ) {
            split[i] =split[i].trim();
        }
        return Arrays.asList(split);
    }

    /**
     * 获取文件访问url
     * @param path
     * @return
     */
    public static String getFileViewUrl(String path) {
        if(StringUtils.isBlank(path)){
            return null;
        }
        try {
            if(path.startsWith(Constants.FileViewUrlType.LOCAL_FILE) || path.startsWith(Constants.FileViewUrlType.LOCAL_FILE_NEW)){
                return env.getProperty(FILE_VIEW_URL).concat(path);
            }else if (path.startsWith(Constants.FileViewUrlType.AMAZONS3)){
                return env.getProperty(AWS_VIEW_URL).concat(path);
            }
            return null;
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * 获取文件访问host
     * @param path
     * @return
     */
    public static String getFileViewHost(String path) {
        if(StringUtils.isBlank(path)){
            return null;
        }
        try {
            if(path.contains(Constants.FileViewUrlType.LOCAL_FILE) || path.contains(Constants.FileViewUrlType.LOCAL_FILE_NEW)){
                return env.getProperty(FILE_VIEW_URL);
            }else if (path.contains(Constants.FileViewUrlType.AMAZONS3)){
                return env.getProperty(AWS_VIEW_URL);
            }
            return null;
        } catch (MissingResourceException e) {
            return null;
        }
    }
}
