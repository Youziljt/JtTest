package com.example.engineeringmode.others.testsuper;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 4:24 PM
 * Description:
 */
public class SuperBase {

    //2
    String message = "父类";

    //1
    public SuperBase(String message) {
        this.message = message;
    }

    public SuperBase() {
    }

    //3
    public void printMessage() {
        System.out.println(message);
    }
}
