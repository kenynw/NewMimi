package com.miguan.newmimi.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.jess.arms.base.App;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.integration.ConfigModule;
import com.miguan.newmimi.BuildConfig;
import com.miguan.newmimi.app.http.HttpResponseCode;
import com.miguan.newmimi.app.http.WrapperConverterFactory;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/14
 */
public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        //使用builder可以为框架配置一些配置信息
        builder.baseurl(Constant.BASE_URL)
                .globalHttpHandler(new GlobalHttpHandler() {
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
                })
                .gsonConfiguration((context12, gsonBuilder) -> {
                    gsonBuilder.serializeNulls() // 支持序列化null的参数
                            .enableComplexMapKeySerialization();// 支持将序列化key为object的map,默认只能序列化key为string的map
                })
                .retrofitConfiguration((context1, retrofitBuilder) -> {
                    retrofitBuilder.addConverterFactory(WrapperConverterFactory.create())
                            .build();
                })
                .okhttpConfiguration((context13, okhttpBuilder) -> {
                    okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS);
                })
                .responseErrorListener(new ResponseErrorListener() {
                    @Override
                    public void handleResponseError(Context context, Throwable t) {
                        if (t instanceof UnknownHostException) {
                            onResponseError(HttpResponseCode.ERROR_UNKNOWN_HOST, "网络不可用!");
                        } else if (t instanceof SocketTimeoutException) {
                            onResponseError(HttpResponseCode.ERROR_SOCKET_TIMEOUT, "请求网络超时");
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
                        onResponseError(httpException.code(), msg);
                    }

                    private void onResponseError(int code, String msg) {
                        ToastUtils.showLong(msg);
                    }
                });
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        //向Application的生命周期中注入一些自定义逻辑
        lifecycles.add(new AppLifecycles() {
            @Override
            public void attachBaseContext(Context base) {
//                MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
            }

            @Override
            public void onCreate(Application application) {
                initLog();
                Utils.init(application);
                initARouter(application);
            }

            private void initLog() {
                if (BuildConfig.LOG_DEBUG) {
                    Timber.plant(new Timber.DebugTree());
                }
            }

            private void initCanary(Application application) {
                if (LeakCanary.isInAnalyzerProcess(application)) {
                    return;
                }
                if (application instanceof App) {
                    AppComponent appComponent = ((App) application).getAppComponent();
                    appComponent.extras().put(RefWatcher.class.getName(),
                            BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
                }
            }

            private void initARouter(Application application) {
                if (AppUtils.isAppDebug(application.getPackageName())) {
                    ARouter.openLog();
                    ARouter.openDebug();
                }
                ARouter.init(application);
            }

            @Override
            public void onTerminate(Application application) {

            }
        });
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        //向Activity的生命周期中注入一些自定义逻辑
        lifecycles.add(new ActivityLifecycleCallbacksImp());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        //向Fragment的生命周期中注入一些自定义逻辑
    }

}
