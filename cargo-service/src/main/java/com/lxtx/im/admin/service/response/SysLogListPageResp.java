package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.dao.model.SysLog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 操作日志列表resp
 *
 * @author liyunhua
 * @date 2018-09-28 0028
 */
@Getter
@Setter
public class SysLogListPageResp {

    private Integer total;
    private Integer size;
    private Integer pages;
    private Integer current;

    List<SysLog> records;
}
