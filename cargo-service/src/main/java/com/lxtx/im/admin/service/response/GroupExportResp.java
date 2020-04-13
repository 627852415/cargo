package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;

/**
 * 群组列表导出
 *
 * @author CaiRH
 * @since 2019-06-10
 */
@Getter
@Setter
public class GroupExportResp {

    /**
     * 主键
     */
    @ExcelField(name = "群ID", orderBy = "1")
    private String groupId;

    private String yxGroupId;

    /**
     * 创建者用户账号(account)
     */
    @ExcelField(name = "创建者", orderBy = "4")
    private String founder;
    /**
     * 名称
     */
    @ExcelField(name = "群名称", orderBy = "3")
    private String name;
    /**
     * 群组简介
     */
    private String summary;
    /**
     * 群头像路径
     */
    @ExcelField(name = "群头像", orderBy = "2")
    private String groupIconPath;

    /**
     * 群人数
     */
    @ExcelField(name = "群人数", orderBy = "5")
    private Integer memberCount;

    /**
     * 创建时间
     */
    @ExcelField(name = "创建时间", orderBy = "6")
    private String createTime;
}
