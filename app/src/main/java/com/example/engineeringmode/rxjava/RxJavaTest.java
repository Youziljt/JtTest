package com.example.engineeringmode.rxjava;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @author ljt
 * Date: 3/26/21
 * Time: 2:29 PM
 * Description:
 */
public class RxJavaTest {




    public static void main(String[] args) {
//        new Observer() {
//            @Override
//            public void update(Observable observable, Object o) {
//
//            }
//        };

//        new androidx.lifecycle.Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//            }
//        };

        rx.Observer<String> observer = new rx.Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        /**
         * onStart(): 这是 Subscriber 增加的方法。它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作，
         * 例如数据的清零或重置。这是一个可选方法，默认情况下它的实现为空。需要注意的是，如果对准备工作的线程有要求（例如弹出一个显
         * 示进度的对话框，这必须在主线程执行）， onStart() 就不适用了，因为它总是在 subscribe 所发生的线程被调用，而不能指定线
         * 程。要在指定的线程来做准备工作，可以使用 doOnSubscribe() 方法，具体可以在后面的文中看到。
         *
         * unsubscribe(): 这是 Subscriber 所实现的另一个接口 Subscription 的方法，用于取消订阅。在这个方法被调用后，Subscriber
         * 将不再接收事件。一般在这个方法调用前，可以使用 isUnsubscribed() 先判断一下状态。 unsubscribe() 这个方法很重要，因为在
         * subscribe() 之后， Observable 会持有 Subscriber 的引用，这个引用如果不能及时被释放，将有内存泄露的风险。所以最好保持一
         * 个原则：要在不再使用的时候尽快在合适的地方（例如 onPause() onStop() 等方法中）调用 unsubscribe() 来解除引用关系，以避免内存泄露的发生。
         */
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String o) {

            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
//                Log.d(tag, "completed");
            }
        };
//        Observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
//https://gank.io/post/560e15be2dca930e00da1083#toc_17
    }
}
