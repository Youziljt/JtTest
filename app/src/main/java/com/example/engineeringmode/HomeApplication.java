package com.example.engineeringmode;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author ljt
 * Date: 4/25/21
 * Time: 10:33 AM
 * Description:
 */
public class HomeApplication extends Application {

    //ARouter 调试开关
    private boolean isDebugARouter = false;

    @Override
    public void onCreate() {
        super.onCreate();


        if (isDebugARouter){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(HomeApplication.this);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
