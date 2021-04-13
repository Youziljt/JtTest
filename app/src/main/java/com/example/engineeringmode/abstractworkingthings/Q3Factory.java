package com.example.engineeringmode.abstractworkingthings;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 10:42 AM
 * Description:
 */
class Q3Factory extends CarFactory {
    @Override
    public ITire createTire() {
        return new NormalTire();
    }

    @Override
    public IEngine createEngine() {
        return new SUVEngine();
    }

    @Override
    public IBrake createBrake() {
        return new NormalBrake();
    }
}
