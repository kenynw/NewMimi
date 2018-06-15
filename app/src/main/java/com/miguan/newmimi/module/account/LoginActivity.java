package com.miguan.newmimi.module.account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.miguan.newmimi.R;
import com.miguan.newmimi.app.ARouterPaths;

@Route(path = ARouterPaths.USER_LOGIN)
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_login);
    }

}
