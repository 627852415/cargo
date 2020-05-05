package com.lxtx.framework.common.utils.bcbbank.model;

import lombok.Data;

/**
 * 上传文档返回对象信息
 */
@Data
public class BcbUploadDocumentResp {

    private StatesResp status;

    private String BytesUploaded;
    private String DocumentTypeValidationSucceeded;
    private String DocumentUID;
    private String ResolvedDocumentType;

    @Data
    public static class StatesResp{
        private String code ;

        private String message;
    }

}
