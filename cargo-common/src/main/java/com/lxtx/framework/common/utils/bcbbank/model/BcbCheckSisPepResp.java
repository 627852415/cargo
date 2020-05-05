package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;

import java.util.List;

@Data
public class BcbCheckSisPepResp {

    private List<BcbCheckIDResp.DataInfo> ServiceTransactionInformation;

    private BcbBaseResp Status;



    @Data
    static class DataInfo{
        private String  Service ;
        private String  ServiceInterpretResult ;
        private String  ServiceTransactionResult ;
        private List<String> ServiceTransactionResultMessage ;
        private String ValidationResult ;

    }


}
