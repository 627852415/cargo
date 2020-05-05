package com.lxtx.framework.common.constants;

/**
 * 字典常量类
 *
 * @author CaiRH
 * @since 20190228
 */
public interface DictConstants {

    //****************************************** domain ****************************************
    /**
     * IM账号相关
     */
    String DICT_DOMAIN_IM_ACCOUNT = "im_account";
    String DICT_DOMAIN_GAME = "game";
    String DICT_DOMAIN_SCAN_PAY = "scan_pay";
    String DICT_DOMAIN_RED_PACKET = "red_packet";
    String DICT_DOMAIN_GAME_MINESWEEPER_REWARD_PLAYER = "game_minesweeper_reward_player";
    String DICT_DOMAIN_GAME_MINESWEEPER_REWARD_BANKER = "game_minesweeper_reward_banker";
    String DICT_DOMAIN_GAME_NOTICE = "game_notice";
    String DICT_DOMAIN_USER_COIN_TRADE = "user_coin_trade";
    String DICT_DOMAIN_GLOBAL = "global";
    String DICT_DOMAIN_YEB = "yeb";
    String DICT_DOMAIN_BCB_BANK = "bcb_bank";
    String DICT_DOMAIN_OFFSITE_EXCHANGE = "offsite_exchange";
    String DICT_DOMAIN_OTC_WITHDRAW = "otc_withdraw";
    String DICT_DOMAIN_AGORA = "agora";
    String DICT_DOMAIN_TOPGATE = "topgate";


    /**
     * 系统管理操作
     */
    String DICT_DOMAIN_SYSTEM_MANAGER = "system_manager";

	/**
     * 扫雷游戏
     */
    String DICT_DOMAIN_GAME_MINESWEEPER = "game_minesweeper";
    /**
     * 币种对波动汇率
     */
    String DICT_DOMAIN_COIN_PAIRS_WAVE_RATE = "coin_pairs_wave_rate";
    /**
     * 余额宝后台币种手续费
     */
    String DICT_DOMAIN_YEB_FEE = "yeb_fee";

    /**
     * 特殊国家域
     */
    String DICT_DOMAIN_SPECIAL_COUNTRY = "special_country";

    /**
     * 邀请好友文案
     */
    String DICT_DOMAIN_INVITE_FRIEND = "invite_friend";


    //****************************************** key ****************************************
    /**
     * 禁用登录账号列表（英文逗号分隔）
     */
    String DICT_KEY_DISABLING_LIST = "disabling_list";
    /**
     * 游戏结果关闭窗口通知key
     */
    String DICT_KEY_END_CLOSE_WINDOW = "end_close_window";

    /***
     * 游戏规则key
     */
    String DICT_KEY_GAME_RULE_URL = "game_rule_url";
    /**
     * 红包超时时间
     */
    String DICT_KEY_RED_PACKET_TIMEOUT = "timeout";

    /**
     * 返佣开关
     */
    String DICT_KEY_REBATE_SWITCH = "rebate_switch";
    /**
     *
     */
    String DICT_KEY_USER_COIN_TRADE_MAX_FAILTIMES = "max_failTimes";

    /**
     * 扫雷游戏庄家收益手续费配置百分比%
     */
    String DICT_KEY_BANKER_PROFIT_FEE_PERCENT = "banker_profit_fee_percent";

    /**
     * 扫雷游戏庄家收益钱包用户账号
     */
    String DICT_KEY_BANKER_PROFIT_USER_ID = "banker_profit_user_id";
    /**
     * 平台红包金额百分比% （如：0.05）
     * 发游戏红包时扣减
     * @since 2019-03-01
     */
    String DICT_KEY_GAME_PLATFORM_REDPACKET_PERCENT = "platform_redpacket_percent";
    /**
     * 商家可用收款币种列表，多个逗号隔开
     * @since 2019-03-08
     */
    String DICT_KEY_RECEIPT_COINID_LIST = "receipt_coinid_list";

    /**
     * 扫码付款收益钱包用户账号
     */
    String DICT_KEY_SCAN_PAY_PROFIT_USER_ID = "scan_pay_profit_user_id";

    /**
     * 扫码付款可支付币种列表，多个逗号隔开
     *
     * @since 2019-03-14
     */
    String DICT_KEY_SCAN_PAY_COIN_ID_LIST = "scan_pay_coin_id_list";

    /**
     * OTC（未认证）提款日限额
     *
     * @since 2019-03-18
     */
    String DICT_KEY_OTC_WITHDRAWAL_DATE_LIMIT = "otc_withdrawal_date_limit";
    /**
     * 币支付提款日限额
     *
     * @since 2020-1-2
     */
    String DICT_KEY_TOPGATE_WITHDRAWAL_DATE_LIMIT = "topgate_withdrawal_date_limit_amount";

    /**
     * OTC（未认证）提款月限额
     *
     * @since 2019-03-18
     */
    String DICT_KEY_OTC_WITHDRAWAL_MONTH_LIMIT = "otc_withdrawal_month_limit";
    /**
     * otc提款限额字典域名
     */
    String DICT_DOMAIN_OTC_LIMIT_AMOUNT = "otc_limit_amount";

    /**
     * 币支付提款限额字典域名
     */
    String DICT_DOMAIN_TOPGATE_LIMIT_AMOUNT = "topgate";

    /**
     * 特殊处理国家编码
     */
    String SPECIAL_COUNTRY = "special_country";
    /**
     * 特殊处理国家编码列表
     */
    String COUNTRY_CODE_LIST = "country_code_list";
    /**
     *
     */
    String SHOW_COIN_LIST = "show_coin_list";
    
    String DEADLINE = "deadline";
    
    String PULL_OFF_DAY = "pull_off_day";

    String MAXIMUM_NUMBER_OF_ORDERS = "maximum_number_of_orders";

    /**
     * 商家交易对象上限
     */
    String MERCHANT_TRANSACTION_LIMIT = "merchant_transaction_limit";
    
    String DEFAULT_PULL_OFF_DAY = "default_pull_off_day";

    /**
     * 币种对波动汇率
     *
     * @since 2019-03-25
     */
    String DICT_KEY_COIN_PAIRS_WAVE_RATE = "%s-%s";


    /**
     * OTC登录 限定用户使用的组编号 domain
     *
     * @since 2019-03-25
     */
    String DICT_DOMAIN_OTC_LOGIN_USABLEGROUP = "otc_login_usablegroup";

    /**
     * OTC登录 限定用户使用的组编号 key
     *
     * @since 2019-03-25
     */
    String DICT_KEY_OTC_LOGIN_USABLEGROUP = "otc_login_usablegroup_groupno";
    /**
     * OTC登录 限定用户使用的组编号 key，极速通道
     *
     * @since 2019-03-25
     */
    String DICT_KEY_OTC_LOGIN_USABLEGROUP_FAST = "otc_login_usablegroup_groupno_fast";

    /**
     * 余额宝后台交易收取手续费币种类型
     */
    String DICT_KEY_YEB_FEE_COIN_ID = "effect_coin_id";

    /**
     * 钱包首页可选虚拟币列表
     * e.g. 1032514234185035777,1032514234155675650
     * @since 2019-03-29
     */
    String DICT_KEY_WALLET_VIRTUAL_COIN_LIST = "wallet_virtual_coin_list";
    /**
     * 钱包模式白名单开关
     *
     * @since 2019-04-03
     */
    String DICT_KEY_WALLET_PATTERN_WHITE_LIST_OPEN = "wallet_pattern_white_list_open";

    /**
     * 特殊国家编码集合
     */
    String DICT_KEY_COUNTRY_CODE_LIST = "country_code_list";

    /**
     * 使用钱包模式最低版本控制
     * 2019-04-11
     */
    String USE_WALLET_PATTERN_MIN_VERSION = "use_wallet_pattern_min_version";
    /**
     * 余额宝默认币种
     */
    String DEFAULT_COIN_NAME = "default_coin_name";
    /**
     * 余额宝返佣说明百分比
     */
    String RAKE_BACK_PERCENTAGE = "rake_back_percentage";
    /**
     * 余额宝返佣说明基数
     */
    String RAKE_BACK_BASE = "rake_back_base";
    /**
     * 余额宝币种列表
     */
    String COIN_NAME_LIST = "coin_name_list";
    /**
     * 是否显示余额宝
     */
    String DICT_KEY_NOT_SHOWING_YEB = "not_showing_yeb";
    /**
     * 余额宝-同步每秒用户数量
     */
    String DICT_KEY_PER_CONSUMER_TOTAL = "per_consumer_total";
    /**
     * 余额宝-同步每秒用户数量的时间间隔(秒)
     */
    String DICT_KEY_PER_CONSUMER_INTERVAL = "per_consumer_interval";
    /**
     * 当前用户是否显示余额宝入口,注册用户的截止时间
     */
    String DICT_KEY_SHOW_YEB_REGISTRATION_TIME_END = "show_yeb_registration_time_end";

    /**
     * 余额宝转入流程测试模式开关（true代表测试模式屏蔽6X提币流程）
     */
    String DICT_KEY_RECHARGE_TEST_MODE = "recharge_test_mode";
    /**
     * Web usb token是否开启 的开关
     */
    String DICT_KEY_USB_TOKEN_SWITCH = "usb_token_switch";
    /**
     * BCB银行资金转移debug开关
     */
    String DICT_KEY_BCB_TRANSFER_DEBUG_SWITCH = "bcb_transfer_debug_switch";
    /**
     * 是否显示银行服务
     */
    String DICT_KEY_NOT_SHOWING_BANK_SERVICE = "not_showing_bank_service";

    /**
     * 线下换汇客服账号
     */
    String DICT_KEY_DOMAIN_OFFSITE_HELPDESK_ACCOUNT="helpdesk_account";
    /**
     * 线下换汇 - 可换汇货币列表
     * @since 2019-04-23
     */
    String DICT_KEY_OFFSITE_EXCHANGE_CURRENCY_LIST = "exchange_currency_list";
    /**
     * 线下换汇 - 发布兑换的手续费
     * @since 2019-04-30
     */
    String DICT_KEY_OFFSITE_EXCHANGE_PUBLISH_FEE = "fee";
    
	/**
	 * 线下换汇—商家的兑换商品最大上架数
	 * 
	 */
	String MAX_GOODS_PUSH_ON_COUNT = "max_goods_push_on_count";

	/**
	 * 线下换汇—商家的兑换商品最大上架数商家的兑换
	 * 
	 */
	String MAX_GOODS_PULL_OFF_COUNT = "max_goods_pull_off_count";
	
	/**
	 * 线下换汇—商家发布兑换的目标兑换金额最小限制数值
	 * 
	 */
	String MIN_TARGET_AMOUNT = "min_target_amount";
    
    /**
     * 线下换汇 - 控制商家申请邀请码状态
     * 必填1、选填2、不显示3
     * EnumOffsiteInviteCodeStatus
     * value: <status>#<标题>， e.g. 1#邀请码（必填）
     * @since 2019-04-26
     */
    String DICT_KEY_OFFSITE_INVITECODE_STATUS = "inviteCode_status";

    /**
     * 邀请好友国际化文案KEY
     */
    String DICT_KEY_INVITE_FRIEND_LOCALE = "invite_friend_locale";
    String DICT_KEY_INVITE_FRIEND_TITLE = "invite_friend_title";
    String DICT_KEY_INVITE_FRIEND_CONTENT = "invite_friend_content";
    String DICT_KEY_INVITE_FRIEND_BUTTON = "invite_friend_button";

    /**
     * 是否显示换汇开关
     */
    String DICT_KEY_NOT_SHOWING_OFFSITE_EXCHANGE = "not_showing_offsite_exchange";


    /**
     * 是否显示游戏
     */
    String DICT_KEY_NOT_SHOWING_GAME = "not_showing_game";


    /**
     * 是否显示游戏
     */
    String DICT_KEY_NOT_SHOWING_WALLET = "not_showing_wallet";
    /**
     * 广播消息发送者列表
     */
    String  DICT_KEY_BROADCAST_ACCOUNT_LIST=  "broadcast_account_list";
    /**
     * 线下换汇 - 商品冻结、返利发放资金账号walletUserId
     * @since 2019-05-23
     */
    String DICT_KEY_OFFSITE_CAPITAL_USERID = "capital_userid";

    /**
     * 钱包首页新户默认展示币种，多个逗号隔开
     *
     * @since 2019-05-29
     */
    String DICT_KEY_SHOW_INDEX_COIN_ID_LIST = "show_index_coin_id_list";

    /**
     * OTC提款收益钱包用户账号
     */
    String DICT_KEY_OTC_WITHDRAW_PROFIT_USER_ID = "otc_withdraw_profit_user_id";

    /**
     * 多人语音通话-最大通话人数
     */
    String MAXIMUM_NUMBER_OF_CALLS = "maximum_number_of_calls";

    /**
     * 多人语音通话-同意加入超时
     */
    String OVER_TIME_NOT_ANSWER = "over_time_not_answer";
    /**
     * 是否显示群多人语音聊天
     */
    String DICT_NOT_SHOWING_VOICE_CHAT = "not_showing_voice_chat";
    
    /***
     * 全局配置-app store登录id和密码列表
     */
    String DICT_APP_STRORE_LOGIN_INFO = "app_store_login_info";
    /**
     * 线下换汇 - 换汇币种对应的国家简码
     * PHP#PH,CNY#CN,USD#US,THB#TH
     * @since 2019-07-26
     */
    String DICT_KEY_OFFSITE_COIN_COUNTRY_CODE = "coin_countryCode";
    /**
     * 换汇更多支付方式logo
     */
    String DICT_KEY_OFFSITE_EXCHANGE_MORE_PAY_TYPE_LOGO = "more_pay_type_logo";

    /**
     * 法币列表默认币种
     */
    String DICT_KEY_WALLET_RECHARGE_LEGAL_DEFAULT_COIN = "recharge_legal_default_coin";

    /**
     * 提现法币列表默认币种
     */
    String DICT_KEY_WALLET_WITHDRAW_LEGAL_DEFAULT_COIN = "withdraw_legal_default_coin";

    /**
     * 字典，柬埔寨特殊账号
     */
    String DICT_DOMAIN_SPECIAL_HK_ACCOUNT = "special_hk_account";
    /**
     * 闪兑默认币对
     */
    String  DICT_KEY_TG_FAST_EXCHANGE_DEFAULT_COIN = "tg_fast_exchange_default_coin";

    String  DICT_KEY_WITHDRAW_VIRTUAL_COIN_DEFAULT_COIN = "withdraw_virtual_coin_default_coin";
    
    /**
     * Topgate闪兑功能开关
     */
    String DICT_KEY_WALLET_FAST_EXCHANGE_SWITCH = "fast_exchange_switch";
}
