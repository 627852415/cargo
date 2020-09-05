package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author 
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_paper")
public class Paper extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField
    private String id;
    /**
     * 标题
     */
    private String name;
    /**
     * 关联类型ID
     */
    private String refId;
    /**
     * 一级关联ID
     */
    private String oneRef;
    /**
     * 二级关联ID
     */
    private String twoRef;
    /**
     * 作者
     */
    private String author;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 排序编号
     */
    private String sortNo;

    @TableField(exist = false)
    private String timeStr;


}
