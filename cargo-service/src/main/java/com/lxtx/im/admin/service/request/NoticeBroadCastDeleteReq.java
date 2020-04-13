package com.lxtx.im.admin.service.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author PengPai
 * Date: Created in 15:04 2020/2/24
 */
@Data
@Builder
public class NoticeBroadCastDeleteReq {

    @NotNull
    private Set<String> ids;
}
