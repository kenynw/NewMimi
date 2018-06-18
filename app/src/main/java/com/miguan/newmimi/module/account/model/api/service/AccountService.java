package com.miguan.newmimi.module.account.model.api.service;

import com.miguan.newmimi.module.account.model.bean.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccountService {

    @GET("")
    Flowable<User> login(
            @Query("mobile") String mobile,
            @Query("password") String password
    );

}
