package com.wuxian.charge.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jianglei on 2017/2/24.
 */

public class ChargeApplication extends Application {
    private static ChargeApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Fresco.initialize(this);
    }

    public static ChargeApplication getInstance() {
        return sInstance;
    }
}
