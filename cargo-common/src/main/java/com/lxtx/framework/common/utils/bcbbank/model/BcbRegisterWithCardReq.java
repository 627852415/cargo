package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 持卡人注册
 *
 * @author CaiRH
 * @since 2019-04-26
 */
@Getter
@Setter
public class BcbRegisterWithCardReq {

    /**
     * Application_ID : 1512
     * Name : jjjhg
     * MiddleName :
     * Email : user@test.com
     * CellPhone : 18020760047
     * CountryCode : 1
     * Password : 123456789
     * AccountId : 1000000000184181
     * BirthDate : 19941224
     * CountryBirthId : 2
     * GenderId : 2
     * LastName : test
     * SecondLastName :
     * Street : frida
     * NumStreet : 12
     * NumApt : 12
     * CountryId : 2
     * Country : UNITED STATES
     * StateId : 33
     * State : ALABAMA
     * City : helmiton
     * ZipCode : 7412
     * Suburb : fri
     * Agent_code : 151
     * Sub_Agent_code : 0
     * Client_Agent_SubAgent_Name : Demo
     */

    private String name;
    private String middleName;
    private String email;
    private String cellPhone;
    private String countryCode;
    private String password = "12345678";
    private String accountId;
    private String birthDate;
    private String countryBirthId;
    private String genderId;
    private String lastName;
    private String secondLastName;
    private String street;
    private String numStreet;
    private String numApt;
    private String countryId;
    private String country;
    private String stateId;
    private String state;
    private String city;
    private String zipCode;
    private String suburb;
    private int agent_code = 151;
    private int sub_Agent_code = 2;
    private String client_Agent_SubAgent_Name = "Demo";
}
