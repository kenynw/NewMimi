package com.miguan.newmimi.module.account.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.miguan.newmimi.module.account.UserContract;
import com.miguan.newmimi.module.account.model.api.service.AccountService;
import com.miguan.newmimi.module.account.model.bean.User;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelayOfFlowable;

@ActivityScope
public class LoginModel extends BaseModel implements UserContract.Model {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Flowable<User> login(String mobile, String password) {
        return mRepositoryManager.obtainCacheService(AccountService.class)
                .login(mobile, password)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelayOfFlowable(3, 2))
                .observeOn(AndroidSchedulers.mainThread());
    }

}
