package com.miguan.newmimi.module.main;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.miguan.newmimi.module.account.model.bean.User;

import io.reactivex.Flowable;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/15
 */
public interface MainContract {

    interface MainView extends IView {

    }

    interface Model extends IModel {
        Flowable<User> getUnreadCount();
    }

}
