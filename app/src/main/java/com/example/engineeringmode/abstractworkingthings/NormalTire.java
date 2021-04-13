package com.example.engineeringmode.abstractworkingthings;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 10:34 AM
 * Description:
 */
public class NormalTire implements ITire {
    @Override
    public void tire() {
        System.out.println("普通轮胎");
    }
}
