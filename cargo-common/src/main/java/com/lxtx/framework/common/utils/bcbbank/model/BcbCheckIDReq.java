package com.lxtx.framework.common.utils.bcbbank.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class BcbCheckIDReq {

    @JSONField(name ="Application_ID")
    private  String Application_ID;

    @JSONField(name ="user_Document")
    private List<DocumentInfo> user_Document;

    @JSONField(name ="MemberID")
    private String MemberID;

    @JSONField(name ="ISO_CountryCode")
    private String ISO_CountryCode;

    @JSONField(name ="BirthDate")
    private String BirthDate;

    @JSONField(name ="Forename")
    private String Forename;

    @JSONField(name ="HouseNameNumber")
    private String HouseNameNumber;

    @JSONField(name ="NumStreet")
    private String NumStreet;

    @JSONField(name ="Street")
    private String Street;

    @JSONField(name ="City")
    private String City;

    @JSONField(name ="State")
    private String State;

    @JSONField(name ="Postcode")
    private String Postcode;

    @JSONField(name ="Surname")
    private String Surname;


    @Data
    public static class  DocumentInfo{
        @JSONField(name ="DocumentReference")
        private  String DocumentReference;

        @JSONField(name ="DocumentUID")
        private  String DocumentUID;

    }



}
