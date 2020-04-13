package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 *
 * </p>
 *
 * @author liboyan
 * @Date 2018-11-17 16:10
 * @Description
 */
@Setter
@Getter
public class FeignYunXinCreateGroupReq {

//    tname	String	是
//    owner	String	是	群主用户帐号，最大长度32字符
//    members	String	是	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
//    announcement	String	否	群公告，最大长度1024字符
//    intro	String	否	群描述，最大长度512字符
//    msg	String	是	邀请发送的文字，最大长度150字符
//    magree	int	是	管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
//    joinmode	int	是	群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414

    /**
     * 群名称，最大长度64字符
     *
     */
    private String tname ;

    /**
     * 群主用户帐号，最大长度32字符
     * */
    private String owner;

    /**
     * 邀请发送的文字，最大长度150字符
     *
     */
    @Length(max = 150,message = "最大长度150字符")
    private String msg;

    private int magree = 0;

    private int joinmode = 0;

    /**
     * ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
     * */
    private String members;

    @Override
    public String toString() {
        return "YunXinCreateGroupReq{" +
                "tname='" + tname + '\'' +
                ", owner='" + owner + '\'' +
                ", msg='" + msg + '\'' +
                ", magree=" + magree +
                ", joinmode=" + joinmode +
                ", members='" + members + '\'' +
                '}';
    }
}
