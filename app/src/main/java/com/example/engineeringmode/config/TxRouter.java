package com.example.engineeringmode.config;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.engineeringmode.bean.ManualBean;

/**
 * @author ljt
 * Date: 4/25/21
 * Time: 10:39 AM
 * Description: 阿里路由 地址管理，层级不少于/xxx/xxx
 * https://zhuanlan.zhihu.com/p/361025253
 */
public class TxRouter {
    public static final String TAG = "TxRouter";
    public static final String MainActivity = "/app/MainActivity";
    public static final String MainActivity2 = "/app/MainActivity2";
    public static final String MainActivity3 = "/app/MainActivity3";


    public static void openMainActivity2() {
        ARouter.getInstance().build(TxRouter.MainActivity2).navigation();
    }

    public static void openMainActivity3(Activity context) {
        ARouter.getInstance()
                .build(MainActivity3)
//                .withBoolean("one", true)
                .withString("two", "from mainActivity")
//                .withParcelable("three", new ManualBean("jt",98))
                .withInt("four", 4)
                .navigation(context);
    }

}
