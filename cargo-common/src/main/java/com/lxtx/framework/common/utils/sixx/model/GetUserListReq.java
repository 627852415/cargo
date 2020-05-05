package com.lxtx.framework.common.utils.sixx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Czh
 * Date: 2018/8/30 下午4:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListReq {

    private String phone;
    private String nickname;
    private Integer enableState;
    private Integer tradeState;
    private Integer page;
    private Integer size;
}