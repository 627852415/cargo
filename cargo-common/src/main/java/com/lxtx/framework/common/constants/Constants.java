
package com.lxtx.framework.common.constants;

/**
 * @author Cherish
 * @date 2018年8月9日
 */
public interface Constants {

    /**
     * 语言key
     */
     String LOCALE_STR = "locale";
     /**
      * 用于开发环境公共服务
      */
     String ROUTE_STR = "route";
    /**
     * 默认语言
     */
     String DEFAULT_LANGUAGE = "zh_CN";

    /**
     * 中文语言
     */
    String CHINESE_LANGUAGE = "zh_CN";

    /**
     * 泰语
     */
     String THAILAND_LANGUAGE = "th_TH";

    /**
     * 英文语言
     */
    String ENGLISH_LANGUAGE = "en_US";

    /**
     * 中文繁体语言
     */
    String TRADITIONAL_LANGUAGE = "zh_TW";

    /**
     * 柬埔寨语言
     */
    String CAMBODIA_LANGUAGE = "km_KH";


    /**
     * 系统级别异常
     */
    String SYSERROR_PARAM_ERROR_CODE = "100001";
    String SYSERROR_INTERNAL_SERVICE_ERROR_CODE = "100002";
    String SYSERROR_INTERNAL_SERVICE_ERROR_MSG = "系统调用异常";
    String SYSERROR_SYSTEM_ERROR_CODE = "100003";
    String SYSERROR_SYSTEM_ERROR_MSG = "系统异常";
    String SYSERROR_SYSTEM_UNAUTHORIZED_EXCEPTION_CODE = "100004";
    String SYSERROR_SYSTEM_UNAUTHORIZED_EXCEPTION_MSG = "没有访问权限";
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_CODE = "100004";
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_MSG = "未获取到请求参数";
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_TIMESTAMP = "签名参数缺失 timestamp";
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_SIGNATURE_KEY = "签名参数缺失 signKey";
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_TRACE_ID = "签名参数缺失 traceId";
    String SYSERROR_SIGNATURE_PARAM_INVALID_ERROR_SIGNATURE = "签名参数缺失 signature";
    String SYSERROR_SIGNATURE_EXPIRED_ERROR_CODE = "100004";
    String SYSERROR_SIGNATURE_EXPIRED_ERROR_MSG = "签名失效";
    String SYSERROR_SIGNATURE_INCORRECT_ERROR_CODE = "100005";
    String SYSERROR_SIGNATURE_INCORRECT_ERROR_MSG = "签名不正确";
    String SYSERROR_SIGNATURE_VALIDATE_ERROR_CODE = "100006";
    String SYSERROR_SIGNATURE_VALIDATE_ERROR_MSG = "校验签名失败";
    String SYSERROR_SIGNATURE_SIGN_ERROR_CODE = "100007";
    String SYSERROR_SIGNATURE_SIGN_ERROR_MSG = "添加签名失败";
    String SYSERROR_DECRYPT_ERROR_CODE = "100008";
    String SYSERROR_DECRYPT_ERROR_MSG = "请求参数解密失败";
    String SYSERROR_ENCRYPT_ERROR_CODE = "100009";
    String SYSERROR_ENCRYPT_ERROR_MSG = "返回参数加密失败";
    String SYSERROR_HTTPREQUEST_METHOD_CODE = "100010";
    String SYSERROR_ENCRYPT_IS_NULL_CODE = "100011";
    String SYSERROR_ENCRYPTT_IS_NULL_MSG = "未获取到请求密文";
    String SYSERROR_FILE_UPLOAD_ERROR_CODE = "100012";
    String SYSERROR_FILE_UPLOAD_ERROR_MSG = "文件上传异常";
    String SYSERROR_FILE_DELETE_ERROR_CODE = "100013";
    String SYSERROR_FILE_DELETE_ERROR_MSG = "删除文件异常";
    String SYSERROR_GROUP_VERIFY_TYPE_CHANGE_CODE = "100014";
    String SYSERROR_GROUP_VERIFY_TYPE_CHANGE_MSG = "进群验证方式已更改，请稍后重试";
    String SYSERROR_PHONE_ERROR_CODE = "100015";
    String SYSERROR_PHONE_ERROR_CODE_MSG = "手机号已经注册";
    String SYSERROR_PHONE_BIND_ERROR_CODE = "100016";
    String SYSERROR_PHONE_BIND_ERROR_CODE_MSG = "手机号已经绑定";
    /**
     * sdk 模块 请求参数类型异常
     */
    String SDK_PARAM_CODE = "2001";
    String SDK_PARAM_MSG = "参数异常 ";


    /**
     * sdk 模块 系统类型异常
     */
    String SDK_SYSTEM_ERROR_CODE = "3001";
    String SDK_SYSTEM_ERROR_MSG = "系统异常";

    /**
     * sdk 模块 业务类型异常
     */

    String SDK_APP_NAME_EXISTED_CODE = "4001";
    String SDK_APP_NAME_EXISTED_MSG = "应用名称已存在";

    String SDK_GENERATE_AES_KEY_CODE = "4002";
    String SDK_GENERATE_AES_KEY_MSG = "生成AES密钥异常";

    String SDK_NOT_FIND_MERCHANT_CODE = "4003";
    String SDK_NOT_FIND_MERCHANT_MSG = "商户不存在";

    String SDK_APP_NOT_EXISTE_CODE = "4004";
    String SDK_APP_NOT_EXISTE_MSG = "应用不存在";

    String SDK_MERCHANT_IS_EXISTE_CODE = "4005";
    String SDK_MERCHANT_IS_EXISTE_MSG = "商户已经入驻";

    String SDK_MERCHANT_NOT_COIN_CODE = "4006";
    String SDK_MERCHANT_NOT_COIN_MSG = "商户没有该币种";

    String SDK_USER_NOT_COIN_CODE = "4007";
    String SDK_USER_NOT_COIN_MSG = "用户没有该币种";

    String SDK_USER_NOT_EXISTE_CODE = "4008";
    String SDK_USER_NOT_EXISTE_MSG = "用户不存在";

    String SDK_ORDER_NOT_EXISTE_CODE = "4009";
    String SDK_ORDER_NOT_EXISTE_MSG = "交易订单不存在";

    String SDK_ORDER_PAID_CODE = "4010";
    String SDK_ORDER_PAID_MSG = "交易订单已支付";

    String SDK_ORDER_CLOSE_CODE = "4011";
    String SDK_ORDER_CLOSE_MSG = "交易订单已关闭";

    String SDK_ORDER_IS_EXISTE_CODE = "4012";
    String SDK_ORDER_IS_EXISTE_MSG = "交易订单已存在";

    String SDK_APP_ID_IS_NULL_CODE = "4013";
    String SDK_APP_ID_IS_NULL_MSG = "appId 不能为空";

    String SDK_APP_IS_EXISTE_CODE = "4014";
    String SDK_APP_IS_EXISTE_MSG = "应用已存在";

    String SDK_INSUFFICIENT_BALANCE_CODE = "4015";
    String SDK_INSUFFICIENT_BALANCE_MSG = "余额不足";

    String SDK_TOKEN_ERROR_CODE = "4016";
    String SDK_TOKEN_ERROR_MSG = "token不正确";

    String SDK_SCOPE_ERROR_CODE = "4017";
    String SDK_SCOPE_ERROR_MSG = "scope不正确";

    String SDK_OPEN_ID_ERROR_CODE = "4018";
    String SDK_OPEN_ID_ERROR_MSG = "openId不正确";

    String SDK_GRANT_TYPE_ERROR_CODE = "4019";
    String SDK_GRANT_TYPE_ERROR_MSG = "grantType类型不正确";

    String SDK_CODE_ERROR_CODE = "4020";
    String SDK_CODE_ERROR_MSG = "code不正确";

    String SDK_REFRESH_TOKEN_ERROR_CODE = "4021";
    String SDK_REFRESH_TOKEN_ERROR_MSG = "refreshToken不正确";

    /**
     * sdk 模块 请求参数类型异常
     */
    String SDK_ENCRYPT_IS_NULL_CODE = "2001";
    String SDK_ENCRYPTT_IS_NULL_MSG = "未获取到请求密文";

    String SDK_DECRYPT_ERROR_CODE = "2002";
    String SDK_DECRYPT_ERROR_MSG = "请求参数解密失败";

    String SDK_ENCRYPT_ERROR_CODE = "2003";
    String SDK_ENCRYPT_ERROR_MSG = "响应参数加密失败";


    /**
     * 签名
     */
     String SIGNATURE_SIGNATURE_KEY = "signKey"; //签名key，匹配相应的密钥
     String SIGNATURE_SIGNATURE_VALUE = "signature"; //签名
     String SIGNATURE_TIMESTAMP_KEY = "timestamp"; //时间戳
     String SIGNATURE_TRACE_ID_KEY = "traceId";
     Integer SIGNATURE_EXPIRE_TIME = 120; //签名有效时间,单位秒
     String API_REQUEST_BODY = "requestBody"; //api  请求参数对象

    /**
     * SDK签名
     */
    //签名key，匹配相应的密钥
     String SDK_SIGN__KEY = "appKey";
    //签名
     String SDK_SIGN__VALUE = "sign";


    /**
     * SDK order 签名
     */
    //签名key，匹配相应的密钥
    String SDK_ORDER_SIGN__KEY = "appKey";
    //签名
    String SDK_ORDER_SIGN__VALUE = "orderSign";
    //
//    String SDK_ORDER_SIGN__TIMESTAMP_KEY = "thirdPartyTimestamp";

    /**
     * 系统参数
     */
     String SYS = "sys";


    /**
     * 管理平台 超级管理员账号
     */
    String SYSTEM = "system";
    /**
     * 默认密码
     */
    String DEF_PASSWORD = "000000";


    long MAX_PUB_ROOT_MENU = 3;

    long MAX_PUB_SUB_MENU = 5;

    String LOCAL_BUCKET = "bucket";


    /**
     * 默认分页-长度
     */
    int PAGE_SIZE = 10;
    /**
     * 默认分页-页码
     */
    int PAGE_CURRENT = 1;

    /**
     * 消息常量类
     *
     * @author liboyan
     * @date 2018/06/15
     */
    interface MessageAction {

        /**
         * 用户之间的普通消息
         */
        String ACTION_0 = "0";

        /**
         * 群里用户发送的 消息
         */
        String ACTION_1 = "1";

        /**
         * 系统向用户发送的普通消息
         */
        String ACTION_2 = "2";

        /**
         * 系统向用户发送的群聊消息
         */
        String ACTION_3 = "3";

        /**
         * 系统向用户发送的更新通讯录（好友接受）
         */
        String ACTION_4 = "4";

        /**
         * 系统向用户发送新的好友通知（红点）
         */
        String ACTION_5 = "5";

        /**
         * 用户之间的私密聊天
         */
        String ACTION_6 = "6";


        /**
         * 发起私密聊天
         */
        String ACTION_7 = "7";

        /**
         * 退出私密聊天
         */
        String ACTION_EXIT_SECRET = "9";
        /**
         * 功能描述: 消息撤回
         */
        String ACTION_10 = "10";

        /**
         * 功能描述: 删除群消息
         */
        String ACTION_11 = "11";
        /**
         *  单聊撤回所有消息
         * */
        String ACTION_12 = "12";

        /**
         * 群聊撤回所有消息
         * */
        String ACTION_13 = "13";

        /**
         * 换汇投诉消息
         * */
        String ACTION_14 = "14";

        /**
         * 群管理-自动添加好友通知
         * */
        String ACTION_19 = "19";
        /**
         * 群管理-审核通知推送
         * */
        String ACTION_18 = "18";
        /**
         * ********************************************1开头统一为聊天消息**********************************************************
         */

        /**
         * 新增管理员
         * */
        String  ACTION_100 ="100";
        /**
         * 移除管理员
         * */
        String  ACTION_101 ="101";

        /**
         * 系统定制消息---进群请求
         */
        String ACTION_102 = "102";

        /**
         * 系统定制消息---同意进群请求
         */
        String ACTION_103 = "103";

        /**
         * 系统定制消息---群解散消息
         */
        String ACTION_104 = "104";

        /**
         * 系统定制消息---邀请入群请求
         */
        String ACTION_105 = "105";

        /**
         * 系统定制消息---同意邀请入群请求
         */
        String ACTION_106 = "106";

        /**
         * 系统定制消息---被踢除群
         */
        String ACTION_107 = "107";

        /**
         * 系统定制消息---消息被阅读
         */
        String ACTION_108 = "108";

        /**
         * 系统定制消息---被好友删除
         */
        String ACTION_109 = "109";

        /**
         * 系统定制消息---好友替换了头像
         */
        String ACTION_110 = "110";

        /**
         * 系统定制消息---好友修改了名称或者签名
         */
        String ACTION_111 = "111";

        /**
         * 系统定制消息---用户退出了群
         */
        String ACTION_112 = "112";

        /**
         * 系统定制消息---用户加入了群
         */
        String ACTION_113 = "113";

        /**
         * 系统定制消息---群信息被修改
         */
        String ACTION_114 = "114";

        /**
         * 系统定制消息---创建群聊
         */
        String ACTION_115 = "115";

        /**
         * 系统定制消息---群主权限转让
         */
        String ACTION_116 = "116";

        /**
         * 系统定制消息--- 是否开启群成员私聊设置
         */
        String ACTION_117 = "117";
        /**
         * 系统定制消息---发起通话请求
         */
        String ACTION_118 = "118";

        /**
         * 系统定制消息---同意通话请求
         */
        String ACTION_119 = "119";
        /**
         * 系统定制消息---通话中
         */
        String ACTION_120 = "120";
        /**
         * 系统定制消息---对方拒绝
         */
        String ACTION_121 = "121";

        /**
         * 系统定制消息---切换语音或视频
         */
        String ACTION_122 = "122";

        /**
         * 系统定制消息---询问客户端是否断线
         */
        String ACTION_123 = "123";

        /**
         * 系统定制消息---对方断开连接
         */
        String ACTION_124 = "124";

        /**
         * 系统定制消息---推流完成可以播放
         */
        String ACTION_125 = "125";

        /**
         * 系统定制消息---阅后即焚时间设置通知
         */
        String ACTION_126 = "126";

        /**
         * 系统定制消息---用户通过邀请链接加入了群组
         */
        String ACTION_127 = "127";

        /**
         * 领取了游戏红包
         */
        String ACTION_128 = "128";

        /**
         * 系统定制消息---群组禁言
         */
        String ACTION_131 = "131";

        /**
         * 系统定制消息---修改群公告
         */
        String ACTION_132 = "132";

        /**
         * 系统定制消息---修改群成员信息
         */
        String ACTION_133 = "133";
        /**
         * 红包v2 - 红包已领完消息
         */
        String ACTION_134 = "134";
        /**
         * 红包v2 - 红包已过期消息
         */
        String ACTION_135 = "135";
        /**
         * ********************************************2开头统一为公众号消息**********************************************************
         */
        /**
         * 系统定制消息---用户向公众号发消息
         */
        String ACTION_200 = "200";

        /**
         * 系统定制消息---公众号向用户回复的消息
         */
        String ACTION_201 = "201";

        /**
         * 系统定制消息---公众号向用户群发消息
         */
        String ACTION_202 = "202";

        /**
         * 系统定制消息---公众号信息更新
         */
        String ACTION_203 = "203";

        /**
         * 系统定制消息---公众号菜单信息更新
         */
        String ACTION_204 = "204";

        /**
         * 系统定制消息---公众号LOGO更新
         */
        String ACTION_205 = "205";

        /**
         ********************************************* 3开头统一为钱包相关消息**********************************************************
         */
        /**
         * 系统定制消息---钱包转账
         */
        String ACTION_300 = "300";
        /**
         * 系统定制消息---钱包转账-进群费用
         */
        String ACTION_301 = "301";
        /**
         * 系统定制消息---钱包转账-换汇secret 支付到账通知
         */
        String ACTION_302 = "302";

        /**
         * 系统定制消息----换汇secret 支付扣款通知
         */
        String ACTION_303 = "303";

        /**
         * 系统服务消息---提款到账通知
         */
        String ACTION_310 = "310";

        /**
         * 系统服务消息---提款驳回通知
         */
        String ACTION_311 = "311";

        /**
         * 系统服务消息---OTC充值到账通知
         */
        String ACTION_312 = "312";
        /**
         * 系统服务消息---OTC提款驳回通知
         */
        String ACTION_313 = "313";
        /**
         * 系统服务消息---OTC提款到账通知
         */
        String ACTION_314 = "314";

        /**
         * 系统服务消息---闪兑到账通知
         */
        String ACTION_318 = "318";

        /**
         * 系统服务消息---交易已提交通知/冻结
         */
        String ACTION_319 = "319";

        /**
         * 系统服务消息---资金提现通知（new)）
         */
        String ACTION_320 = "320";

        /**
         ********************************************* 35开头统一为代发工资消息**********************************************************
         */
        /**
         * 系统服务消息---好友转账资金服务通知 转账人
         */
        String ACTION_3501 = "3501";
        /**
         * 系统服务消息---好友转账资金服务通知 接收人
         */
        String ACTION_3502 = "3502";
        /**
         * 系统服务消息---充值资金服务通知
         */
        String ACTION_3601 = "3601";
        /**
         * 系统服务消息---提现资金服务通知
         */
        String ACTION_3602 = "3602";

        /**
         * 系统服务消息---提现资金处理中通知
         */
        String ACTION_3603 = "3603";
        
        /**
         * 充值发起通知
         */
        String ACTION_3604 = "3604";

        /**
         * 系统服务通知--红包退款
         */
        String ACTION_430 = "430";

        /**
         * 发起游戏红包action
         */
        String ACTION_431 = "431";

        /**
         * 游戏结束金额变动通知（游戏退款）
         */
        String ACTION_432 = "432";

        /**
         * 游戏获胜后的结果（游戏结果）
         */
        String ACTION_433 = "433";

        /**
         * 游戏结束通知给群所有人（游戏结束通知）
         */
        String ACTION_434 = "434";

        /**
         * 游戏取消排队和给其他人的通知
         */
        String ACTION_435 = "435";
        /**
         * 游戏返佣通知
         * @since 2019-01-11
         */
        String ACTION_436 = "436";

        /**
         * 游戏通知关闭游戏窗口（游戏结束）
         */
        String ACTION_437 = "437";

        /**
         * 扫码付款/收款
         */
        String ACTION_438 = "438";

        /**
         * 银行转账服务通知
         */
        String ACTION_439 = "439";

        /**
         * 保证金缴纳
         * @since 2019-04-28
         */
        String ACTION_440 = "440";
        /**
         * 保证金返还
         * @since 2019-04-28
         */
        String ACTION_441 = "441";
        /**
         * 商品发布资金冻结
         * @since 2019-04-28
         */
        String ACTION_442 = "442";
        /**
         * 商品发布资金解冻
         * @since 2019-04-28
         */
        String ACTION_443 = "443";
        /**
         * 卖家下单通知
         * @since 2019-04-29
         */
        String ACTION_445 = "445";
        /**
         * 商品下架通知
         * @since 2019-05-17
         */
        String ACTION_446 = "446";
        /**
         * 买家兑换资金到账通知
         */
        String ACTION_447 = "447";
        /**
         * 卖家订单交易返利到账通知
         */
        String ACTION_448 = "448";
        /**
         * 余额宝转入交易
         */
        String ACTION_500 = "500";
        /**
         * 余额宝转出交易
         */
        String ACTION_501 = "501";
        /**
         * 余额宝：当前用户是否显示余额宝入口
         */
        String ACTION_502 = "502";
        /**
         * 撤销投诉
         */
        String ACTION_549 = "549";
        /**
         * 投诉处理结果
         */
        String ACTION_550 ="550";
        /**
         * 客服收到投诉
         */
        String ACTION_551 ="551";
        /**
         * 线下换汇买家确认通知
         */
        String ACTION_552 ="552";
        /**
         * 线下换汇成交通知
         */
        String ACTION_553 ="553";
        /**
         * 线下换汇取消通知
         */
        String ACTION_554 ="554";
        /**
         * 线下换汇误点付款通知
         */
        String ACTION_555 ="555";
        /**
         * 线下换汇 - 修改支付方式通知 （商家收）
         */
        String ACTION_556 ="556";
        /**
         * 线下换汇 - 修改支付方式通知 （买家收）
         */
//        String ACTION_557 ="557";
        
        /**
         * 线下换汇 - 商家修改付款方式详细 （买家收）
         */
        String ACTION_558 ="558";

        /**
         * 换汇 - 下单问候语
         */
        String ACTION_559 ="559";
        
        /**
         * 换汇 - 商家—我已付款
         */
        String ACTION_570 ="570";
        
        /**
         * 换汇 - 买家误点付款通知-买家-商家两条
         */
        String ACTION_571 ="571";
        
        /**
         * 换汇 - 买家确认完成通知-买家-商家两条
         */
        String ACTION_572 ="572";
        
        /**
         * 换汇 - 买家交易资金冻结
         */
        String ACTION_576 ="576";
        
        /**
         * 换汇 - 买家交易资金解冻
         */
        String ACTION_577 ="577";
        
        /**
         * 换汇 - 买家换汇交易资金扣除
         */
        String ACTION_578 ="578";
        
        /**
         * 换汇 - 商家换汇交易资金到账
         */
        String ACTION_579 ="579";

        /**
         * 线下换汇 - 买家修改付款方式详细 （商家收）
         */
        String ACTION_580 ="580";
        
        /**
         * 线下换汇 - 下级交易返利通知
         */
        String ACTION_581 ="581";

        /**
         ********************************************* 56开头统一为代发工资消息**********************************************************
         */
        /**
         * 代发工资 服务通知
         */
        String ACTION_561 ="561";


        /**
         ********************************************* 4开头统一为系统控制消息**********************************************************
         */
        /**
         * 系统定制消息---强制下线消息
         */
        String ACTION_444 = "444";
        /**
         * ********************************************6开头统一为SDK消息**********************************************************
         */
        String ACTION_601 = "601";

        /**
         * ********************************************7开头统一为漂流瓶消息**********************************************************
         */
        /**
         * 系统定制消息---漂流瓶消息
         */
        String ACTION_700 = "700";

        /**
         * 系统定制消息---删除漂流瓶
         */
        String ACTION_701 = "701";

        /**
         * ********************************************8开头统一为圈子动态消息**********************************************************
         */
        /**
         * 系统定制消息---好友新动态消息
         */
        String ACTION_800 = "800";

        /**
         * 系统定制消息---好友新动态评论消息
         */
        String ACTION_801 = "801";

        /**
         * 系统定制消息---好友新动态评论回复评论消息
         */
        String ACTION_802 = "802";

        /**
         * 系统定制消息---删除动态
         */
        String ACTION_803 = "803";

        /**
         * 系统定制消息---删除评论或取消点赞
         */
        String ACTION_804 = "804";

        /**
         * ********************************************9开头统一为动作消息**********************************************************
         */
        /**
         * 系统定制消息---好友下线消息
         */
        String ACTION_900 = "900";

        /**
         * 系统定制消息---好友上线消息
         */
        String ACTION_901 = "901";

        /**
         * 系统定制消息---更新用户数据
         */
        String ACTION_998 = "998";

        /**
         * 系统定制消息---在另一台设备登录强制下线消息
         */
        String ACTION_999 = "999";

        /**
         * ********************************************10开头统一为广播消息**********************************************************
         */

        /**
         * 系统定制消息--- 广播开关通知
         */
        String ACTION_1000 = "1000";

        /**
         * 系统定制消息--- 广播消息
         */
        String ACTION_1001 = "1001";

        /**
         * agora 用户超时未接听
         */
        String ACTION_1100 ="1100";

        /**
         * agora 用户退出频道
         */
        String ACTION_1101 ="1101";

        /**
         * agora 关闭频道
         */
        String ACTION_1102 ="1102";
        

        /**
         * agora 一对一通话用户超时未接听
         */
        String ACTION_1103 ="1103";
    }

    /**
     * 消息类型常量类
     *
     * @author liboyan
     * @date 2018/06/15
     */
    interface MessageFormat {

        /**
         * 文字
         */
        String FORMAT_TEXT = "0";

        /**
         * 图片
         */
        String FORMAT_IMAGE = "1";

        /**
         * 语音
         */
        String FORMAT_VOICE = "2";

        /**
         * 文件
         */
        String FORMAT_FILE = "3";

        /**
         * 地图
         */
        String FORMAT_MAP = "4";

        /**
         * 视频
         */
        String FORMAT_VIDEO = "8";

        /**
         * 回复文字 9
         */
        String REPLY_TEXT = "9";

        /**
         * @ (指定) 10
         */
        String SPECIFY = "10";

        /**
         * 回复图片 11
         */
        String REPLY_IMG = "11";

        /**
         * 回复视频 12
         */
        String Reply_V = "12";
        /**
         * 实时语音
         */
        String FORMAT_LIVE_VOICE = "13";
        /**
         * 实时视频
         */
        String FORMAT_LIVE_VIDEO = "14";
        /**
         * 好友转账
         */
        String FORMAT_TRANSFER = "15";

        /**
         * 收到一个红包
         */
        String FORMAT_GET_A_RED_PACKET = "16";

        /**
         * 别人领取了红包推送
         */
        String FORMAT_OTHER_RECEIVE_RED_PACKET = "17";

        /**
         * 红包退还
         */
        String FORMAT_REJECT_RED_PACKET = "18";

        /**
         * 领取了牛牛红包
         */
        String FORMAT_RECEIVE_NIU_NIU_RED_PACKET = "20";

        /**
         * 发红包action
         */
        String FORMAT_SEND_NIU_NIU_RED_PACKET = "21";

        /**
         * 牛牛游戏结束时收到的金额变动通知
         */
        String FORMAT_NIU_NIU_GAME_BLACK_MONEY = "22";

        /**
         * 游戏获胜后的结果
         */
        String FORMAT_NIU_NIU_GAME_WIN_RESULT = "23";

        // 前端自定义 -- 名片消息 -- 24

        /**
         * 游戏取消排队和给其他人的通知
         */
        String FORMAT_GAME_CANCEL_LINE ="25";
        /**
         * 领取了21点游戏红包
         */
        String FORMAT_RECEIVE_BLACKJACK_RED_PACKET = "26";

        /**
         * 21点游戏结束通知
         */
        String FORMAT_END_GAME_BLACKJACK_RED_PACKET = "27";

        /**
         * 21点游戏退款通知
         */

        String FORMAT_REFUND_BLACKJACK_RED_PACKET = "28";

        /**
         * 21点获胜通知
         */
        String FORMAT_WIN_RESULT_BLACKJACK_RED_PACKET = "29";

        /**
         * 系统服务消息---提款到账通知
         */
        String FORMAT_PLATFORM_APPLY_SUCCESS = "30";

        /**
         * 系统服务消息---提款驳回通知
         */
        String FORMAT_PLATFORM_APPLY_FAILURE = "31";

       // 前端自定义 -- 表情消息 -- 32

        /**
         * 开始游戏通知
         * @since 2018-12-25
         */
        String FORMAT_GAME_START = "33";

        /**
         * 系统服务消息---OTC提款驳回通知
         */
        String FORMAT_OTC_WITHDRAW_FAILURE = "34";

        /**
         * 系统服务消息---OTC提款到账通知
         */
        String FORMAT_OTC_WITHDRAW_SUCCESS = "35";

        /**
         * 系统服务消息---游戏分佣通知
         * @since 2019-01-10
         */
        String FORMAT_GAME_REBATE = "36";

        /**
         * 系统服务消息---OTC充值到账通知
         */
        String FORMAT_OTC_RECHARGE_SUCCESS = "37";

        /**
         * 系统服务消息---闪兑到账通知
         */
        String FORMAT_FEX_SUCCESS = "38";

        /**
         * 系统服务消息---交易已提交通知
         */
        String FORMAT_FEX_COMMIT = "39";

        /**
         * 系统服务消息---闪兑资金解冻通知
         */
        String FORMAT_FEX_FAILURE = "40";


        /**
         * 领取了扫雷游戏红包
         */
        String FORMAT_RECEIVE_MINESWEEPER_RED_PACKET = "41";

        /**
         * 扫雷游戏退款通知
         */
        String FORMAT_MINESWEEPER_GAME_REFUND = "43";
        /**
         * 扫雷游戏结果通知
         */
        String FORMAT_MINESWEEPER_GAME_RESULT = "44";
        /**
         * 扫雷游戏结束通知
         */
        String FORMAT_MINESWEEPER_GAME_END = "45";
        /**
         * 扫码付款-付款成功通知
         */
        String FORMAT_PAY_SUCCESS_NOTICE = "46";
        /**
         * 扫码付款-收款结算通知
         */
        String FORMAT_RECEIPT_SUCCESS_NOTICE = "47";
        /**
         * 扫码付款-收款通知（付款成功时发送）
         */
        String FORMAT_RECEIPT_PAYSUCCESS_NOTICE = "48";
        /**
         * SDK 订单收款-收款通知（收款成功时发送）
         */
        String FORMAT_SDK_RECEIPT_SUCCESS_NOTICE = "61";
        /**
         * SDK 订单付款-付款通知（付款成功时发送）
         */
        String FORMAT_SDK_PAY_SUCCESS_NOTICE = "62";

        /**
         * 余额宝转入交易通知
         */
        String FORMAT_YEB_RECHARGE_NOTICE = "50";
        /**
         * 余额宝转出交易通知
         */
        String FORMAT_YEB_WITHDRAW_NOTICE = "51";

        /**
         * BCB银行充值服务通知
         */
        String FORMAT_BCB_RECHARGE_NOTICE = "55";
        /**
         * BCB银行提现服务通知
         */
        String FORMAT_BCB_WITHDRAW_NOTICE = "56";

        /**
         * BCB银行工本费服务通知
         */
        String FORMAT_BCB_APPLY_WITHHOLDED_NOTICE = "57";


        /**
         * 代发工资员工绑定企业服务通知
         */
        String FORMAT_SALARY_APPLY_NOTICE = "90";

        /**
         * 代发工资资金扣减服务通知
         */
        String FORMAT_SALARY_PAY_AMOUNT_NOTICE = "91";
        /**
         * 代发工资资资金转入服务通知
         */
        String FORMAT_SALARY_INCOME_NOTICE = "92";

        /**
         * 代发工资资-工资条服务通知
         */
        String FORMAT_SALARY_ITEM_NOTICE = "93";

        /**
         * 线下换汇服务通知—保证金缴纳及返回、发布兑换冻结、解冻资金-secret 支付通知-secret扣款通知
         */
        String FORMAT_OFFSITE_EXCHANGE_NOTICE = "80";
        /**
         * 群-自动添加好友
         */
        String FORMAT_FRIEND_AUTO_ADD_MANAGER= "82";
        /**
         * 群-自动添加好友
         */
        String FORMAT_FRIEND_AUTO_ADD= "83";
        /**
         * 时间110
         */
        String TIME = "110";

        /**
         * 系统 112
         */
        String SYSTEM = "112";
        /**
         * 系统 112
         */
        String SYSTEM_SERVICE_NOTIFICATION = "350";
        
        /**
         * 充值发起通知
         */
        String SYSTEM_SERVICE_RECHARGE_NOTIFICATION = "354";


        /**
         * 客服收到投诉修改
         */
        String FORMAT_OFFSITE_COMPLAINT_UPDATE ="1001";

        /**
         * 群公告通知
         */
        String FORMAT_GROUP_NOTICE = "85";
        /**
         * 红包已领完/过期通知
         * @SINCE 2019-12-24
         */
        String FORMAT_RED_PACKET_FINISH = "86";

    }

    /**
     * 定义登录的redis的key
     */
    String SMS_LOGIN_TOKEN = "SMS_LOGIN_TOKEN_";
    String SMS_UPDATE_TOKEN = "SMS_UPDATE_TOKEN_";
    String SMS_UPDATE_TOKEN_PHONE = "SMS_UPDATE_TOKEN_PHONE_";
    String SMS_UPDATE_TOKEN_BCB_CARD_APPLY= "SMS_UPDATE_TOKEN_BCB_CARD_APPLY_";
    /**
     * 将数量保存在redis里面的key
     */
    String SMS_LOGIN_TOKEN_NUM = "SMS_LOGIN_TOKEN_NUM_";
    String SMS_UPDATE_TOKEN_NUM = "SMS_UPDATE_TOKEN_NUM_";
    String SMS_UPDATE_TOKEN_PHONE_NUM = "SMS_UPDATE_TOKEN_PHONE_NUM_";
    String SMS_UPDATE_TOKEN_BCB_CARD_APPLY_NUM = "SMS_UPDATE_TOKEN_BCB_CARD_APPLY_NUM_";
    String SMS_UPDATE_TOKEN_TIME = "SMS_UPDATE_TOKEN_TIME_";
    String SMS_UPDATE_TOKEN_PHONE_TIME = "SMS_UPDATE_TOKEN_PHONE_TIME_";
    String SMS_UPDATE_TOKEN_BCB_CARD_APPLY_TIME = "SMS_UPDATE_TOKEN_BCB_CARD_APPLY_TIME_";
    String SMS_LOGIN_TOKEN_TIME = "SMS_LOGIN_TOKEN_TIME_";

    String SMS_INVITE_REGISTER_TOKEN_NUM = "SMS_INVITE_REGISTER_TOKEN_NUM";
    String SMS_INVITE_REGISTER_TOKEN_TIME = "SMS_INVITE_REGISTER_TOKEN_TIME";
    String SMS_INVITE_REGISTER_TOKEN_PHONE = "SMS_INVITE_REGISTER_TOKEN_PHONE";

    /**bcb申请银行卡的短信验证码*/
    String SMS_CODE_BCB_CARD_APPLY = "SMS_CODE_BCB_CARD_APPLY_";
    /**
     * bcb申请银行卡的重复短信验证码
     */
    String SMS_REPEATE_SEND_CODE_BCB_CARD_APPLY = "SMS_REPEATE_SEND_CODE_BCB_CARD_APPLY_";

    /**
     * 存储im的redis前缀
     */
    String IM_REDIS_PREFIX = "IM_";

    /**
     * 禁止连续请求后台
     */
    String FORBID_FREQUENT_CLICK = "forbid_frequent_click_";

    String CHINA_PHONE_CODE = "CN";

    String SMS_REGISTER_TOKEN = "register_";
    String SMS_REGISTER_TOKEN_NUM = "register_num_";
    String SMS_REGISTER_TOKEN_TIME = "register_time_";

    /**
     * 存放redis的已校验短信验证手机
     */
    String SMS_VERIFIED_CODE_ = "verified_code_";

    /**
     * 昵称最长
     */
    int NICKNAME_MAX_LENGTH = 30;

    /**
     * 允许的图片类型
     */
    String ALLOW_AVATAR_TYPE = "jpg|png|jpeg";

    /**
     * 允许的头像最大size
     */
    long ALLOW_AVATAR_MAX_SIZE = 1024 * 10 * 1024;
    /**
     * 类型  1表示登录，2表示注册，3表示修改密码  ，4修改电话号码，5表示忘记支付密码重置，6表示bcb银行卡官网登录密码，7表示换手机号码前校验旧号码，8表示第一次设置支付密码短信校验
     */
    String IS_TYPE_LOGIN = "1";
    String IS_TYPE_REGISTER = "2";
    String IS_TYPE_UPDATE = "3";
    String IS_TYPE_UPDATE_PHONE = "4";
    String IS_TYPE_PAYPASSWORD = "5";
    String IS_TYPE_BCB_CARD_LOGIN_PASSWORD = "6";
    String IS_TYPE_UPDATE_PHONE_CHECK_USED_PHONE = "7";
    String IS_TYPE_FIRST_SET_PAY_PASSWORD = "8";
    String IS_TYPE_INVITE_REGISTER = "9";
    String PHONE_STATE_SG = "0";
    String PHONE_STATE_CN = "100";
    String ACCESS_TOKEN = "access-token";
    /**
     * token 有效期为一天
     */
    int ACCESS_TOKEN_TIMEOUT = 1;

    /**
     * Redis 好友列表缓存key
     */
    String FRIEND_LIST = "FRIEND_LIST_";
    /**
     * Redis 好友列表缓存key有效期
     */
    String FRIEND_REFUSE_MESSAGE = "对方拒收你的消息";

    /**
     * 全球手机区号表的版本号管理
     */
    String GLOBAL_CODE_VERSION = "global_code_version";
    int FRIEND_LIST_TIMEOUT = 60 * 2;
    /**
     * A给B发送消息，存在缓存中的key的连接值
     */
    String FRIEND_SEND_MESSAGE = "_send_";

    /**
     * Redis A发送消息给B缓存key有效期:7天
     */
    int FRIEND_SEND_MESSAGE_TIMEOUT = 60 * 60 * 24 * 7;

    String CODE_STR = "code";
    String CODE_STATE = "stat";
    String CODE_ERROR = "error";
    String CODE_MSG = "msg";
    /**
     * 聊天：A给B发消息时,发现被B删除
     */
    String NOT_FRIEND_SEND_MESSAGE = "errors.not.friend.push.message";
    /**
     * 聊天：A给B发消息时,发现被B拉黑
     */
    String BLOCKED_SEND_MESSAGE = "errors.blocked.push.message";
    /**
     * 好友账号无效
     */
    String FRIEND_ACCOUNT_INVALID = "errors.friend.account.invalid";

    /**
     * 缩略图最多上传个数
     */
    int THUMBNAIL_MAX_SIZE = 9;
    /**
     * 提示：好友不存在
     */
    String FRIEND_NOT_FIND = "errors.friend.notFound";
    /**
     * 账号长度
     */
    int ACCOUNT_LENGTH = 32;


    /**
     * 群聊最大限制人数500
     */
    int MAXIMUM = 500;

    String PROFILE_LOCAL = "local";

    String PROFILE_DEV = "dev";

    String PROFILE_TEST = "test";

    String PROFILE_PRO = "prod";

    /**
     * 测试用手机验证码
     */
    String TEST_PHONE_NUM = "8888";

    String REDIS_USER_TOKEN_PREFIX = "user_token_";

    /**
     * 解散群的人数条件，必须为2人
     */
    Integer DISBAND_GROUP_NUM = 2;

    Integer CREATE_GROUP_MIN_NUM = 3;


    Integer BIT_SIZE = 1024;

    /**
     * @since 2019-04-10
     */
    String VERSION_NAME = "versionName";
    /**
     * 平台账号
     * @since 2019-04-24
     */
    String PLATFORM_USER_ID = "platformUserId";
    /**
     * 平台类型
     * @since 2019-04-24
     */
    String PLATFORM_TYPE = "platformType";
    /**
     * http
     */
    String HTTP_OPTIONS = "OPTIONS";

    /***
     * 全局配置model
     */
    String DICT_DOMAIN_GLOBAL = "global";

    /***
     * 全局配置-客服帐号配置key
     */
    String DICT_KEY_HELPDESK_ACCOUNT_LIST = "helpdesk_account_list";
    /**
     * 余额宝七日年化率币种ID
     */
    String YEB_SEVEN_RATE_COIN_ID = "yeb_seven_rate_coin_id";

    interface FileViewUrlType {
        String LOCAL_FILE = "file";
        String LOCAL_FILE_NEW = "new";
        String AMAZONS3 = "amazons3";
    }
}
