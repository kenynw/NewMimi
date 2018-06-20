package com.miguan.newmimi.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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
        ActivityConfig activityConfig;
        if (activity instanceof IActivityConfig) {
            activityConfig = ((IActivityConfig) activity).getActivityConfig();
        } else {
            activityConfig = new ActivityConfig();
        }
        initToolbar(activity, activityConfig);
        initStatusBar(activity, activityConfig);
        activity.getIntent().putExtra(ExtraConstant.ACTIVITY_BEAN, activityConfig);
    }

    private void initToolbar(Activity activity, ActivityConfig activityConfig) {
        Toolbar toolbar = activity.findViewById(R.id.base_toolbar);
        if (toolbar != null) {
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).setSupportActionBar(toolbar);
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }

            View.OnClickListener onBackClickListener = view -> activity.onBackPressed();
            ImageView ivBack = activity.findViewById(R.id.base_toolbar_back);
            if (ivBack != null) {
                if (activityConfig.mToolbarBackVisible) {
                    ivBack.setVisibility(View.VISIBLE);
                    ivBack.setOnClickListener(onBackClickListener);
                    if (activityConfig.mToolbarBackRes > 0) {
                        ivBack.setImageResource(activityConfig.mToolbarBackRes);
                    }
                } else {
                    ivBack.setVisibility(View.GONE);
                }
            } else {
                toolbar.setNavigationOnClickListener(onBackClickListener);
            }

            TextView tvTitle = activity.findViewById(R.id.base_toolbar_title);
            if (tvTitle != null && !TextUtils.isEmpty(activity.getTitle())) {
                tvTitle.setText(activity.getTitle());
            }
        }
    }

    private void initStatusBar(Activity activity, ActivityConfig activityConfig) {
        View statusBar = activity.findViewById(R.id.base_status_bar);
        if (statusBar != null) {
            ImmersionBar immersionBar = ImmersionBar.with(activity)
                    .statusBarDarkFont(true, 0.2f)
                    .navigationBarEnable(false)
                    .statusBarView(statusBar);
            if (activityConfig.mStatusBarColor > 0) {
                immersionBar.statusBarColor(activityConfig.mStatusBarColor);
            } else {
                immersionBar.transparentBar();
            }
            immersionBar.init();
            activityConfig.setImmersionBar(immersionBar);;
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
        ActivityConfig activityConfig = (ActivityConfig) activity.getIntent().getSerializableExtra(ExtraConstant.ACTIVITY_BEAN);
        if (activityConfig != null) {
            if (activityConfig.getImmersionBar() != null) {
                activityConfig.getImmersionBar().destroy();
            }
        }
    }

}
