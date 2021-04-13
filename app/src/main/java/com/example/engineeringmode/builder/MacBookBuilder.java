package com.example.engineeringmode.builder;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 10:14 AM
 * Description:
 */
public class MacBookBuilder extends Builder{


    private  Computer computer =  new Macbook();

    @Override
    public void buildBoard(String board) {

        computer.setmBoard(board);
    }

    @Override
    public void buildDisplay(String display) {

        computer.setmDisplay(display);
    }

    @Override
    public void buildOs() {

        computer.mOs = "11.  11";
    }

    @Override
    public Computer create() {
        return computer;
    }
}
