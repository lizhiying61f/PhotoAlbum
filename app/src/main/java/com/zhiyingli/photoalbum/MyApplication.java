package com.zhiyingli.photoalbum;

import android.app.Application;

/**
 * Created by zhiyingli on 2016/11/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
