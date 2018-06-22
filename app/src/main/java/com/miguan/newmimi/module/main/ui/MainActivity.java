package com.miguan.newmimi.module.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.miguan.newmimi.R;
import com.miguan.newmimi.app.ARouterPaths;
import com.miguan.newmimi.module.main.MainContract;
import com.miguan.newmimi.module.main.di.component.DaggerMainComponent;
import com.miguan.newmimi.module.main.di.module.MainModule;
import com.miguan.newmimi.module.main.preseinter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.main_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    public void login(View view) {
        ARouter.getInstance().build(ARouterPaths.USER_LOGIN).navigation();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }

    @Override
    public void killMyself() {

    }

}
