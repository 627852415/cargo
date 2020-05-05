package com.lxtx.framework.common.utils.sixx.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 返回用户结果POJO
* Description: TODO
* @author hongweizhang
*
 */
@Data
public class MerChantResultPojo {

    private int id;

    private String name;

    private String phone;

    private String email;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private int tradeState;

    private int enableState;

}
