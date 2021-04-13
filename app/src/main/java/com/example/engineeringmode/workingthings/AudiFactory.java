package com.example.engineeringmode.workingthings;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 9:58 AM
 * Description:
 */
public abstract class AudiFactory {

    //创建方法根据类名 获取实例

    public abstract <T extends AudiCar> T createAudiCar(Class<T> clz);
}
