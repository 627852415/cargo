package com.lxtx.im.admin.service.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: LaoWeiJie
 * @create: 2019-11-22
 **/
@Getter
@Setter
public class UserAuthenticationPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    @JsonIgnore
    private List<String> platformUserIdList;
    private List<UserAuthenticationPageDto> records;

    @Getter
    @Setter
    public static class UserAuthenticationPageDto{

        /**
         * Id
         */
        private String id;

        /**
         * 钱包用户Id
         */
        private String userId;

        /**
         * 状态【0：正常， 1：禁用】
         */
        private Integer status;

        /**
         * 认证状态【1:未认证、2:待审核、3:已认证、4:已拒绝】
         */
        private Integer certificateStatus;

        /**
         * 证件号码
         */
        private String certificateNo;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * im用户Id
         */
        private String platformUserId;

        /**
         * im用户名
         */
        private String name;

        /**
         * 手机号
         */
        private String telephone;

        /**
         * 地区
         */
        private String countryPhoneCode;

    }

}
