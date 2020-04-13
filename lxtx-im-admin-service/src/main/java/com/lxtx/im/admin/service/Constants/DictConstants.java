package com.lxtx.im.admin.service.Constants;

/**
 * @description: 通知常量类
 * @author: CXM
 * @create: 2018-10-12 18:43
 */
public interface DictConstants {

    /**
     * 每笔交易监控通知域
     */
    String PER_AMOUNT_NOTICE = "per_amount_notice";
    /**
     * 添加币种通知域
     */
    String ADD_COIN_NOTICE = "add_coin_notice";
    /**
     * 每个币种每笔最大交易金额域
     */
    String COIN_PER_AMOUNT_MAX = "coin_per_amount_max";

    /**
     * 通知类型
     */
    String TYPE = "type";

    /**
     * 系统管理操作
     */
    String DICT_DOMAIN_SYSTEM_MANAGER = "system_manager";
    
    /**
     * 机器人token
     */
    String TELEGRAM_BOT_TOKEN = "telegram_bot_token";
    /**
     * 监控推送的电报群id
     */
    String TELEGRAM_CHAT_ID = "telegram_chat_id";
    /**
     * 手机
     */
    String TELEPHONE = "telephone";
    /**
     * 邮箱
     */
    String EMAIL = "email";
    /**
     * 邮箱抄送人
     */
    String CC = "cc";
    /**
     * send_in_blue 域
     */
    String SEND_IN_BLUE = "send_in_blue";
    /**
     * send_in_blue 的api_key
     */
    String API_KEY = "api_key";
    
    /**
     * admin系统管理操作
     */
    String DICT_DOMAIN_ADMIN_SYSTEM_MANAGER = "admin_system_manager";
    
    /**
     * 交易记录导出数据数据数量限制(用于后台管理)
     */
    String TRANSACTION_EXPORT_SIZE = "transaction_export_size";
}
