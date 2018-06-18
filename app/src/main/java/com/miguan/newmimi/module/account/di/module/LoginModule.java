package com.miguan.newmimi.module.account.di.module;

import dagger.Module;
import dagger.Provides;

import com.jess.arms.di.scope.ActivityScope;
import com.miguan.newmimi.module.account.UserContract;
import com.miguan.newmimi.module.account.model.LoginModel;
import com.tbruyelle.rxpermissions2.RxPermissions;

@Module
public class LoginModule {
    private UserContract.LoginView view;

    /**
     * 构建LoginModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LoginModule(UserContract.LoginView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.LoginView provideLoginView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideLoginModel(LoginModel model) {
        return model;
    }

}