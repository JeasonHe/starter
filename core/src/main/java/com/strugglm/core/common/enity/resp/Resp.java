package com.strugglm.core.common.enity.resp;

import com.strugglm.core.common.enums.RespEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: core模块对其他系统的返回值结构体
 * @author: hejiale
 * @create: 2019/11/03 20:59
 */
@Data
public class Resp<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String status;
    private String msg;
    private T data;

    private Resp(RespEnum respEnum) {
        code = respEnum.getCode();
        status = respEnum.getStatus();
        msg = respEnum.getMsg();
    }

    private Resp(RespEnum respEnum, T dataOuter) {
        code = respEnum.getCode();
        status = respEnum.getStatus();
        msg = respEnum.getMsg();
        data = dataOuter;
    }

    public static Resp success() {
        return new Resp<>(RespEnum.SUCCESS);
    }

    public static <T> Resp success(T data) {
        return new Resp<>(RespEnum.SUCCESS, data);
    }

    public static <T> Resp success(T data, String desc) {
        return new Resp<>(RespEnum.SUCCESS, data).setMsg(desc);
    }

    public static Resp fail() {
        return new Resp(RespEnum.FAIL);
    }

    public static Resp fail(String desc) {
        return new Resp(RespEnum.FAIL).setMsg(desc);
    }

    public static Resp fail(RespEnum respEnum) {
        return new Resp(respEnum);
    }

    public static Resp fail(RespEnum respEnum, String desc) {
        return new Resp(respEnum).setMsg(desc);
    }

    public boolean isSuccess() {
        return RespEnum.SUCCESS.getCode().equals(code);
    }

    private Resp<T> setMsg(String desc) {
        this.msg = desc;
        return this;
    }
}
