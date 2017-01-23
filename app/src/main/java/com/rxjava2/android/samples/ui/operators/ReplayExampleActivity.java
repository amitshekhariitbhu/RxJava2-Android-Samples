package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class ReplayExampleActivity extends ExampleBaseActivity {

    /* Using replay operator, replay ensure that all observers see the same sequence
     * of emitted items, even if they subscribe after the Observable has begun emitting items
     */
    protected void doSomeWork() {

        PublishSubject<Integer> source = PublishSubject.create();
        ConnectableObservable<Integer> connectableObservable = source.replay(3); // bufferSize = 3 to retain 3 values to replay
        connectableObservable.connect(); // connecting the connectableObservable

        source.onNext(-1);
        source.onNext(0);
        connectableObservable.subscribe(this.<Integer>getObserver("First"));

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();


        /*
         * Replay操作符会给onComplete后订阅Observable的订阅者Replay发射最后几个元素。
         * 在onComplete之前订阅的不受影响（会收到完整的元素。哪怕在订阅之前已经开始onNext数据了）
         *
         * it will emit 2, 3, 4 as (count = 3), retains the 3 values for replay
         */
        connectableObservable.subscribe(this.<Integer>getObserver("Second"));

    }


}