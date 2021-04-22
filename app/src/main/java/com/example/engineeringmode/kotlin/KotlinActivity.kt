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

    //=============================================================
    /**
     *  知识清单：
    协程调度器CoroutineDispatcher
    协程下上文CoroutineContext作用
    协程启动模式CoroutineStart
    协程作用域CoroutineScope
    挂起函数以及suspend关键字的作用
     */









}