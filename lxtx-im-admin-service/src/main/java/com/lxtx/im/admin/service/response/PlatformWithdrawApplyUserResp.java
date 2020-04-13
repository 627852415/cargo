package com.lxtx.im.admin.service.response;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @description:  系统提现申请Im用户信息返回
* @author:   CXM
* @create:   2018-12-19 16:23
*/
@Getter
@Setter
public class PlatformWithdrawApplyUserResp {
    /**
     *  账号,主键
     */
    @TableId
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
    private String name;
    private String code;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号的国际简码
     */
    private String countryCode;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 级别
     */
    private Integer grade;
    /**
     * 特性标志位字段
     */
    private String feature;
    /**
     * 账号状态，0:启用，1：禁用，2：冻结
     */
    private String state;
    /**
     * 当前维度
     */
    private Double latitude;
    /**
     * 当前位置信息
     */
    private String location;
    /**
     * 当前经度
     */
    private Double longitude;
    /**
     * 在线状态0 离线 1在线
     */
    private String online;
    /**
     * 组织编号
     */
    private String orgCode;
    /**
     * 用户头像
     */
    private String userAvatarUrl;
    /**
     * 二维码名片
     */
    private String qrcodeUrl;

    /**
     * 是否显示完整手机号，0：否，1：是
     */
    private Boolean showPhone;

    /**
     * 区号+电话号码拼接，如8613812345678
     */
    private String fullTelephone;

    /**
     * 备注
     * */
    private String remarks;
    /**
     * 创建时间
     * */
    private Date createTime;
    /**
     * 创建人
     * */
    private String createBy;
    /**
     * 更新时间
     *
     * */
    private Date updateTime;
    /**
     * 更新人
     * */
    private  String updateBy;
    /**
     * 删除标记 0：未删除，1：已删除
     * 不要私有化
     * */
    private  Boolean delFlag;

}
