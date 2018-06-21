package com.miguan.newmimi.app;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import retrofit2.HttpException;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/21
 */
public class ResponseErrorListenerImp implements ResponseErrorListener {
    @Override
    public void handleResponseError(Context context, Throwable t) {
        if (t instanceof UnknownHostException) {
            ToastUtils.showLong("网络不可用!");
        } else if (t instanceof SocketTimeoutException) {
            ToastUtils.showLong("请求网络超时");
        } else if (t instanceof HttpException) {
            convertStatusCode((HttpException) t);
        }
    }

    private void convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        ToastUtils.showLong(msg);
    }

}
