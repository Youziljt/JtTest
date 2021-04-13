package com.example.engineeringmode.javakonw;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 2:01 PM
 * Description:
 */
interface MyInterface {

    /**
     *
     接口里可以包含成员变量（只能是静态常量）、方法（只能是抽象实例方法、类方法（static修饰的，通过类名.方法名调用）或 默认方法 default）、内部类（包括内部接口、枚举）定义。
     接口里的所有成员都是public访问权限。
     接口里定义的所有成员变量系统都默认使用public static final来修饰。
     接口里的普通方法必须是抽象方法（隐式声明为public abstract），即没有方法实现；但类方法（static）、默认方法（default）必须要由实现。
     */

    public static final String s = "sssssssssssss";

    void test();




}
