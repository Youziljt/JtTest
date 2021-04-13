package com.example.engineeringmode.abstractworkingthings;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 10:43 AM
 * Description:
 */
class Client {

    public static void main(String[] args) {
        Q3Factory q3Factory = new Q3Factory();
        q3Factory.createBrake();
        q3Factory.createEngine();
        q3Factory.createTire();
        System.out.println("----------------");
    }
}
