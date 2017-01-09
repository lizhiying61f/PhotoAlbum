package com.zhiyingli.photoalbum;

import android.app.Application;

/**
 * Created by zhiyingli on 2016/11/21.
 */

public class AppUtils {
    private static Application mApplication;
    private AppUtils(){}

    public static void init(Application application){
        mApplication = application;
    }

    public static Application getApplication(){
        return mApplication;
    }
}
