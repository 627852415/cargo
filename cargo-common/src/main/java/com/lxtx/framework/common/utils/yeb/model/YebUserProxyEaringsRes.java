package com.lxtx.framework.common.utils.yeb.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * author lin hj on 2019/3/29
 *
 */
@Data
public class YebUserProxyEaringsRes implements Serializable {

    private int total;

    private List<DataInfo> list;

    @Getter
    @Setter
    public class DataInfo{
        /**
         * 地址
         */
        private String address;
        /**
         * ，收益日期
         */
        private String date;
        /**
         * 返佣金额
         */
        private String rebate;
    }



}
