package com.example.engineeringmode.kotlinactivity.other;

/**
 * @author ljt
 * Date: 4/21/21
 * Time: 5:07 PM
 * Description: 协程介绍
 */
public class CoroutineAbout {
    /**
     * 协程是一种并发设计模式，可以在Android平台上使用他来简化异步执行的代码。以同步的方式去编写异步执行的代码，协程依赖于线程，但是协程挂起时不需要阻塞
     * 线程，几乎是无代价的。协程的创建是通过CoroutineScope 创建，协程的启动方式有三种：
     *
     * 1，runBlocking：T 启动一个新的协程并阻塞调用它的线程，直到里面的代码执行完毕，返回类型是泛型T，就是你协程体中最后一行是什么类型，最终返回的是什么类型
     * T就是什么类型。
     * 2，launch：Job 启动一个协程但不会阻塞调用线程，必须在协程作用域（CoroutineScope）中才能调用，返回值是一个Job。
     * 3，async:Deferred<T> 启动一个协程但不会阻塞调用线程，必须在协程作用域（CoroutineScope）中才能调用，以Deferred 对象的形式返回协程任务。返回值泛
     * 型T 同runBlocking 类似，都是协程体的最后一行的类型。
     *
     * 😊 什么是Job 、Deferred 、协程作用域
     * Job我们可以认为他就是一个协程作业是通过CoroutineScope.launch生成的，同时它运行一个指定的代码块，并在该代码块完成时完成。我们可以通过isActive、
     * isCompleted、isCancelled来获取到Job的当前状态。
     *   Deferred继承自Job，我们可以把它看做一个带有返回值的Job，
     *
     *
     *   https://juejin.cn/post/6953441828100112392
     *
     */









}
