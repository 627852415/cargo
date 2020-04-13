package com.lxtx.im.admin.feign.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class FeignSalaryBindEmployeeReq implements Serializable {

    private String companyId;
}
