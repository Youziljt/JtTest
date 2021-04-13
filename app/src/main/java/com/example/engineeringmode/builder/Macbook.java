package com.example.engineeringmode.builder;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 10:05 AM
 * Description: 具体的computer类
 */
public class Macbook extends Computer {

    @Override
    public void setmOs() {
    }

    @Override
    public void setmBoard(String board) {
        mBoard = board;
    }

    @Override
    public void setmDisplay(String display) {
        mDisplay = display;
    }
}
