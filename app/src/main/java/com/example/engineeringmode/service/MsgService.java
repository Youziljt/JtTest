package com.example.engineeringmode.service;

import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

/**
 * @author ljt
 * Date: 3/1/21
 * Time: 9:45 AM
 * Description:
 */
public class MsgService {


//    new ServiceConnection
    //一个Activity 是一个单独的类： 都是从Activity基类中继承来的，是页面的窗口。
    //Intent 的调用是用来进行架构屏幕之间切换的。Intent是描述应用想要做什么，Intent 数据结构中两个最重要的部分是动作和动作的数据。
    //service 是运行在后台的代码，不能与用户交互，可以运行在自己的进程，也可以运行在其他应用程序进程的上下文里。需要通过某一个activity或者其他cntext 对象来调用。

    private IBinder binder = new MyBinder();

    public class MyBinder extends Binder {

        public MsgService getService() {

            return MsgService.this;
        }

    }

    //被启动的服务startService的生命周期：

    /**
     * 1，如果一个Service 被activity调用Context.startService（）方法启动，那么不管是否有activity使用bindService（）绑定或者unbindService（）解除绑定到该service，该Service，
     * 都在后台运行，如果一个service被多次执行startService（），那么它的onCreate（）只会执行一次。也就是说只会创建一个实例。但是它的onStartCommand（）将会被执行多次，对应startService（）的
     * 次数，该Service 会一直在后台运行，直到被调用stopService（）或自身的stopSelf（）方法。如果当时系统资源不够，也会结束该服务。
     *
     * 2，被绑定的服务周期：
     * 如果一个service 被bindService（）方法启动，不管调用bindService（）几次，onCreate（）会执行一次，onStartCommand不会执行。会调用onBind（）方法。当连接建立以后，Service 会一直
     * 执行，除非调用Context.unbindService断开连接或者之前调用bindService（）的context不存在了，系统就会自定停止。
     *
     * 3，如果一个service 又被启动又被绑定，则该service 将会一直在后台运行，调用unbindService（）将不会停止Service。而必须调用stopService（）或service的stopSelf（）方法来停止服务。
     * */


}
