package com.example.engineeringmode.fanshe;

/**
 * @anthor ljt
 * Date: 2/25/21
 * Time: 2:34 PM
 * Description: java 反射获取 获取Class对象。
 */
class FanSheTest {


    public static void main(String[] args) {

        //直接通过一个class静态变量class获取
        Class cls =  String.class;


        //如果我们有一个实例变量，可以通过该实例方法getClass()获取
        String s = "sssss";
//        Class<? extends String> aClass = s.getClass();
        Class clsTwo = s.getClass();

        clsTwo.getSimpleName();
        clsTwo.getDeclaredFields();
        try {
            clsTwo.getDeclaredMethod("",String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        //如果知道完整类名，可以使用Class.forName（）方法获取：
        try {
            Class clsThree = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        new Integer(1);

    }
}
