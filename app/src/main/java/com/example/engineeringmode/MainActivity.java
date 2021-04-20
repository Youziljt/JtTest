package com.example.engineeringmode;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.engineeringmode.widget.DrawCubicView;
import com.example.engineeringmode.widget.MyView;
import com.example.engineeringmode.widget.givelike.PeriscopeLayout;
import com.example.engineeringmode.widget.jt.TestView;

public class MainActivity extends AppCompatActivity {

    /**
     * 自定义view 相关
     */
    private PeriscopeLayout viewById;
    private DrawCubicView cubicView;
    private TestView test_view;
    private ImageView iv_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewById = findViewById(R.id.heart_layout);
        cubicView = findViewById(R.id.draw_cubic_view);
        test_view = findViewById(R.id.tv_test_view);
        iv_move = findViewById(R.id.iv_move);



        findViewById(R.id.member_send_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewById.addHeart();
            }
        });

        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cubicView.moveLeft();
            }
        });

        findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cubicView.moveRight();
            }
        });

        findViewById(R.id.tv_test_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test_view.addHeart();
            }
        });

        final float curTranslationX = iv_move.getTranslationX();

        findViewById(R.id.tv_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(iv_move, "translationX", curTranslationX, 500f, curTranslationX);
                alpha.setDuration(5000);
                alpha.start();            }
        });

    }
}