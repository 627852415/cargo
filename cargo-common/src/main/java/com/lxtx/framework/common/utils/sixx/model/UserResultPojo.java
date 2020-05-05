package com.lxtx.framework.common.utils.sixx.model;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 返回用户结果POJO
* Description: TODO
* @author hongweizhang
*
 */
public class UserResultPojo {

    private int id;

    private String nickname;

    private String phone;

    private String email;

    private Date createTime;

    private int tradeState;

    private int enableState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getTradeState() {
        return tradeState;
    }

    public void setTradeState(int tradeState) {
        this.tradeState = tradeState;
    }

    public int getEnableState() {
        return enableState;
    }

    public void setEnableState(int enableState) {
        this.enableState = enableState;
    }

    

}
