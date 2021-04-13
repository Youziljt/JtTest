package com.example.engineeringmode.others.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 5:28 PM
 * Description:  https://zhuanlan.zhihu.com/p/143484740
 * 线程池：
 */
public class TimerTaskThread {


    /**
     * 核心线程：长期存活，闲置也不会被销毁
     * 普通线程：具有寿命，闲置时间超过寿命就会被销毁。
     *
     * ThreadPoolExecutor
     * 核心数：corePoolSize 线程池中核心线程的数量
     * maximumPoolSize 最大容量 线程池最大允许保留多少线程
     * keepAliveTime 线程池中普通线程的存活时间
     *
     */

    public void createThreadPool(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //todo ...

            }
        });

    }
    //单线程线程池
    public static ExecutorService newSingleThreadExecutor(){
        return new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
    }
    //核心线程数为1，最大线程数为1，也就是说这个线程池数固定为1
    //使用场景： 当多个任务都需要访问一个资源的时候。

    //固定容量线程池
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
    //核心线程数为n，最大线程数为n，
    //使用场景：明确同时执行任务数量时候

    //缓存线程池
    public static ExecutorService newCachedThreadPool(){
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }
    //核心线程数为0，最大线程数无上限，线程超时时间为60s，
    //使用场景：处理大量耗时较短的任务


    //定时线程
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }
    //核心线程数自定，最大线程数无上限，
    //使用场景：处理延时任务。

    /**
     * ThreadPoolExecutor有两个方法可以供我们执行，分别是submit()和execute()。
     * 通常情况下，在不需要线程执行返回结果值时，我们使用execute 方法。 而当我们需要返回值时，则使用submit方法，他会返回一个Future对象。
     * Future不仅仅可以获得一个结果，他还可以被取消，我们可以通过调用future的cancel（）方法，取消一个Future的执行。 比如我们加入了一个线程，但是在这过程中我们又想中断它，则可通过sumbit 来实现。
     *
     * ThreadPoolExecutor执行任务时大致遵循如下流程
     * 1，如果线程池中的线程数未达到核心线程数，就会立马启用一个核心线程去执行。
     * 2，如果线程池中的线程数已经达到了核心线程数，且任务队列workQueue未满，则将新线程放入workQueue中等待。
     * 3，如果线程池中的线程数已经达到核心线程数但未超过线程池规定的最大值，且workQueue已满，则会开启一个非核心线程来执行任务。
     * 4，如果线程池中的线程数已经超过了线程池规定的最大值，则拒绝执行该任务，采取饱和策略，并抛出RejectedExecutionException异常。
     *
     */




}
