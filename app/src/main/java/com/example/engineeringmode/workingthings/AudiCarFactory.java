package com.example.engineeringmode.workingthings;

/**
 * @anthor ljt
 * Date: 2/20/21
 * Time: 10:02 AM
 * Description:
 */
public class AudiCarFactory extends AudiFactory{


    @Override
    public <T extends AudiCar> T createAudiCar(Class<T> clz) {

        AudiCar car = null;

        try {
            car = (AudiCar) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T)car;
    }


}
