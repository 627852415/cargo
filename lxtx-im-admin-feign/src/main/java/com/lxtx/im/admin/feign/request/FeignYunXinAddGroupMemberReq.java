package com.lxtx.im.admin.feign.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2018-11-17 17:53
 * @Description
 */
@Data
public class FeignYunXinAddGroupMemberReq {

//    参数说明
//    参数	类型	必须	说明
//    tid	String	是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
//    owner	String	是	群主用户帐号，最大长度32字符
//    members	String	是	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
//    magree	int	是	管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
//    msg	String	是	邀请发送的文字，最大长度150字符
//    attach	String	否	自定义扩展字段，最大长度512

    @Length(max = 128,message = "tid 最大长度128字符")
    private  String tid ;

    @Length(max = 128,message = "owner 最大长度32字符")
    private String  owner;

    private  String  members ;

    private  int  magree = 0;

    @Length(max = 128,message = "msg 150")
    private  String  msg;
}
