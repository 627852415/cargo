package com.lxtx.im.admin.service.response;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * @author PengPai
 * Date: Created in 18:15 2020/2/21
 */
@Builder
@Data
public class NoticeCommand implements Serializable {

    private String id;
    //指令唯一标识
    private String instructId;
    //指令名称
    private String name;
    //指令描述
    private String description;
}
