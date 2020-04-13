package com.lxtx.im.admin.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PengPai
 * Date: Created in 1:12 2020/2/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalCodeSimpleInfo {
    //国家简码
    private String countryCode;
    //电话简码
    private String phoneCode;
    //国家名称
    private String countryName;
    //选中状态
    private boolean chooseFlag;
}
