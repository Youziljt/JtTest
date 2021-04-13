package com.example.engineeringmode.javakonw;

/**
 * @anthor ljt
 * Date: 3/1/21
 * Time: 9:57 AM
 * Description:
 */
class TestDogParent {

    public static void main(String[] args) {

        //创建成员内部类： new 类 。 new 类名。

        TestDog testDog = new TestDog();

        TestDog.Inner inner = testDog.new Inner();

        inner.fun();

        int leiStatic = TestDog.age;


        //静态内部类：

        TestDog.Inner2 inner2 = new TestDog.Inner2();
        int f = inner2.num2;

        //饮用静态内部类： 需要直接类名.类名.变量名
        int fh = TestDog.Inner2.num;

        System.out.println(inner2.num2+"");


        //方法内部类：
        testDog.fun4();




    }
}
