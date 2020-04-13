package com.lxtx.im.admin.dao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Czh
 * Date: 2018/9/27 下午3:10
 */
@Getter
@Setter
public class BaseModel {
    /**
     * 备注
     */
    public String remarks;
    /**
     * 创建时间
     */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    public Date createTime;
    /**
     * 创建人
     */
    @TableField(value="create_by",fill = FieldFill.INSERT)
    public String createBy;
    /**
     * 更新时间
     */
    @TableField(value="update_time",fill = FieldFill.INSERT_UPDATE)
    public Date updateTime;
    /**
     * 更新人
     */
    @TableField(value="update_by",fill = FieldFill.INSERT_UPDATE)
    public String updateBy;
    /**
     * 删除标记 0：未删除，1：已删除
     * 不要私有化
     */
    @TableField(value="del_flag",fill = FieldFill.INSERT)
    @TableLogic
    public Boolean delFlag;
}