package com.miguan.newmimi.module.main.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.miguan.newmimi.R;
import com.miguan.newmimi.app.ARouterPaths;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        super.onCreate(savedInstanceState);
    }

    public void login(View view) {
        ARouter.getInstance().build(ARouterPaths.USER_LOGIN).navigation();
    }

}
