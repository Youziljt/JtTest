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


    @Autowired(name = "two")
    String two;

    @Autowired(name = "four")
    int four;

//    @Autowired(name = "one")
//    Boolean one;

//    @Autowired(name = "three")
//    ManualBean manualBean;

    private TextView tv;
    private TextView tv_text;
    private TextView tv_text1;
    private TextView tv_text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv = findViewById(R.id.tv);
        tv_text = findViewById(R.id.tv_text);
        tv_text1 = findViewById(R.id.tv_text1);
        tv_text2 = findViewById(R.id.tv_text2);

        tv.setText(two);
        tv_text.setText("four：" + four);
//        tv_text1.setText("布尔值：" + one);
//        if (manualBean != null) {
//            tv_text2.setText("数据为：" + " ManualBean" + "名字：" + manualBean.name + "年龄" + manualBean.age);
//        }


    }
}