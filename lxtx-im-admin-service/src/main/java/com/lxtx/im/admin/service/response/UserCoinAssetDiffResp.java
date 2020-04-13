package com.lxtx.im.admin.service.response;

import com.lxtx.im.admin.service.utils.ExcelField;
import lombok.Getter;
import lombok.Setter;


/**
 * 资产流水表
 *
 * @author tangdy
 * @since 2018-12-25
 */
@Getter
@Setter
public class UserCoinAssetDiffResp {

    @ExcelField(name = "content", orderBy = "1")
    private String content;

}
