package com.example.engineeringmode.javakonw;

import java.lang.reflect.GenericDeclaration;
import java.security.PublicKey;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 10:48 AM
 * Description:  https://blog.csdn.net/zhao_miao/article/details/83245816
 */
public class TestDog {

    private String name = "test";

//    public Inner inner;

    public static int age = 20;

    //成员内部类：

    class Inner {

//        成员内部类内部不允许存在任何static变量或者 方法
//        public  static int num = 10;

//        public static void test(){
//
//        }

        public void fun() {
            System.out.println(name);
        }

    }

    //成员方法不允许有任何静态属性

    public void testFun() {
        int a = 1;
//          static int b = 2;
    }

    //--------------
    //静态内部类--------创建不需要依赖外部类
    //不可以使用人任何外部类的非静态方法。

    static class Inner2 {
        public static int num = 2;
        public  int num2 = 3;
        //        name = 3； xxxxxxx
        public void fun() {
            System.out.println(age + "");
        }
    }

    //----------
    //方法内部类
    //定义在方法中的类
    //不允许使用任何带有修饰符的变量

    public void fun4() {
//        private int num =5;
//        public int num =5;

        int c = 6 ;

        class Inner {
            public int a = 3;

        }
    }

    //匿名内部类
    //与方法内部类完全一致，但是还有自己的特点：
    //1 必须继承一个抽象类或者实现一个接口：
    // 2匿名内部类没有类名，因此没有构造方法。


    public void fun5() {

        int l = 3;
//        public int lp = 3;

        class text implements MyInterface {

            int k = 3;

            @Override
            public void test() {

            }
        }

        class text2 extends MyAbstract {

            @Override
            void fun3() {

            }
        }

//        abstract class text3 extends MyAbstract{
//        }
    }


}
