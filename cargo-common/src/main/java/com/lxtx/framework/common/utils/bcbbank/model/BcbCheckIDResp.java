package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;

import java.util.List;

@Data
public class BcbCheckIDResp {


    private DataInfo ServiceTransactionInformation;

    private BcbBaseResp Status;

    @Data
    static class DataInfo{
        private String  Service ;
        private String  ServiceTransactionResult ;
        private String  ServiceTransactionResultMessage ;
        private List<String> ServiceValidationDetails ;
        private String ValidationResult ;
        private String callid ;

    }


}
