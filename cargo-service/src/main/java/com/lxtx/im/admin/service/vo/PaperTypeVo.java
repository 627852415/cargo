package com.lxtx.im.admin.service.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 类型表
 * </p>
 *
 * @author 
 * @since 2020-04-17
 */
@Data
public class PaperTypeVo {

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
     * 排序编号
     */
    private Integer sortNo;

    private String paperId;

    private String oneRef;
    private String twoRef;

    /**
     * 是否有子菜单
     */
    public List<PaperTypeVo> sub = Lists.newArrayList();



}
