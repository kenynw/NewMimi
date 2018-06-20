package com.miguan.newmimi.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.miguan.newmimi.R;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/15
 */
public class ActivityLifecycleCallbacksImp implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ActivityBean activityBean = new ActivityBean();
        initToolbar(activity);
        initStatusBar(activity, activityBean);
        activity.getIntent().putExtra(ExtraConstant.ACTIVITY_BEAN, activityBean);
    }

    private void initToolbar(Activity activity) {
        Toolbar toolbar = activity.findViewById(R.id.base_toolbar);
        if (toolbar != null) {
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).setSupportActionBar(toolbar);
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }

            View.OnClickListener onBackClickListener = view -> activity.onBackPressed();
            View view = activity.findViewById(R.id.base_toolbar_back);
            if (view != null) {
                view.setOnClickListener(onBackClickListener);
            } else {
                toolbar.setNavigationOnClickListener(onBackClickListener);
            }

            TextView tvTitle = activity.findViewById(R.id.base_toolbar_title);
            if (tvTitle != null && !TextUtils.isEmpty(activity.getTitle())) {
                tvTitle.setText(activity.getTitle());
            }
        }
    }

    private void initStatusBar(Activity activity, ActivityBean activityBean) {
        View statusBar = activity.findViewById(R.id.base_status_bar);
        if (statusBar != null) {
            ImmersionBar immersionBar = ImmersionBar.with(activity)
                    .transparentStatusBar()
                    .statusBarDarkFont(true, 0.2f)
                    .navigationBarEnable(false)
                    .statusBarView(statusBar);
            immersionBar.init();
            activityBean.setImmersionBar(immersionBar);;
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityBean activityBean = (ActivityBean) activity.getIntent().getSerializableExtra(ExtraConstant.ACTIVITY_BEAN);
        if (activityBean != null) {
            if (activityBean.getImmersionBar() != null) {
                activityBean.getImmersionBar().destroy();
            }
        }
    }

}
