package com.miguan.newmimi.module.account.model.api.service;

import com.miguan.newmimi.module.account.model.bean.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccountService {

    /**
     * 用户登录
     */
    @GET("user/login")
    Observable<User> login(
            @Query("tel") String mobile,
            @Query("pwd") String password,
            @Query("imei") String deviceId
    );

}
