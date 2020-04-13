package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 */
@Getter
@Setter
public class YebUserEarningSyncReq {

    @NotBlank(message = "userId不能为空")
    private String userId;

}
