package com.strugglm.core.common.enums;


/**
 * 返回值的枚举
 * @author jeason
 */

public enum RespEnum {
    SUCCESS(1,"success","成功"),
    FAIL(0,"fail","失败"),
    FAIL_OKHTTP(001,"fail_okhttp","HTTP请求失败");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态
     */
    private String status;
    /**
     * 描述
     */
    private String msg;

    RespEnum(Integer code, String status, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
