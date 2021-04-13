package com.example.engineeringmode.builder;

import com.example.engineeringmode.others.ComputerTwo;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 10:22 AM
 * Description:
 */
public class Test {

    public static void main(String[] args) {

        //one-------
        MacBookBuilder macBookBuilder = new MacBookBuilder();

        Director director = new Director(macBookBuilder);
        director.construct("英特尔主板", "Retina显示器");

        System.out.println("Computer Info:" + macBookBuilder.create().toString());


        //two-------
        ComputerTwo builder = new ComputerTwo.Builder("因特尔", "三星")
                .setDisplay("三星24寸")
                .setKeyboard("罗技")
                .setDisplay("2")
                .build();

        //to others
        /**
         * 产生的原因：
         * 1。第一种主要是阅读及使用不方便，可以想像，在进行一个类的构造参数时候，首先需要决定使用哪一个，
         * 然后里面很多参数，有需要弄清楚其中的含义，很容易混传。
         * 2。构建过程中对象的状态容易发生变化，造成错误。因为那个类中的属性是分布设置的。
         *
         */

    }
}
