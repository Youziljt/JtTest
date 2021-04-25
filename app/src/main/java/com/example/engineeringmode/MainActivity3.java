package com.example.engineeringmode;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.engineeringmode.base.BaseActivity;
import com.example.engineeringmode.bean.ManualBean;
import com.example.engineeringmode.config.TxRouter;

@Route(path = TxRouter.MainActivity3)
public class MainActivity3 extends BaseActivity {

    @Autowired(name = "is")
    Boolean one;

    @Autowired(name = "two")
    String two;

    @Autowired(name = "three")
    ManualBean manualBean;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv = findViewById(R.id.tv_text);
//        if (one) {
//            tv.setText("数据为：" + two + " ManualBean" + "名字：" + manualBean.name + "年龄" + manualBean.age);
//        }
    }
}