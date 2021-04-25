package com.example.engineeringmode.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author ljt
 * Date: 4/25/21
 * Time: 10:48 AM
 * Description:
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ARouter.getInstance().destroy();
    }
}
