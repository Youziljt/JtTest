package com.example.engineeringmode.threadtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 10:12 AM
 * Description:
 */
public class HandlerActivityTest extends AppCompatActivity {


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    //refresh ui
                    break;
                case 3:
                    break;
            }
        }

    };
    ;


    /**
     *
     * 线程Thread /循环器Looper  /  handler
     * 一个 Thread 只能绑定一个 Looper ，但可以有多个 handler
     * 一个looper 可绑定多个 handler
     * 一个handler只可以绑定一个looper
     *
     */


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //更新ui
//                      mTextView.setText("执行了线程1的UI操作");

                    }
                });

            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message obtain = Message.obtain();
                obtain.what= 1;
                obtain.obj = "ss";
                handler.sendMessage(obtain);

            }
        }.start();
    }
}
