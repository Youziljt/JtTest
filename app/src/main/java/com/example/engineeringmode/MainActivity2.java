package com.example.engineeringmode;


import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.engineeringmode.base.BaseActivity;
import com.example.engineeringmode.config.TxRouter;


@Route(path = TxRouter.MainActivity2)
public class MainActivity2 extends BaseActivity {

    /**
     * https://www.jianshu.com/p/6d855e984b99
     * 测试下ANR：
     * InputDispatching Timeout：5秒内无法响应屏幕触摸事件或键盘输入事件
     * BroadcastQueue Timeout ：在执行前台广播（BroadcastReceiver）的onReceive()函数时10秒没有处理完成，后台为60秒。
     * Service Timeout ：前台服务20秒内，后台服务在200秒内没有执行完毕。
     * ContentProvider Timeout ：ContentProvider的publish在10s内没进行完。
     * <p>
     * https://www.jianshu.com/p/c44c8ca37ad5
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
}