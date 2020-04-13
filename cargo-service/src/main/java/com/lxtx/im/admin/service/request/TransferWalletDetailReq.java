package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @Description 钱包转账详情入参
 * @author qing 
 * @date: 2019年11月21日 下午6:13:30
 */
@Setter
@Getter
public class TransferWalletDetailReq {
	
    @NotBlank(message = "id不能为空")
    private String id;
    
}
