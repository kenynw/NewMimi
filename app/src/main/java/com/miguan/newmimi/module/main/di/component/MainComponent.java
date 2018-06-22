package com.miguan.newmimi.module.main.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.miguan.newmimi.module.main.di.module.MainModule;
import com.miguan.newmimi.module.main.ui.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivity activity);

}