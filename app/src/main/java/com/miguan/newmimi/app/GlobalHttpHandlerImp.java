package com.miguan.newmimi.app;

import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.jess.arms.http.GlobalHttpHandler;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/21
 */
public class GlobalHttpHandlerImp implements GlobalHttpHandler {

    @Override
    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
        return response;
    }

    @Override
    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        if (TextUtils.isEmpty(request.header("timestamp"))) {
            request = request.newBuilder()
                    .header("timestamp", String.valueOf(System.currentTimeMillis()))
                    .build();
        }
        return request;
    }

}
