package com.miguan.newmimi.module.account.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.miguan.newmimi.R;
import com.miguan.newmimi.app.ARouterPaths;
import com.miguan.newmimi.app.ActivityConfig;
import com.miguan.newmimi.app.BaseActivity;
import com.miguan.newmimi.module.account.UserContract;
import com.miguan.newmimi.module.account.di.component.DaggerLoginComponent;
import com.miguan.newmimi.module.account.di.module.LoginModule;
import com.miguan.newmimi.module.account.model.bean.User;
import com.miguan.newmimi.module.account.presenter.LoginPresenter;

import butterknife.OnClick;

@Route(path = ARouterPaths.USER_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter> implements UserContract.LoginView {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.account_activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public ActivityConfig getActivityConfig() {
        return super.getActivityConfig()
                .setStatusBarColor(R.color.colorAccent);
    }

    @OnClick(R.id.account_tv_login)
    void clickLogin() {
        mPresenter.validateCredentials("", "");
    }

    @Override
    public void onMobileError() {

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void loginSuccess(User user) {

    }

    @Override
    public void loginFailed() {

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
