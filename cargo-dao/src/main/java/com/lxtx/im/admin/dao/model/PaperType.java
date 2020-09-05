package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 类型表
 * </p>
 *
 * @author 
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_paper_type")
public class PaperType extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField
    private String id;
    /**
     * 类型名
     */
    private String name;
    /**
     * 父ID
     */
    private String pId;

    /**
     * 文章（paper表）ID
     */
    private String paperId;


    private String code;

    /**
     * 1 为一级区域
     *
     */
    private Integer topLevel;

    /**
     * 排序编号
     */
    private Integer sortNo;


}
