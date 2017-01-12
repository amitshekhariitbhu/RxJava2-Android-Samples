package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class PublishSubjectExampleActivity extends AbsExampleActivity {

    /* PublishSubject emits to an observer only those items that are emitted
     * by the source Observable, subsequent to the time of the subscription.
     */
    protected void doSomeWork() {

        //PublishSubject就像鼠标事件，不管有没有订阅者，他都按照他的设定发事件，什么时候有订阅者，那么订阅者就从那个时候获取事件。
        //与之相反的是ReplaySubject,不管订阅者什么时候订阅，都能获取完整事件。
        PublishSubject<Integer> source = PublishSubject.create();
        source.onNext(-1);
        source.onNext(0);

        source.subscribe(this.<Integer>getObserver("First")); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
//        source.onComplete();//如果在这里onComplete了，那么后面的订阅者只能收到onComplete事件

        /*
         * it will emit 4 and onComplete for second observer also.
         */
        source.subscribe(this.<Integer>getObserver("Second"));

        source.onNext(4);
        source.onComplete();

    }

}