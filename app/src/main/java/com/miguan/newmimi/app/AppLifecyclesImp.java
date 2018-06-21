package com.miguan.newmimi.app;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.jess.arms.base.App;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miguan.newmimi.BuildConfig;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/19
 */
public class AppLifecyclesImp implements AppLifecycles {

    @Override
    public void attachBaseContext(Context base) {
//                MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return;
        }
        initCanary(application);
        initLog();
        Utils.init(application);
        initARouter(application);
    }

    private void initLog() {
        if (BuildConfig.LOG_DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    // LeakCanary内存检测
    private void initCanary(Application application) {
        if (application instanceof App) {
            AppComponent appComponent = ArmsUtils.obtainAppComponentFromContext(application);
            appComponent.extras().put(RefWatcher.class.getName(),
                    BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        }
    }

    // 阿里路由
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

}
