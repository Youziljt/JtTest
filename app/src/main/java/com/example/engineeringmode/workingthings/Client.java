package com.example.engineeringmode.workingthings;

import android.graphics.BitmapFactory;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 10:09 AM
 * Description:
 */
public class Client {

    public static void main(String[] args) {

        AudiCarFactory audiCarFactory = new AudiCarFactory();

        audiCarFactory.createAudiCar(AudiQ3.class).drive();

//        BitmapFactory.decodeResource()

    }
}
