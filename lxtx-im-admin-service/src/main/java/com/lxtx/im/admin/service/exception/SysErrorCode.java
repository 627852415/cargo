package com.lxtx.im.admin.service.exception;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  系统错误码
 * </p>
 *
 * @author liboyan
 * @version 1.0
 * @create 2018-05-08
 */
public enum  SysErrorCode {

   PARA_ERROR("100001", "errors.param.invalid"),
   CONFLICT("100002", "业务异常"),
   LOGIN_FAIL("100004","登入异常"),
   SYSTEM_ERROR("999999", " 系统错误"),
   ;


   SysErrorCode(String code, String msg) {
      this.code = code;
      this.msg = msg;
   }

   private String code;
   private String msg;

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getMsg() {
      return msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String toJsonStr() {
      Map map = new HashMap();
      map.put("msg", this.getMsg());
      map.put("code", this.getCode());
      return JSONObject.toJSONString(map);
   }


   @Override
   public String toString() {
      return "IMErrorCode{" +
              "code='" + code + '\'' +
              ", msg='" + msg + '\'' +
              '}';
   }
}