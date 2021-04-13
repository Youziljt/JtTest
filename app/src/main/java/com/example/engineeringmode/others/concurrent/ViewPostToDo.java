package com.example.engineeringmode.others.concurrent;

/**
 * @anthor ljt
 * Date: 2/2/21
 * Time: 4:33 PM
 * Description:  view.post 到底做了什么
 *
 *      https://www.cnblogs.com/dasusu/p/8047172.html
 */
public class ViewPostToDo {

    /**
     * view.post 可以更新ui，即使在子线程中调用 view.post();
     * view.post 中操作执行时，view 的宽高已经计算完毕，所以经常看见在activity的onCreate（）里面调用来获取view高度为0
     * 会不会导致内存泄漏。
     */


    /**
     * View.post(Runnable) 内部会自动分两种情况处理，当 View 还没 attachedToWindow 时，会先将这些 Runnable 操作缓存下来；否则就直接通过 mAttachInfo.mHandler 将这些 Runnable 操作 post 到主线程的 MessageQueue 中等待执行。
     *
     * 如果 View.post(Runnable) 的 Runnable 操作被缓存下来了，那么这些操作将会在 dispatchAttachedToWindow() 被回调时，通过 mAttachInfo.mHandler.post() 发送到主线程的 MessageQueue 中等待执行。
     *
     * mAttachInfo 是 ViewRootImpl 的成员变量，在构造函数中初始化，Activity View 树里所有的子 View 中的 mAttachInfo 都是 ViewRootImpl.mAttachInfo 的引用。
     *
     * mAttachInfo.mHandler 也是 ViewRootImpl 中的成员变量，在声明时就初始化了，所以这个 mHandler 绑定的是主线程的 Looper，所以 View.post() 的操作都会发送到主线程中执行，那么也就支持 UI 操作了。
     *
     * dispatchAttachedToWindow() 被调用的时机是在 ViewRootImol 的 performTraversals() 中，该方法会进行 View 树的测量、布局、绘制三大流程的操作。
     *
     * Handler 消息机制通常情况下是一个 Message 执行完后才去取下一个 Message 来执行（异步 Message 还没接触），所以 View.post(Runnable) 中的 Runnable 操作肯定会在 performMeaure() 之后才执行，所以此时可以获取到 View 的宽高。
     *
     */

}
