package com.miguan.newmimi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.miguan.newmimi.module.account.model.bean.User;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/21
 */
public class DaoSharedPreferences {

    private static Context sContext;

    private static DaoSharedPreferences sInstance;

    private SharedPreferences mPreferences;

    private SharedPreferences.Editor mEditor;

    private static final String NAME = "MimiDb";

    private static final String USER = "user";

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    private DaoSharedPreferences() {
        if (sContext == null) {
            throw new RuntimeException("请在Application中初始化!");
        }
        mPreferences = sContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static DaoSharedPreferences getInstance() {
        if (sInstance == null) {
            sInstance = new DaoSharedPreferences();
        }

        return sInstance;
    }

    /**
     * 获取用户信息
     */
    public User getUser() {
        User userInfo = null;
        String userInfoStr = mPreferences.getString(USER, "");
        if (!TextUtils.isEmpty(userInfoStr)){
            userInfo = GsonUtil.parse(userInfoStr, User.class);
        }
        return userInfo;
    }

    /**
     * 设置用户信息
     * @param user
     */
    public void setUser(User user) {
        if (user == null){
            return;
        }
        mEditor.putString(USER, GsonUtil.toJson(user));
        mEditor.apply();
    }


}
