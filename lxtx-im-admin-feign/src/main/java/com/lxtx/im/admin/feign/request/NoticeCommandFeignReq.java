package com.lxtx.im.admin.feign.request;

import lombok.*;

import java.util.Set;

/**
 * @author PengPai
 * Date: Created in 14:09 2020/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class NoticeCommandFeignReq extends BasePageReq {

    //主键集合
    private Set<String> ids;
    //指令唯一标识
    private String instructId;
    //指令名称
    private String name;
    //指令描述
    private String description;
}
