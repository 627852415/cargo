package com.lxtx.im.admin.service.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * BCB银行卡分页列表
 *
 * @author : CaiRH
 * @since : 2019-04-23
 */
@Getter
@Setter
public class BcbBankcardListPageReq extends BasePageReq {
    /**
     * 搜索手机号、姓名、邮箱 查询条件
     */
    private String query;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /**
     * 结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 查询状态【1：待初审，2：待复审，3：结果】
     */
    private Integer selectStatus;

}
