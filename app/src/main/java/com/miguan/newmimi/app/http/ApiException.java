package com.miguan.newmimi.app.http;

import java.io.IOException;

/**
 */

public class ApiException extends IOException {

    private int code;

    private String msg;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
