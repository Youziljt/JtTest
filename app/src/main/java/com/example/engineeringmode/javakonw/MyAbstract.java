package com.example.engineeringmode.javakonw;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 2:04 PM
 * Description:
 */
public abstract class MyAbstract {

    /**
     * 抽象类必须使用abstract修饰符来修饰，抽象方法也必须使用abstract修饰符来修饰，抽象方法不能有方法体。
     *
     * 抽象类不能被实例化，无法使用new关键字来调用抽象类的构造器创建抽象类的实例。即使抽象类不包含抽象方法，这个抽象类也不能创建实例。
     *
     * 抽象类可以包含成员变量、方法（普通方法和抽象方法都可以）、构造器、初始化块、内部类（接口、枚举）5种成分。抽象类的构造器不能用于创建实例，主要用于被其子类调用。
     * 含有抽象方法的类（包括直接定义了一个抽象方法；或继承了一个抽象父类，但没有完全实现父类包含的抽象方法；或实现了一个接口，但没有完全实现接口包含的抽象方法三种情况）只能被定义成抽象类。
     */

    int a = 1;
    int b = 2;

    void fun1() {
        int c = a + b;
    }

    public void MyAbstract(){

    }

    //抽象类中的抽象方法
    //子类必须实现-----除非子类是abstract
    //
    abstract void fun3();

}
