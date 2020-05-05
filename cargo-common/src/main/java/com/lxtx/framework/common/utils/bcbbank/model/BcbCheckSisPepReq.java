package com.lxtx.framework.common.utils.bcbbank.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class BcbCheckSisPepReq {

    @JSONField(name ="Application_ID")
    private Integer Application_ID;

    @JSONField(name ="DayOfBirth")
    private String DayOfBirth;

    @JSONField(name ="MemberID")
    private Integer MemberID;

    @JSONField(name ="Forename")
    private String Forename;

    @JSONField(name ="HouseNameNumber")
    private String HouseNameNumber;

    @JSONField(name ="MonthOfBirth")
    private String MonthOfBirth;

    @JSONField(name ="Postcode")
    private String Postcode;

    @JSONField(name ="Street")
    private String Street;

    @JSONField(name ="NumStreet")
    private String NumStreet;

    @JSONField(name ="Surname")
    private String Surname;

    @JSONField(name ="YearOfBirth")
    private String YearOfBirth;



}
