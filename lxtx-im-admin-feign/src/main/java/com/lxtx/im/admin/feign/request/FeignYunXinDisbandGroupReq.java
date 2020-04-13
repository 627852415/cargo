package com.lxtx.im.admin.feign.request;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2018-11-17 18:21
 * @Description
 */
@Data
public class FeignYunXinDisbandGroupReq {

//    参数	类型	必须	说明
//    tid	String	是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
//    owner	String	是	，最群主用户帐号大长度32字符


    private String tid;

    private String owner;

}
