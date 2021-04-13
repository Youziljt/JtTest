package com.example.engineeringmode.abstractworkingthings;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 10:38 AM
 * Description:
 */
class NormalBrake implements IBrake {
    @Override
    public void brake() {
        System.out.println("普通制作系统");
    }
}
