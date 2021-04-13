package com.example.engineeringmode.builder;

import android.app.Dialog;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 10:18 AM
 * Description: 负责构建computer
 */
public class Director {

    Builder mBuilder = null;


    public Director(Builder builder) {
        mBuilder = builder;
    }

    public void construct(String board, String display) {

        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.buildOs();
        mBuilder.create();
    }
}
