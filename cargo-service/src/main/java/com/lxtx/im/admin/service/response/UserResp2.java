package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回用户对象参数封装
 *
 * @Author liyunhua
 * @Date 2018-08-16 0016
 */
@Getter
@Setter
public class UserResp2 implements Serializable {

    /**
     * 旧手机号
     */
    private String oldTelephone;
    /**
     * 旧国际简码
     */
    private String oldCountryCode;

    @ExcelField(name = "UID", orderBy = "2")
    private String uid;

    /**
     * 账号,主键
     */
    @ExcelField(name = "账号", orderBy = "1")
    private String account;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 0:女 1：男
     */
    private String gender;
    /**
     * 用户签名
     */
    private String motto;
    /**
     * 名称
     */
    @ExcelField(name = "名称", orderBy = "3")
    private String name;

    /**
     * 客户端mid生产后缀
     */
    private String code;
    /**
     * 手机号的国际简码
     */
    @ExcelField(name = "国际简码", orderBy = "4")
    private String countryCode;
    /**
     * 电话
     */
    @ExcelField(name = "电话号码", orderBy = "5")
    private String telephone;

    /**
     * 账号状态，0:启用，1：禁用，2：冻结
     */
    @ExcelField(name = "状态", orderBy = "10")
    private String state;

    /**
     * 用户头像
     */
    private String userAvatarUrl;
    /**
     * 二维码名片
     */
    private String qrcodeUrl;

    /**
     * 区号+电话号码拼接，如8613812345678
     */
    private String fullTelephone;

    /**
     * 是否接受新消息通知设置
     */
    private Boolean receiveMessage;

    /**
     * 是否有提示音设置
     */
    private Boolean soundMessage;

    /**
     * 是否接受通知消息内容显示设置
     */
    private Boolean receiveMessageDetail;

    /**
     * 保存token放入到user对象里面
     */
    private String userToken;

    /**
     * 网易云信token
     */
    private String yxToken;

    /**
     * 头像，昵称是否完整
     */
    private Boolean isPerfect;

    /**
     * 是否显示完整手机号
     */
    private Boolean showPhone;

    /**
     * 用户自定义提示音返回
     */
//    private UserSettingSoundResp sound;

    /**
     * 个性签名
     */
    private String personalizedSign;

    /**
     * 注册IP
     */
    @ExcelField(name = "注册IP", orderBy = "7")
    private String createIp;

    /**
     * 注册时间
     */
    @ExcelField(name = "注册时间", orderBy = "6")
    private String createTime;

    /**
     * 登录IP
     */
    @ExcelField(name = "登录IP", orderBy = "9")
    private String loginIp;

    /**
     * 登录时间
     */
    @ExcelField(name = "登录时间", orderBy = "8")
    private String loginTime;
    /**
     * agora的用户ID
     */
    private Long agoraUid;

    /**
     * 是否存在/设置过密码
     */
    private Boolean hasPassword;
}
