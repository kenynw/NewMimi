package com.miguan.newmimi.util;

import android.app.Application;
import android.content.Context;

import com.jess.arms.utils.ArmsUtils;
import com.miguan.newmimi.module.account.model.bean.User;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/21
 */
public class MimiUtils {

    private static Context sContext;

    public static String KEY_USER = "user";

    public static void init(Application application) {
        sContext = application.getApplicationContext();
        DaoSharedPreferences.init(sContext);
    }

    public static void setUser(User user) {
        if (user != null) {
            ArmsUtils.obtainAppComponentFromContext(sContext).extras().put(KEY_USER, user);
            DaoSharedPreferences.getInstance().setUser(user);
        }
    }

    public static User getUser() {
        User user = (User) ArmsUtils.obtainAppComponentFromContext(sContext).extras().get(KEY_USER);
        if (user == null) {
            user = DaoSharedPreferences.getInstance().getUser();
        }
        return user;
    }

}
