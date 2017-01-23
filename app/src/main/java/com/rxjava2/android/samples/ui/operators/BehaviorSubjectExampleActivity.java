package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class BehaviorSubjectExampleActivity extends ExampleBaseActivity {

    /* When an observer subscribes to a BehaviorSubject, it begins by emitting the item most
     * recently emitted by the source Observable (or a seed/default value if none has yet been
     * emitted) and then continues to emit any other items emitted later by the source Observable(s).
     */
    protected void doSomeWork() {

        BehaviorSubject<Integer> source = BehaviorSubject.createDefault(-1);

//        source.onNext(0); //如果启用这句话，firstObserver将获得 0 (最近发射的最后一个数据)，1，2，3，4

        source.subscribe(getObserver("First")); // it will get -1, 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 3(last emitted), 4 and onComplete for second observer also.
         */
        source.subscribe(getObserver("Second"));

        source.onNext(4);
        source.onComplete();

        source.subscribe(getObserver("Third"));

    }


}