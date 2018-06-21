package com.miguan.newmimi.app;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.gyf.barlibrary.ImmersionBar;

import java.io.Serializable;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/19
 *
 * 用于Activity的配置，需要实现{@link IActivityConfig}才能生效
 * 后根据具体使用业务进行扩展
 */
public class ActivityConfig implements Serializable, Cloneable {

    public static ActivityConfig DEFAULT = new ActivityConfig();

    private ImmersionBar mImmersionBar; // 在Activity中传递用于销毁

    boolean mToolbarBackVisible = true; // 返回图标是否可见

    @DrawableRes int mToolbarBackRes; // 返回图标资源

    @ColorRes int mStatusBarColor; // 任务栏颜色

    public static ActivityConfig getDefault() {
        return DEFAULT.clone();
    }

    public ImmersionBar getImmersionBar() {
        return mImmersionBar;
    }

    public void setImmersionBar(ImmersionBar immersionBar) {
        mImmersionBar = immersionBar;
    }

    public ActivityConfig setToolbarBackVisible(boolean visible) {
        mToolbarBackVisible = visible;
        return this;
    }

    public ActivityConfig setStatusBarColor(@ColorRes int res) {
        mStatusBarColor = res;
        return this;
    }

    @Override
    protected ActivityConfig clone() {
        try {
            return (ActivityConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ActivityConfig();
    }

}
