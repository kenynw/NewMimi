package com.miguan.newmimi.module.account.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.miguan.newmimi.module.account.UserContract;
import com.miguan.newmimi.module.account.model.api.service.AccountService;
import com.miguan.newmimi.module.account.model.bean.User;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class LoginModel extends BaseModel implements UserContract.Model {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<User> login(String mobile, String password, String deviceId) {
        return mRepositoryManager.obtainRetrofitService(AccountService.class)
                .login(mobile, password, deviceId)
                .retryWhen(new RetryWithDelay(3, 2))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
