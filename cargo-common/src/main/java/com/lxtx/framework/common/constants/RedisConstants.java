
package com.lxtx.framework.common.constants;

/**
 * @author LiuLP
 * @date 2018年8月9日
 */
public interface RedisConstants {
    /**
     * 群添加用户锁前缀
     */
    String GROUP_ADD_MEMBER_LOCK = "group_add_member_lock_";
    /**
     * 群添加用户锁前缀
     */
    String GROUP_REMOVE_MEMBER_LOCK = "group_remove_member_lock_";

    /**
     * 用户资产锁前缀【已弃用】
     */
    String USER_COIN_LOCK = "user_coin_lock_";


    /**
     * 余额宝用户锁
     */
    String USER_YEB_LOCK = "user_yeb_lock_";

    /**
     * 余额宝同步所有用户的锁
     */
    String USER_YEB_SYNC_LOCK = "user_yeb_sync_lock";

    /**
     * 用户锁前缀
     */
    String USER_LOCK = "user_lock_";

    /**
     * 代发工资资金转入锁
     */
    String SALARY_TRANSFER_IN_LOCK = "salary_transfer_in_lock_";

    /**
     * 代发工资资金扣减锁
     */
    String SALARY_TRANSFER_OUT_LOCK = "salary_transfer_out_lock_";

    String YEB_TRANSFER_OUT_LOCK = "yeb_transfer_out_lock_";

    /**
     * 同步资产统计锁
     */
    String ASSET_STATISTICS_LOCK = "asset_statistics_lock_";

    /**
     * 余额宝资产统计锁
     */
    String YEB_ASSETS_STATISTICS_LOCK = "yeb_assets_statistics_lock_";

    /**
     * 添加好友关系（锁）
     */
    String ADD_FRIEND_LOCK = Constants.IM_REDIS_PREFIX + "add_friend_lock_";

    /**
     * 红包退款锁前缀
     */
    String KEY_RETURN_RED = "key_return_red_";
    /**
     * 用户token前缀
     */
    String REDIS_USER_TOKEN_PREFIX = "user_token_";
    /**
     * 用户token对象前缀
     */
    String GET_USER_BY_TOKEN_RESP = "token_resp";
    /**
     * 用户cim长连接会话前缀
     */
    String CIM_PREFIX = "cim_";
    /**
     * 用户音视频聊天中并未断线
     */
    String USER_NOT_DISCONNECT = "not_disconnect_";
    /**
     * 群成员列表前缀
     */
    String GROUP_MEMBER_LIST_PREFIX = "group_member_list_";

    /**
     * 用户黑名单key
     */
    String BLACK_LIST_STRING = "black_list_string_";

    /**
     * 国际简码列表
     */
    String GLOBAL_CODE_LIST = "global_code_list";

    /**
     * 群成员id
     */
    String GROUP_MEMBER_LIST = "group_member_list_";

    /**
     * niuniu游戏结束锁
     */
    String NIU_NIU_GAME_END_LOCK = "niu_niu_game_end_lock_";

    /**
     * 21点游戏结束锁
     */
    String BLACKJACK_GAME_END_LOCK = "blackjack_game_end_lock_";

    // =========================红包 key=============================
    /**
     * 红包对象缓存
     * string类型
     * key: IM_redPacket_send_<红包id>
     * value: com.lxtx.im.wallet.dao.model.RedPacketSend
     */
    String RED_PACKET_SEND = Constants.IM_REDIS_PREFIX + "redPacket_send_";
    /**
     * 红包队列
     * list类型。
     * key: IM_redPacket_send_queue_<红包id>
     * value: com.lxtx.im.wallet.dao.model.RedPacketPool
     *
     * @since 2018-11-26
     */
    String RED_PACKET_SEND_QUEUE_ = Constants.IM_REDIS_PREFIX + "redPacket_send_queue_";
    /**
     * 红包锁
     * string类型。用于领红包时加锁
     * key： IM_redPacket_lock_<红包id>
     * value： 用户id
     */
    String RED_PACKET_LOCK = Constants.IM_REDIS_PREFIX + "redPacket_lock_";

    // 红包领取锁，拼接用户ID
    String LOCK_GRAB_RED_PACKET_ = Constants.IM_REDIS_PREFIX + "lock_grab_red_packet_";

    /**
     * 用户红包领取状态
     * string类型。用于判断用户是否领取红包
     * key: IM_redPacket_user_received_<红包id>_<用户id>
     * value: 	用户id
     *
     * @since 2018-11-26
     */
    String RED_PACKET_USER_RECEIVED_ = Constants.IM_REDIS_PREFIX + "redPacket_user_received_";
    /**
     * 红包重复点击锁
     * String类型。
     * key： RED_PACKET_RECEIVE_<红包id>_<用户id>
     * value： 用户id
     */
    String RED_PACKET_DBCLICK_ = Constants.IM_REDIS_PREFIX + "redPacket_receive_dbclick_";
    /**
     * 红包过期时间（设置红包多久过期，默认24小时）
     * String类型。对应：t_im_dict， red_packet timeout。该值从后台配置。
     * key： red_packet_timeout
     * value: 超时时间（秒）
     */
    String RED_PACKET_TIMEOUT = "red_packet_timeout";
    /**
     * 红包是否领完标识
     * String类型。当领完红包时增加该标识
     * key： IM_redPacket_finished_<红包id>
     * value: ""
     *
     * @since 2018-11-26
     */
    String RED_PACKET_FINISHED_ = Constants.IM_REDIS_PREFIX + "redPacket_finished_";
    /**
	 * 红包已领取队列
	 * sort set类型
	 */
    @Deprecated
	String RED_PACKET_RECEIVED_QUEUE_ = Constants.IM_REDIS_PREFIX + "redPacket_received_queue_";
    /**
     * 红包已领取队列 v2
     * sort set类型
     * 2019-12-19
     */
    String RED_PACKET_RECEIVED_QUEUE_V2_ = Constants.IM_REDIS_PREFIX + "redPacket_received_queue_v2_";
    /**
     * 红包领取处理锁
     * key = red_packet_receive_msg_handle_lock_[redPacketId]_[receiveId]
     * 2019-12-26
     */
    String RED_PACKET_RECEIVE_MSG_HANDLE_LOCK_ = Constants.IM_REDIS_PREFIX + "red_packet_receive_msg_handle_lock_";
    /**
     * 红包已领取数量
     * key = red_packet_receive_total_num_[redPacketId]
     * 2019-12-26
     */
    @Deprecated
    String RED_PACKET_RECEIVE_TOTAL_NUM_ = Constants.IM_REDIS_PREFIX + "red_packet_receive_total_num_";
    /**
     * 修复红包新旧数据锁
     * 2020-01-04
     */
    String RED_PACKET_COMPATIBLEOLDDATA_LOCK = Constants.IM_REDIS_PREFIX + "red_packet_compatibleolddata_lock";
    /**
     * 红包群成员信息缓存
     * hash类型： key：red_packet_group_info_[群id]，hashKey：[im account]， value：[RedPacketGroupInfoCacheDTO]
     */
    String RED_PACKET_GROUP_MEMBER_INFO_ = Constants.IM_REDIS_PREFIX + "red_packet_group_member_info_";
    /**
     * 更新红包完成状态锁 lock
     */
    String RED_PACKET_UPDATE_FINISH_STATUS_LOCK_ = Constants.IM_REDIS_PREFIX + "red_packet_update_finish_status_lock_";
    // =========================红包 key end=============================

    /**
     * 游戏用户token前缀
     */
    String REDIS_GAME_USER_TOKEN_PREFIX = "game_user_token_";
    String REDIS_GAME_USER_PREFIX = "game_user_";

    /**
     * PC登录二维码前缀
     *
     * @since 2018-11-29
     */
    String LOGIN_TOKEN_PREFIX = "login_token_";

    /**
     * 保存游戏发起者的数据
     */
    String Game_RED_PACKET_SEND = Constants.IM_REDIS_PREFIX + "game_redPacket_send_";

    /**
     * SDK 签名
     */
    String SDK_SIGN_KEY_SECRET = "sdk_sign_key_secret";

    /**
     * 同一个群只能有一个人发起游戏
     */
    String SEND_NIUNIU_GAME_ONLY_ONE = "send_niuniu_game_only_one_";

    /**
     * 牛牛领取红包个数
     */
    String NIUNIU_GAME_RECEIVE_COUNT = Constants.IM_REDIS_PREFIX + "niuniu_game_receive_count_";

    /**
     * 21点游戏领取红包个数
     */
    String BLACKJACK_GAME_RECEIVE_COUNT = Constants.IM_REDIS_PREFIX + "blackjack_game_receive_count_";

    /**
     * 用户好友缓存(备注名)
     * 保存好友对account的备注名，hashkey是好友或非好友(friendAccount)的账号， value是friendAccount为account设置的备注名
     * key: IM_friend_remarkname:<account>
     * hashkey: <friendAccount>
     * value: 备注名
     *
     * @since 2018-12-10
     */
    String FRIEND_REMARKNAME = Constants.IM_REDIS_PREFIX + "friend_remarkname:";

    /**
     * niuniu游戏结束锁
     */
    String CLOSE_GAME_LOCK = Constants.IM_REDIS_PREFIX + "close_game_lock_";
    /**
     * 游戏返点统计锁
     *
     * @since 2019-01-10
     */
    String GAME_REBATE_LOCK = Constants.IM_REDIS_PREFIX + "game_rebate_lock";
    /**
     * 游戏返点统计锁
     *
     * @since 2019-01-24
     */
    String GAME_REBATE_INSERT_LOCK = Constants.IM_REDIS_PREFIX + "game_rebate_insert_lock";
    /**
     * 游戏返点结算锁
     *
     * @since 2019-01-10
     */
    String GAME_REBATE_SETTLE_LOCK = Constants.IM_REDIS_PREFIX + "game_rebate_settle_lock";

    /**
     * 字典项
     */
    String DICT_INFO = Constants.IM_REDIS_PREFIX + "dict_";

    /**
     * 用户添加或移除表情包锁
     */
    String USER_STICKER_UPDATE_LOCK = Constants.IM_REDIS_PREFIX + "user_sticker_update_lock_";
    /**
     * 添加下级关系（锁）
     */
    String ADD_INVITE_LOWER_LOCK = Constants.IM_REDIS_PREFIX + "add_invite_lower_lock_";

    /**
     * 游戏返点统计锁(新)
     *
     * @since 2019-01-10
     */
    String GAME_REBATE_LOCK_NEW = Constants.IM_REDIS_PREFIX + "game_rebate_lock_new";

    /**
     * 游戏返点结算锁（新）
     *
     * @since 2019-01-10
     */
    String GAME_NEW_REBATE_SETTLE_LOCK = Constants.IM_REDIS_PREFIX + "game_new_rebate_settle_lock";

    String GAME_REBATE_INSERT_LOCK_NEW = Constants.IM_REDIS_PREFIX + "game_rebate_insert_lock_new";

    /**
     * 开始游戏锁
     */
    String JOB_GAME_START_LOCK = Constants.IM_REDIS_PREFIX + "job_game_start_lock";
    /**
     * 用户登录锁前缀
     */
    String USER_LOGIN_LOCK = "user_login_lock_";

    /**
     * 用户资产交易任务锁
     *
     * @since 2019-02-26
     */
    String USER_COIN_TRADE_TASK_LOCK = Constants.IM_REDIS_PREFIX + "user_coin_trade_task_lock_";
    /**
     * 平台总账户资金转入锁
     *
     * @since 2019-02-26
     */
    String PROFIT_USER_TRANSFER_IN = Constants.IM_REDIS_PREFIX + "profit_user_transfer_in_lock_";
    /**
     * 消息表路由key
     */
    String MESSAGE_ROUTER_KEY_ = "message_router_key_";

    /**
     * 用户使用语言
     */
    String USER_LANGUAGE_KEY = "user_language_";

    /**
     * 商家结算锁前缀
     */
    String MERCHANT_SETTLE_LOCK = "merchant_settle_lock_";

    /**
     * 付款订单下闪兑订单锁前缀
     */
    String PAY_ORDER_FAST_EXCHANGE_LOCK = "pay_order_fast_exchange_lock_";

    /**
     * 钱包用户前缀
     */
    String T_WALLET_USER = "t_wallet_user_";

    /**
     * 钱包用户前缀
     */
    String T_WALLET_USER_TYPE = "t_wallet_user_type_";

    /**
     * 余额宝转入申请站外提币结果处理订单锁前缀
     */
    String YEB_SIXX_WITHDRAW_LOCK = "yeb_sixx_withdraw_lock_";

    /**
     * 余额宝接口失败处理锁
     */
    String YEB_CALL_FAIL_LOCK = "yeb_call_fail_lock_";

    /**
     * 向余额宝发起存款订单锁
     */
    String YEB_DEPOSIT_LOCK = "yeb_deposit_lock_";

    /**
     * 余额宝转出资金订单锁
     */
    String YEB_WITHDRAW_LOCK = "yeb_withdraw_lock_";

    /**
     * OAuth 2.0 code 前缀
     */
    String OAUTH_CODE_PREFIX = "oauth_code_";
    /**
     * OAuth 2.0 scope 前缀
     */
    String OAUTH_SCOPE_PREFIX = "oauth_scope_";
    /**
     * OAuth 2.0 account 前缀
     */
    String OAUTH_ACCOUNT_PREFIX = "oauth_account_";
    /**
     * OAuth 2.0 token 前缀
     */
    String OAUTH_TOKEN_PREFIX = "oauth_token_";
    /**
     * OAuth 2.0 refreshToken 前缀
     */
    String OAUTH_REFRESH_TOKEN_PREFIX = "oauth_refresh_token_";
    /**
     * OAuth 2.0 code 有效时间
     */
    int OAUTH_CODE_EXPIRE = 10 * 60;
    /**
     * OAuth 2.0 token 有效时间
     */
    int OAUTH_TOKEN_EXPIRE = 30 * 60;
    /**
     * OAuth 2.0 refresh_token 有效时间
     */
    int OAUTH_REFRESH_TOKEN_EXPIRE = 24 * 60 * 60;

    /**
     * BCB资金转移锁
     */
    String BCB_TRANSFER_LOCK = "bcb_transfer_lock_";

    /**
     * BCB资金转移失败接口
     */
    String BCB_TRANSFER_FAIL_LOCK = "bcb_transfer_fail_lock_";

    /**
     * BCB提交办卡注册
     */
    String BCB_BANK_APPLY_REG_LOCK = "bcb_bank_apply_reg_lock_";

    /**
     * 线下换汇 - 商品操作锁 （买家下单、商品下架等）
     * key： offsite_gooods_operation_<goodsId>
     *
     * @since 2019-04-26
     */
    String OFFSITE_GOOODS_OPERATION_LOCK = "offsite_gooods_operation_lock_";
    /**
     * 线下换汇 - 订单操作锁 （确认完成、定时任务取消等）
     * key： offsite_order_operation_<orderId>
     *
     * @since 2019-04-28
     */
    String OFFSITE_ORDER_OPERATION_LOCK = "offsite_order_operation_lock_";
    /**
     * 线下换汇 - 取消过期订单定时任务锁
     *
     * @since 2019-04-28
     */
    String JOB_OFFSITE_CANNEL_EXPIRE_ORDER_LOCK = Constants.IM_REDIS_PREFIX + "job_offsite_cannel_expire_order_lock";
    /**
     * 线下换汇 - 下架商品定时任务锁
     *
     */
    String JOB_OFFSITE_OFF_GOODS_LOCK = Constants.IM_REDIS_PREFIX + "job_offsite_off_goods_lock";

    /**
     * 线下换汇 - 订单返利 MQ处理锁
     * key： offsite_order_rebate_mq_handle_lock
     *
     * @since 2019-05-27
     */
    String OFFSITE_ORDER_REBATE_MQ_HANDLE_LOCK = Constants.IM_REDIS_PREFIX + "offsite_order_rebate_mq_handle_lock";
    /**
     * 线下换汇 - 补发发放换汇、商家返利定时任务锁
     *
     * @since 2019-05-28
     */
    String JOB_OFFSITE_ORDER_REBATE_REPROCESS_LOCK = Constants.IM_REDIS_PREFIX + "job_offsite_order_rebate_reprocess_lock";
    /**
     * 线下换汇 - 地区汇率修改锁
     * key： offsite_wave_rate_area_lock
     *
     * @since 2019-06-03
     */
    String OFFSITE_WAVE_RATE_AREA_LOCK = Constants.IM_REDIS_PREFIX + "offsite_wave_rate_area_lock";
    /**
     * 资金报警 - 消息处理锁
     * key： alert_handle_assets_msg_lock
     *
     * @since 2019-06-11
     */
    String ALERT_HANDLE_ASSETS_MSG_LOCK = Constants.IM_REDIS_PREFIX + "alert_handle_assets_msg_lock";
    /**
     * 资金报警 - 缓存key前缀
     * key格式: IM_alert_assets_<userid>_<subType>
     * value: timestamp (score: timestamp)
     *
     * @since 2019-06-11
     */
    String ALERT_ASSETS_MSG_PREFIX = Constants.IM_REDIS_PREFIX + "alert_assets_";

    /**
     * 资金快照定时任务任务锁
     */
    String ASSETS_SNAPSHOT_TASK_LOCK = "assets_snapshot_task_lock";

    /**
     * agora频道操作锁
     */
    String AGORA_CHANNEL_OPERATION_LOCK = "agora_channel_operation_lock";

    /**
     * agora频道用户操作锁
     */
    String AGORA_CHANNEL_USER_OPERATION_LOCK = "agora_channel_user_operation_lock";

    /**
     * 生成群邀请码锁
     * @since 2019-06-25
     */
    String GROUP_CREATE_INVITE_CODE_LOCK = Constants.IM_REDIS_PREFIX + "group_create_invite_code_lock";
    /**
     * 生成用户邀请码锁
     * @since 2019-06-25
     */
    String USER_CREATE_INVITE_CODE_LOCK = Constants.IM_REDIS_PREFIX + "user_create_invite_code_lock";

    /**
     * 生成用户UID锁
     * @since 2019-06-25
     */
    String USER_CREATE_UID_CODE_LOCK = Constants.IM_REDIS_PREFIX + "user_create_uid_code_lock";
    /**
     * 生成群成员ID锁
     * @since 2019-06-25
     */
    String CREATE_GROUP_MEMBER_ID_LOCK = Constants.IM_REDIS_PREFIX + "create_group_member_id_lock";
    /**
     * 群组验证表新增锁
     *
     */
    String GROUP_VERIFY_LOCK = Constants.IM_REDIS_PREFIX + "group_verify_lock";
    /**
     * 黑名单表新增锁
     *
     */
    String BLACKLIST_ADD_LOCK = Constants.IM_REDIS_PREFIX + "blackList_add_Lock";

    /**
     * 个人app 离线消息缓存前缀
     *
     */
    String  IM_PUSH_APP_OFFLINE_MSG = Constants.IM_REDIS_PREFIX + "push_app_offline_msg_";

    /**
     * 个人pc 离线消息缓存前缀
     *
     */
    String  IM_PUSH_PC_OFFLINE_MSG = Constants.IM_REDIS_PREFIX + "push_pc_offline_msg_";


    /**
     * 消息内容
     *
     */
    String  IM_PUSH_MSG = Constants.IM_REDIS_PREFIX + "push_msg_";

    /**
     * 单个群消息统计
     *
     */
    String  IM_PUSH_MSG_GROUP_TOTAL = Constants.IM_REDIS_PREFIX + "push_msg_group_total_%s_%s";

    /**
     * 每个群的活跃人数
     *
     */
    String  IM_PUSH_MSG_GROUP_MEMBER = Constants.IM_REDIS_PREFIX + "push_msg_group_member_%s_%s";
    /**
     * im 用户信息
     */
    String IM_CORE_USER_ =  Constants.IM_REDIS_PREFIX + "core_user_";

    /**
     * 单个群消息统计
     *
     */
    String  IM_PUSH_MSG_GROUP_ID_TOTAL = Constants.IM_REDIS_PREFIX + "push_msg_group_id_total_%s_%s";

    /**
     * 所有平台的资产统计
     *
     */
    String  IM_WALLET_STATISTICS_USER_COIN_ALL_PLATFORM = Constants.IM_REDIS_PREFIX + "statistics_user_coin_all_platform";

    /**
     * 币种缓存
     * 2019-11-13
     */
    String IM_COIN_ = Constants.IM_REDIS_PREFIX + "coin_";
    /**
     * 用户收藏夹锁
     */
    String USER_FAVORITES_LOCK = "user_favorites_lock_";

    /**
     * 用户的好友头像和备注名缓存
     * key：user_friends_avatar_and_remarkname_用户平台id
     * value： Map<String, FriendInfoResp>
     * 2019-11-13
     */
    String USER_FRIENDS_AVATAR_AND_REMARKNAME_ = Constants.IM_REDIS_PREFIX + "user_friends_avatar_and_remarkname_";

    /**
     * im 黑名单信息
     */
    String IM_CORE_BLACKLIST =  Constants.IM_REDIS_PREFIX + "core_blacklist_%s_%s";

    /**
     * 同步平台用户更新信息锁
     * key： platform_user_update_task_lock
     *
     * @since 2019-12-2
     */
    String PLATFORM_USER_UPDATE_TASK_LOCK = Constants.IM_REDIS_PREFIX + "platform_user_update_task_lock";
    /**
     * 更新mq消息发送表状态锁
     * @since 2019-12-04
     */
    String MQ_MSG_SEND_UPDATE_STATUS_LOCK = Constants.IM_REDIS_PREFIX + "mq_msg_send_update_status_";

    /**
     * 交易记录-好友转账导出锁
     */
    String ADMIN_EXPORT_FRIENDS_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_friends_lock_";

    /**
     * 交易记录-钱包转账导出锁
     */
    String ADMIN_EXPORT_WALLET_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_wallet_lock_";

    /**
     * 交易记录-资金入账导出锁
     */
    String ADMIN_EXPORT_RECHARGE_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_recharge_lock_";

    /**
     * 交易记录-OTC充值导出锁
     */
    String ADMIN_EXPORT_OTC_RECHARGE_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_otc_recharge_lock_";

    /**
     * 交易记录-OTC提现导出锁
     */
    String ADMIN_EXPORT_OTC_WITHDRAW_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_otc_withdraw_lock_";

    /**
     * 交易记录-红包发送导出锁
     */
    String ADMIN_EXPORT_REDPACKETS_GIVE_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_redpackets_give_lock_";

    /**
     * 交易记录-红包领取导出锁
     */
    String ADMIN_EXPORT_REDPACKETS_GET_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_redpackets_get_lock_";

    /**
     * 交易记录-闪兑导出锁
     */
    String ADMIN_EXPORT_FAST_GET_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_fast_get_lock_";

    /**
     * 交易记录-扫码付款导出锁
     */
    String ADMIN_EXPORT_PAY_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_pay_lock_";
    /**
     * 交易记录-扫码付款导出锁
     */
    String ADMIN_EXPORT_SALARY_IN_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_salary_in_lock_";

    /**
     * 交易记录-理财宝导出锁
     */
    String ADMIN_EXPORT_YEB_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_yeb_lock_";

    /**
     * 交易记录-Topgate充值导出锁
     */
    String ADMIN_EXPORT_TOPGATE_RECHARGE_LOCK = Constants.IM_REDIS_PREFIX + "admin_export_topgate_recharge_lock_";
    /**
     * agoraUid 数据库最后一个值
     */
    String IM_AGORA_UID_LAST_KEY = Constants.IM_REDIS_PREFIX + "agora_uid_last_key";

    /**
     * userUid 数据库最后一个值
     */
    String IM_USER_UID_LAST_KEY = Constants.IM_REDIS_PREFIX + "user_uid_last_key";

    /**
     * group_memberUid 数据库最后一个值
     */
    String IM_GROUP_MEMBER_GM_ID_LAST_KEYS = Constants.IM_REDIS_PREFIX + "group_member_gm_id_last_key";
    /**
     * agoraUid Redis 计数器
     */
    String IM_AGORA_UID_INCR_KEY = Constants.IM_REDIS_PREFIX + "agora_uid_incr_key";

    /**
     * userUid Redis 计数器
     */
    String IM_USER_UID_INCR_KEY = Constants.IM_REDIS_PREFIX + "user_uid_incr_key";

    /**
     * group_member Redis 计数器
     */
    String IM_GRPUP_MEMBER_ID_INCR_KEY = Constants.IM_REDIS_PREFIX + "group_member_id_incr_key";

    /**
     * 资金池锁
     * @since 2019-12-04
     */
    String BIZ_ASSETS_TRANSFER_POOL_LOCK = "biz_assets_transfer_pool_lock_";
    /**
     * MQ消息缓存
     * IM_mq_msg_send_<消息id>
     */
    String MQ_MSG_SEND_CACHE_KEY_ = Constants.IM_REDIS_PREFIX + "mq_msg_send_";

    /**
     * Topgate充值订单锁
     */
    String TOPGATE_RECHARGE_ORDER_LOCK = Constants.IM_REDIS_PREFIX + "topgate_recharge_order_lock_";
    /**
     * 币支付提现订单锁
     */
    String TOPGATE_WITHDRAW_ORDER_LOCK = Constants.IM_REDIS_PREFIX + "topgate_withdraw_order_lock_";
    /**
     * topgate闪兑订单锁
     */
    String TOPGATE_FAST_EXCHANGE_ORDER_LOCK = Constants.IM_REDIS_PREFIX + "topgate_fast_exchange_order_lock_";

    /**
     * 后台手动处理提现申请操作锁
     *
     * @since 2019-12-30
     */
    String MANUAL_HANDLING_OF_WITHDRAWAL_OPERATION = "manual_handling_of_withdrawal_operation_";

    /**
     * 后台手动处理Mq消息重试处理锁
     *
     * @since 2020-01-03
     */
    String MANUAL_HANDLING_MQ_MSG_SEND_LOCK = "manual_handling_mq_msg_send_lock_";

    /**
     * 返佣定时任务锁
     * @since 2020-02-12
     */
    String REBATE_SCHEDULE_LOCK = Constants.IM_REDIS_PREFIX + "rebate_schedule_lock";
    /**
     * 返佣发送处理锁
     * key : rebate_proccess_lock_[rebateId]
     * @since 2020-02-12
     */
    String REBATE_PROCCESS_LOCK_ = Constants.IM_REDIS_PREFIX + "rebate_proccess_lock_";
}