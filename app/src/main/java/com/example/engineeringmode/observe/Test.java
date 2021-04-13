package com.example.engineeringmode.observe;

/**
 * @anthor ljt
 * Date: 2/25/21
 * Time: 9:48 AM
 * Description:
 */
public class Test {

    public static void main(String[] args) {

        DevTechFrontier devTechFrontier = new DevTechFrontier();

        Coder xiaoming = new Coder("xiaoming");
        Coder xiaohong = new Coder("xiaohong");


        devTechFrontier.addObserver(xiaoming);
        devTechFrontier.addObserver(xiaohong);

        devTechFrontier.postNewPublication("改变就是现在。");

    }

}
