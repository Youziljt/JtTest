package com.example.engineeringmode;

/**
 * @anthor ljt
 * Date: 3/2/21
 * Time: 10:32 AM
 * Description:
 */
class Test {

    public static void main(String[] args) {
        String d = "114.465302,40.004717";
        String[] split = d.split(",");

        String s = split[0];
        String s1 = split[1];

        System.out.println(s);
        System.out.println(s1);
    }
}
