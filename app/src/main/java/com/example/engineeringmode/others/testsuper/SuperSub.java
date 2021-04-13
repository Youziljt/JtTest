package com.example.engineeringmode.others.testsuper;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 4:25 PM
 * Description:
 */
public class SuperSub extends SuperBase {
    String message = "子类";

    public SuperSub(String message) {
        //1）super 关键字可用于访问父类的构造方法
        super(message);
    }

    public SuperSub() {
        //3）当方法发生重写时，super 关键字可以访问父类的同名方法
        super.printMessage();
        printMessage();
    }

    public void getParentMessage() {
        //2）super 关键字可以访问父类的变量
        System.out.println(super.message);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
