package com.hby.crudbackend.entity;

import lombok.Data;

@Data
public class R<T> {

    private boolean success;
    private int code;
    private String msg;
    private T data;

    public static R<Void> success(int code, String msg) {
        R<Void> r = new R<>();
        r.setSuccess(true);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> successWithData(int code, String msg, T data) {
        R<T> r = new R<>();
        r.setSuccess(true);
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R<Void> fail(int code, String msg) {
        R<Void> r = new R<>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
