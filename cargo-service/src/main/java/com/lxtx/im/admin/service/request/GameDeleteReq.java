package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 游戏删除
 *
 * @author CaiRH
 */
@Setter
@Getter
public class GameDeleteReq {
    /**
     * 主键
     */
    @NotBlank(message = "ID不能为空")
    private String id;
}
