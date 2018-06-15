package com.miguan.newmimi.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.miguan.newmimi.R;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/15
 */
public class ActivityLifecycleCallbacksImp implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Toolbar toolbar = activity.findViewById(R.id.base_toolbar);
        if (toolbar != null) {
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).setSupportActionBar(toolbar);
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }

            View.OnClickListener onBackClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onBackPressed();
                }
            };
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

    }

}
