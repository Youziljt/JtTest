package com.example.engineeringmode.kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.engineeringmode.R
import kotlinx.coroutines.*

/**
 * @author ljt
 * Date: 4/22/21
 * Time: 9:11 AM
 * Description: 描述协程相关～
 * https://juejin.cn/post/6953441828100112392
 */
class KotlinActivity : AppCompatActivity() {

    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            start()
        }
    }

    /**
     *  D/runBlocking: 启动一个协程
        D/runBlocking: 32
        D/launch: 启动一个协程
        D/launch: StandaloneCoroutine{Completed}@5f27549
        D/async: DeferredCoroutine{Active}@76e5e4e
        D/async: 启动一个协程
     */

    /**
     * 结果可行参数解释：
     *
     * runBlocking 会阻塞当前线程，直到自身结束，才会继续往下执行。
     *
     * 后两个不会阻塞当前线程，但是launchJob 始终在asyncJob 前面。并发设计模式，所以其日志输出是无序的。
     */

    private fun start(){

        val runBlockingJob = runBlocking {
            Log.d("runBlocking", "启动一个协程")
        }
        Log.d("runBlocking", "$runBlockingJob")

        val launchJob = GlobalScope.launch{
            Log.d("launch", "启动一个协程")
        }
        Log.d("launch", "$launchJob")

        val async = GlobalScope.async {
            Log.d("async", "启动一个协程")
        }
        Log.d("async", "$async")
    }

    /***
     *      现在我们看到asyncJob.await也是输出我们之前定义好的返回值，
     *      同时DeferredCoroutine的状态变成了{Completed}，这是因为await()是在不阻塞线程的情况下等待该值的完成并继续执行,
     *      当deferred计算完成后返回结果值，或者如果deferred被取消，则抛出相应的异常CancellationException。
     *      但是又因为await()是挂起函数，他会挂起调用他的协程。所以我们看到的DeferredCoroutine的状态是{Completed}，
     *      同时输出的await日志也是在最后面。
     */
    private fun startNext(){
        GlobalScope.launch{
            val launchJob = launch{
                Log.d("launch", "启动一个协程")
            }
            Log.d("launchJob", "$launchJob")
            val asyncJob = async{
                Log.d("async", "启动一个协程")
                "我是async返回值" //默认返回值
            }
            Log.d("asyncJob.await", ":${asyncJob.await()}")  //public suspend fun await(): T （suspend挂起函数）
            Log.d("asyncJob", "$asyncJob")  //Job 的状态
        }
    }

    //同步执行
    private  fun startSynchronous(){
        GlobalScope.launch(Dispatchers.Main) {
            for (index in 1 until  10) {
                //同步执行
                launch {
                    Log.d("launch$index", "启动一个协程")
                }
            }
        }
    }

    //并发执行
    private fun startConcurrent() {
        GlobalScope.launch {
            for (index in 1 until  10) {
                //并发执行
                launch {
                    Log.d("launch$index", "启动一个协程")
                }
            }
        }
    }

    //===========================知识清单=================================
    /**
     *  https://juejin.cn/post/6953287252373930021
    协程调度器CoroutineDispatcher
    协程下上文CoroutineContext作用
    协程启动模式CoroutineStart
    协程作用域CoroutineScope
    挂起函数以及suspend关键字的作用
     */

    //============================协程调度器CoroutineDispatcher===============================
    //调度器它确定了相关的协程在哪个线程或哪些线程上执行。协程调度器可以将协程限制在一个特定的线程执行，或将它分派到一个线程池，亦或是让它不受限地运行。
    /**
     * 官方预制 4个 参考Dispatchers.kt
     * Default：默认调度器，CPU密集型任务调度器，适合处理后台计算。通常处理一些单纯的计算任务，或者执行时间较短任务。比如：Json的解析，数据计算等。
       IO：IO调度器，，IO密集型任务调度器，适合执行IO相关操作。比如：网络处理，数据库操作，文件操作等。 （后台数据上传）
       Main：UI调度器， 即在主线程上执行，通常用于UI交互，刷新等。 （界面刷新）
       Unconfined：非受限调度器，又或者称为“无所谓”调度器，不要求协程执行在特定线程上。
     */
    //问题索引？ 现在我们需要通过网络请求获取到数据的时候填充到我们的布局当中，但是网络处理在IO线程上，而刷新UI是在主线程上，那我们应该怎么办。
    //官方为我们提供了一个withContext顶级函数，同时还会携带一个泛型T
    private fun switchThreads(){
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                //网络请求...
                "请求结果"
            }
            btn.text = result
        }
    }

    //============================协程下上文CoroutineContext作用===============================

    private fun testCoroutineContext(){
        val coroutineContext1 = Job() + CoroutineName("这是第一个上下文")
        Log.d("coroutineContext1", "$coroutineContext1")
        val  coroutineContext2 = coroutineContext1 + Dispatchers.Default + CoroutineName("这是第二个上下文")
        Log.d("coroutineContext2", "$coroutineContext2")
        val coroutineContext3 = coroutineContext2 + Dispatchers.Main + CoroutineName("这是第三个上下文")
        Log.d("coroutineContext3", "$coroutineContext3")
    }

//    D/coroutineContext1: [JobImpl{Active}@21a6a21, CoroutineName(这是第一个上下文)]
//    D/coroutineContext2: [JobImpl{Active}@21a6a21, CoroutineName(这是第二个上下文), Dispatchers.Default]
//    D/coroutineContext3: [JobImpl{Active}@21a6a21, CoroutineName(这是第三个上下文), Dispatchers.Main]
    //因为+运算符是不对称的，所以在使用的时候，要注意顺序。



    //============================  协程启动模式CoroutineStart===============================
//    CoroutineStart协程启动模式，是启动协程时需要传入的第二个参数。协程启动有4种：
//    DEFAULT    默认启动模式，我们可以称之为饿汉启动模式，因为协程创建后立即开始调度，虽然是立即调度，单不是立即执行，有可能在执行前被取消。
//    LAZY    懒汉启动模式，启动后并不会有任何调度行为，直到我们需要它执行的时候才会产生调度。也就是说只有我们主动的调用Job的start、join或者await等函数时才会开始调度。
//    ATOMIC  一样也是在协程创建后立即开始调度，但是它和DEFAULT模式有一点不一样，通过ATOMIC模式启动的协程执行到第一个挂起点之前是不响应cancel 取消操作的，ATOMIC一定要涉及到协程挂起后cancel 取消操作的时候才有意义。
//    UNDISPATCHED 协程在这种模式下会直接开始在当前线程下执行，直到运行到第一个挂起点。这听起来有点像 ATOMIC，不同之处在于UNDISPATCHED是不经过任何调度器就开始执行的。当然遇到挂起点之后的执行，将取决于挂起点本身的逻辑和协程上下文中的调度器。

    private fun testCoroutineStart(){
        val defaultJob = GlobalScope.launch{
            Log.d("defaultJob", "CoroutineStart.DEFAULT")
        }
        defaultJob.cancel()
        val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY){
            Log.d("lazyJob", "CoroutineStart.LAZY")
        }
        val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC){
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起前")
            delay(100)
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起后")
        }
        atomicJob.cancel()
        val undispatchedJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED){
            Log.d("undispatchedJob", "CoroutineStart.UNDISPATCHED挂起前")
            delay(100)
            Log.d("atomicJob", "CoroutineStart.UNDISPATCHED挂起后")
        }
        undispatchedJob.cancel()
    }

//    D/defaultJob: CoroutineStart.DEFAULT
//    D/atomicJob: CoroutineStart.ATOMIC挂起前
//    D/undispatchedJob: CoroutineStart.UNDISPATCHED挂起前
//      或者
//    D/undispatchedJob: CoroutineStart.UNDISPATCHED挂起前
//    D/atomicJob: CoroutineStart.ATOMIC挂起前


    private fun testUnDispatched(){
        GlobalScope.launch(Dispatchers.Main){
            val job = launch(Dispatchers.IO) {
                Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
                delay(100)
                Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
            }
            Log.d("${Thread.currentThread().name}线程", "-> join前")
            job.join()
            Log.d("${Thread.currentThread().name}线程", "-> join后")
        }
    }

//    D/main线程: -> join前
//    D/DefaultDispatcher-worker-1线程: -> 挂起前
//    D/DefaultDispatcher-worker-1线程: -> 挂起后
//    D/main线程: -> join后

    private fun testUnDispatched2(){
        GlobalScope.launch(Dispatchers.Main){
            val job = launch(Dispatchers.IO,start = CoroutineStart.UNDISPATCHED) {
                Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
                delay(100)
                Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
            }
            Log.d("${Thread.currentThread().name}线程", "-> join前")
            job.join()
            Log.d("${Thread.currentThread().name}线程", "-> join后")
        }
    }

//    D/main线程: -> 挂起前
//    D/main线程: -> join前
//    D/DefaultDispatcher-worker-1线程: -> 挂起后
//    D/main线程: -> join后

    //注意理解
//    这是因为当以UNDISPATCHED启动时,协程在这种模式下会直接开始在当前线程下执行，直到第一个挂起点。遇到挂起点之后的执行，将取决于挂起点本身的逻辑和协程上下文中的调度器，即join处恢复执行时，因为所在的协程有调度器，所以后面的执行将会在调度器对应的线程上执行。


    //把子协程在launch的时候使用UNDISPATCHED模式启动，去掉Dispatchers.IO调度器，
    private fun testUnDispatched3(){
        GlobalScope.launch(Dispatchers.Main){
            val job = launch(start = CoroutineStart.UNDISPATCHED) {
                Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
                delay(100)
                Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
            }
            Log.d("${Thread.currentThread().name}线程", "-> join前")
            job.join()
            Log.d("${Thread.currentThread().name}线程", "-> join后")
        }
    }
//    D/main线程: -> 挂起前
//    D/main线程: -> join前
//    D/main线程: -> 挂起后
//    D/main线程: -> join后


    //总结如下：
//    无论我们是否指定协程调度器，挂起前的执行都是在当前线程下执行。
//    如果所在的协程没有指定调度器，那么就会在join处恢复执行的线程里执行，即我们上述案例中的挂起后的执行是在main线程中执行。
//    当我们指定了协程调度器时，遇到挂起点之后的执行将取决于挂起点本身的逻辑和协程上下文中的调度器。即join处恢复执行时，因为所在的协程有调度器，所以后面的执行将会在调度器对应的线程上执行。




    //============================  协程作用域===============================
    /***
     * 协程作用域CoroutineScope为协程定义作用范围，每个协程生成器launch、async等都是CoroutineScope的扩展，
     * 并继承了它的coroutineContext自动传播其所有Element和取消。协程作用域本质是一个接口,不建议手工实现该接口，
     * 而应该首选委托实现。下面我们列出了部分CoroutineScope相关定义：
     */

    private fun  testCoroutineScope(){
        GlobalScope.launch(Dispatchers.Main){
            Log.d("父协程上下文", "$coroutineContext")
            launch(CoroutineName("第一个子协程")) {
                Log.d("第一个子协程上下文", "$coroutineContext")
            }
            launch(Dispatchers.Unconfined) {
                Log.d("第二个子协程协程上下文", "$coroutineContext")
            }
        }
    }

//    D/父协程上下文: [StandaloneCoroutine{Active}@81b6e46, Dispatchers.Main]
//    D/第二个子协程协程上下文: [StandaloneCoroutine{Active}@f6b7807, Dispatchers.Unconfined]
//    D/第一个子协程上下文: [CoroutineName(第一个子协程), StandaloneCoroutine{Active}@bbe6d34, Dispatchers.Main]
//    复制代码

    //可以看到第一个子协程的覆盖了父协程的Job,但是它继承了父协程的调度器 Dispatchers.Main，同时也新增了一个CoroutineName。
    //第二个子协程覆盖了父协程的Job，也将父协程的调度器覆盖为Unconfined，但是他没有继承第一个子协程的CoroutineName，这就是我们说的覆盖的效果仅限自身范围内有效。

    private fun testCoroutineScope3() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
        }
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            supervisorScope {
                Log.d("scope", "--------- 1")
                launch(CoroutineName("scope2") + exceptionHandler) {
                    Log.d("scope", "--------- 2")
                    throw  NullPointerException("空指针")
                    Log.d("scope", "--------- 3")
                    val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                        Log.d("scope", "--------- 4")
                        delay(2000)
                        Log.d("scope", "--------- 5")
                    }
                    scope3.join()
                }
                val scope4 = launch(CoroutineName("scope4") + exceptionHandler) {
                    Log.d("scope", "--------- 6")
                    delay(2000)
                    Log.d("scope", "--------- 7")
                }
                scope4.join()
                Log.d("scope", "--------- 8")
            }
        }
    }

//    D/scope: --------- 1
//    D/scope: --------- 2
//    D/exceptionHandler: CoroutineName(scope2) java.lang.NullPointerException: 空指针
//    D/scope: --------- 6
//    D/scope: --------- 7
//    D/scope: --------- 8

    //supervisorScope 子协程抛出的异常，会由子协程内部执行。不会抛给父协程。





    //============================  挂起函数===============================

    //使用suspend关键字修饰的函数叫作挂起函数，挂起函数只能在协程体内，或着在其他挂起函数内调用。那挂起又是啥玩意呢





    //



}