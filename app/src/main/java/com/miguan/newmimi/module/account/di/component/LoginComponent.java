package com.miguan.newmimi.module.account.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;
import com.miguan.newmimi.module.account.di.module.LoginModule;
import com.miguan.newmimi.module.account.ui.LoginActivity;


@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}