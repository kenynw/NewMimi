package com.miguan.newmimi.module.account;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.miguan.newmimi.module.account.model.bean.User;

import io.reactivex.Observable;

public interface UserContract {

    interface LoginView extends IView {
        void onMobileError();
        void onPasswordError();
        Activity getActivity();
        void loginSuccess(User user);
        void loginFailed();
    }

    interface Model extends IModel {
        Observable<User> login(String mobile, String password, String deviceId);
    }

}
