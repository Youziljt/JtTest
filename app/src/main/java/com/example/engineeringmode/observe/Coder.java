package com.example.engineeringmode.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * @anthor ljt
 * Date: 2/25/21
 * Time: 9:41 AM
 * Description: 创建具体的观察者 需要继承Observer接口
 */
public class Coder implements Observer {

    public String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object o) {

        System.out.println(name+"：内容跟新了。");
    }

    @Override
    public String toString() {
        return "Coder{" +
                "name='" + name + '\'' +
                '}';
    }
}
