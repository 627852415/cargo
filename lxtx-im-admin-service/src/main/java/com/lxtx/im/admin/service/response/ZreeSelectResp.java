package com.lxtx.im.admin.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ZreeSelectResp {

    private Integer code = 0;

    private List<Map<String, String>> data;
}
