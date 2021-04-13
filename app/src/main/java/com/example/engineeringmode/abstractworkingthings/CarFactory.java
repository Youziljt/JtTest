package com.example.engineeringmode.abstractworkingthings;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 10:30 AM
 * Description:
 */
public abstract class CarFactory {

    //轮胎
    public abstract ITire createTire();


    //发动机
    public abstract IEngine createEngine();


    //制作系统
    public abstract IBrake createBrake();


}
