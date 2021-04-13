package com.example.engineeringmode.builder;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 10:02 AM
 * Description: 计算机抽象类，相当于 product 角色
 */
public abstract class Computer {

    protected String mBoard;//cpu核心数

    protected String mDisplay;//内存

    protected String mOs;//操作系统

    public void setmBoard(String mBoard) {
        mBoard = mBoard;
    }

    public void setmDisplay(String mDisplay) {
        mDisplay = mDisplay;
    }

    public void setmOs() {

    }

//    @Override
//    public String toString() {
//        return "Computer{" +
//                "mBoard='" + mBoard + '\'' +
//                ", mDisplay='" + mDisplay + '\'' +
//                ", mOs='" + mOs + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOs='" + mOs + '\'' +
                '}';
    }
}
