package com.example.engineeringmode.observe;

import java.util.Observable;

/**
 * @anthor ljt
 * Date: 2/25/21
 * Time: 9:46 AM
 * Description: 被观察者。继承Observable类
 */
public class DevTechFrontier extends Observable {

    public void postNewPublication(String con){

        setChanged();

        notifyObservers(con);

    }

}
