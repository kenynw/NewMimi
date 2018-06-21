package com.miguan.newmimi.module.account.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.miguan.newmimi.module.account.UserContract;
import com.miguan.newmimi.module.account.model.bean.User;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class LoginPresenter extends BasePresenter<UserContract.Model, UserContract.LoginView> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    AppManager mAppManager;
    @Inject
    Application mApplication;
    @Inject
    RxPermissions mRxPermissions;

    @Inject
    public LoginPresenter(UserContract.Model model, UserContract.LoginView rootView) {
        super(model, rootView);
    }

    public void validateCredentials(String mobile, String password) {
        if (TextUtils.isEmpty(mobile)) {
            mRootView.onMobileError();
        } else if (TextUtils.isEmpty(password)) {
            mRootView.onPasswordError();
        } else {
            login(mobile, password);
        }
    }

    private void login(String mobile, String password) {
        mModel.login(mobile, password, "")
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<User>(mErrorHandler) {
                    @Override
                    public void onNext(User user) {

                    }
                });
    }

}
