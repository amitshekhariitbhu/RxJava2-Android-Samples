package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.subjects.ReplaySubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class ReplaySubjectExampleActivity extends ExampleBaseActivity {

    /* ReplaySubject emits to any observer all of the items that were emitted
     * by the source Observable, regardless of when the observer subscribes.
     */
    protected void doSomeWork() {

        //ReplaySubject 和 PublishSubject相反，
        //ReplaySubject不管订阅者什么时候订阅都能获取到完整的发射数据。
        //而PublishSubject会一直按照自己的步调发射数据，你在哪订阅就从这个时间点开始才能获取到事件
        //所谓的完整数据指的是从第一个onNext 一直到 onComplete 或 onError
        ReplaySubject<Integer> source = ReplaySubject.create();


        source.onNext(-1);
        source.onNext(0);

        source.subscribe(this.<Integer>getObserver("First")); // it will get -1, 0, 1, 2, 3, 4

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
//        source.onComplete();
//OnComplete后发射数据就无效了。如果仍要发射数据，需要重新创建ReplaySubject 并重新订阅
//        source.onNext(5);

        /*
         * it will emit -1, 0, 1, 2, 3, 4 for second observer also as we have used replay
         */
        source.subscribe(this.<Integer>getObserver("Second"));

        source.onNext(6);
        source.onComplete();

    }


}