package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class BcbCountriesListResp {


    private List<Countrity> countries;

    private BcbBaseResp status;


    @Getter
    @Setter
    public class Countrity{
        private  Integer MoneyId;
        private String Lada;
        private String CountryShort;
        private String CountryCode;
        private Integer CountryId;
        private String CountryName;
    }


}
