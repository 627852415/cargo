package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 额外分佣配置表
 * </p>
 *
 * @author 
 * @since 2020-03-25
 */
@Getter
@Setter
public class CommissionExtraConfigAddReq {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 配置类型  1：白名单配置  2：通用配置
     */
    private Integer type;
    /**
     * 返佣白名单ID
     */
    private String wid;
    /**
     * 级别ID
     */
    private String levelId;
    /**
     * 返佣比例
     */
    private Float ratio;


}
