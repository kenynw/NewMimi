package com.miguan.newmimi.app;

import com.gyf.barlibrary.ImmersionBar;

import java.io.Serializable;

/**
 * Copyright (c) 2018 Miguan Inc All rights reserved.
 * Created by Liaopeikun on 2018/6/19
 */
public class ActivityBean implements Serializable {

    private ImmersionBar mImmersionBar;

    public ImmersionBar getImmersionBar() {
        return mImmersionBar;
    }

    public void setImmersionBar(ImmersionBar immersionBar) {
        mImmersionBar = immersionBar;
    }

}
