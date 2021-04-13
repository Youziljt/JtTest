package com.example.engineeringmode.threadtest;

/**
 * @anthor ljt
 * Date: 2/22/21
 * Time: 9:24 AM
 * Description:
 *
 * 继承Thread类，复杂代码多，不可以单继承。
 * https://blog.csdn.net/qq_41648631/article/details/103045252
 *
 */
public class ThreadDemo {

    public static void main(String[] args) {

        ThreadTestOne threadTestOne = new ThreadTestOne();



    }


    static class ThreadTestOne extends Thread{

        @Override
        public void run() {
            super.run();
            for (int i = 0; i <60 ; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }

        }
    }

    public void test(){

//        MyThread myThread = new MyThread();
//        myThread.start();


        //一般这样写。。。。  匿名类
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//
//            }
//        }.start();

//        MyThread myThread = new MyThread();
//        Thread thread = new Thread(myThread,"sss");
//        thread.start();

    }

    class MyThread1 extends Thread{

        @Override
        public void run() {
            super.run();
            //

        }
    }


    private class MyThread implements Runnable{

        @Override
        public void run() {

//            try {
//                MyThread1.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }

}
