package com.example.engineeringmode;

import android.app.Application;
import android.os.StrictMode;

/**
 * @author ljt
 * Date: 3/29/21
 * Time: 3:09 PM
 * Description:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
//        if (DEVELOPER_MODE) {
        /**
         * 严格模式。ThreadPolicy Builder penaltyLog（）您可以在使用应用程序查看冲突发生时观察adb logcat的输出
         * 如果发现有问题的违规行为，可以使用多种工具来帮助解决：线程、处理程序
         * AsyncTask、IntentService等。但不要觉得必须修复StrictMode发现的所有问题—特别是，在正常的活动生命周期中，
         * 许多磁盘访问情况往往是必需的。使用StrictMode查找您意外完成的操作。不过，UI线程上的网络请求几乎总是一个问题
         */
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
//        }
        super.onCreate();
    }
}
