package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.ExampleBaseActivity;

import io.reactivex.subjects.AsyncSubject;

/**
 * Created by amitshekhar on 17/12/16.
 */

public class AsyncSubjectExampleActivity extends ExampleBaseActivity {

    /* An AsyncSubject emits the last value (and only the last value) emitted by the source
     * Observable, and only after that source Observable completes. (If the source Observable
     * does not emit any values, the AsyncSubject also completes without emitting any values.)
     */
    protected void doSomeWork() {
        AsyncSubject<Integer> source = AsyncSubject.create();

        source.onNext(0);
        source.subscribe(getObserver("First")); // it will emit only 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 4 and onComplete for second observer also.
         */
        source.subscribe(getObserver("Second"));

        source.onNext(4);
        source.onComplete();

        source.subscribe(getObserver("Third"));
    }


}